package searchAlgos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ds.Node;

public class BFSsearch extends AbstractSearch {
	
	//Neighbors of any visited node goes here
	protected Queue<Node> frontier = new LinkedList<Node>();	

	
	public BFSsearch(Node r){
		
		this.root = r;
		
	}
	
	@Override
	public boolean Search(){
		
		frontier.add(root);
		explored = new ArrayList<Node>();
		
		while(!frontier.isEmpty()){
				
			Node state = frontier.remove();
			explored.add(state);
			
			
			//check for goal in explored nodes				
			if(GoalState(state)){
				
					foundGoal = true;
					
					getSolPath(state);

					return foundGoal;
			}
			else{
					
					//expand its children
					state.Expand(this);

					this.expandedNodes++;
					
					//loop on children to put them in frontier
					if(state.getChildren() != null){
						System.out.println("executing...");
						for (Node e : state.getChildren()){
							frontier.add(e);
						}
						
					}
					
				}		
		}
		
		
		return foundGoal;
	}

	
	@Override
	public boolean IsFrontier(Node state) {
		
		if(!frontier.isEmpty()){
			
			for(Node e:frontier){
				if(SamePuzzle(state.getPuzzleState(),e.getPuzzleState()))
						return true;
			}
			
		}
		
		return false;
		
	}
	
	
	
}
