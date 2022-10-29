 

import java.util.List;

import application.weightedGraph.Graph;
import application.weightedGraph.Node;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class pathfindingMain extends Application {
	static weightedGraph.Graph m;

	@Override
	public void start(Stage primaryStage) {
		try {
			initUI(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Help with VBox -->	https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm#CHDGHCDG
	// Help with scroll bar --> https://www.youtube.com/watch?v=uioL-R7Nh9o&ab_channel=GeniusCoders
	// Help with TextField --> https://www.youtube.com/watch?v=TfXGGLZ0yPA&ab_channel=ParwizForogh
	private void initUI(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setRight(addVBox());
		// HBox hBox = new HBox(5);
		// Pane hBox = new HBox();
		Scene scene = new Scene(root, 1665, 1000);
		Image image = new Image("file:Singapore mrt.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		// iv.setPreserveRatio(true);
		// iv.setFitHeight(1000);
		root.getChildren().add(iv);
	
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Singapore MRT System Pathfinding");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox addVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(10);

		Text title = new Text("Find path: ");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
		vbox.getChildren().add(title);

		TextField start = new TextField("Boarding Station");
		start.setMinWidth(100);
		start.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 30));
		TextField des = new TextField("Alighting Station");
		des.setMinWidth(100);
		des.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 30));
		
		ScrollPane sc = new ScrollPane();

		Button b = new Button("Uniform Cost Search");
		b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				uniformCostSearch u = new uniformCostSearch(start.getText(), des.getText(), m);
				u.uSearch();
				List<Node> path = u.printPath(des.getText());
				String s = "Uniform Cost Search: \n";
				for (Node n : path) {
					s += n.nodeToString() + "\n";
				}
				Text t = new Text(s);
				t.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 23));
				sc.setContent(t);
				vbox.getChildren().addAll(t, sc);

			}
		});
		Button b2 = new Button("Greedy Search");
		b2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				greedySearch g = new greedySearch(start.getText(), des.getText(), m);
				g.gSearch();
				List<Node> path2 = g.printPath(des.getText());
				String s2 = "Greedy Search: \n";
				for (Node n : path2) {
					s2 += n.nodeToString() + "\n";
				}
				Text t2 = new Text(s2);
				t2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 23));
				sc.setContent(t2);
				vbox.getChildren().addAll(t2, sc);
			}
		});
		Button b3 = new Button("A*");
		b3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				aStarSearch a = new aStarSearch(start.getText(), des.getText(), m);
				a.aSearch();
				List<Node> path3 = a.printPath(des.getText());
				String s3 = "A* Search: \n";
				for (Node n : path3) {
					s3 += n.nodeToString() + "\n";
				}
				Text t3 = new Text(s3);
				t3.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 23));
				sc.setContent(t3);
				vbox.getChildren().addAll(t3, sc);
			}
		});

		vbox.getChildren().addAll(start, des, b, b2, b3);
		return vbox;
	}

	public static void main(String[] args) {

		// Singapore MRT map
		weightedGraph g = new weightedGraph();
		m = g.new Graph();
		// purple line
		m.addNode("Punggol", 329, 111); // Home
		m.addNode("Sengkang", 309, 134);
		m.addNode("Buangkok", 299, 143);
		m.addNode("Hougang", 290, 152);
		m.addNode("Kovan", 282, 161);
		m.addNode("Serangoon", 269, 173);
		m.addNode("Woodleigh", 156, 185);
		m.addNode("Potong Pasir", 243, 199);
		m.addNode("Boon Keng", 236, 212);
		m.addNode("Farrer Park", 236, 226);
		m.addNode("Little India", 236, 240);
		m.addNode("Dhoby Ghaut", 224, 268);
		m.addNode("Clarke Quay", 215, 283);
		m.addNode("Chinatown", 208, 291);
		m.addNode("Outram Park", 197, 303);
		m.addNode("HarbourFront", 190, 339); // common location 1
		// yellow line
		m.addNode("Telok Blangah", 171, 327);
		m.addNode("Labrador Park", 159, 318);
		m.addNode("Pasir Panjang", 149, 307);
		m.addNode("Haw Par Villa", 144, 294);
		m.addNode("Kent Ridge", 139, 281);
		m.addNode("one-north", 136, 268);
		m.addNode("Buona Vista", 134, 254);
		m.addNode("Holland Village", 137, 235);
		m.addNode("Farrer Road", 142, 219);
		m.addNode("Botanic Gardens", 152, 199);
		m.addNode("Caldecott", 179, 176);
		m.addNode("Marymount", 197, 167);
		m.addNode("Bishan", 222, 162);
		m.addNode("Lorong Chuan", 248, 164);
		m.addNode("Bartley", 290, 186);
		m.addNode("Tai Seng", 301, 198);
		m.addNode("MacPherson", 312, 215);
		m.addNode("Paya Lebar", 318, 236);
		m.addNode("Dakota", 320, 254);
		m.addNode("Mountbatten", 319, 267);
		m.addNode("Stadium", 316, 282);
		m.addNode("Nicoll Highway", 311, 295);
		m.addNode("Promenade", 304, 310);
		m.addNode("Bayfront", 282, 327);
		m.addNode("Marina Bay", 259, 338);
		m.addNode("Esplanade", 273, 303);
		m.addNode("Bras Basah", 253, 280);
		// Red Line
		m.addNode("Jurong East", 82, 254);
		m.addNode("Bukit Batok", 81, 226);
		m.addNode("Bukit Gombak", 81, 205);
		m.addNode("Choa Chu Kang", 82, 179);
		m.addNode("Yew Tee", 82, 153);
		m.addNode("Kranji", 82, 131);
		m.addNode("Marsiling", 96, 103);
		m.addNode("Woodlands", 129, 98);
		m.addNode("Admiralty", 150, 98);
		m.addNode("Sembawang", 214, 86);
		m.addNode("Canberra", 208, 103);
		m.addNode("Yishun", 219, 116);
		m.addNode("Khatib", 222, 131);
		m.addNode("Yio Chu Kang", 221, 146);
		m.addNode("Ang Mo Kio", 221, 162);
		m.addNode("Braddell", 221, 171);
		m.addNode("Toa Payoh", 221, 186);// common location 2
		m.addNode("Novena", 213, 202);
		m.addNode("Newton", 200, 214);
		m.addNode("Orchard", 188, 234);
		m.addNode("Somerset", 199, 250);
		m.addNode("City Hall", 248, 301);
		m.addNode("Raffles Place", 250, 316);
		m.addNode("Marina South Pier", 268, 350);
		// PurpleLine
		m.addEdge("Punggol", "Sengkang", 3);
		m.addEdge("Sengkang", "Buangkok", 2);
		m.addEdge("Buangkok", "Hougang", 2);
		m.addEdge("Hougang", "Kovan", 2);
		m.addEdge("Kovan", "Serangoon", 3);
		m.addEdge("Serangoon", "Woodleigh", 34);
		m.addEdge("Woodleigh", "Potong Pasir", 1);
		m.addEdge("Potong Pasir", "Boon Keng", 3);
		m.addEdge("Boon Keng", "Farrer Park", 2);
		m.addEdge("Farrer Park", "Little India", 2);
		m.addEdge("Little India", "Dhoby Ghaut", 2);
		m.addEdge("Dhoby Ghaut", "Clarke Quay", 2);
		m.addEdge("Clarke Quay", "Chinatown", 2);
		m.addEdge("Chinatown", "Outram Park", 2);
		m.addEdge("Outram Park", "HarbourFront", 3);
		// YellowLine
		m.addEdge("Serangoon", "Lorong Chuan", 2);
		m.addEdge("Lorong Chuan", "Bishan", 2);
		m.addEdge("Bishan", "Marymount", 3);
		m.addEdge("Marymount", "Caldecott", 2);
		m.addEdge("Caldecott", "Botanic Gardens", 5);
		m.addEdge("Botanic Gardens", "Farrer Road", 12);
		m.addEdge("Farrer Road", "Holland Village", 21);
		m.addEdge("Holland Village", "Buona Vista", 2);
		m.addEdge("Buona Vista", "one-north", 2);
		m.addEdge("one-north", "Kent Ridge", 2);
		m.addEdge("Kent Ridge", "Haw Par Villa", 2);
		m.addEdge("Haw Par Villa", "Pasir Panjang", 2);
		m.addEdge("Pasir Panjang", "Labrador Park", 3);
		m.addEdge("Labrador Park", "Telok Blangah", 2);
		m.addEdge("Telok Blangah", "HarbourFront", 2);
		m.addEdge("Serangoon", "Bartley", 3);
		m.addEdge("Bartley", "Tai Seng", 2);
		m.addEdge("Tai Seng", "MacPherson", 2);
		m.addEdge("MacPherson", "Paya Lebar", 2);
		m.addEdge("Paya Lebar", "Dakota", 2);
		m.addEdge("Dakota", "Mountbatten", 2);
		m.addEdge("Mountbatten", "Stadium", 2);
		m.addEdge("Stadium", "Nicoll Highway", 2);
		m.addEdge("Nicoll Highway", "Promenade", 2);
		m.addEdge("Promenade", "Bayfront", 2);
		m.addEdge("Bayfront", "Marina Bay", 2);
		m.addEdge("Promenade", "Esplanade", 2);
		m.addEdge("Esplanade", "Bras Basah", 2);
		m.addEdge("Bras Basah", "Dhoby Ghaut", 2);
		// RedLine
		m.addEdge("Marina South Pier", "Marina Bay", 3);
		m.addEdge("Marina Bay", "Raffles Place", 2);
		m.addEdge("Raffles Place", "City Hall", 2);
		m.addEdge("City Hall", "Dhoby Ghaut", 3);
		m.addEdge("Dhoby Ghaut", "Somerset", 2);
		m.addEdge("Somerset", "Orchard", 2);
		m.addEdge("Orchard", "Newton", 3);
		m.addEdge("Newton", "Novena", 2);
		m.addEdge("Novena", "Toa Payoh", 3);
		m.addEdge("Toa Payoh", "Braddell", 2);
		m.addEdge("Braddell", "Bishan", 2);
		m.addEdge("Bishan", "Ang Mo Kio", 4);
		m.addEdge("Ang Mo Kio", "Yio Chu Kang", 2);
		m.addEdge("Yio Chu Kang", "Khatib", 6);
		m.addEdge("Khatib", "Yishun", 2);
		m.addEdge("Yishun", "Canberra", 3);
		m.addEdge("Canberra", "Sembawang", 3);
		m.addEdge("Sembawang", "Admiralty", 3);
		m.addEdge("Admiralty", "Woodlands", 3);
		m.addEdge("Woodlands", "Marsiling", 2);
		m.addEdge("Marsiling", "Kranji", 3);
		m.addEdge("Kranji", "Yew Tee", 5);
		m.addEdge("Yew Tee", "Choa Chu Kang", 3);
		m.addEdge("Choa Chu Kang", "Bukit Gombak", 4);
		m.addEdge("Bukit Gombak", "Bukit Batok", 2);
		m.addEdge("Bukit Batok", "Jurong East", 3);
		launch(args);
		// m.print();

	}
}

//Romania Map

/*
 * r.addNode("Neamt"); r.addNode("Iasi"); r.addNode("Vaslui");
 * r.addNode("Urziceni"); r.addNode("Hirsova"); r.addNode("Eforie");
 * r.addNode("Bucharest"); r.addNode("Giurgiu"); r.addNode("Fagaras");
 * r.addNode("Pitesti"); r.addNode("Sibiu"); r.addNode("Oradea");
 * r.addNode("Zerind"); r.addNode("Arad"); r.addNode("Timisoara");
 * r.addNode("Lugoj"); r.addNode("Mehadia"); r.addNode("Mehadia");
 * r.addNode("Dobreta"); r.addNode("Craiova"); r.addNode("Rimnicu Vilcea");
 * 
 * r.addEdge("Neamt", "Iasi", 87); r.addEdge("Iasi", "Vaslui", 92);
 * r.addEdge("Vaslui", "Urziceni", 142); r.addEdge("Urziceni", "Hirsova", 98);
 * r.addEdge("Urziceni", "Bucharest", 86); r.addEdge("Hirsova", "Eforie", 86);
 * r.addEdge("Bucharest", "Giurgiu", 90); r.addEdge("Bucharest", "Fagaras",
 * 211); r.addEdge("Bucharest", "Pitesti", 101); r.addEdge("Fagaras", "Sibiu",
 * 99); r.addEdge("Pitesti", "Rimnicu Vilcea", 97); r.addEdge("Pitesti",
 * "Craiova", 138); r.addEdge("Rimnicu Vilcea", "Craiova", 146);
 * r.addEdge("Rimnicu Vilcea", "Sibiu", 80); r.addEdge("Sibiu", "Oradea", 151);
 * r.addEdge("Sibiu", "Arad", 140); r.addEdge("Oradea", "Zerind", 71);
 * r.addEdge("Zerind", "Arad", 75); r.addEdge("Arad", "Timisoara", 118);
 * r.addEdge("Timisoara", "Lugoj", 111); r.addEdge("Lugoj", "Mehadia", 70);
 * r.addEdge("Mehadia", "Dobreta", 75); r.addEdge("Dobreta", "Craiova", 120);
 * System.out.println("Map of Romania"); r.print();
 */
