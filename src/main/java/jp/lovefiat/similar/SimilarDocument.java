package jp.lovefiat.similar;

import java.util.List;

/**
 * ドキュメントの類似度計算器
 *
 */
public interface SimilarDocument {
	
	float compare(List<String> words1, List<String> words2);
	
}
