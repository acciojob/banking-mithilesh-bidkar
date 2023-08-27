package com.driver;
import java.util.*;

public class CurrentAccount extends BankAccount {
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if (balance < 5000) {
            throw new Exception("Insufficient Balance");
        }

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean isValid = isValid(tradeLicenseId);

        if (!isValid) {



        int maxChar = countMaxChar(tradeLicenseId);

        if (maxChar > (tradeLicenseId.length() + 1) / 2) {
            throw new Exception("Valid License can not be generated");
        } else {

            tradeLicenseId = getNewId(tradeLicenseId);

        }


    }
}

    public boolean isValid(String tradeLicenseId ){

        char[] arr = tradeLicenseId.toCharArray();

        for (int i=0;i<arr.length-1;i++){

            if(arr[i]==arr[i+1]){
                return false;
            }
        }

        return true;
    }

    public int countMaxChar(String tradeLicenseId){
        HashMap<Character,Integer> map = new HashMap<>();
        int max =0;

        for (int i=0;i<tradeLicenseId.length();i++){
            char curr = tradeLicenseId.charAt(i);

            map.put(curr,map.getOrDefault(curr,0)+1);
        }

        for (Character x : map.keySet()){
            Math.max(max,map.get(x));
        }

        return max;
    }

    public String getNewId(String s){
            int[] f = new int[26];
            int n = s.length();

            for (int i = 0; i < n; i++) {
                f[s.charAt(i) - 'a']++;
            }

            PriorityQueue<Pair> p = new PriorityQueue<>((a, b) -> b.freq - a.freq);
            for (int i = 0; i < 26; i++) {
                if (f[i] != 0) {
                    p.offer(new Pair(f[i], (char) (i + 'a')));
                }
            }

            StringBuilder ans = new StringBuilder();
            while (p.size() >= 2) {
                Pair p1 = p.poll();
                Pair p2 = p.poll();
                ans.append(p1.ch);
                ans.append(p2.ch);
                if (p1.freq > 1) {
                    p.offer(new Pair(p1.freq - 1, p1.ch));
                }
                if (p2.freq > 1) {
                    p.offer(new Pair(p2.freq - 1, p2.ch));
                }
            }

            if (!p.isEmpty()) {
                ans.append(p.poll().ch);
            }

            return ans.toString();
        }
 public class Pair {
            int freq;
            char ch;

            public Pair(int freq, char ch) {
                this.freq = freq;
                this.ch = ch;
            }
        }

}
