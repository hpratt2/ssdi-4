package winevault.nlp;

import java.util.TreeMap;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EmbeddedStemmingPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

public class BagOfWords {
	private TreeMap<String,Integer> bag = null;		// bag of words (term frequencies)
	private double[] vector;						// TF-IDF weighted vector
	private int documentTerms = 0;					// number of terms in this document
	
	/** Initializes an empty bag of words represented by its term bag (term frequencies) 
     * and its TF-IDF weighted vector built from the corpus. */
	public BagOfWords() {
		bag = new TreeMap<String,Integer>();
		vector = new double[Corpus.getInstance().numTerms()];
	}
	
	/** Adds a string to this BagOfWords by parsing its terms, updating the frequency of 
	 * a terms occurrence, and updating the TF-IDF weights of this BagOfWords.	
	 * @param string the string to add to this BagOfWords */
	public void add(String string) {
		try {
			TokenizerFactory tf = new UimaTokenizerFactory();
			Tokenizer tok = tf.create(string.toLowerCase());
			EmbeddedStemmingPreprocessor pp = new EmbeddedStemmingPreprocessor(
					new EndingPreProcessor());
			Corpus c = Corpus.getInstance();
			while(tok.hasMoreTokens()) {
				String str = pp.preProcess(tok.nextToken());
				if(!c.contains(str)) continue;
				if(bag.containsKey(str))
					bag.replace(str, bag.get(str) + 1);
				else
					bag.put(str,  1);
				documentTerms++;
			}
			updateVector();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Returns the terms and their frequencies of this BagOfWords.
	 * @return the bag of words (terms and their frequencies) */
	public TreeMap<String,Integer> getBag(){ return this.bag; }
	
	/** Returns the TF-IDF weight vector representation of this BagOfWords.
	 * @return the TF-IDF weight vector representation of this BagOfWords */
	public double[] getVector(){ return this.vector; }
	
	/** Updates the TF-IDF weight vector representation of this BagOfWords whenever new 
	 * terms are added to the document or frequencies are updated. */
	private void updateVector() {
		if(bag == null) return;
		Corpus c = Corpus.getInstance();
		int i = 0;
		for(String key : c.terms()) vector[i++] = tf_idf(key);
	}
	
	/** Computes and returns the TF-IDF weight of a given term in this BagOfWords. The
	 * TF-IDF weight of a term is given by its term frequency (percentage of occurrence 
	 * in this document) times the inverse document frequency (the log of the number of  
	 * documents in the corpus / number of documents containing the given term).
	 * @param term the term to compute a TF-IDF weight for
	 * @return the TF-IDF weight of the given term */
	private double tf_idf(String term) {
		if(!bag.containsKey(term)) return 0;
		Corpus c = Corpus.getInstance();
		double tf = (double)bag.get(term) / documentTerms;
		double idf = Math.log(c.documents() / (double)c.documentsContaining(term));
		return tf * idf;
	}
	
	/** Computes and returns the cosine similarity of two BagOfWords objects by their 
	 * TF-IDF weight vectors. The cosine similarity of two vectors u and v is given by 
	 * their dot product divided by the product of their norms (magnitudes). A measure of 
	 * -1 indicates that the documents have nothing in common, whereas a measure of 1 
	 * indicates that the documents are identical.
	 * @param other the BagOfWords to compare to
	 * @return the cosine similarity of the two BagOfWords */
	public double similarityTo(BagOfWords other) {
		double[] u = this.vector;
		double[] v = other.getVector();
		
		double dot = 0.0, normU = 0.0, normV = 0.0;
		
		for(int i = 0; i < Math.min(u.length, v.length); i++) {
			dot += u[i] * v[i];
			normU += u[i] * u[i];
			normV += v[i] * v[i];
		}
		return dot / Math.sqrt(normU * normV);
	}
}
