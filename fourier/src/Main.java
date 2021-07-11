import java.util.Arrays;

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
	//int r[] = { 25, 50, 100, 75, 50 };
	//double theta[] = { 0, Math.PI, 0, Math.PI/2, Math.PI };
	//int a[] = { -2, -1, 0, 1, 2 };
	double time = 0;
	double[][] test = {{0,100},{-100, 100},{-100,0},{-100, -100},{0,-100},{100,-100},{100,0},{100,100}};
	double[][] params = input.CFourierSeries(test);
	double x[] = new double[params.length];
	double y[] = new double[params.length];
	
	

	public void start(Stage window) throws Exception {
		System.out.println(Arrays.deepToString(params));
		x[0] = 500 + params[0][0] * Math.cos(params[0][2] * time + params[0][1]);
		y[0] = 400 + params[0][0] * Math.sin(params[0][2] * time + params[0][1]);
		for (int i = 1; i < params.length; i++) {
			x[i] = x[i-1] + params[i][0] * Math.cos(params[i][2] * time + params[i][1]);
			y[i] = y[i-1] + params[i][0] * Math.sin(params[i][2] * time + params[i][1]);
			System.out.println(x[i]+", "+y[i]);
		}
		Group root = new Group();
		Ellipse circle[] = new Ellipse[params.length];
		Ellipse points[] = new Ellipse[params.length];
		Line line[] = new Line[params.length];
		circle[0] = new Ellipse(500, 400, params[0][0], params[0][0]);
		circle[0].setStroke(Color.WHITE);
		circle[0].setFill(Color.TRANSPARENT);
		line[0] = new Line(500, 400, x[0], y[0]);
		line[0].setStroke(Color.WHITE);
		points[0] = new Ellipse(test[0][0]+500, test[0][1]+400, 10, 10);
		points[0].setStroke(Color.RED);
		for (int i = 1; i < params.length; i++) {
			circle[i] = new Ellipse(x[i - 1], y[i - 1], params[i][0], params[i][0]);
			circle[i].setStroke(Color.WHITE);
			circle[i].setFill(Color.TRANSPARENT);
			line[i] = new Line(x[i - 1], y[i - 1], x[i], y[i]);
			line[i].setStroke(Color.WHITE);
			points[i] = new Ellipse(test[i][0]+500, test[i][1]+400, 10, 10);
			points[i].setStroke(Color.RED);
		}
		Rectangle rect = new Rectangle(1000, 800);
		KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				time += 0.01;
				x[0] = 500 + params[0][0] * Math.cos(params[0][2] * time + params[0][1]);
				y[0] = 400 + params[0][0] * Math.sin(params[0][2] * time + params[0][1]);
				for (int i = 1; i < params.length; i++) {
					x[i] = x[i-1] + params[i][0] * Math.cos(params[i][2] * time + params[i][1]);
					y[i] = y[i-1] + params[i][0] * Math.sin(params[i][2] * time + params[i][1]);
				}
				line[0].setEndX(x[0]);
				line[0].setEndY(y[0]);
				for (int i = 1; i < params.length; i++) {
					circle[i].setCenterX(x[i - 1]);
					circle[i].setCenterY(y[i - 1]);
					line[i].setStartX(x[i - 1]);
					line[i].setStartY(y[i - 1]);
					line[i].setEndX(x[i]);
					line[i].setEndY(y[i]);
				}
				if (time <= Math.PI * 2) {
					Ellipse drawn = new Ellipse(x[params.length-1], y[params.length-1], 1, 1);
					drawn.setStroke(Color.WHITE);
					root.getChildren().add(drawn);
				}
			}

		});
		Timeline t = new Timeline(frame);
		t.setCycleCount(javafx.animation.Animation.INDEFINITE);
		t.play();
		root.getChildren().addAll(rect);
		for (int i = 0; i < params.length; i++) {
			root.getChildren().addAll(circle[i], line[i],points[i]);
		}
		Scene scene = new Scene(root, 1000, 800);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}

}
