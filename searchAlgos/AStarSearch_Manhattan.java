package searchAlgos;

import ds.MinPriorityQueue;
import ds.Node;

import java.util.ArrayList;
import java.util.Comparator;


public class AStarSearch_Manhattan extends AbstractSearch {

	private Comparator<Node> comparator = new HeuristicFunction();
	private MinPriorityQueue<Node> frontier = new MinPriorityQueue<Node>(comparator);
	

	/*public MinPriorityQueue<Node> getFrontier(){
		return frontier;
	}*/
	
	public AStarSearch_Manhattan(Node r){

		root = r;
	}

	@Override
	public boolean Search() {

		
		
		explored = new ArrayList<Node>();
		frontier.insert(root);

		while(!frontier.isEmpty()){
			
//			for (Node e: frontier){
//				e.PrintPuzzle();
//				System.out.println(e.getAtotalCost());
//			}
//			System.out.println("5lsst");
			Node state = frontier.delMin();
		
			
			explored.add(state);

			if (GoalState(state)){

				foundGoal = true;
				getSolPath(state);
				return foundGoal;
			} else{

				//expand its children
				state.Expand(this);
				this.expandedNodes++;

				if(state.getChildren() != null){

					for (Node e : state.getChildren()){
						
						frontier.insert(e);

						for (Node f: frontier){
							if( e.getPuzzle() == f.getPuzzle() && e.getAtotalCost() < f.getAtotalCost())
								f.getParent().getChildren().remove(f);	
						}
							
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

	private class HeuristicFunction implements Comparator<Node>{

		@Override
		public int compare(Node n1, Node n2) {
			
			int x = this.manhattan(n1) + getStateCost(n1);
			int y = this.manhattan(n2) + getStateCost(n2);
		    n1.setAtotalCost(x);
		    n2.setAtotalCost(y);
//			System.out.println(x);
//			System.out.println(y);
//			System.out.println("koko");
//			return x-y;
		
			if ( this.manhattan(n1)+ getStateCost(n1) > this.manhattan(n2)+getStateCost(n2) )
				return 1;
			else if ( this.manhattan(n1)+ getStateCost(n1) < this.manhattan(n2)+getStateCost(n2) )
				return -1;
			return 0;
		}

		private int manhattan(Node n) {               // sum of Manhattan distances between blocks and goal
			int sum = 0;
			for (int i = 0; i < 9; i++)
				if (n.getPuzzle()[i] != i && n.getPuzzle()[i] != 0)
					sum += manhattan(n.getPuzzle()[i], i);
			return sum;
		}

		private int manhattan(int val, int current) {  // return manhattan distance of a misplaced block

			int rowDiff = 0, colDiff = 0, xCurrent, yCurrent;
			
			if( current == 0 || current == 1 || current == 2)
				xCurrent = 0;
			else if (current == 3 || current == 4 || current == 5 )
				xCurrent = 1;
			else
				xCurrent = 2;
			
			if( current == 0 || current == 3 || current == 6)
				yCurrent = 0;
			else if (current == 1 || current == 4 || current == 7 )
				yCurrent = 1;
			else
				yCurrent = 2;
			
			
			switch(val){
			case 1:
			rowDiff = Math.abs(xCurrent - 0);
			colDiff = Math.abs(yCurrent - 1);
			break;
			
			case 2:
				rowDiff = Math.abs(xCurrent - 0);
				colDiff = Math.abs(yCurrent - 2);
				break;
				
			case 3:
				rowDiff = Math.abs(xCurrent - 1);
				colDiff = Math.abs(yCurrent - 0);
				break;
				
			case 4:
				rowDiff = Math.abs(xCurrent - 1);
				colDiff = Math.abs(yCurrent - 1);
				break;
				
			case 5:
				rowDiff = Math.abs(xCurrent - 1);
				colDiff = Math.abs(yCurrent - 2);
				break;
				
			case 6:
				rowDiff = Math.abs(xCurrent - 2);
				colDiff = Math.abs(yCurrent - 0);
				break;
				
			case 7:
				rowDiff = Math.abs(xCurrent - 2);
				colDiff = Math.abs(yCurrent - 1);
				break;
				
			case 8:
				rowDiff = Math.abs(xCurrent - 2);
				colDiff = Math.abs(yCurrent - 2);
				break;
			}
			return rowDiff+colDiff;
		}
		
		 
		

	}
}
