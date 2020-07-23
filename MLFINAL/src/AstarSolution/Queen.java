package AstarSolution;


public class Queen {
	private int row;
	private int col;
	
	public Queen(int row,int col) {
		this.row=row;
		this.col=col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean canAttack(Queen otherQueen) {
		if((this.row == otherQueen.getRow()) || (this.col == otherQueen.getCol())) {
			return true;
		}
		
		else if (Math.abs(this.col - otherQueen.getCol()) == Math.abs(this.row - otherQueen.getRow())) {
			return true;
		}
		
		return false;
	}
}
