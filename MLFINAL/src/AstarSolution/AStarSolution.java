package AstarSolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import Strategy.StrategySolution;

public class AStarSolution implements StrategySolution {
	int numberOfQueens;
	int max_iter;
	private boolean printFullPath;
	
	public AStarSolution(int numberOfQueens,boolean printPath) {
		// TODO Auto-generated constructor stub
		this.printFullPath = printPath;
		this.numberOfQueens = numberOfQueens;
		
		max_iter = 10000;
	}
	
	public Node generateInitial() {                   
		int[][]  temp = new int [numberOfQueens][numberOfQueens];
		for(int i=0;i < numberOfQueens; i++) {
			int randomIndex = (int) (Math.random()*numberOfQueens);
			
			for(int j=0;j<numberOfQueens;j++) {
				
				if(randomIndex == j) 
					temp[i][j] = 1;
				else
					temp[i][j] = 0;	
			}
		}
		
		return new Node(temp, numberOfQueens, 0, null);
		
	}
	
	public void solve(){
		
		QueueComparator q_comparator = new QueueComparator();
		PriorityQueue<Node> openSet = new PriorityQueue<Node>(q_comparator);
		ArrayList<Node> closedSet = new ArrayList<Node>();
		
		Node initial = generateInitial();
		System.out.println("Initial state:");
		
		initial.printBoard();
		
		openSet.add(initial);
		Node current = null;
		boolean found = false;
		int counter = 0;
		
		
		while(!openSet.isEmpty() && !found && counter < max_iter) {
			
			current = openSet.poll();
			counter++;
			ArrayList<Node> succesors = current.getNeighbors();
			closedSet.add(current);
			
			if(printFullPath) {
				System.out.println("Current iteration:"+counter+ ", current huristic:"+current.getHuristic()
					+", current f_n"+current.getFn());
				current.printBoard();
			}
			
			if(current.isGoal()) {
				System.out.println("Goal state:");
				current.printBoard();
				found = true;
				break;
			}
			
			for(Node succesor : succesors ) {
				
				if(inClosedSet(closedSet, succesor)) {
					continue;
				}
				if(!(inOpenSet(openSet, succesor))) {
					openSet.add(succesor);
					continue;
				}
				if (isInOpenSetButBetter(openSet, succesor)) {
					openSet.add(succesor);
					continue;
				}	
			}	
		}
		
		if(!found) {
			System.out.println("No solution found after "+ max_iter+ " iterations");
		}
		if(found && !printFullPath) {
			printSolutionPath(current);
		}
			
	}
	public boolean inClosedSet(ArrayList<Node> closedSet,Node node) {
	
		for(Node temp:closedSet) {
			if(node.compareByBoard(temp))
				return true;
		}
		return false;
	
	}
	
	public boolean inOpenSet(PriorityQueue<Node> openSet,Node node) {
		Iterator<Node> value = openSet.iterator();
		
		while (value.hasNext()) { 
            Node temp =value.next();
            
            if(node.compareByBoard(temp)) {
            	return true;
            }
        }
		return false;
	}
	
	public boolean isInOpenSetButBetter(PriorityQueue<Node> openSet,Node node) {
		Iterator<Node> value = openSet.iterator();
		while (value.hasNext()) { 
            Node temp =value.next();
            if(node.compareByBoard(temp)) {
            	
            	if(node.getGn()<temp.getGn()) {
            		value.remove();
            		return true;
            	}
            }
            	
		}
		return false;
	}
	
	public void printSolutionPath(Node node) {
		if(node.isInitial()) {
			System.out.println("Initial state:");
			node.printBoard();
		}
		else {
			printSolutionPath(node.getParent());
			System.out.println("f_n: " +node.getFn()+", Huristic: "+node.getHuristic());
			node.printBoard();
		}
	}

	
	
}
