package jp.lovefiat.similar;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Dice係数による類似度比較器
 * 
 */
public class DiceSimilar implements SimilarDocument {

	@Override
	public float compare(List<String> str1, List<String> str2) {
		Collection<String> set1 = new HashSet<>(str1);
		Collection<String> set2 = new HashSet<>(str2);
		Collection<String> meets = new HashSet<>();
		
		for (String word : set1) {
			if (set2.contains(word)) {
				meets.add(word);
			}
		}
		
		return (2 * (float)meets.size() / ((float)set1.size() + (float)set2.size()) );
	}

}
