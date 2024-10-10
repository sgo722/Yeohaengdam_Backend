package com.ssafy.yeohaengdam.algorithm;

import org.springframework.stereotype.Service;

//@Service
public class StringCheckByRabinKarp implements StringCheckService {

    public final static int d = 10;

    public boolean isContain(String text, String pattern) {
        int q = 103;
        int m = pattern.length();
        int n = text.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;
// Calculate hash value for pattern and text
        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

// Find the match
        for (i = 0; i <= n - m; i++) {
            if (p == t) {
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == m)
                    return true;
            }

            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }

        return false;
    }

//    // 라빈카프 디버그 코드
//    public static void main(String[] args) {
//        CheckSearchCondition checkSearchCondition = new StringChekyByRabinKarp();
//        String text = "asd";
//        String pattern = "asd";
//        boolean flag = checkSearchCondition.isContain(text, pattern);
//        System.out.println(flag);
//    }
}
