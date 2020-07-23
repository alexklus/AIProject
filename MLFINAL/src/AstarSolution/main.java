package AstarSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AStarSolution a = new AStarSolution(8,false);
		a.solve();
		
		
		
		
	}
	public static boolean isInSetButWorst(PriorityQueue<Node> openSet,Node node) {
		Iterator<Node> value = openSet.iterator();
		while (value.hasNext()) { 
            Node temp =value.next();
            if(node.compareByBoard(temp)) {
            	
            	if(node.getGn()>temp.getGn()) {
            		return true;
            	}
            }
            	
		}
		return false;
	}
	public static void print(int[][] board) {
		for(int i=0;i<board.length;i++) {
			
			for(int j=0;j<board[i].length;j++) {
				
				if(board[i][j]==1)
					System.out.print("Q ");
				else
					System.out.print("- ");		
				}
			System.out.println();
			
				
		}
			
	}
	public static int[][] copyOf(int[][] board){
		
		int size = board.length;
		int[][] result = new int[size][size];
		
		for(int i=0;i<size;i++) {	
			
			for(int j=0;j<size;j++) {
				result[i][j] = board[i][j];
			}
		}
		return result;
	}
	
	
	
	

}
