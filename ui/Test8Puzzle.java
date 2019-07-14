package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import searchAlgos.BFSsearch;
import searchAlgos.DFSsearch;
import ds.Node;

public class Test8Puzzle extends Application{

	public static void main(String[] args) {
        
		 Application.launch(args);
		 
	    }
	 
	 static Stage primaryStage = new Stage();
	@Override
	public void start(Stage stage) throws Exception {
		
		
		int [] puzzle0 = {
							1,4,2,
							3,0,5,
							6,7,8
		};
		
		int [] puzzle = {
						1,2,5,
						3,4,0,
						6,7,8
					};
		
		int [] puzzle1 = {
							6,1,8,
							4,0,2,
							7,3,5
						};
		
		int [] puzzle2 = {
							8,6,4,
							2,1,3,
							5,7,0
						 };
		
		primaryStage.setScene(PuzzleHome.display());
		
		primaryStage.show();
	
	}
	
}
