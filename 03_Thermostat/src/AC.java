
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AC extends Application {
	private int acTemp;
	private boolean onOff;
	private Circle c2;
	private Circle c3;
	private Text t6;

	public AC() {
		acTemp = 24;
	}

	public int getACTemp() {
		return acTemp;
	}

	public void turnOn(Circle face, Circle onOffButton) {
		onOff = true;
		face.setFill(Color.rgb(140, 180, 245));
		onOffButton.setFill(Color.GREEN);
	}

	public void turnOff(Circle face, Circle onOffButton) {
		onOff = false;
		face.setFill(Color.rgb(84, 107, 143));
		onOffButton.setFill(Color.RED);
	}

	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 800, 800);

		// Structure of thermostat
		Circle c1 = new Circle(400, 400, 400); //black border of thermostat
		c2 = new Circle(400, 400, 370); //blue off screen of thermostat
		c2.setFill(Color.rgb(84, 107, 143));
		root.getChildren().add(c1);
		root.getChildren().add(c2);

		Text text = new Text("OUTSIDE");
		text.setFont(Font.font("Verdana", 20));
		text.setX(200);
		text.setY(530);
		root.getChildren().add(text);

		Text text1 = new Text("INSIDE");
		text1.setFont(Font.font("Verdana", 20));
		text1.setX(500);
		text1.setY(530);
		root.getChildren().add(text1);

		// Add in temperatures
		// AC Temp
		Text t5 = new Text(Integer.toString(getACTemp()) + "°" + "C");
		t5.setFont(Font.font("OCR A Extended", 125));
		t5.setX(190);
		t5.setY(365);
		root.getChildren().add(t5);

		// Inside temp
		Environment e = Environment.getInstance();
		t6 = new Text(Double.toString(e.getEnvironmentTemp()) + "°" + "C");
		t6.setFont(Font.font("OCR A Extended", 60));
		t6.setX(450);
		t6.setY(600);
		root.getChildren().add(t6);

		// Outside temp
		Text t7 = new Text(Integer.toString(e.getOutTemp()) + "°" + "C");
		t7.setFont(Font.font("OCR A Extended", 60));
		t7.setX(170);
		t7.setY(600);
		root.getChildren().add(t7);

		// On and Off button
		c3 = new Circle(400, 100, 20);
		c3.setFill(Color.RED);
		root.getChildren().add(c3);

		// Manually turn AC on and off using button
		c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				onOff = !onOff;
				if (onOff == true) {
					turnOn(c2, c3);
				} else {
					turnOff(c2, c3);
				}
			}
		});

		// Learned about animationTimer here:
		// https://stackoverflow.com/questions/50337303/how-do-i-change-the-speed-of-an-animationtimer-in-javafx
		Agent a = new Agent();
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (onOff == true) {
					a.sense();
					a.decide(onOff);
					t6.setText(Double.toString(a.act()) + "°" + "C");
				} else {
					a.sense();
					a.decide(onOff);
					t6.setText(Double.toString(a.act()) + "°" + "C");
				}
			}
		};
		timer.start();

		// These buttons don't do anything
		Rectangle plus = new Rectangle(520, 250, 50, 50);
		plus.setFill(Color.BLACK);
		root.getChildren().add(plus);
		Text text3 = new Text("+");
		text3.setFont(Font.font("Verdana", 40));
		text3.setFill(Color.WHITE);
		text3.setX(530);
		text3.setY(290);
		root.getChildren().add(text3);
		Rectangle minus = new Rectangle(520, 350, 50, 50);
		minus.setFill(Color.BLACK);
		root.getChildren().add(minus);
		Text text4 = new Text("-");
		text4.setFill(Color.WHITE);
		text4.setFont(Font.font("Verdana", 50));
		text4.setX(535);
		text4.setY(390);
		root.getChildren().add(text4);

		primaryStage.setTitle("Thermostat");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
}
