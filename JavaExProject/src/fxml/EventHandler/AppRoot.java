package fxml.EventHandler;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppRoot extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
//		HBox root = FXMLLoader.load(getClass()<-런타임시에 이걸 기준으로.getResource("Root.fxml"));<-여기에 있는 파일을 읽어오겠다. 
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml")); // fxml을 끌어와서 실행시키는 역할.

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("FXML sample");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
