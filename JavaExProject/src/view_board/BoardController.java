package view_board;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BoardController implements Initializable {
	Connection conn;
	@FXML
	TableView<Board> tableView;
	@FXML TextField txtTitle;
	@FXML ComboBox comboPublic;
	@FXML TextField dateExit;
	@FXML TextArea txtContent;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

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
//		Platform.M();
	}

}
