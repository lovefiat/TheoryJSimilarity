package jp.lovefiat.similar;

import java.util.Collection;
import java.util.HashSet;

/**
 * N-Gram による文字列の（文字レベル）類似度 比較器
 *
 */
public class NGramSimilar implements SimilarString {
	
	private int mN = 3;
	private Collection<String> mModel;
	private float mSimilarity;
	private boolean mRemoveDelimiter = true;
	private boolean mCharacterizebothends = true;
	
	/**
	 * コンストラクタ（Trigram）
	 */
	public NGramSimilar() {
		
	}
	/**
	 * コンストラクタ(N-Gram)
	 * @param n N
	 */
	public NGramSimilar(int n) {
		mN = n;
	}

	@Override
	public float compare(String str1, String str2) {
		mSimilarity = 0f;
		String strP1 = normalize(str1);
		String strP2 = normalize(str2);
		
		Collection<String> map = makeModel(strP1);
		Collection<String> map2 = makeModel(strP2);
		
		// 規模が大きい方をモデルとする
		if (map.size() > map2.size()) {
			mModel = map;
			map = map2;
		} else {
			mModel = map2;
		}
		float incidence = 0;
		for (String item : map) {
			if (mModel.contains(item)) {
				++incidence;
			}
		}
		mSimilarity = (incidence / (float)mModel.size());
		return mSimilarity;
	}
	
	public void setRemoveDelimiter(boolean removeDelimiter) {
		this.mRemoveDelimiter = removeDelimiter;
	}

	public boolean isRemoveDelimiter() {
		return mRemoveDelimiter;
	}
	public void setCharacterizeBothEnds(boolean enable) {
		mCharacterizebothends = enable;
	}
	public boolean isCharacterizeBothEnds() {
		return mCharacterizebothends;
	}
	/**
	 * 類似度を取得
	 * 
	 * @return 類似度(100分率)
	 */
	public float getSimilarity() {
		return mSimilarity;
	}
	/**
	 * 任意の文字列より不要な文字を取り除く
	 * 
	 * @param str 任意の文字列
	 * @return 除去済の文字列
	 */
	private String normalize(String str) {
		if (str == null) {
			return null;
		}
		String res = str;
		if (isRemoveDelimiter()) {
			res = str.replaceAll("\\r\\n\\t\\s　、。", "");
		}
		if (isCharacterizeBothEnds()) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<mN-1; i++) {
				sb.append("^");
			}
			sb.append(str);
			for (int i=0; i<mN-1; i++) {
				sb.append("$");
			}
			res = sb.toString();
		}
		return res;
	}
	/**
	 * 文字列をN-Gramモデル化
	 * 
	 * @param str 文字列
	 * @return
	 */
	private Collection<String> makeModel(String str) {
		Collection<String> model = new HashSet<>();
		for (int i=0; i<str.length()-mN; i++) {
			model.add(str.substring(i, i+mN));
		}
		return model;
	}

}
