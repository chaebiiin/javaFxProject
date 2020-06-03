package view_board;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class BoardController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	public void handleBtnRegAction(ActionEvent e) {
		
	}
	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}

}
