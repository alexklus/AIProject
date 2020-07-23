package AstarSolution;

import java.util.Comparator;

public class QueueComparator implements Comparator<Node> {
	
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		if(o1.getFn()<o2.getFn())
			return -1;
		if(o1.getFn()>o2.getFn())
			return 1;
		return 0;
	}
}
