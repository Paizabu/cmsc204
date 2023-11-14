import java.util.ArrayList;

//author: Paizabu Min

public class MorseCodeTree<T> implements LinkedConverterTreeInterface<String>{

	private TreeNode<String> root;
	ArrayList<String> morseList = new ArrayList<>();
	
	/*
	 *  calls the buildTree method
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/*
	 * 	Returns a reference to the root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/*
	 * sets the root of the MorseCodeTree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	@Override
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}

	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		char dotOrDash = code.charAt(0);
		switch (dotOrDash) {
			case '.':
				if(root.getLeft() == null) {
					root.setLeft(new TreeNode(""));
				}
				root = root.getLeft();
				break;
			case '-':
				if(root.getRight() == null) {
					root.setRight(new TreeNode(""));
				}
				root = root.getRight();	
				break;
		}
		if(code.length() == 1) {
			root.setData(letter);
		}
		else {
			addNode(root, code.substring(1), letter);
		}
		
		
	}
	
	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		
		char dotOrDash = code.charAt(0);
		switch (dotOrDash) {
			case '.':
				root = root.getLeft();
				break;
			case '-':
				root = root.getRight();
				break;
		}
		
		if(code.length() != 1) {
			return fetchNode(root, code.substring(1));
		}
		
		return root.getData();
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void buildTree() {
		root = new TreeNode("");
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		LNRoutputTraversal(root, morseList);
		return morseList;
	}
	
	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root.getLeft() != null){
			LNRoutputTraversal (root.getLeft(), list);
	    }
	    list.add(root.getData());
	    if (root.getRight() != null){
	    	LNRoutputTraversal (root.getRight(), list);
	    }
		
	}

}
