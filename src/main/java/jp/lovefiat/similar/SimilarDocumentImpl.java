package jp.lovefiat.similar;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class SimilarDocumentImpl implements SimilarDocument {

	/** インスタントクラスタ */
	private Collection<String> mInstantCluster;

	@Override
	public float compare(List<String> words1, List<String> words2) {
		
		Collection<String> wordList1 = words1;
		Collection<String> wordList2 = words2;
		
		mInstantCluster = getSharedCluster();
		// 学習結果が存在しない場合はインスタント学習
		if (mInstantCluster == null) {
			mInstantCluster = new HashSet<>();
			train(mInstantCluster, wordList1);
			train(mInstantCluster, wordList2);
		}
		// 入力をベクトル化
		Map<String, Integer> vec1 = vectorize(wordList1);
		Map<String, Integer> vec2 = vectorize(wordList2);
		// コサイン類似度を算出
		return calculate(vec1, vec2);
	}
	/**
	 * 共有クラスタを返す
	 * @return 共有クラスタ
	 */
	abstract protected Collection<String> getSharedCluster();
	/**
	 * 共有クラスタを作成する
	 * 
	 * @return 作成した共有クラスタ
	 */
	abstract protected Collection<String> newSharedCluster();
	/**
	 * 計算する
	 * 
	 * @param vec1 ベクトル化された比較項目１
	 * @param vec2 ベクトル化された比較項目２
	 * @return スコア
	 */
	abstract protected float calculate(Map<String, Integer> vec1, Map<String, Integer> vec2);
	
	/**
	 * 学習する
	 * @param words ワードリスト
	 */
	public void train(Collection<String> words) {
		
		Collection<String> cluster = getSharedCluster();
		if (cluster == null) {
			cluster = newSharedCluster();
		}
		train(cluster, words);
	}
	/**
	 * 学習する
	 * 
	 * @param cls 集団
	 * @param words ワードリスト
	 */
	protected void train(Collection<String> cls, Collection<String> words) {
		cls.addAll(words);
	}
	/**
	 * 母集団よりベクトル枠を作成
	 * 
	 * @return ベクター
	 */
	protected Map<String, Integer> getVector() {
		Map<String, Integer> vec = new HashMap<>();
		for (String word : mInstantCluster) {
			vec.put(word, 0);
		}
		
		return vec;
	}
	/**
	 * ベクトル化
	 * 
	 * @param words ワードリスト
	 * @return ベクター
	 */
	protected Map<String, Integer> vectorize(Collection<String> words) {
		
		Map<String, Integer> vec = getVector();
		for (String word : words) {
			appendVector(vec, word);
		}
		return vec;
	}
	/**
	 * ベクトル更新
	 * 
	 * @param vec ベクター
	 * @param word ワード
	 */
	protected void appendVector(Map<String, Integer> vec, String word) {
		Integer i;
		
		i = vec.get(word);
		++i;
		vec.put(word, i);
		
	}

}
