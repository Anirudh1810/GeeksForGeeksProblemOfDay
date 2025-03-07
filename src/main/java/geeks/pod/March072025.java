package geeks.pod;

public class March072025 {

    public static int longestPalinSubseq(String s) {
        int n = s.length();
        // Create a 2D dp array where dp[i][j] represents the length of the
        // longest palindromic subsequence in the substring s[i...j]
        int[][] dp = new int[n][n];

        //Every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // Fill the dp table
        // We need to process the table diagonally from bottom-left to top-right
        // This is because to compute dp[i][j], we need dp[i+1][j-1], dp[i+1][j], and dp[i][j-1]
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // If the characters at both ends are the same, add 2 to the length
                // of the palindromic subsequence in the substring s[i+1...j-1]
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                // If the characters at both ends are different, take the maximum of
                // the palindromic subsequence in s[i+1...j] and s[i...j-1]
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        // dp[0][n-1] contains the length of the longest palindromic subsequence in the entire string s[0...n-1]
        return dp[0][n-1];
    }

    public static int longestPalinSubseq1(String s) {

        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();

        int[][] dp = new int[n+1][n+1];
        for( int i = 1; i <= n; i++ )
            for( int j = 1; j <= n; j++ )
                if( s.charAt(i-1) == r.charAt(j-1) )
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max( dp[i-1][j], dp[i][j-1] );

        return dp[n][n];
    }
}

/*

-------------Longest Palindromic Subsequence---------------------
Given a string s, return the length of the longest palindromic subsequence.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without
 changing the order of the remaining elements.

A palindromic sequence is a sequence that reads the same forward and backward.

Examples:

Input: s = "bbabcbcab"
Output: 7
Explanation: Subsequence "babcbab" is the longest subsequence which is also a palindrome.

Input: s = "abcd"
Output: 1
Explanation: "a", "b", "c" and "d" are palindromic and all have a length 1.

Input: s = "agbdba"
Output: 5
Explanation: The longest palindromic subsequence is "abdba", which has a length of 5. The characters in this
subsequence are taken from the original string "agbdba", and they maintain the order of the string while
forming a palindrome.

Constraints:
1 ≤ s.size() ≤ 1000
The string contains only lowercase letters.

*/