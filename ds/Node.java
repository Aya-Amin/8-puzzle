package ds;

import java.util.ArrayList;
import java.util.List;

import searchAlgos.AStarSearch_Euclidean;
import searchAlgos.AStarSearch_Manhattan;
import searchAlgos.AbstractSearch;
import searchAlgos.BFSsearch;
import searchAlgos.DFSsearch;


public class Node {
	
	private Node parent ;
	
	private List<Node> children ;
	
	private  int [] puzzle = new int [9];
		
	private int col = 3;
	
	private int zeroPos;
	
	
	private AbstractSearch tempSearch = null;
	
	private int atotalCost;
	
	private double aEucTotalCost;
	
	
	
	public Node(int [] p){
		
		//set puzzle
		for(int i=0;i<getPuzzle().length;i++){
			this.puzzle[i] = p[i];
		}			
	
	}
	
	public double getaEucTotalCost() {
		return aEucTotalCost;
	}


	public void setaEucTotalCost(double aEucTotalCost) {
		this.aEucTotalCost = aEucTotalCost;
	}


	public int getAtotalCost() {
		return atotalCost;
	}


	public void setAtotalCost(int atotalCost) {
		this.atotalCost = atotalCost;
	}

	
	public void PrintPuzzle(){
		
		System.out.println("\n");
		int m=0;
		for(int i=0; i < col; i++){
			for(int j=0; j < col; j++){
				System.out.print(getPuzzle()[m] + " ");
				m++;
			}
		  System.out.println("\n");
		}
		
	}

	
	public int [] getPuzzleState(){
		return getPuzzle();
	}
	
	public List<Node> getChildren(){
		return children;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public void Expand(AbstractSearch s){
		
		if( s instanceof BFSsearch)
			tempSearch = (BFSsearch) s;
		else if( s instanceof DFSsearch)
			tempSearch = (DFSsearch) s;
		else if( s instanceof AStarSearch_Euclidean)
			tempSearch = (AStarSearch_Euclidean) s;
		else
			tempSearch = (AStarSearch_Manhattan) s;

		
		//find the position of the zero 
		for(int i=0; i<getPuzzle().length; i++){
			if(getPuzzle()[i] == 0){
				zeroPos = i;
				break;
			}
		}
		
		//expand all possible nodes in it
		this.children = new ArrayList<Node>();
		
		MoveTop  (zeroPos);
		MoveDown (zeroPos);
		MoveLeft (zeroPos);
		MoveRight(zeroPos);
		
	}
	
	private void MoveRight(int i){
				
		if(i % col < col-1){
			
			int [] pc = new int[9];
			System.arraycopy(this.puzzle,0,pc,0, 9);
			
			//swapping
			int temp = pc [i+1];
			pc[i+1] = pc[i];
			pc[i] = temp;
			

			//set new created node as child to its parent
			Node child = new Node(pc);
			
			if(!tempSearch.IsFrontier(child) && !tempSearch.IsExplored(child)){
				this.children.add(child);
				child.parent = this;
			}
			
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Manhattan){
				this.children.add(child);
				child.parent = this;
			}
			
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Euclidean){
				this.children.add(child);
				child.parent = this;
			}
			
		}	

	}
	
	private void MoveLeft(int i){
		
		if(i % col > 0){
					
			int [] pc = new int[9];
			System.arraycopy(this.puzzle,0,pc,0, 9);
					
			//swapping
			int temp = pc [i-1];				
			pc[i-1] = pc[i];
			pc[i] = temp;
					
			//set new created node as child to its parent
			Node child = new Node(pc);
			
			if(!tempSearch.IsFrontier(child) && !tempSearch.IsExplored(child)){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Manhattan){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Euclidean){
				this.children.add(child);
				child.parent = this;
			}
					
		}
		
	}

	private void MoveTop(int i){
		
		if(i - col >= 0){
			
			int [] pc = new int[9];
			System.arraycopy(this.puzzle,0,pc,0, 9);
			
			//swapping
			int temp = pc [i-3];
			pc[i-3] = pc[i];
			pc[i] = temp;
			
			//set new created node as child to its parent
			Node child = new Node(pc);

			if(!tempSearch.IsFrontier(child) && !tempSearch.IsExplored(child)){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Manhattan){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Euclidean){
				this.children.add(child);
				child.parent = this;
			}
		}
		
	}

	private void MoveDown(int i){
			
		if(i + col < getPuzzle().length){
			
			int [] pc = new int[9];
			System.arraycopy(this.puzzle,0,pc,0, 9);
			
			//swapping
			int temp = pc [i+3];
			pc[i+3] = pc[i];
			pc[i] = temp;
			
			//set new created node as child to its parent
			Node child = new Node(pc);
			
			if(!tempSearch.IsFrontier(child) && !tempSearch.IsExplored(child)){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Manhattan){
				this.children.add(child);
				child.parent = this;
			}
			else if( tempSearch.IsFrontier(child) && tempSearch instanceof AStarSearch_Euclidean){
				this.children.add(child);
				child.parent = this;
			}
			
		}
		
	}

	public  int [] getPuzzle() {
		return puzzle;
	}


	public  void setPuzzle(int [] puzzle) {
		this.puzzle = puzzle;
	}

}
