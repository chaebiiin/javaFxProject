package view_board;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class BoardController implements Initializable {
	Connection conn;
	@FXML
	TableView<Board> tableView;
	@FXML
	TextField txtTitle;
	@FXML
	ComboBox comboPublic;
	@FXML
	TextField dateExit;
	@FXML
	TextArea txtContent;
	@FXML
	Button btnModify, btnBack;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		showList();

	}

	public void showList() {
		ObservableList<Board> boardList = getBoardList();
		boardList.add(new Board("test", "공개", "2020/06/30", "내용"));
		tableView.setItems(boardList);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {

			@Override
			public void changed(ObservableValue<? extends Board> observable, Board oldValue, Board newValue) {
				txtTitle.setText(newValue.getTitle());
				comboPublic.setValue(newValue.getPublicity());
				dateExit.setText(newValue.getExitDate());
				txtContent.setText(newValue.getContent());
			}

		});
		// title
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
		});
		tcTitle.setText("title");

		// exitDate
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));
		tcExitDate.setText("ExitDate");

		tableView.getColumns().add(tcTitle);
		tableView.getColumns().add(tcExitDate);

		tableView.setItems(boardList);
	}

	public ObservableList<Board> getBoardList() {
		ObservableList<Board> list = FXCollections.observableArrayList();
		String sql = "SELECT title, publicity, exit_date, content FROM board";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getString("title"), rs.getString("publicity"), rs.getString("exit_date"),
						rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void handleBtnBackAction(ActionEvent e) {

	}

	public void handleBtnNextAction(ActionEvent e) {

	}

	public void handleBtnModifyAction(ActionEvent e) {
		ObservableList<Board> list = FXCollections.observableArrayList();
		String sql = "UPDATE board SET content = ? WHERE title = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtContent.getText());
			pstmt.setString(2, txtTitle.getText());
			pstmt.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		messagePopup("수정되었습니다.");
		showList();
		
	}

	public void messagePopup(String message) {
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color : white; -fx-background-radius : 10;"); //팝업창 색상, 팝업창 겉면의 각? 지정 
		hbox.setAlignment(Pos.CENTER);
		//컨트롤 이미지뷰
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/icons/dialog-info.png"));
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		
		//컨트롤 라벨
		Label label = new Label();
		
		HBox.setMargin(label, new Insets(0, 5, 0, 5));
		label.setText(message);
		label.setStyle("-fx-text-fill: black;"); //글자색 지정
		
		//컨테이너에 컨트롤 담기.
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(label);
		
		// 팝업창 생성하고 호출하기. 
		Popup popup = new Popup();

		popup.getContent().add(hbox);
		popup.setAutoHide(true); // 팝업창이 뜨고 다시 실행하면(?) 팝업창이 사라지게 만듬.

		popup.show(btnBack.getScene().getWindow()); // 등록 버튼을 입력했을 때, 팝업창을 띄워주는 역할

	}

}
