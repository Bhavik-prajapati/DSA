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

    public static void main(String[] args) {
        Week2 w2=new Week2();
//        char ch[]={'h','e','l','l','o'};
//        w2.reverseString(ch);
//        String[] in={"flower","flow","flight"};
//        System.out.println(w2.longestCommonPrefix(in));

//        w2.isPalindrome("race a car");
        w2.firstUniqChar("loveleetcode");




    }
}
