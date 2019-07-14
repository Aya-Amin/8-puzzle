package ui;

import java.io.FileNotFoundException;

import ds.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import searchAlgos.AStarSearch_Euclidean;
import searchAlgos.AStarSearch_Manhattan;
import searchAlgos.AbstractSearch;
import searchAlgos.BFSsearch;
import searchAlgos.DFSsearch;

public class GoalPath extends PuzzleHome{
	
	static Scene display(AbstractSearch s) throws FileNotFoundException{
		
		
		if(s instanceof BFSsearch){	
			
			s = (BFSsearch) s;
		
		}else if(s instanceof DFSsearch){
			
			s = (DFSsearch) s;
			
		}else if(s instanceof AStarSearch_Euclidean){
			
			s = (AStarSearch_Euclidean) s;
			
		}else{
			
			s = (AStarSearch_Manhattan) s;
		}
		
		
		ScrollPane scPane = new ScrollPane();
		
	
		VBox vPane = new VBox();
		vPane.setSpacing(10);
		
		StackPane temp = new StackPane();
		
		for(Node e : s.getSolList()){
			
			temp = DrawPuzzle(e.getPuzzle());
			
			vPane.getChildren().add(temp);
				
		}
		
		
		scPane.setContent(vPane);
		scPane.setPannable(true);
		
		
		Scene goalScene= new Scene(scPane, 200,500);
		
		return goalScene;
		
		
	}
}
