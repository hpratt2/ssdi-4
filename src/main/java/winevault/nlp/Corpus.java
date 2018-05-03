package winevault.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeMap;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EmbeddedStemmingPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

import winevault.dao.ReviewDAO;
import winevault.dao.WineDAO;
import winevault.model.IReview;
import winevault.model.IWine;
import winevault.util.ConnectionDataTestLarge;

public class Corpus {
	private static Corpus instance = null; // Singleton
	
	// Stored corpus location
	private static final URL DATA = Corpus.class.getResource("data/corpus.txt");
	private static final String PATH = "src/main/java/winevault/nlp/data/corpus.txt";
	
	// Corpus settings
	private static final int MIN_TERM_LEN = 4;
	private static final int MIN_TERM_DOCS = 3;
	
	// Instance fields
	private TreeMap<String,Integer> corpus = null;	// Map of terms -> # documents
	private static int numDocuments = 0;			// Number of documents in corpus
	
	/** Private constructor - maintains only one instance created by the class. */
	private Corpus() {
		File storedCorpus = null;
		try {
			storedCorpus = new File(DATA.getFile());
		} catch(Exception e) {
			storedCorpus = new File(PATH);
		}
		
		if(storedCorpus.exists() && !storedCorpus.isDirectory())
			loadStoredCorpus();
		else
			buildStoredCorpus();
	}
	
	/** Loads the corpus from file. */
	private void loadStoredCorpus() {
		try {
			corpus = new TreeMap<String,Integer>();
			Scanner fs = new Scanner(new FileInputStream(DATA.getFile()),"UTF-8");
			if(!fs.hasNextLine()) {
				System.out.println("ERROR READING CORPUS FILE");
				corpus = null;
				fs.close();
				return;
			}
			numDocuments = Integer.parseInt(fs.nextLine());
			while(fs.hasNextLine()) {
				String[] line = fs.nextLine().split("\t");
				corpus.put(line[0], Integer.parseInt(line[1]));
			}
			fs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Builds a corpus from the existing wines in the database and their aggregated
	 * reviews; i.e. all reviews corresponding to a given wine constitute a document.
	 * The corpus is saved to be loaded in future instantiations. */
	private void buildStoredCorpus() {
		try {
			System.out.println("Building Corpus");
			corpus = new TreeMap<String,Integer>();
			numDocuments = 0;
			// Required DAOs
			WineDAO winedao = new WineDAO(new ConnectionDataTestLarge());
			ReviewDAO reviewdao = new ReviewDAO(new ConnectionDataTestLarge());
			// Tokenization and pre-processing utilities
			TokenizerFactory tf = new UimaTokenizerFactory();
			EmbeddedStemmingPreprocessor pp = new EmbeddedStemmingPreprocessor(
					new EndingPreProcessor());
			// Output
			File out = new File(PATH);
			
			PrintWriter pw = new PrintWriter(out);
			
			for(IWine wine : winedao.getWineList()) {
				// Create document corpus
				List<String> terms = new ArrayList<String>();
				for(IReview review : reviewdao.getReviewsByWineID(wine.getID())) {
					Tokenizer tokenizer = tf.create(review.getContent().toLowerCase());
					while(tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
						token = pp.preProcess(token);
						if(!terms.contains(token)) terms.add(token);
					}
				}
				// Add document corpus to aggregated corpus
				for(String term : terms) {
					if(term.length() < 2) continue;
					if(corpus.containsKey(term))
						corpus.replace(term, corpus.get(term) + 1);
					else
						corpus.put(term, 1);
				}
				numDocuments++;
				System.out.print(".");
			}
			System.out.println();
			
			// Save the corpus
			pw.println(numDocuments);
			String[] corpusTerms = corpus.keySet().toArray(new String[corpus.size()]);
			Arrays.sort(corpusTerms);
			for(String term : corpusTerms) {
				int documentsContainingTerm = corpus.get(term);
				if(term.length() < MIN_TERM_LEN || documentsContainingTerm < MIN_TERM_DOCS) continue;
				pw.println(term + "\t" + documentsContainingTerm);
			}
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Returns whether this corpus contains the given term.
	 * @param term the term to check for
	 * @return true if the term is contained in the corpus, false otherwise */
	public boolean contains(String term) { return corpus.containsKey(term); }
	
	/** Returns the number of documents in the corpus containing the given term.
	 * @param term the term to check for
	 * @return the number of documents in the corpus containing the given term
	 */
	public int documentsContaining(String term) {
		if(corpus.containsKey(term)) return corpus.get(term);
		return 0;
	}
	
	public NavigableSet<String> terms() { return corpus.navigableKeySet(); }
	
	/** Returns the number of terms contained in the corpus.
	 * @return the number of terms contained in the corpus */
	public int numTerms() { return corpus.size(); }
	
	
	/** Returns the number of documents contained in the corpus.
	 * @return the number of documents contained in the corpus */
	public int documents() { return numDocuments; }
	
	/** Returns the singleton Corpus of this class. If it has not been instantiated, the 
	 * instance is created and then returned.
	 * @return the singleton Corpus of this class */
	public static Corpus getInstance() {
		if(instance == null) instance = new Corpus();
		return instance;
	}
	
	/** Builds corpus. Should be used before running web application as relative paths
	 * are unreliable in this context. */
	public static void main(String[]args) {
		Corpus c = new Corpus();
		System.out.println("Corpus built with " + 
				c.documents() + " documents and " + c.numTerms() + " terms.");
	}
}
