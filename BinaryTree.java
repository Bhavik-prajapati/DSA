import com.sun.source.tree.Tree;
import org.w3c.dom.Node;

import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
public class BinaryTree {
   public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans=new ArrayList<>();

    if(root!=null){
        ans.add(root.val);
        ans.addAll(preorderTraversal(root.left));
        ans.addAll(preorderTraversal(root.right));
    }
    return ans;
   }

    public List<Integer> inorderTraversal(TreeNode root) {

       List<Integer> ans=new ArrayList<>();
       if(root!=null){
           ans.addAll(inorderTraversal(root.left));
           ans.add(root.val);
           ans.addAll(inorderTraversal(root.right));
       }
       return ans;
    }

    public List<Integer> postorderTraversal(TreeNode root) {

       List<Integer> ans=new ArrayList<>();
       if(root!=null){
           ans.addAll(postorderTraversal(root.left));
           ans.addAll(postorderTraversal(root.right));
           ans.add(root.val);
       }
       return ans;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

       List<List<Integer>> ans=new ArrayList<>();
       if(root==null){
           return ans;
       }

        Queue<TreeNode> queue=new LinkedList<>();
       queue.offer(root);
       while (!queue.isEmpty()){
           int levelsize=queue.size();
           List<Integer> currentlevel=new ArrayList<>();
           for (int i = 0; i < levelsize; i++) {

               TreeNode currnode=queue.poll();
               currentlevel.add(currnode.val);
               if(currnode.left!=null) {
                   queue.offer(currnode.left);
               }
               if(currnode.right!=null) {
                   queue.offer(currnode.right);
               }
           }
        ans.add(currentlevel);
       }
       return ans;


    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;

       invertTree(root.left);
       invertTree(root.right);

       return root;
    }


    private int preIdx = 0;                // current root index inside preorder
    private Map<Integer, Integer> pos = new HashMap<>(); // val → index in inorder

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            pos.put(inorder[i],i);
        }
        return helper(preorder,0,inorder.length-1);
    }

    private TreeNode helper(int[] preorder, int inleft, int inright) {
        if(inleft>inright){
            return null;
        }
        int rootval=preorder[preIdx++];
        TreeNode root=new TreeNode(rootval);
        int mid=pos.get(rootval);
        root.left=helper(preorder,inleft,mid-1);
        root.right=helper(preorder,mid+1,inright);
        return root;

    }



    public static void staircase(int n) {

    }


        public static void main(String[] args) {


//            TreeNode root = new TreeNode(4);
//            root.left = new TreeNode(2);
//            root.right = new TreeNode(7);
//
//            root.left.left = new TreeNode(1);
//            root.left.right = new TreeNode(3);
//
//            root.right.left = new TreeNode(6);
//            root.right.right = new TreeNode(9);
//
//
//            BinaryTree b1=new BinaryTree();
//            b1.invertTree(root);
//


//        System.out.println(b1.inorderTraversal(t1));





    }
}
