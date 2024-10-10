package com.ssafy.yeohaengdam.algorithm;

public class StringCheckByBruteForce implements StringCheckService{

    public boolean isContain(String text, String pattern) {
        int[] prefixArray = mkPrefixArray(pattern);
        int j=0;
        for(int i=0; i<text.length(); i++) {
            while(j > 0 && text.charAt(i) != pattern.charAt(j)) j = prefixArray[j-1];
            if(text.charAt(i) == pattern.charAt(j)) j++;
            if(j == pattern.length()) {
                return true;
            }
        }

        return false;
    }


    private int[] mkPrefixArray(String pattern) {
        int[] prefixArray = new int[pattern.length()];
        int j = 0;
        for(int i=1; i<pattern.length(); i++) {
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) j = prefixArray[j-1];
            if(pattern.charAt(i) == pattern.charAt(j)) prefixArray[i] = ++j;
        }

        return prefixArray;
    }

    // CheckSearchCondtion 테스트코드
//	public static void main(String[] args) {
//		CheckSearchCondition kmp = new CheckSearchCondition();
//		String text = "asdfasdf";
//		String pattern = "asd";
//		if(kmp.isCotain(text, pattern)) {
//			System.out.println("포함합니다.");
//		}else {
//			System.out.println("포함하지 않습니다.");
//		}
//	}
}
