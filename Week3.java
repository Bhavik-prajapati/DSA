import java.util.*;

class MyQueue {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        return stackOut.pop();
    }

    public int peek() {
    return stackIn.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty();
    }
}

class MinStack {

    private Stack<Integer> stak;
    private Stack<Integer> minStacks;

    public MinStack() {
        stak=new Stack<>();
        minStacks=new Stack<>();
    }

    public void push(int val) {
        stak.push(val);
        if(minStacks.isEmpty() || val<=minStacks.peek())
        {
            minStacks.push(val);
        }

    }

    public void pop() {
        int pop=stak.pop();

        if(pop==minStacks.peek())
        {
            minStacks.pop();
        }

    }

    public int top() {

        return stak.peek();
    }

    public int getMin() {
        return minStacks.peek();
    }
}

public class Week3 {

//    https://leetcode.com/problems/climbing-stairs/

    public int climbStairs(int n) {
        if(n<=3) return n;
        int a=3;
        int b=2;
        for (int i = 0; i < n-3; i++) {
            a=a+b;
            b=a-b;
        }
        return a;

    }

//    https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result) {

        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // make a deep copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            backtrack(nums, tempList, result);
            tempList.remove(tempList.size() - 1);
        }

    }


//    https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {

            if(entry.getValue()>nums.length/2){
                return entry.getKey();
            }
        }
        return -1;
    }

    public int majorityElementOptimal(int[] nums) {

        int count=0;
        int candidate=0;
        for(int num:nums){
            if(count==0){
                candidate=num;
            }

            if(num==candidate){

                count+=1;
            }else{
                count+=-1;
            }
        }

        return candidate;
    }

//    https://leetcode.com/problems/kth-largest-element-in-an-array/
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);

            return nums[nums.length-k];
        }

    public int findKthLargestOptimal(int[] nums, int k) {

        PriorityQueue<Integer> minheap=new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {

            minheap.offer(nums[i]);

            System.out.println(minheap);
            if(minheap.size()>k){
                minheap.poll();
            }
        }
        return minheap.peek();
    }


//    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[] {first, last};
    }
    private int findFirst(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        int idx=-1;
        while (start<=end){

            int mid=start+(end-start)/2;
            if(nums[mid]==target){
                idx=mid;
                end=mid-1;
            }else if(nums[mid]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return idx;
    }
    private int findLast(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        int idx=-1;
        while (start<=end){
            int mid=start+(end-start)/2;
            if(nums[mid]==target){
                idx=mid;
                start=mid+1;
            }else if(nums[mid]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return idx;
    }

//    https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {

        int st=0;
        int end=nums.length-1;

        while (st<=end){
            int mid=st+(end-st)/2;

            if(nums[mid]==target){
                return mid;
            }

            if(nums[st]<=nums[mid]){

                if(nums[st]<=target && target<nums[mid]){
                    end=mid-1;
                }else{
                    st=mid+1;
                }
            }else{

                if(nums[mid]<target && target<=nums[end]){
                    st=mid+1;
                }else{
                    end=mid-1;
                }
            }

        }
        return -1;


    }



        public static void main(String[] args) {



//        PriorityQueue<Integer> p=new PriorityQueue<>();
//        p.add(10);
//        p.add(20);
//        p.add(30);
//        p.add(99);
//        p.add(40);
//        System.out.println(p);
////        System.out.println(p.peek());
//            p.poll();
//            p.poll();
//            System.out.println(p);




//            MinStack minStack = new MinStack();
//            minStack.push(1);
//            minStack.push(-5);
//            minStack.push(-4);
//            minStack.push(2);
//            minStack.push(3);
//            minStack.push(4);
//            System.out.println(minStack.getMin());
//            MyQueue m1=new MyQueue();
//            m1.push(1);
//            m1.push(2);
//            m1.push(3);
//            m1.push(4);
//            m1.pop();
//            System.out.println(m1.peek());

//            minStack.push(-2);
//            minStack.push(0);
//            minStack.push(-3);
//            minStack.getMin(); // returns -3
//            minStack.pop();
//            minStack.top();    // returns 0
//            minStack.getMin(); // returns -2


//        Week3 w3=new Week3();
//        int[] arr={4,5,6,7,0,1,2};
//        int ans=w3.search(arr,0);
//        System.out.println(ans);

//        int[] arr={2,2,1,1,1,2,2};
//            int[] arr={3,2,1,5,6,4};
//            PriorityQueue<Integer> minheap=new PriorityQueue<>();
//            for(int num:arr){
//                minheap.offer(num);
//                System.out.println(minheap);
//
//            }
//
//            minheap.poll();
//            minheap.poll();
//            System.out.println(minheap);

//        System.out.println(w3.majorityElementOptimal(arr));

//            System.out.println(  w3.findKthLargestOptimal(arr,2));
//        System.out.println(w3.climbStairs(3));

    }
}
