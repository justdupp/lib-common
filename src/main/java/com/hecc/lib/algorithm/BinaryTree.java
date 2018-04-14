package com.hecc.lib.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther xuhoujun
 * @Description: 二叉树工具类
 * @Date: Created In 下午10:20 on 2018/4/12.
 */
public class BinaryTree<T> {

    /**
     * 先序创建二叉树 这里不使用中序的原因是因为中序确定不了唯一性二叉树
     * 这里引入了空虚节点
     *
     * @param treeDate ABDH##I##E##CF#J##G##
     * @return 根节点
     */
    public TreeNode<T> createBinaryPre(LinkedList<T> treeDate) {
        TreeNode<T> root = null;
        T data = treeDate.removeFirst();
        if (data != null) {
            root = new TreeNode(data, null, null);
            root.left = createBinaryPre(treeDate);
            root.right = createBinaryPre(treeDate);
        }
        return root;
    }

    /**
     * 先序遍历迭代
     *
     * @param root
     */
    public void printBinaryTreePre(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode treeNode = nodeStack.pop();
            System.out.print(treeNode.data);
            if (treeNode.right != null) {
                nodeStack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                nodeStack.push(treeNode.left);
            }
        }
    }

    /**
     * 先序遍历二叉树--递归
     *
     * @param root 二叉树
     */
    public void printBinaryTreePreRecur(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.data);
            printBinaryTreePreRecur(root.left);
            printBinaryTreePreRecur(root.right);
        }
    }

    /**
     * 中序遍历二叉树--递归
     *
     * @param root 二叉树
     */
    public void printBinaryTreeMidRecur(TreeNode<T> root) {
        if (root != null) {
            printBinaryTreeMidRecur(root.left);
            System.out.print(root.data);
            printBinaryTreeMidRecur(root.right);
        }
    }

    /**
     * 后序遍历二叉树--递归
     *
     * @param root 二叉树
     */
    public void printBinaryTreeBacRecur(TreeNode<T> root) {
        if (root != null) {
            printBinaryTreeBacRecur(root.left);
            printBinaryTreeBacRecur(root.right);
            System.out.print(root.data);
        }
    }

    /**
     * 获取二叉树中节点个数-- 递归
     *
     * @param root 二叉树
     * @return 节点个数
     */
    public int getNodeCountRec(TreeNode<T> root) {
        if (root != null) {
            return getNodeCountRec(root.left) + getNodeCountRec(root.right) + 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取二叉树中节点个数
     *
     * @param root 二叉树
     * @return 节点个数
     */
    public int getNodeCount(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int nodeCount = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.remove();
            if (treeNode.left != null) {
                queue.add(treeNode.left);
                nodeCount++;
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
                nodeCount++;
            }
        }
        return nodeCount;
    }

    /**
     * 递归的方式获取二叉树的深度
     * @param root 二叉树
     * @return
     */
    public int getDepthRec(TreeNode<T> root){
        if(root == null){
            return 0;
        }
        int leftDepth = getDepthRec(root.left);
        int rightDepth = getDepthRec(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }

    /**
     * 获取二叉树的深度
     * @param root
     * @return
     */
    public int getDepth(TreeNode<T> root){
        if(root == null){
            return 0;
        }
        int depth = 0;
        int currentLevel = 1;
        int nextLevel = 0;
        LinkedList<TreeNode> treeNodeLinkedList = new LinkedList<>();
        treeNodeLinkedList.add(root);
        while(!treeNodeLinkedList.isEmpty()){
            TreeNode treeNode = treeNodeLinkedList.remove();
            currentLevel--;
            if(treeNode.left != null){
                treeNodeLinkedList.add(treeNode.left);
                nextLevel++;
            }
            if(treeNode.right != null){
                treeNodeLinkedList.add(treeNode.right);
                nextLevel++;
            }
            if(currentLevel == 0){
                depth++;
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return depth;
    }


    public static void main(String[] args) {
      /*   BinaryTree<Character> binaryTree=new BinaryTree<>();

       //输入ABDH##I##E##CF#J##G##（#用null代替）
       LinkedList<Character> tree=new LinkedList<>();
        tree.add('A');tree.add('B');tree.add('D');
        tree.add('H');tree.add(null);tree.add(null);
        tree.add('I');tree.add(null);tree.add(null);
        tree.add('E');tree.add(null);tree.add(null);
        tree.add('C');tree.add('F');tree.add(null);
        tree.add('J');tree.add(null);tree.add(null);
        tree.add('G');tree.add(null);tree.add(null);

        TreeNode<Character> root = binaryTree.createBinaryPre(tree);
        */

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        // 123##4##56###
        LinkedList<Integer> tree = new LinkedList<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(null);
        tree.add(null);
        tree.add(4);
        tree.add(null);
        tree.add(null);
        tree.add(5);
        tree.add(6);
        tree.add(null);
        tree.add(null);
        tree.add(null);

        TreeNode<Integer> root = binaryTree.createBinaryPre(tree);
        System.out.println("获取二叉树深度");
        System.out.println("递归获取 ： "+ binaryTree.getDepthRec(root));
        System.out.println("非递归获取 ： "+ binaryTree.getDepth(root));

        //先序遍历（递归）
        System.out.println("先序遍历");
        binaryTree.printBinaryTreePreRecur(root);
        System.out.println();
        //中序遍历（递归）
        System.out.println("中序遍历");
        binaryTree.printBinaryTreeMidRecur(root);
        System.out.println();
        //后序遍历（递归）
        System.out.println("后序遍历");
        binaryTree.printBinaryTreeBacRecur(root);
        System.out.println();
    }
}
