
package searchAlgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import ds.Node;

public abstract class AbstractSearch {

	protected List<Node> solutionPath = new ArrayList<Node>();
	
	protected Node root;
	
	protected boolean foundGoal = false;

	//visited nodes go here
	protected List<Node> explored ;
	
//	protected List<Node> currentFrontier ;
	
	protected int depth = 0;
	
	protected int cost = 0;
	
	protected int expandedNodes = 0;
	
	
	public Node getRoot(){
		return root;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getExpandedNodes(){
		return this.expandedNodes;
	}
	
	//get solution path and update depth
	public void getSolPath(Node sol){
		
		Stack<Node> temp = new Stack<Node>();
		
		Node tn = sol;
		
		while(tn != null){
			
			temp.push(tn);
			tn = tn.getParent();
			
			this.depth ++;

		}
		depth --;
		

		
		//set solutionPath
		while(temp.size()>0){
			
			solutionPath.add(temp.pop());
			
		}
		
		//print path
		System.out.println("Solution path:");
		for (Node e:solutionPath){
			e.PrintPuzzle();
		}
		
		this.cost = solutionPath.size()- 1;
		
	}
	
	public int getStateCost(Node n){
			
			int stateDepth = 0;
			Node tn = n;
			
			while(tn != null){
	
				tn = tn.getParent();
				stateDepth ++;
			}
			stateDepth --;
	//		System.out.println(stateDepth);
			return stateDepth;
	}

	
	protected  boolean GoalState(Node state){
		
		return Arrays.equals(state.getPuzzleState(), new int[] {0,1,2,3,4,5,6,7,8} );
		
	}
	
	public abstract boolean Search();
	
	public boolean IsExplored(Node state){
		
		if(!explored.isEmpty()){
			
			for(Node e:explored){
				if(SamePuzzle(state.getPuzzleState(),e.getPuzzleState()))
						return true;
			}
		}
		return false;
	}

	public abstract boolean IsFrontier(Node state);
	
	protected boolean SamePuzzle(int[] p1, int[] p2) {
		
		for(int i=0; i< p1.length; i++){
			
			if(p1[i] != p2[i])
				return false;
			
		}
		return true;
		
	}

	public List<Node> getSolList() {
	
		return solutionPath;
		
	}
	
}
