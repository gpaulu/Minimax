import java.util.Arrays;
import java.util.Random;


public class BinaryMinimax {
	private class Node{
		public int value;
		public Turn type;
		public Node left;
		public Node right;
		Node(Turn t){
			type = t;
		}
		Node(int i){
			type = Turn.LEAF;
			value = i;
		}
	}
	private Node root;
	//depth of tree
	private int ply;
	private int[] values;
	
	BinaryMinimax(int ply, int[] values){
		if(Math.pow(2.0, ply) != values.length){
			throw new RuntimeException("Wrong number of values");
		}
		this.ply = ply;
		this.values = values;
		root = new Node(Turn.MAX);
		makeTree(root, ply, values);
	}
	
	//generates a random tree
	//values [-100,100]
	public static BinaryMinimax createRandom(int ply){
		Random rand = new Random();
		int[] values = new int[(int)Math.pow(2.0, ply)];
		for(int i=0; i<values.length; i++){
			values[i] = rand.nextInt(200) - 100;
		}
		return new BinaryMinimax(ply, values);
	}
	
	//Recursively construct the minimax tree with the given values
	private void makeTree(Node node, int depth, int[] values){
		if(depth <= 1){
			node.left = new Node(values[0]);
			node.right = new Node(values[1]);
		}
		else{
			//give half the values to the right subtree and the other half to the left subtree
			int[] first = Arrays.copyOfRange(values, 0, values.length/2);
			int[] last = Arrays.copyOfRange(values, values.length/2, values.length);
			if(node.type == Turn.MAX){
				node.left = new Node(Turn.MIN);
				node.right = new Node(Turn.MIN);
			}
			else if(node.type == Turn.MIN){
				node.left = new Node(Turn.MAX);
				node.right = new Node(Turn.MAX);
			}
			else{
				throw new RuntimeException("Unintialized node in tree construction");
			}
			makeTree(node.left, depth-1, first);
			makeTree(node.right, depth-1, last);
		}
	}
	
	public int MAXVALUE(){
		return findMax(root);
	}
	
	private int findMax(Node node){
		if(node.type == Turn.LEAF){
			return node.value;
		}
		else if(node.type == Turn.MAX){
			return Math.max(findMax(node.left), findMax(node.right));
		}
		else if(node.type == Turn.MIN){
			return Math.min(findMax(node.left), findMax(node.right));
		}
		throw new RuntimeException("Unintialized node in tree search");
	}

	public int getPly() {
		return ply;
	}
	public int[] getValues(){
		return values;
	}
}
