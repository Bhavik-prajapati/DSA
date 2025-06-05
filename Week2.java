import java.awt.image.ImageProducer;
import java.sql.Statement;
import java.sql.Struct;
import java.util.*;

public class Week2 {

//    https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s) {

        int st=0;
        int end=s.length-1;

        while (st<=end){
            char temp=s[st];
            s[st]=s[end];
            s[end]=temp;
            st++;
            end--;
        }
    }

//    https://leetcode.com/problems/longest-common-prefix/submissions/1620691957/
    public String longestCommonPrefix(String[] strs) {

        Arrays.sort(strs);
        int idx=0;
        String st=strs[0];
        String end=strs[strs.length-1];
        while (idx<st.length()){

            if(st.charAt(idx)==end.charAt(idx)){
                idx++;
            }
            else{
                break;
            }

        }
        return idx==0?"":st.substring(0,idx);
    }

    public String longestCommonPrefixOptimal(String[] strs) {

        if(strs==null || strs.length==0){
            return "";
        }
        String prefix=strs[0];
        for (int i = 1; i < strs.length; i++) {

            int j=0;
            while (j<prefix.length()&& j<strs[i].length() && prefix.charAt(j)==strs[i].charAt(j)){
                j++;
            }
            prefix=prefix.substring(0,j);
            if(prefix.isEmpty()){
                return "";
            }
            return prefix;

        }

        return "";

    }

//    https://leetcode.com/problems/valid-palindrome/description/


    public boolean isPalindrome(String s) {

        String s1=removeextrachars(s);

        int st=0;
        int end=s1.length()-1;
        while (st<=end){

            if(s1.charAt(st)!=s1.charAt(end)){
                return false;
            }
            st++;
            end--;

        }

        return true;


    }

    private String removeextrachars(String s) {

        s = s.replaceAll("[^a-z0-9]", "").toLowerCase();
//        System.out.println(s);
        return s;

    }


//    https://leetcode.com/problems/first-unique-character-in-a-string/description/


    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        char ans='A';

        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
            if(entry.getValue()==1){
                ans=entry.getKey();
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {

            if(s.charAt(i)==ans){
                return i;
            }

        }
        return -1;
    }

    public int firstUniqCharOptimal(String s) {
        int[] frequency = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i)-'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if(frequency[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }

//    https://leetcode.com/problems/valid-anagram/

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] p=s.toCharArray();
        char[] q=s.toCharArray();
        Arrays.sort(p);
        Arrays.sort(q);

        return Arrays.equals(p, q);
    }
    public boolean isAnagramOptimized(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        int[] count=new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for (int c:count) {
            if(c!=0){
                return false;
            }
        }
        return true;
    }

//    https://leetcode.com/problems/group-anagrams/

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String ,List<String >> map=new HashMap<>();

        for (String word:strs){
            char ch[]=word.toCharArray();
            System.out.println(ch);

            Arrays.sort(ch);
            String sortedword=new String(ch);

            System.out.println(sortedword);

            if(!map.containsKey(sortedword)){
                map.put(sortedword,new ArrayList<>());
            }
            map.get(sortedword).add(word);
        }
        return new ArrayList<>(map.values());
    }


//    https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/

    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()){
            return 0;
        }
        if(needle.length()>haystack.length()){
            return -1;
        }

        int idx=0;
        for (int i = 0; i < haystack.length(); i++) {
            if(haystack.charAt(i)==needle.charAt(0)){
                idx=i;
                break;
            }
        }

        if(haystack.substring(idx,idx+needle.length()).equals(needle)){
            return idx;
        }
        return -1;

    }

//    https://leetcode.com/problems/valid-palindrome-ii/


    public boolean isPalindromestr(String str,int st,int end){
        while (st<=end){
            if(str.charAt(st)!=str.charAt(end)){
                return false;
            }
            st++;
            end--;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        int left=0;
        int right=s.length()-1;
        while (left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return isPalindromestr(s,left+1,right) || isPalindromestr(s,left,right-1);
            }
            left++;
            right--;
        }
        return true;
    }

//    https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remaining = target - nums[i];
            if (hm.containsKey(remaining)) {
                return new int[] { hm.get(remaining), i };
            }
            hm.put(nums[i], i);
        }
        return new int[] {};
    }

//    https://leetcode.com/problems/contains-duplicate-ii/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int current=nums[i];
            if(hm.containsKey(current)){
                int previdx= hm.get(current);
                if(i-previdx<=k){
                    return true;
                }

            }
            hm.put(current,i);

        }
        return false;
    }

//    https://leetcode.com/problems/sort-characters-by-frequency/description/

    public String frequencySort(String s) {

        HashMap<Character,Integer> hm=new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch=s.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(hm.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue()); // descending order


        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : list) {
            char ch=entry.getKey();
            int freq=entry.getValue();
            for (int i = 0; i < freq; i++) {
                sb.append(ch);
            }

        }
        return sb.toString();
    }

//    https://leetcode.com/problems/valid-parentheses/description/
    public boolean isValid(String s) {
        Stack<Character> st=new Stack<>();
        for (int i = 0; i <s.length() ; i++) {

            char ch=s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            }else{
            if(st.isEmpty()){
                return false;
            }

                char top=st.pop();
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }


        }
        return st.isEmpty();
    }

//    https://leetcode.com/problems/minimum-window-substring/description/

    private boolean containsAll(String sub, String t) {
        int[] sCount = new int[128];
        int[] tCount = new int[128];
        for (char c : t.toCharArray()) tCount[c]++;
        for (char c : sub.toCharArray()) sCount[c]++;
        for (int i = 0; i < 128; i++) {
            if (tCount[i] > sCount[i]) return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {


        int minlen=Integer.MAX_VALUE;
        String  ans="";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <=s.length() ; j++) {
                String sub=s.substring(i,j);
                if(containsAll(sub,t))
                {
                    if(sub.length()<minlen){
                        minlen=sub.length();
                        ans=sub;
                    }
                }
                
            }
            
        }
        return ans;

    }

    public String minWindowOPT(String s, String t) {
        if (s.length() < t.length()) return "";

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, start = 0, minLen = Integer.MAX_VALUE;
        int required = tMap.size();
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (tMap.containsKey(c) && window.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (tMap.containsKey(leftChar) && window.get(leftChar).intValue() < tMap.get(leftChar).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

//    https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> window = new HashSet<>();
        int maxlen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            while (window.contains(ch)) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(ch);

            maxlen=Math.max(maxlen,right-left+1);
        }

        return maxlen;
    }

//    https://leetcode.com/problems/kth-largest-element-in-an-array/

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length-k];
    }

    public static void main(String[] args) {
        Week2 w2=new Week2();
//        String s="abcabcbb";

        System.out.println(w2.lengthOfLongestSubstring("ADOBECODEBANC"));
//        int[] arr={3,2,3,1,2,4,5,5,6};
//        System.out.println(w2.findKthLargest(arr,4));


//        String t="ABC";
//        System.out.println(w2.lengthOfLongestSubstring(s));

//        System.out.println(w2.containsNearbyDuplicate(arr,3));


//        System.out.println(w2.validPalindrome("abca"));


//        System.out.println(w2.strStr("sadbutsad","sad"));

//        String[] sts= {"eat", "tea", "tan", "ate", "nat", "bat"};
//
//        List<List<String>> l1 =w2.groupAnagrams(sts);


//        char ch[]={'h','e','l','l','o'};
//        w2.reverseString(ch);
//        String[] in={"flower","flow","flight"};
//        System.out.println(w2.longestCommonPrefix(in));

//        w2.isPalindrome("race a car");
//        System.out.println(w2.firstUniqCharOptimal("loveleetcode"));

//        System.out.println(w2.isAnagramOptimized("anagram","nagaram"));

    }
}






