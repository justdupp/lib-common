package com.hecc.lib.algorithm;

import java.util.LinkedList;

/**
 * @Auther xuhoujun
 * @Description: 二叉树
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
     * 先序遍历二叉树
     *
     * @param root
     */
    public void PrintBinaryTreePreRecur(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.data);
            PrintBinaryTreePreRecur(root.left);
            PrintBinaryTreePreRecur(root.right);
        }
    }

    /**
     * 中序遍历二叉树
     *
     * @param root
     */
    public void PrintBinaryTreeMidRecur(TreeNode<T> root) {
        if (root != null) {
            PrintBinaryTreeMidRecur(root.left);
            System.out.print(root.data);
            PrintBinaryTreeMidRecur(root.right);
        }
    }

    /**
     * 后序遍历二叉树
     *
     * @param root
     */
    public void PrintBinaryTreeBacRecur(TreeNode<T> root) {
        if (root != null) {
            PrintBinaryTreeBacRecur(root.left);
            PrintBinaryTreeBacRecur(root.right);
            System.out.print(root.data);
        }
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

        //先序遍历（递归）
        System.out.println("先序遍历");
        binaryTree.PrintBinaryTreePreRecur(root);
        System.out.println();
        //中序遍历（递归）
        System.out.println("中序遍历");
        binaryTree.PrintBinaryTreeMidRecur(root);
        System.out.println();
        //后序遍历（递归）
        System.out.println("后序遍历");
        binaryTree.PrintBinaryTreeBacRecur(root);
        System.out.println();
    }
}
