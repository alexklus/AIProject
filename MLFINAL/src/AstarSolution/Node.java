package AstarSolution;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	private Node parent;
	private int[][] board;
	private int heuristic, g_n, f_n;
	private ArrayList<Queen> queens;
	private int numberOfQueens;
	
	
	
	public Node(int[][] board,int numberOfQueens,int g_n,Node parent) {
		// TODO Auto-generated constructor stub
		
		queens = new ArrayList<Queen>();
		this.numberOfQueens = numberOfQueens;
		this.g_n = g_n;
		this.board = new int[numberOfQueens][numberOfQueens];
		
		for(int i = 0; i<numberOfQueens ; i++) {
			
			for(int j = 0 ; j<numberOfQueens ; j++) {
				
				this.board[i][j] = board[i][j];
				if(this.board[i][j] == 1) {
					queens.add(new Queen(i, j));
				}
			}
		}
		calculateHeuristic();
		this.parent = parent;
		this.f_n = this.g_n + this.heuristic;	
	}
	
	public int getFn() {
		return this.f_n;
	}
	
	public int getGn() {
		return this.g_n;
	}
	
	public boolean isGoal() {
		return heuristic == 0;
	}
	
	public int [][] getBoard(){
		return this.board;
	}
	
	public int getHuristic() {
		return heuristic;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public boolean isInitial() {
		return this.parent == null;
	}
	
	public void calculateHeuristic() {
		
		for(int i = 0; i<numberOfQueens;i++) {
			
			for(int j=i+1; j<numberOfQueens;j++) {
				
				if(queens.get(i).canAttack(queens.get(j))) {
					
					this.heuristic++;
				}
			}
		}
	}
	
	public void printBoard() {
		
		for(int i = 0; i<numberOfQueens ; i++) {
				
				for(int j = 0 ; j<numberOfQueens ; j++) {
					
					if(board[i][j]==1)
						System.out.print("Q ");
					else
						System.out.print("- ");		
				}
				System.out.println();
			}
	}
	
	public ArrayList<Node> getNeighbors(){
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		for( Queen queen:queens) {
			
			int row = queen.getRow();
			int col = queen.getCol();
			
			if(valinIndex(row, col-1)) {
				int[][] tempBoard = copyOf(this.board);
				tempBoard[row][col] = 0;
				tempBoard[row][col-1] = 1;
				
				Node child = new Node(tempBoard, this.numberOfQueens, this.g_n+1,this);
				neighbors.add(child);
				
			}
			
			if(valinIndex(row, col+1)) {
				int[][] tempBoard = copyOf(this.board);
				tempBoard[row][col] = 0;
				tempBoard[row][col+1] = 1;
				
				Node child = new Node(tempBoard, this.numberOfQueens, this.g_n+1,this);
				neighbors.add(child);
			}
			
		}
		return neighbors;
	}
	
	public boolean valinIndex(int row,int col) {
		return row>=0 && row<numberOfQueens && col>=0 && col <numberOfQueens;
	}
	
	public int[][] copyOf(int[][] board){
			
			int size = board.length;
			int[][] result = new int[size][size];
			
			for(int i=0;i<size;i++) {	
				
				for(int j=0;j<size;j++) {
					result[i][j] = board[i][j];
				}
			}
			return result;
		}
	
	public boolean compareByBoard(Node otherNode) {
		int[][] otherBoard = otherNode.getBoard();
		
		if(this.board.length!= otherBoard.length)
			return false;
		for(int i=0; i<numberOfQueens;i++) {
			
			if (!(Arrays.equals(board[i], otherBoard[i])))
				return false;
		}
		return true;
	}
}
