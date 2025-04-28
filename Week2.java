import java.util.Arrays;

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

//    public String longestCommonPrefix(String[] strs) {
//
//    }

    public static void main(String[] args) {
        Week2 w2=new Week2();
//        char ch[]={'h','e','l','l','o'};
//        w2.reverseString(ch);
        String[] in={"flower","flow","flight"};
        System.out.println(w2.longestCommonPrefix(in));



    }
}
