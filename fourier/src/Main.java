import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	int r[] = { 25, 50, 100, 75, 50 };
	double theta[] = { 0, Math.PI, 0, Math.PI/2, Math.PI };
	int a[] = { -2, -1, 0, 1, 2 };
	double time = 0;
	double x[] = new double[5];
	double y[] = new double[5];
	

	public void start(Stage window) throws Exception {
		x[0] = 400 + r[0] * Math.cos(a[0] * time + theta[0]);
		y[0] = 300 + r[0] * Math.sin(a[0] * time + theta[0]);
		for (int i = 1; i < 5; i++) {
			x[i] = x[i - 1] + r[i] * Math.cos(a[i] * time + theta[i]);
			y[i] = y[i - 1] + r[i] * Math.sin(a[i] * time + theta[i]);
		}
		Group root = new Group();
		Ellipse circle[] = new Ellipse[5];
		Line line[] = new Line[5];
		circle[0] = new Ellipse(400, 300, r[0], r[0]);
		circle[0].setStroke(Color.WHITE);
		circle[0].setFill(Color.TRANSPARENT);
		line[0] = new Line(400, 300, x[0], y[0]);
		line[0].setStroke(Color.WHITE);
		for (int i = 1; i < 5; i++) {
			circle[i] = new Ellipse(x[i - 1], y[i - 1], r[i], r[i]);
			circle[i].setStroke(Color.WHITE);
			circle[i].setFill(Color.TRANSPARENT);
			line[i] = new Line(x[i - 1], y[i - 1], x[i], y[i]);
			line[i].setStroke(Color.WHITE);
		}
		Rectangle rect = new Rectangle(800, 600);
		KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				time += 0.01;
				x[0] = 400 + r[0] * Math.cos(a[0] * time + theta[0]);
				y[0] = 300 + r[0] * Math.sin(a[0] * time + theta[0]);
				for (int i = 1; i < 5; i++) {
					x[i] = x[i - 1] + r[i] * Math.cos(a[i] * time + theta[i]);
					y[i] = y[i - 1] + r[i] * Math.sin(a[i] * time + theta[i]);
				}
				line[0].setEndX(x[0]);
				line[0].setEndY(y[0]);
				for (int i = 1; i < 5; i++) {
					circle[i].setCenterX(x[i - 1]);
					circle[i].setCenterY(y[i - 1]);
					line[i].setStartX(x[i - 1]);
					line[i].setStartY(y[i - 1]);
					line[i].setEndX(x[i]);
					line[i].setEndY(y[i]);
				}
				if (time <= Math.PI * 2) {
					Ellipse drawn = new Ellipse(x[4], y[4], 1, 1);
					drawn.setStroke(Color.WHITE);
					root.getChildren().add(drawn);
				}
			}

		});
		Timeline t = new Timeline(frame);
		t.setCycleCount(javafx.animation.Animation.INDEFINITE);
		t.play();
		root.getChildren().addAll(rect);
		for (int i = 0; i < 5; i++) {
			root.getChildren().addAll(circle[i], line[i]);
		}
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}

}
