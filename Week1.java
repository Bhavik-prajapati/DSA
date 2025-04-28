import com.sun.security.auth.UnixNumericGroupPrincipal;

import java.lang.reflect.AnnotatedArrayType;
import java.util.*;

public class Week1 {
    //    https://leetcode.com/problems/running-sum-of-1d-array/
    public static  int[] runningSum(int[] nums) {
        int[] ans=new int[nums.length];
        ans[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            int sum=0;
            for (int j = i; j >=0 ; j--) {
                sum+=nums[j];
            }
            ans[i]=sum;
        }
        return ans;
    }
    public static  int[] runningSumbetter(int[] nums) {
        int[] ans=new int[nums.length];
        ans[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans[i]=ans[i-1]+nums[i];
        }
        return ans;
    }
    public static  int[] runningSumbest(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i]=nums[i]+nums[i-1];
        }
        return nums;
    }

//    https://leetcode.com/problems/richest-customer-wealth/

    public static int maximumWealth(int[][] accounts) {

        int n=accounts.length;
        int m=accounts[0].length;
        int ans=Integer.MIN_VALUE;
        for (int i = 0; i <n ; i++) {
            int sum=0;
            for (int j = 0; j < m; j++) {
                sum+=accounts[i][j];
            }
            ans=Math.max(ans,sum);
        }

        return ans;
    }
    public static int maximumWealthbetter(int[][] accounts) {
        return Arrays.stream(accounts)
                .mapToInt(row -> Arrays.stream(row).sum())
                .max()
                .orElse(0);
    }

//    https://leetcode.com/problems/maximum-subarray/

    public static int maxSubArrayBetter(int[] nums) {

        int n=nums.length;
        int maxsum=Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum=0;
            for (int j = i; j < n; j++) {
                sum+=nums[j];
                maxsum=Math.max(maxsum,sum);
            }
        }
        return maxsum;
    }

    public static int maxSubArrayOptimalKadensAlgo(int[] nums) {
        int sum=0;
        int maxi=nums[0];
        for (int i = 0; i < nums.length; i++) {

            sum=sum+nums[i];
            maxi=Math.max(maxi,sum);
            if(sum<0){
                sum=0;
            }

        }
        return maxi;
    }

//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

    public static int maxProfit(int[] prices) {

        int min=Integer.MAX_VALUE;
        int maxprofit=0;
        for (int i = 0; i < prices.length; i++) {

            if(prices[i]<min){
                min=prices[i];
            }else{
                int profit=prices[i]-min;
                maxprofit=Math.max(profit,maxprofit);
            }
        }
        return maxprofit;
    }

    //    https://leetcode.com/problems/subarray-sum-equals-k/description/

    public static int subarraySum(int[] nums, int k) {
        int n=nums.length;
        int counter=0;
        for (int i = 0; i <n; i++) {
            int sum=0;
            for (int j = i; j <n; j++) {
                sum+=nums[j];
            }
            if(sum==k){
                counter++;
            }
        }
        return counter;
    }

    public static int subarraySumOptimalPrefixSum(int[] nums, int k) {

        int n = nums.length;
        int ans = 0;
        HashMap<Integer,Integer> hm=new HashMap<>();
        hm.put(0,1);
        int sum=0;
        for (int i = 0; i < n; i++) {

            sum+=nums[i];
            int rsum=sum-k;
            if(hm.containsKey(sum-k)){
                ans+=hm.get(sum-k);
            }
            hm.put(sum,hm.getOrDefault(sum,0)+1);
        }


        return ans;

    }

//    https://leetcode.com/problems/minimum-size-subarray-sum/description/
    public static int minSubArrayLen(int target, int[] nums) {
        int minlenwindow=Integer.MAX_VALUE;
        int low=0;
        int high=0;
        int sum=0;
        for (high = 0; high < nums.length; high++) {
            sum=sum+nums[high];
            while (sum>=target){
                int currwinlen=high-low+1;
                minlenwindow=Integer.min(minlenwindow,currwinlen);
                sum=sum-nums[low];
                low++;
            }
        }
        return minlenwindow==Integer.MAX_VALUE ? 0 :minlenwindow;



    }

//    https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public static int[] twoSum(int[] numbers, int target) {

        int n=numbers.length;
        int[] ans=new int[2];

        for (int i = 0; i < n; i++) {

            for (int j = i+1; j < n; j++) {

                if(numbers[i]+numbers[j]==target){
                    ans[0]=i+1;
                    ans[1]=j+1;
                    return ans;
                }

            }
        }

        return ans;


    }

    public static int[] twoSumbetter(int[] numbers, int target) {

        int n=numbers.length;
        int[] ans=new int[2];
        HashMap<Integer,Integer> hm=new HashMap<>();

        for (int i = 0; i < n; i++) {

            int requiredval=target-numbers[i];

            if(hm.containsKey(requiredval)){
                ans[0]=i+1;
                ans[1]=hm.get(requiredval);
            }
            else{
                hm.put(numbers[i],hm.getOrDefault(numbers[i],i+1));
            }
        }
        Arrays.sort(ans);
        return ans;
        
        



    }
    public static int[] twoSumOptimal(int[] numbers, int target)
    {
        int left=0;
        int right=numbers.length-1;
        while (left<right){
            int sum=numbers[left]+numbers[right];

            if(sum==target){
                return new int[]{left+1,right+1};
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{};

    }

//    https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public static int removeDuplicates(int[] nums) {

        Set<Integer> s=new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            s.add(nums[i]);
        }

        int counter=0;
        for(Integer val:s){
            nums[counter++]=val;
        }
        for (int i = counter; i <nums.length ; i++) {
            nums[i]=Integer.MAX_VALUE;
        }
        Arrays.sort(nums);
        return s.size();
    }


    public static int removeDuplicatesOptimal(int[] nums) {
            
        int i=0;
        for (int j = i+1; j < nums.length; j++) {

            if(nums[j]!=nums[i]){
                nums[i+1]=nums[j];
                i++;
            }
        }

        return i+1;
    }

//    https://leetcode.com/problems/merge-sorted-array/

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i=0;
        int j=0;
        ArrayList<Integer> arr=new ArrayList<>();

        while (i<m && j<n){
            if(nums1[i]<=nums2[j]){
                arr.add(nums1[i]);
                i++;
            }else{
                arr.add(nums2[j]);
                j++;
            }
        }
        while (i<m){
            arr.add(nums1[i]);
            i++;
        }

        while (j<n){
            arr.add(nums2[j]);
            j++;
        }

        for (int k = 0; k < arr.size(); k++) {
            nums1[k] = arr.get(k);
        }
        System.out.println(Arrays.toString(nums1));


    }

    public static void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {

        int i=m-1;
        int j=n-1;
        int k=m+n-1;

        while (j>=0){
            if(i>=0 && nums1[i]>nums2[j]){

                nums1[k]=nums1[i];
                i--;
                k--;
            }else{
                nums1[k]=nums2[j];

                j--;
                k--;
            }
        }
        System.out.println(Arrays.toString(nums1));


    }

//    https://leetcode.com/problems/move-zeroes/


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void moveZeroes(int[] nums) {
        int lastZetoTrack=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0) {
                swap(nums, lastZetoTrack, i);
                lastZetoTrack++;
            }
        }

        System.out.println(Arrays.toString(nums));
    }

//https://leetcode.com/problems/squares-of-a-sorted-array/

//    O(N) O(1)
    public static int[] sortedSquares(int[] nums) {
        int n=nums.length;

        for (int i = 0; i < n; i++) {
            nums[i]=nums[i]*nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public static int[] sortedSquaresOptimal(int[] nums) {
        int n= nums.length;
        int[] ans=new int[n];
        int l=0;
        int r=n-1;
        int idx=n-1;

        while (l<=r){
            int left=nums[l]*nums[l];
            int right=nums[r]*nums[r];

            if(left>right){
                ans[idx]=left;
                l++;
            }else{
                ans[idx]=right;
                r--;
            }
            idx--;
        }
        return ans;

    }

//    https://leetcode.com/problems/gas-station/

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int totalgas=0,totalcost=0;
        for (int val:gas){
            totalgas+=val;
        }
        for (int val:cost){
            totalcost+=val;
        }

        if(totalgas<totalcost){
            return -1;
        }

        int  start=0;
        int currgas=0;

        for (int i = 0; i < gas.length; i++) {
            currgas+=(gas[i]-cost[i]);
            if(currgas<0){
                start=i+1;
                currgas=0;
            }
        }

        return start;
    }



    public static void main(String[] args) {

        int[] gas={1,2,3,4,5};
        int[] cost={3,4,5,1,2};
        Week1 w=new Week1();
        System.out.println(w.canCompleteCircuit(gas,cost));




//        moveZeroes(arr1);
//        int[] arr2={2,5,6};
//
//            mergeOptimal(arr1,3,arr2,arr2.length);

//        int ans=removeDuplicates(arr);

    }
}
