package ui;

import java.io.FileNotFoundException;





import searchAlgos.AStarSearch_Manhattan;
import searchAlgos.AbstractSearch;
import searchAlgos.BFSsearch;
import searchAlgos.DFSsearch;
import searchAlgos.AStarSearch_Euclidean;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PuzzleResult extends PuzzleHome {
	
	static Scene display() throws FileNotFoundException{
		
		Text title = new Text();
		
		if(s instanceof BFSsearch){
			
			title.setText("BFS SEARCH");	
			
			s = (BFSsearch) s;
		
		}else if(s instanceof DFSsearch){
			
			title.setText("DFS SEARCH");
			
			s = (DFSsearch) s;
			
		}else if(s instanceof AStarSearch_Euclidean){
			
			title.setText("A*-EUCLIDEAN SEARCH");
			
			s = (AStarSearch_Euclidean) s;
			
		}else{
			
			title.setText("A*-MANHATTAN SEARCH");
			
			s = (AStarSearch_Manhattan) s;
			
		}
		
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		BorderPane.setAlignment(title, Pos.CENTER);
		
		long st = System.currentTimeMillis();
		
		Label stateLabel = new Label("executing...");
		Label costLabel = new Label();
		Label nodeExpandedLabel = new Label();
		Label searchDepthLabel = new Label();
		Label runningTimeLabel= new Label();
		
		
		if(s.Search()){
		
			long et = System.currentTimeMillis();		
			
			stateLabel.setText("Search Succeed!");
			
			costLabel.setText("Cost Of Path: "+ s.getCost());
			
			nodeExpandedLabel.setText("Expanded Nodes: "+ s.getExpandedNodes());
			
			searchDepthLabel.setText("Search Depth: "+s.getCost());
			
			runningTimeLabel.setText("Running Time: "+ (et-st)/1000+" sec");
				
				
		}else{
			
			stateLabel.setText("Search Failed!");
		
		}
		
		Button goalPathBtn = new Button("Goal Path");
		goalPathBtn.setOnAction(e -> {
					try {
						  Stage stage = new Stage();
				            stage.setTitle("Goal Path");
				            stage.setScene(GoalPath.display(s));
				            stage.show();
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}

		});
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> {
			try {
				Test8Puzzle.primaryStage.setScene(PuzzleHome.display());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
			
		
		
		
		
			
		BorderPane pane = new BorderPane();
		pane.setTop(title);
		
		VBox child = new VBox(goalPathBtn,stateLabel,costLabel,nodeExpandedLabel,searchDepthLabel,runningTimeLabel);
		child.setAlignment(Pos.CENTER);
		pane.setCenter(child);
		
		backBtn.setAlignment(Pos.CENTER);
		backBtn.setPadding(new Insets(10));
		pane.setBottom(backBtn);
		
		
		Scene homeScene= new Scene(pane, 350, 350);
		
		return homeScene;
		
	}
	
	
}
