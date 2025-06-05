import javax.lang.model.type.IntersectionType;
import java.sql.PreparedStatement;
import java.util.*;

public class KthLargest {

    private PriorityQueue<Integer> minheap;
    private  int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minheap = new PriorityQueue<>(k);

        for (int num : nums) {
            add(num);  // Use add method to maintain heap of size k
        }
    }

    public int add(int val) {
        if (minheap.size() < k) {
            minheap.offer(val);
        } else if (val > minheap.peek()) {
            minheap.poll();
            minheap.offer(val);
        }
        return minheap.peek();
    }

//    bruteforce
//    public static int[] topKFrequent(int[] nums, int k) {
//
//        HashMap<Integer,Integer> hm=new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
//        }
//        System.out.println(hm);
//        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(hm.entrySet());
//        entryList.sort((a, b) -> b.getValue() - a.getValue());
//
//        int[] result = new int[k];
//        for (int i = 0; i < k; i++) {
//            result[i] = entryList.get(i).getKey();
//        }
//        return result;
//
//    }

    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> hm=new HashMap<>();

        for(int num:nums){
            hm.put(num,hm.getOrDefault(num,0)+1);
        }
//        System.out.println(hm);
        PriorityQueue<Map.Entry<Integer,Integer>> minheap=new PriorityQueue<>(
                (a,b)->a.getValue()-b.getValue()
        ) ;

        for (Map.Entry<Integer,Integer> entry:hm.entrySet()){
            minheap.add(entry);
            if(minheap.size()>k){
                minheap.poll();
            }
        }

        int[] res=new int[k];
        int i=0;
        while (!minheap.isEmpty()){
            res[i++]=minheap.poll().getKey();
        }
        return  res;

    }

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxheap=new PriorityQueue<>(Collections.reverseOrder());
        for (int num:stones){
            maxheap.add(num);
        }
        while (maxheap.size() > 1){

                int x=maxheap.poll();
                int y=maxheap.poll();
                if(x!=y){
                    maxheap.add(x-y);
                }
        }

        if(!maxheap.isEmpty()){
            return maxheap.poll();
        }else{
            return 0;
        }

    }
    public static int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals,(a,b)->Integer.compare(a[1],b[1]));

        int counter=0;
        int end=intervals[0][1];

        for (int i = 1; i <intervals.length ; i++) {

            if(intervals[i][0]<end){
                counter++;
            }else{
                end=intervals[i][1];
            }
        }
        return counter;
    }


    public static int maxProfit(int[] prices) {



        int profit=0;
        for (int i = 1; i <prices.length ; i++) {
            if(prices[i]>prices[i-1]){
                    profit+=prices[i]-prices[i-1];
            }
        }
        return profit;


//        int min=Integer.MAX_VALUE;
//        int maxp=0;
//        for (int i = 0; i < prices.length; i++) {
//
//            if(prices[i]<min){
//                min=prices[i];
//            }else{
//
//                int profit=prices[i]-min;
//                maxp=Math.max(profit,maxp);
//            }
//        }
//
//        return maxp;


    }


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res=new ArrayList<>();
        backtrack(nums,new ArrayList<>(),res);
        return res;
    }


//    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> res=new ArrayList<>();
//        backtrackforcombinationsum(candidates, target, 0, new List<Integer>() {
//        }, res);
//        return res;
//    }
//
//    public  void backtrackforcombinationsum(int[] candidate,int target,int start,List<Integer> curr,List<List<Integer>> res)
//    {
//        if(target==0){
//            res.add(new ArrayList<>(curr));
//            return;
//        }
//        if(target<0){
//            return;
//        }
//
//        for (int i = start; i <candidate.length ; i++) {
//            curr.add(candidate[i]);
//            backtrack(candidate,target-candidate[i],i,curr,res);
//            curr.remove(curr.size()-1);
//        }
//    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ls=new ArrayList<>();
        backtracksubsets(0,nums,new ArrayList<>(),ls);
        return ls;
    }


    private static void backtracksubsets(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {

        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtracksubsets(i+1,nums,current,result);
            current.remove(current.size()-1);
        }

    }




    public static List<List<Integer>> permutee(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        getPermutations(nums,0,ans);
        return ans;
    }

    public static void getPermutations(int[] nums,int idx,List<List<Integer>> ans){

        if(idx==nums.length)
        {
            List<Integer> newans=new ArrayList<>();
            for(int n:nums)
            {
            newans.add(n);
            }
            ans.add(newans);
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i); // Correct swap
            getPermutations(nums, idx + 1, ans);
            swap(nums, idx, i); // Backtrack
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        back(candidates,target,0,new ArrayList<>(),ans);
        return ans;

    }

    private void back(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> ans) {

        if(target==0){
            ans.add(new ArrayList<>(curr));
            return;
        }

        if(target<0)
        {
            return;
        }

        for (int i = start; i <candidates.length ; i++) {
            curr.add(candidates[i]);
            back(candidates,target-candidates[i],i,curr,ans);
            curr.remove(curr.size()-1);
        }
    }

    public List<List<Integer>> subsetsopt(int[] nums) {

        List<List<Integer>> res=new ArrayList<>();
        backing(0,nums,new ArrayList<>(),res);
        return res;

    }

    private void backing(int idx, int[] nums, List<Integer> current,  List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // add current subset
        for (int i = idx; i <nums.length ; i++) {
            current.add(nums[i]);
            backing(i+1,nums,current,result);
            current.remove(current.size()-1);
        }
    }


    public static List<List<String>> partition(String s) {

        List<List<String>> res=new ArrayList<>();
        List<String> ans=new ArrayList<>();


        helper(s,res,ans);

        return res;

    }

    public static boolean ispalindromornot(String s){

        if(s.length()<=1){
            return true;
        }

        int st=0;
        int end=s.length()-1;

        while (st< end){
            if(s.charAt(st)!=s.charAt(end)){
                return false;
            }

            st++;
            end--;
        }
        return true;
    }

    public static int hammingWeight(int n) {

        String bin=Integer.toBinaryString(n);
        int count=0;
        for(char ch:bin.toCharArray()){
            if(ch=='1'){
                count++;
            }
        }
        return count;

    }

    public static int hammingWeighotpimal(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // removes the rightmost 1
            count++;
        }
        return count;
    }


    private static void helper(String s, List<List<String>> res, List<String> ans) {

        if(s.length()==0){
            res.add(new ArrayList<>(ans));
        }

        for (int i = 0; i < s.length(); i++) {
            String leftpartition=s.substring(0,i+1);
            if(ispalindromornot(leftpartition)){
                ans.add(leftpartition);
                helper(s.substring(i+1),res,ans);
                ans.remove(ans.size()-1);
            }
        }
    }

    public static void main(String[] args) {


        String s="aab";
        System.out.println(partition(s));
        





//        int [] nums={2, 3, 6, 7};
//        int target=7;
//        List<List<Integer>> result = subsets(nums, target);
//        System.out.println(result);
//        List<List<Integer>> res=new ArrayList<>();
//        backtrack(nums,new ArrayList<>(),res);
//        System.out.println(res);


//        int[] arr={2,7,4,1,8,1};
//        int n=arr.length;

//        int[][] arr={{1,2},{2,3},{3,4},{1,3}};
//        int[] arr={7,1,5,3,6,4};
//        System.out.println(maxProfit(arr));
//        int n=arr.length;
//        System.out.println(eraseOverlapIntervals(arr));




//        System.out.println(lastStoneWeight(arr));

//
//        int[] ans=topKFrequent(arr,2);
//        System.out.println(Arrays.toString(ans));
//






    }

    private static void backtrack(int[] arr, List<Integer> temp, List<List<Integer>> res) {

        if(temp.size()==arr.length)
        {
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int arrs:arr){


            if(temp.contains(arrs)){
                continue;
            }
            temp.add(arrs);
            backtrack(arr,temp,res);
            temp.remove(temp.size()-1);
        }

    }
}
