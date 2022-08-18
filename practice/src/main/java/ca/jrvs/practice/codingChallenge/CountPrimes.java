package ca.jrvs.practice.codingChallenge;

public class CountPrimes {

    /**
     * Big-O Analysis: O(n^2) time worst-case, O(n) space worst-case
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean primes[] = new boolean[n];
        int cnt = 0;

        for (int i = 2; i < n; i++) {
            if (!primes[i]) {
                cnt++;
                for (int j = 2; i * j < n; j++) {
                    primes[i * j] = true;
                }
            }
        }
        return cnt;
    }

    /**
     * Big-O Analysis: O(nloglogn) time worst-case, O(n) space worst-case
     * Taken from: https://leetcode.com/problems/count-primes/discuss/57588/My-simple-Java-solution
     *
     * @param n
     * @return
     */
    public int countPrimesFast(int n) {
        if(n <= 2) return 0;
        int ans = 1;// don't forget to record 2. :-)
        boolean[] isCompositeArr = new boolean[n];
        int upper = (int) Math.sqrt(n);
        for(int i = 3;i < n;i=i+2){//1.scan only odd number
            if(isCompositeArr[i]) continue;
            ans++;
            if(i > upper) continue;//2. avoid i^2 overflow.
            for(int j = i*i; j < n;j = j + 2*i){//3. initialize j to i^2
                //4. increase 2i to keep j as an odd number
                isCompositeArr[j] = true;
            }
        }
        return ans;
    }
}
