package jp.lovefiat.TheoryJSimilarity;

import jp.lovefiat.similar.COSSimilar;
import jp.lovefiat.similar.DiceSimilar;
import jp.lovefiat.similar.EuclideanSimilar;
import jp.lovefiat.similar.JaccardSimilar;
import jp.lovefiat.similar.NGramSimilar;
import jp.lovefiat.similar.SampleTokenizer;
import jp.lovefiat.similar.Samplizer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		test();
	}

	private static void test() {

		float score;

		Samplizer samplizer = new SampleTokenizer();

		// 盗用された文章のサンプルにてテスト
		String str1 = "「梅雨」北海道と小笠原諸島を除く日本において見られる特有の気象で、5月から7月半ばにかけて毎年めぐってくる、雨の多い期間のこと。梅雨に入ることを梅雨入り、梅雨が終わって夏になることを梅雨明けと言い、日本各地の地方気象台・気象庁が梅雨入り・梅雨明けの発表をする。 ";
		String str2 = "梅雨（ばいう、つゆ）とは、北海道と小笠原諸島を除く日本や朝鮮半島南部、華南や華中の沿海部や台湾において見られる特有の気象で、5月から7月半ばにかけて毎年めぐってくる、雨の多い期間のこと。梅雨の時季が始まることを梅雨入り、梅雨が終わって夏になることを梅雨明けと言い、日本では、各地の地方気象台・気象庁が梅雨入り・梅雨明けの発表をする。";

		NGramSimilar ngram = new NGramSimilar(3);
		score = ngram.compare(str1, str2);
		println(String.format("# SCORE: %f （Tri-gramによる類似度）", score));

		ngram = new NGramSimilar(2);
		score = ngram.compare(str1, str2);
		println(String.format("# SCORE: %f （Bi-gramによる類似度）", score));

		str1 = "私 は ソフトウェア エンジニア です 。";
		str2 = "私 は データ アナリスト です 。";

		JaccardSimilar jaccard = new JaccardSimilar();
		score = jaccard.compare(samplizer.samplize(str1), samplizer.samplize(str2));
		println(String.format("# SCORE: %f （ジャッカード係数による類似度）", score));

		DiceSimilar dice = new DiceSimilar();
		score = dice.compare(samplizer.samplize(str1), samplizer.samplize(str2));
		println(String.format("# SCORE: %f （ダイス係数による類似度）", score));

		str1 = "リンゴ みかん みかん バナナ";
		str2 = "リンゴ バナナ バナナ キウイ";

		COSSimilar cos = new COSSimilar();
		score = cos.compare(samplizer.samplize(str1), samplizer.samplize(str2));
		println(String.format("# SCORE: %f （コサイン類似度）", score));

		EuclideanSimilar euclidean = new EuclideanSimilar();
		score = euclidean.compare(samplizer.samplize(str1), samplizer.samplize(str2));
		println(String.format("# SCORE: %f （ユークリッド距離計算による類似度）", score));
	}

	private static void println(String log) {
		System.out.println(log);
	}

}
