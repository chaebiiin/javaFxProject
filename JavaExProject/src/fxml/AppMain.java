package fxml;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();
		root.setPadding(new Insets(10,10,10,10));
//		root.setPadding(new Insets(10));

		root.setSpacing(10);

		TextField textFiled = new TextField();
		textFiled.setPrefWidth(200);

		Button button = new Button();
		button.setText("확인");

		ObservableList list = root.getChildren();
		list.add(textFiled);
		list.add(button);

		Scene scene = new Scene(root);

		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
