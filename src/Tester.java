//uses BinaryMinimax.java and Turn.java
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generate 5 random trees and test minimax search with no lookahead
		for(int i=0; i<5; i++){
			System.out.println("Tree: " + (i+1));
			try{
				BinaryMinimax tree = BinaryMinimax.createRandom(i+2);
				System.out.println("Ply: " + tree.getPly());
				int[] values = tree.getValues();
				System.out.print("Values: ");
				for(int j=0; j<values.length-1; j++){
					System.out.print(values[j] + ", ");
				}
				System.out.println(values[values.length-1]);
				System.out.println("MAX-VALUE: " + tree.MAXVALUE() + "\n");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

}
