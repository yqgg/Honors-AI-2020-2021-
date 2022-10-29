package application;

import application.Grid.Node;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gridPathMain extends Application {
	private boolean status;
	private boolean uChosen;
	private boolean gChosen;
	private boolean aChosen;

	// http://zetcode.com/gui/javafx/animation/
	@Override
	public void start(Stage primaryStage) {

		Pane root = new Pane();
		Scene scene = new Scene(root, 850, 850);

		Grid grid = new Grid(20, 20);

		Rectangle rec = new Rectangle(850, 850);
		rec.setFill(Color.DARKGRAY);
		root.getChildren().add(rec);

		for (int r = 0; r < 20; r++) {
			for (int c = 0; c < 20; c++) {
				if (Grid.grid[r][c].getType() == -1) {
					Text wall = new Text("#");
					wall.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					wall.setX(c * 40 + 40);
					wall.setY(r * 40 + 40);
					root.getChildren().add(wall);
				} else if (grid.getGrid(r, c) == -2) {
					Text player = new Text("P");
					player.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
					player.setX(c * 40 + 40);
					player.setY(r * 40 + 40);
					root.getChildren().add(player);
				} else if (grid.getGrid(r, c) == -3) {
					Text goal = new Text("G");
					goal.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
					goal.setX(c * 40 + 40);
					goal.setY(r * 40 + 40);
					root.getChildren().add(goal);
				}
			}
		}

		Rectangle r1 = new Rectangle(110, 300, 150, 60);
		Text t1 = new Text("UCS");
		t1.setX(150);
		t1.setY(342);
		t1.setFont(new Font("OCR A Extended", 39));
		t1.setFill(Color.WHITE);
		t1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				uChosen = !uChosen;
				if (uChosen == true) {
					rec.setFill(Color.rgb(237, 226, 190));
					r1.setFill(Color.GOLD);
				} else {
					rec.setFill(Color.DARKGRAY);
					r1.setFill(Color.BLACK);
				}
			}
		});

		Rectangle r2 = new Rectangle(350, 300, 150, 60);
		Text t2 = new Text("Greedy");
		t2.setX(360);
		t2.setY(341);
		t2.setFont(new Font("OCR A Extended", 37));
		t2.setFill(Color.WHITE);
		t2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				gChosen = !gChosen;
				if (gChosen == true) {
					rec.setFill(Color.rgb(237, 190, 190));
					r2.setFill(Color.RED);
				} else {

					rec.setFill(Color.DARKGRAY);
					r2.setFill(Color.BLACK);
				}
			}
		});

		Rectangle r3 = new Rectangle(590, 300, 150, 60);
		Text t3 = new Text("A-Star");
		t3.setX(600);
		t3.setY(341);
		t3.setFont(new Font("OCR A Extended", 37));
		t3.setFill(Color.WHITE);
		t3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				aChosen = !aChosen;
				if (aChosen == true) {
					rec.setFill(Color.rgb(223, 206, 240));
					r3.setFill(Color.PURPLE);
				} else {
					rec.setFill(Color.DARKGRAY);
					r3.setFill(Color.BLACK);
				}
			}
		});
		root.getChildren().addAll(r1, t1, r2, t2, r3, t3);

		// Insert image:
		// https://www.youtube.com/watch?v=F8bY18QAj6U&ab_channel=ProjectRin
		Image image = new Image("file:playButton.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		iv.setPreserveRatio(true);
		iv.setFitHeight(300);
		iv.setX(300);
		iv.setY(400);
		root.getChildren().add(iv);

		iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// Scale Transition:
				// https://docs.oracle.com/javafx/2/api/javafx/animation/ScaleTransition.html

				ScaleTransition st = new ScaleTransition(Duration.millis(1000), iv);
				st.setByX(-1);
				st.setByY(-1);
				st.setCycleCount(1);
				st.play();
				ScaleTransition st1 = new ScaleTransition(Duration.millis(1000), r1);
				st1.setByX(-1);
				st1.setByY(-1);
				st1.setCycleCount(1);
				st1.play();
				ScaleTransition st2 = new ScaleTransition(Duration.millis(1000), r2);
				st2.setByX(-1);
				st2.setByY(-1);
				st2.setCycleCount(1);
				st2.play();
				ScaleTransition st3 = new ScaleTransition(Duration.millis(1000), r3);
				st3.setByX(-1);
				st3.setByY(-1);
				st3.setCycleCount(1);
				st3.play();

				root.getChildren().removeAll(t1, t2, t3);
				status = !status;
			}
		});

		// PathTransition:
		// https://www.youtube.com/watch?v=xwWARVJB5g0&ab_channel=GenuineCoder

		System.out.println("VISITED NODES: ");
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				search s = new search();
				if (uChosen == true) {
					s.uniform();
					if (status == true && s.getFound() == true) {
						for (int i = 1; i < s.getVisited().size(); i++) {
							Text player = new Text("*");
							player.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
							Line line = new Line(s.getVisited().get(i - 1).getC() * 40 + 50,
									s.getVisited().get(i - 1).getR() * 40 + 30, s.getVisited().get(i).getC() * 40 + 50,
									s.getVisited().get(i).getR() * 40 + 30);
							line.setFill(Color.WHITE);
							PathTransition ptr = new PathTransition();
							ptr.setNode(player);
							ptr.setDuration(Duration.seconds(2));
							ptr.setPath(line);
							ptr.setCycleCount(PathTransition.INDEFINITE);
							ptr.play();
							root.getChildren().add(player);
						}
						uChosen = false;
					}
				} else if (gChosen == true) {
					s.greedy();
					if (status == true && s.getFound() == true) {
						for (int i = 1; i < s.getVisited().size(); i++) {
							Text player = new Text("*");
							player.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
							Line line = new Line(s.getVisited().get(i - 1).getC() * 40 + 50,
									s.getVisited().get(i - 1).getR() * 40 + 30, s.getVisited().get(i).getC() * 40 + 50,
									s.getVisited().get(i).getR() * 40 + 30);
							line.setFill(Color.WHITE);
							PathTransition ptr = new PathTransition();
							ptr.setNode(player);
							ptr.setDuration(Duration.seconds(2));
							ptr.setPath(line);
							ptr.setCycleCount(PathTransition.INDEFINITE);
							ptr.play();
							root.getChildren().add(player);
						}
						gChosen = false;
					}
				} else if (aChosen == true) {
					s.aStar();
					if (status == true && s.getFound() == true) {
						for (int i = 1; i < s.getVisited().size(); i++) {
							Text player = new Text("*");
							player.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
							Line line = new Line(s.getVisited().get(i - 1).getC() * 40 + 50,
									s.getVisited().get(i - 1).getR() * 40 + 30, s.getVisited().get(i).getC() * 40 + 50,
									s.getVisited().get(i).getR() * 40 + 30);
							line.setFill(Color.WHITE);
							PathTransition ptr = new PathTransition();
							ptr.setNode(player);
							ptr.setDuration(Duration.seconds(2));
							ptr.setPath(line);
							ptr.setCycleCount(PathTransition.INDEFINITE);
							ptr.play();
							root.getChildren().add(player);
						}
						aChosen = false;
					}
				}
			}
		};
		timer.start();

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
