//author: Paizabu Min
//The external Tree Node for Linked Trees
public class TreeNode<T> {
	
	private T dataNode;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	/*
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode - the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		this.dataNode = dataNode;
		left = null;
		right = null;
	}
	
	/*
	 * used for making deep copies
	 * @param node - node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.dataNode = node.dataNode;
		this.left = node.left;
		this.right = node.left;
	}
	
	/*
	 * Return the data within this TreeNode
	 */
	public T getData() {
		return dataNode;
	}

	/**
	 * @param dataNode the dataNode to set
	 */
	public void setData(T dataNode) {
		this.dataNode = dataNode;
	}

	/**
	 * @return the left
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
}
