package winevault.nlp;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

import winevault.dao.ReviewDAO;
import winevault.dao.WineDAO;
import winevault.model.IReview;
import winevault.model.IWine;
import winevault.util.ConnectionDataTestLarge;

public class Corpus {
	private static final URL DATA_URL = Corpus.class.getResource("data/corpus.txt");
	private static final String DATA_FILE = "src/main/java/winevault/nlp/data/corpus.txt";
	private static TreeMap<String,Integer> corpus = null;
	private static int numDocuments = 0;
	
	private static void build() {
		try {
			// Init resources
			corpus = new TreeMap<String,Integer>();
			numDocuments = 0;
			WineDAO wdao = new WineDAO(new ConnectionDataTestLarge());
			ReviewDAO rdao = new ReviewDAO(new ConnectionDataTestLarge());
			TokenizerFactory tf = new UimaTokenizerFactory();
			PrintWriter pw = new PrintWriter(new File(DATA_FILE));
			List<IWine> wines = wdao.getWineList();
			// Build the corpus
			for(IWine wine : wines) {
				List<IReview> reviews = rdao.getReviewsByWineID(wine.getID());
				List<String> words = new ArrayList<String>();
				for(IReview review : reviews) {
					String content = review.getContent();
					Tokenizer tok = tf.create(content);
					while(tok.hasMoreTokens()) {
						String str = tok.nextToken();
						if(!words.contains(str))
							words.add(str);
					}
				}
				// Add document tokens to corpus & update number of documents
				// which contain the token
				for(String word : words) {
					if(corpus.containsKey(word))
						corpus.replace(word, corpus.get(word) + 1);
					else
						corpus.put(word, 1);
				}
				numDocuments++;
			}
			pw.println(numDocuments);
			// Write corpus to file
			String[] keys = corpus.keySet().toArray(new String[0]);
			Arrays.sort(keys);
			for(String k : keys) {
				if(k.length() < 2 || corpus.get(k) < 2) continue;
				pw.println(k + "\t" + corpus.get(k));
			}
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void load() {
		try {
			corpus = new TreeMap<String,Integer>();
			Scanner fs = new Scanner(new File(DATA_URL.getFile()));
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
	
	private static void checkInit() {
		if(corpus == null) {
			File corpusFile = new File(DATA_URL.getFile());
			if(corpusFile.exists() && !corpusFile.isDirectory())
				load();
			else
				build();
		}
	}
	
	public static TreeMap<String,Integer> get() {
		checkInit();
		return corpus;
	}
	
	public static boolean contains(String key) {
		checkInit();
		return corpus.containsKey(key);
	}
	
	public static int documentsContaining(String key) {
		checkInit();
		if(corpus.containsKey(key))
			return corpus.get(key);
		return 0;
	}
	
	public static int size() {
		checkInit();
		return corpus.size();
	}
	
	public static int N() {
		checkInit();
		return numDocuments;
	}
}
