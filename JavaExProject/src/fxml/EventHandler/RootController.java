package fxml.EventHandler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RootController implements Initializable {
	@FXML
	TextField textField;
	@FXML Button btnok;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textField.setText("초기화 합니다.");				
		btnok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
			
		});
		
	}

}
