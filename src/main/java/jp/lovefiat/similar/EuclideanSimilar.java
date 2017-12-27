package jp.lovefiat.similar;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * ユークリッド距離
 *
 */
public class EuclideanSimilar extends SimilarDocumentImpl {
	
	/** 共有クラスタ */
	private static Collection<String> sCluster;

	@Override
	protected Collection<String> getSharedCluster() {
		return sCluster;
	}

	@Override
	protected Collection<String> newSharedCluster() {
		sCluster = new HashSet<>();
		return sCluster;
	}

	@Override
	protected float calculate(Map<String, Integer> vec1, Map<String, Integer> vec2) {
		
		// 平方根の和
		double sum = 0;
		float n;
		int n1;
		int n2;
		boolean hasCommon = false;
		for (String key : vec1.keySet()) {
			if (!vec1.containsKey(key)
					|| !vec2.containsKey(key)) {
				// 共通しない項目は無視
				continue;
			}
			hasCommon = true;
			n1 = vec1.get(key);
			n2 = vec2.get(key);
			n = n1 - n2;
			sum += n * n;
		}
		if (!hasCommon) {
			// 類似点なし
			return 0;
		}
		if (sum == 0) {
			// 完全一致
			return 1;
		}
		return (float)(1f / (1f + Math.sqrt(sum)));
	}
}
