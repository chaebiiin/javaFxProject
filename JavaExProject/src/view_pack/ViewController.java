package view_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;


public class ViewController implements Initializable {
	@FXML
	ListView listView;
	@FXML
	TableView<Phone> tableView;
	@FXML ImageView imageView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> list = FXCollections.observableArrayList("Galaxy S1", "Galaxy S2", "Galaxy S3",
				"Galaxy S4");
		list.add("Galaxy S5");
		list.add("Galaxy S6");
		list.add("Galaxy S7");
		listView.setItems(list);
		
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
			}
			
		});

		tableView.setItems(FXCollections.observableArrayList(new Phone("Galaxy S1", "phone01.png"),
				new Phone("Galaxy S2", "phone02.png"), new Phone("Galaxy S3", "phone03.png"),
				new Phone("Galaxy S4", "phone04.png"), new Phone("Galaxy S5", "phone05.png"),
				new Phone("Galaxy S6", "phone06.png"), new Phone("Galaxy S7", "phone07.png")
				));
		
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
		
		
		tableView.getSelectionModel().selectedItemProperty().addListener (new ChangeListener <Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
				imageView.setImage(new Image ("/images/" + newValue.getImage()));	
			}
		});
		
		imageView.setImage(new Image ("/images/phone01.png"));
	}

	public void handleBtnOkAction() {

	}

	public void handleBtnCancleAction() {

	}

}
