package application;
	
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;
//Eyas Shalhoub 1201681
//Yaqeen issa 1201576
public class Main extends Application {
	
	int cur=0;
	List<State> ans;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox hb = new HBox(15);
			State initialState = new State (3, 3, Position.LEFT, 0, 0);	
			Pane root = new Pane();
			HBox choice = new HBox(10);
			VBox menu = new VBox(10);
			Scene scene = new Scene(root,1920,1080);

			Label title = new Label("Missionaries and Cannibals");
			title.setStyle("-fx-text-fill: white; -fx-font-size: 50px;");
			Label Guide = new Label(" Chose the algorithm you want to use");
			Guide.setStyle("-fx-text-fill: white; -fx-font-size: 30px;");
			Button bfs = new Button("Breadth First Search");
			Button dfs = new Button("Depth First Search");
			
			choice.getChildren().addAll(bfs,dfs);
			Label description = new Label("Three missionaries and three cannibals must cross a river using a boat which can carry at most two people,"
					+ " under the constraint that, for both banks, if there are missionaries present on the bank,"
					+ " they cannot be outnumbered by cannibals (if they were, the cannibals would eat the missionaries)."
					+ " The boat cannot cross the river by itself with no people on board. And, in some variations,"
					+ " one of the cannibals has only one arm and cannot row. The task is to get everyone to the other side,"
					+ " with no missionaries being eaten.");

			description.setStyle("-fx-text-fill: black; -fx-font-size: 15px;");
			description.setWrapText(true);
			description.setMaxWidth(500);
			menu.getChildren().addAll(title,description,Guide,choice);

			choice.setBackground(new Background( new BackgroundFill(Color.SLATEGREY, null, null)));
			choice.setAlignment(Pos.CENTER);
			choice.setPadding(new Insets(0,0,0,0));
			menu.setPadding(new Insets(0,0,0,0));
			menu.setBackground(new Background( new BackgroundFill(Color.SLATEGREY, null, null)));
			menu.setAlignment(Pos.CENTER);
			
			Scene scene2 = new Scene(menu,1920,1080);
			
			Label Rightc=new Label();
			Label Rightm=new Label();
			Label leftc=new Label();
			Label leftm=new Label();
			
			leftc.setText("Click on the boat to start the game");
			leftm.setText("Click on the boat to procced to the next step");
			Rightc.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
			Rightm.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
			leftc.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
			leftm.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
			
			Rightc.setTranslateX(1300);
			Rightc.setTranslateY(200);
			Rightm.setTranslateX(1300);
			Rightm.setTranslateY(250);
			leftc.setTranslateX(100);
			leftc.setTranslateY(200);
			leftm.setTranslateX(100);
			leftm.setTranslateY(250);

			root.getChildren().add(Rightc);
			root.getChildren().add(Rightm);
			root.getChildren().add(leftc);
			root.getChildren().add(leftm);			
			
			bfs.setOnAction(e -> {
				ans =  executeBFS(initialState);
				Rightc.setText("Breadth First Search");
				primaryStage.setScene(scene);
				primaryStage.show();
			});

			dfs.setOnAction(e -> {
				ans =  executeDFS(initialState);
				Rightc.setText("Depth First Search");
				primaryStage.setScene(scene);
				primaryStage.show();
			});
			
			Line L = new Line();

			L.setStartX(00);
			L.setStartY(600);
			L.setEndY(600);
			L.setEndX(1700);
			
			StackPane sp = new StackPane();
			sp.setLayoutX(100);
			sp.setLayoutY(100);
			sp.setAlignment(Pos.CENTER);
			sp.setPadding(new Insets(0,0,0,0));
			
			Image boat = new Image("file:src\\application\\boat1.png");
			
			ImageView ivb = new ImageView(boat);
			ivb.setFitWidth(180);
			ivb.setFitHeight(120);
			
			Image canibal = new Image("file:src\\application\\canibal.png");
			
			ImageView ivc = new ImageView(canibal);
			ivc.setFitWidth(50);
			ivc.setFitHeight(65);	
			
			Image mi = new Image("file:src\\application\\mi.png");
			
			ImageView ivm = new ImageView(mi);
			ivm.setFitWidth(50);
			ivm.setFitHeight(65);
			
			hb.getChildren().addAll(ivm,ivc);			

			ImageView[] missionaries = new ImageView[3];
			ImageView[] cannibals = new ImageView[3];
			for(int i=0;i<3;i++) {
				missionaries[i] = new ImageView(mi);
				cannibals[i] = new ImageView(canibal);
			}
			for(int i=0;i<3;i++) {
				
					missionaries[i].setTranslateX(100+i*30);
					missionaries[i].setTranslateY(600);
					cannibals[i].setTranslateX(100+i*30);
					cannibals[i].setTranslateY(650);
			}

			for(int i=0;i<3;i++) {
				missionaries[i].setFitWidth(50);
				missionaries[i].setFitHeight(65);
				root.getChildren().addAll(missionaries[i]);
			}
			
			for(int i=0;i<3;i++) {
				cannibals[i].setFitWidth(50);
				cannibals[i].setFitHeight(65);
				root.getChildren().addAll(cannibals[i]);
			}

			PathTransition t= new PathTransition();
			t.setNode(sp);
			t.setDuration((Duration.millis(1500)));
			t.setPath(L);
			t.setCycleCount(1);
			t.setRate(1);

			hb.setOnMouseClicked(e -> {
			    if (ans!=null && cur < ans.size()) {
			        Double g = t.getRate() * -1;
			        t.setRate(g);
			        t.play();
			        State temp = ans.get(cur);
			        State prev ;
			        if(cur>0) {
			        	 prev = ans.get(cur - 1);}
			        else {
			        	 prev = ans.get(cur);}

			        hb.getChildren().clear();

			        // Calculate the differences in missionaries and cannibals
			        int mDiff = prev.getMissionaryLeft() - temp.getMissionaryLeft();
			        int cDiff = prev.getCannibalLeft() - temp.getCannibalLeft();

			        // Add the appropriate number of missionaries to the boat
			        for (int i = 0; i < Math.abs(mDiff); i++) {
						ImageView r = new ImageView(mi);
						r.setFitWidth(50);
						r.setFitHeight(65);
						r.setId("mi");
						hb.getChildren().add(r);
						
			        }

			        // Add the appropriate number of cannibals to the boat
			        for (int i = 0; i < Math.abs(cDiff); i++) {
						ImageView r = new ImageView(canibal);
						r.setFitWidth(50);
						r.setFitHeight(65);
						r.setId("canibal");
			            hb.getChildren().add(r);
			        }

					for(int i=0;i<3;i++) {
						if(prev.getMissionaryLeft()<i+1) {
							missionaries[i].setTranslateX(100+i*30);
							missionaries[i].setTranslateY(600);
						}else {							
							missionaries[i].setTranslateX(1700+i*30);
							missionaries[i].setTranslateY(600);
						}

					}
					
					for(int i=0;i<3;i++) {
						if(prev.getCannibalLeft()<i+1) {
							cannibals[i].setTranslateX(100+i*30);
							cannibals[i].setTranslateY(650);
						}else {
							cannibals[i].setTranslateX(1700+i*30);
							cannibals[i].setTranslateY(650);
						}

					}
					
					for(int i=0;hb.getChildren().size()>i;i++) {
						if(hb.getChildren().get(i).getId().equals("canibal")) {
							cannibals[i].setTranslateX(2000);
						}else {
							missionaries[i].setTranslateX(2000);
						}			
					}
					
			        Rightm.setText("Current Step: " + cur);
			        cur++;
			   
					t.onFinishedProperty().set(s->{
						for(int i=0;i<3;i++) {
							if(temp.getMissionaryLeft()<i+1) {
								missionaries[i].setTranslateX(100+i*30);
								missionaries[i].setTranslateY(600);
							}else {
								missionaries[i].setTranslateX(1700+i*30);
								missionaries[i].setTranslateY(600);
							}

						}
						for(int i=0;i<3;i++) {
							
							if(temp.getCannibalLeft()<i+1) {
								cannibals[i].setTranslateX(100+i*30);
								cannibals[i].setTranslateY(650);
							}else {
								cannibals[i].setTranslateX(1700+i*30);
								cannibals[i].setTranslateY(650);
							}

						}
					
						hb.getChildren().clear();
						
					     if(cur==ans.size()){
						        leftc.setText("The Animiation has finished and the problem was solved!");
						        leftm.setText("Good Bye");
						     	}
					});
			    }
			});
			
			sp.getChildren().add(0,ivb);
			sp.getChildren().add(1,hb);
			hb.setPadding(new Insets(0,0,0,0));
			root.getChildren().add(sp);
			root.setPadding(new Insets(0,0,0,0));
			
			root.setBackground(new Background( new BackgroundImage(new Image("file:src\\application\\riverhd.jpg",1920,1200,false,false),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT)));
			Image icon = new Image("file:src\\application\\icon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Missionaries and Cannibals");
			primaryStage.setMaximized(true);
			primaryStage.setScene(scene2);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	

	private static List<State> executeBFS(State initialState) {
		BreadthFirstSearch search = new BreadthFirstSearch();
		State solution = search.exec(initialState);
		return printSolution(solution);
	}

	private static List<State> executeDFS(State initialState) {
		DepthFirstSearch search = new DepthFirstSearch();
		State solution = search.exec(initialState);
		return printSolution(solution);
	}

	private static List<State> printSolution(State solution) {
		
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

//			int depth = path.size() - 1;
//			for (int i = depth; i >= 0; i--) {
//				state = path.get(i);
//				if (state.isGoal()) {
//					System.out.print(state.toString());
//				} else {
//					System.out.print(state.toString() + " -> ");
//				}
//			}
//			System.out.println("\nDepth: " + depth);
			return path;
		}
		return null;
	}
}