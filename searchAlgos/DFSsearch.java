package searchAlgos;

import java.util.ArrayList;
import java.util.Stack;

import ds.Node;

public class DFSsearch extends AbstractSearch{
	
	//Neighbors of any visited node goes here
	protected Stack<Node> frontier = new Stack<Node>();
		
	public DFSsearch(Node r){
			
			this.root = r;
			
		}
		
		@Override
	public boolean Search(){
		
		frontier.push(root);
		explored = new ArrayList<Node>();
		
		while(!frontier.isEmpty()){
			
			Node state = frontier.pop();
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
						for (int i=state.getChildren().size()-1; i>=0 ;i-- ){
							frontier.push(state.getChildren().get(i));
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
