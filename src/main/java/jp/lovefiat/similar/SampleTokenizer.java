package jp.lovefiat.similar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleTokenizer implements Samplizer {

	@Override
	public List<String> samplize(String source) {
		String[] tokens = source.split("\\s|、|。");
		return new ArrayList<>(Arrays.asList(tokens));
	}

}
