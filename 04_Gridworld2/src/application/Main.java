 

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	private boolean status;
	private Agent[] agents;
	private int agentsLeft;

	@Override
	public void start(Stage primaryStage) {
		try {
			initUI(primaryStage);
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private void initUI(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 870);
		Gridworld gw = Gridworld.getInstance();

		// Draw maze
		for (int r = 0; r < gw.getGridLength(); r++) {
			for (int c = 0; c < gw.getGridWidth(r); c++) {
				root.getChildren().add(gw.draw(r, c));
			}
		}

		// Start button graphics
		root.getChildren().add(gw.drawMenuBar());
		Rectangle rectangle = new Rectangle(85, 730, 250, 100);
		rectangle.setFill(Color.rgb(104, 159, 163));
		rectangle.setArcWidth(30);
		rectangle.setArcHeight(30);
		root.getChildren().add(rectangle);
		Rectangle rectangle2 = new Rectangle(85, 730, 225, 75);
		rectangle2.setFill(Color.rgb(215, 243, 245));
		rectangle2.setArcWidth(30);
		rectangle2.setArcHeight(30);
		root.getChildren().add(rectangle2);
		Text t = new Text("START");
		t.setFont(Font.font("OCR A Extended", 50));
		t.setX(125);
		t.setY(780);
		t.setFill(Color.rgb(104, 159, 163));
		root.getChildren().add(t);
		Text t2 = new Text("Searching for exit...");
		t2.setFont(Font.font("OCR A Extended", 40));
		t2.setX(240);
		t2.setY(770);
		t2.setFill(Color.rgb(104, 159, 163));
		Text t3 = new Text("Exit found. Victory!");
		t3.setFont(Font.font("OCR A Extended", 40));
		t3.setX(240);
		t3.setY(770);
		t3.setFill(Color.rgb(104, 159, 163));

		rectangle2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				status = !status;
				root.getChildren().add(gw.drawMenuBar());
				root.getChildren().add(t2);
			}
		});

		agents = new Agent[2];
		agentsLeft = agents.length;

		Agent a = new Agent();
		agents[0] = a;
		agents[0].setLocation(1, 1);
		root.getChildren().add(agents[0].drawAgent());

		Agent b = new Agent();
		agents[1] = b;
		agents[1].setLocation(10, 14);
		root.getChildren().add(agents[1].drawAgent());

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (status == true) {
					for (int i = 0; i < agents.length; i++) {
						try {
							agents[i].sense();
							System.out.println(agents[i].decide());
							if (agents[i].decide().equals("End")) {
								agents[i].act();
								root.getChildren().remove(agents[i].drawAgent());
								agentsLeft-=1;
								if(agentsLeft == 0) {
									root.getChildren().add(gw.drawMenuBar());
									root.getChildren().add(t3);
									super.stop();
								}
							}
							agents[i].decide();
							agents[i].act();
							root.getChildren().add(agents[i].drawAgent());

						} catch (Exception e) {
							root.getChildren().remove(agents[i].drawAgent());
						}
					}
				}
			}
		};
		timer.start();

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Grid World");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
