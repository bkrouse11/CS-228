package edu.iastate.cs228.hw4;

/**
 * Class for decoding an archived message with a binary-tree-based algorithm
 * @author Brandon Rouse
 */

public class MsgTree {
	
	/**
	 * Instance variable holding the character at the specific leaf
	 */
	
	public char payloadChar;
	
	/**
	 * Instance variables holding left subtree
	 */
	
	public MsgTree left;
	
	/**
	 * Instance variable holding right subtree
	 */
	
	public MsgTree right;
	
	/**
	 * Instance variable holding the index of the characters in the tree
	 */
	
	private static int staticCharIdx = 0;
	
	/**
	 * Constructor building the tree from a string
	 * @param encodingString string used to build the tree
	 */
	
	public MsgTree(String encodingString) {
		//recursiveTreeBuilder(this,encodingString);
		recursiveTreeBuilder(this,encodingString);
	}
	
	/**
	 * Constructor for a single node with null children
	 * @param payloadChar character within the node
	 */
	
	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
	}
	
	/**
	 * Method to print characters and their binary codes
	 * @param root
	 * @param code
	 */
	
	public static void printCodes(MsgTree root, String code) {
		MsgTree temp = root; 
		String s = "";
		for(char character : code.toCharArray()) {
			if(character == '0') {
				temp = temp.left;
				s += '0';
			}
			
			else {
				temp = temp.right;
				s += '1';
			}
			
			if(temp.payloadChar != '^') {
				System.out.println(temp.payloadChar + "           " + s);
				s = "";
				temp = root;
			}
		}
	}
	
	/**
	 * Method to print the tree through preorder traversal
	 * @param node tree to be traversed through
	 */
	
	public static void preorderTraversal(MsgTree root) {
		if(root == null) {
			return;
		}
		
		System.out.print(root.payloadChar);
		
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}
	
	/**
	 * Recursive method to build binary tree
	 * @param encoder encoding string
	 * @return New Binary Search Tree with encoding string
	 */
	
	public MsgTree recursiveTreeBuilder(MsgTree node, String encoder) {
		
		node.payloadChar = encoder.charAt(staticCharIdx);
		
		//MsgTree tmp = new MsgTree(encoder.charAt(staticCharIdx++));
		
		if(encoder.charAt(staticCharIdx) == '^') {
			node.left = recursiveTreeBuilder(new MsgTree(encoder.charAt(staticCharIdx++)), encoder);
			node.right = recursiveTreeBuilder(new MsgTree(encoder.charAt(staticCharIdx++)), encoder);
		}
		
		return node;
		
	}
	
	/**
	 * Method to decode the archived message
	 * @param codes the MsgTree holing the characters of the message
	 * @param msg the message in binary form
	 */
	
	public static void decode(MsgTree codes, String msg) {
		MsgTree temp = codes; 
		for(char character : msg.toCharArray()) {
			if(character == '0') {
				temp = temp.left;
			}
			
			else {
				temp = temp.right;
			}
			
			if(temp.payloadChar != '^') {
				System.out.print(temp.payloadChar);
				temp = codes;
			}
		}
	}
}