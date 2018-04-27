package winevault.nlp;

import java.util.TreeMap;

import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

public class BagOfWords {
	private TreeMap<String,Integer> bag = null;		// bag of words
	private TreeMap<String,Double> vector = null;	// TF-IDF weighted vector
	private int documentTerms = 0;
	
	public BagOfWords() {
		bag = new TreeMap<String,Integer>();
		vector = new TreeMap<String,Double>();
	}
	
	public void add(String string) {
		try {
			TokenizerFactory tf = new UimaTokenizerFactory();
			Tokenizer tok = tf.create(string);
			while(tok.hasMoreTokens()) {
				String str = tok.nextToken();
				if(!Corpus.contains(str)) continue;
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
	
	public TreeMap<String,Integer> getBOW(){ return this.bag; }
	
	public TreeMap<String,Double> getVector(){ return this.vector; }
	
	private void updateVector() {
		if(bag == null) return;
		for(String key : Corpus.get().keySet()) {
			vector.put(key, tf_idf(key));
		}
	}
	
	private double tf_idf(String key) {
		if(!bag.containsKey(key)) return 0;
		double tf = (double)bag.get(key) / documentTerms;
		double idf = Math.log(Corpus.N() / (double)Corpus.documentsContaining(key) + 1);
		return tf * idf;
	}
	
	public double similarityTo(BagOfWords b) {
		// Cosine similarity
		double div = vectorMagnitude(this.getVector()) * vectorMagnitude(b.getVector());
		if(div == 0) return 0;
		return dotProduct(this.getVector(), b.getVector()) / div;
	}
	
	private static double dotProduct(TreeMap<String,Double> u, TreeMap<String,Double> v) {
		double dp = 0;
		for(String key : Corpus.get().keySet()) {
			dp += u.get(key) * v.get(key);
		}
		return dp;
	}
	
	private static double vectorMagnitude(TreeMap<String,Double> u) {
		double mag = 0;
		for(double x : u.values()) {
			mag += x * x;
		}
		return Math.sqrt(mag);
	}
}
