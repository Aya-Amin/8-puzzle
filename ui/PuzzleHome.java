package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



import ds.Node;
import searchAlgos.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PuzzleHome {
	
	static AbstractSearch s;
	static int[] p = {0,1,2,3,4,5,6,7,8,9};
	static StackPane puzzlePane;
	
	static Scene display() throws FileNotFoundException{
		
		Text title = new Text("8 PUZZLE");
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		BorderPane.setAlignment(title, Pos.CENTER);
		
		
		TextField puzzle = new TextField();
		puzzle.setPromptText("Enter your Puzzle..");
		puzzle.setPrefColumnCount(10);
		
		BorderPane pane = new BorderPane();
		pane.setTop(title);
		
		
		
		Button enter = new Button("Enter");
		HBox input = new HBox(puzzle,enter);
		input.setAlignment(Pos.CENTER);
		
		puzzlePane = DrawPuzzle(p);
		
		enter.setOnAction(e -> {
			if(puzzle.getText() != null){
				
				String[] temp = puzzle.getText().split(","); 
				
				for (int i=0; i<temp.length; i++){
					
					p[i] = Integer.parseInt(temp[i]);
				}	
				
				try {
					puzzlePane = DrawPuzzle(p);
					
					VBox center = new VBox(input,puzzlePane);
					center.setAlignment(Pos.CENTER);
					center.setSpacing(30);
					pane.setCenter(center);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		Button bfsBtn= new Button("BFS");
		bfsBtn.setOnAction(e -> {
			
			s = new BFSsearch(new Node(p));
			
			try {
				Test8Puzzle.primaryStage.setScene(PuzzleResult.display());
			} catch (Exception e1) {
				e1.printStackTrace();
			}	

		});
		
		Button dfsBtn= new Button("DFS");
		dfsBtn.setOnAction(e -> {
			
			s = new DFSsearch(new Node(p));
			
			try {
				Test8Puzzle.primaryStage.setScene(PuzzleResult.display());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		
		Button AStarEuclidianBtn= new Button("A* Euc.");
		AStarEuclidianBtn.setOnAction(e -> {
			
			s = new AStarSearch_Euclidean(new Node(p));
			
			try {
				Test8Puzzle.primaryStage.setScene(PuzzleResult.display());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		
		Button AStarManhatanBtn= new Button("A* Man.");
		AStarManhatanBtn.setOnAction(e -> {
			
			s = new AStarSearch_Manhattan(new Node(p));
			
			try {
				Test8Puzzle.primaryStage.setScene(PuzzleResult.display());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		
		
		

//		puzzlePane.setAlignment(Pos.CENTER);
		
		VBox center = new VBox(input,puzzlePane);
		center.setAlignment(Pos.CENTER);
		center.setSpacing(30);
		pane.setCenter(center);
		
		HBox child = new HBox(bfsBtn,dfsBtn,AStarEuclidianBtn,AStarManhatanBtn);
		child.setAlignment(Pos.CENTER);
		child.setSpacing(20);
		child.setPadding(new Insets(10));
		pane.setBottom(child);
		
		Scene homeScene= new Scene(pane, 350, 350);
		
		return homeScene;
	}
	
	
	protected static StackPane DrawPuzzle(int[] p) throws FileNotFoundException{
		
		GridPane pane = new GridPane();
		int m= 0;
		for(int i=0; i < 3; i++){
			for(int j=0; j < 3; j++){
				
				pane.add(new ImageView(new Image(new FileInputStream("imgs\\"+p[m]+".png"))), j, i); 
				
				m++;
			}
		}
		pane.setGridLinesVisible(true);
		pane.setAlignment(Pos.CENTER);
		
		return new StackPane(new ImageView(new Image(new FileInputStream("imgs\\board.png"))),pane);
	}
	
	
}
