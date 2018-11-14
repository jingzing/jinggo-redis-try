package wang.jinggo.tutorial.es.cnword.bigramSeg;

/**
 * 词的基本信息
 * @author luogang
 *
 */
public class WordEntry {
	public String word; /* 词*/
	public WordTypes posInf; /* 词的各种词性及相关频率 */
	public BigramMap biEntry;

	public WordEntry(String w) {
		word = w;
		biEntry = new BigramMap(0, 0);
		posInf = new WordTypes(0);
		posInf.total = 1;
	}

	public WordEntry(String w, WordTypes types, BigramMap b) {
		word = w;
		posInf = types;
		biEntry = b;
	}

	public static WordEntry getStartWord() {//增加开始状态
		WordTypes types = new WordTypes(1);
		types.insert(0, PartOfSpeech.start, 1000);
		WordEntry start = new WordEntry("start", types, null);
		return start;
	}

	public String toString() {
		return word + ":pos" + posInf.toString()+" BigramMap:"+biEntry;
	}
}