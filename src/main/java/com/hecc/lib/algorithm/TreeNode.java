package com.hecc.lib.algorithm;

/**
 * @author xuhoujun
 * @description: 节点树
 * @date: Created In 下午10:21 on 2018/4/12.
 */
public class TreeNode<T> {

    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    /**
     * 前序插入构造方法
     *
     * @param data  根节点数据
     * @param left  左子树
     * @param right 右子树
     */
    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
