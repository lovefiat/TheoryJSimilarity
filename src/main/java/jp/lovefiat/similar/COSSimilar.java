package jp.lovefiat.similar;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Cos類似度
 *
 */
public class COSSimilar extends SimilarDocumentImpl {
	
	/** 共有クラスタ */
	private static Collection<String> sCluster;

	/**
	 * 計算する
	 * 
	 * @param vec1 ベクトル化された比較項目１
	 * @param vec2 ベクトル化された比較項目２
	 * @return
	 */
	@Override
	protected float calculate(Map<String, Integer> vec1, Map<String, Integer> vec2) {
		// 分子
		double numerator = 0;
		Integer i;
		for (String word : vec1.keySet()) {
			i = vec1.get(word);
			i = i * vec2.get(word);
			numerator += i;
		}
		// 分母A
		double denominatorA = 0;
		for (String word : vec1.keySet()) {
			i = vec1.get(word);
			i = i * i;
			denominatorA += i;
		}
		denominatorA = Math.sqrt(denominatorA);
		// 分母B
		double denominatorB = 0;
		for (String word : vec2.keySet()) {
			i = vec1.get(word);
			i = i * i;
			denominatorB += i;
		}
		denominatorB = Math.sqrt(denominatorB);
		
		// コサイン類似度を算出
		return (float)(numerator / (denominatorA * denominatorB));
	}

	@Override
	protected Collection<String> getSharedCluster() {
		return sCluster;
	}

	@Override
	protected Collection<String> newSharedCluster() {
		sCluster = new HashSet<>();
		return sCluster;
	}

}
