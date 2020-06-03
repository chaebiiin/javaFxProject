package input_pack;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class InputController implements Initializable {
	@FXML
	Button btnReg;
	@FXML
	Button btnCancel;
	@FXML
	TextField txtTitle;
	@FXML
	PasswordField txtPassword;
	@FXML
	ComboBox<String> comboPublic;
	@FXML
	DatePicker dateExit;
	@FXML
	TextArea txtContent;

	Connection conn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleBtnRegAction(ActionEvent e) {
		if (txtTitle.getText() == null || txtTitle.getText().equals("")) {
			messageDialog("제목을 입력하세요");
		} else if (txtPassword.getText() == null || txtPassword.getText().equals("")) {
			messageDialog("비번을 입력하세요");
		} else if (comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			messageDialog("공개여부를 입력하세요");
		} else if (dateExit.getValue() == null || dateExit.getValue().equals("")) {
			messagePopup("종료일을 입력하세요");
		} else if (txtContent.getText() == null || txtContent.getText().equals("")) {
			messagePopup("내용을 입력하세요");
		} else {
			// DB입력... Connection, PreparedStatement, executeUpdate(); 필요
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String sql = "insert into board(title, password, publicity, exit_date, content)" + "values(?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtTitle.getText());
				pstmt.setString(2, txtPassword.getText());
				pstmt.setString(3, comboPublic.getValue());
				pstmt.setString(4, dateExit.getValue().format(formatter));
				pstmt.setString(5, txtContent.getText());

				int r = pstmt.executeUpdate();
				System.out.println(r + "건 입력됨.");

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// 각 필드 초기화.
			txtTitle.setText(null);
			txtPassword.setText(null);
			comboPublic.setValue("공개");
			dateExit.setValue(null);
			txtContent.setText(null);
		} // if

	} // BtnRegAction
	
	public void messageDialog(String message) {
		Stage customStage = new Stage(StageStyle.UTILITY);
		customStage.initModality(Modality.WINDOW_MODAL);
		customStage.initOwner(btnReg.getScene().getWindow());
		customStage.setTitle("확인");
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400, 150); //외부컨테이너 사이즈 조절
		
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/icons/dialog-info.png"));
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);
		imageView.setLayoutX(15);
		imageView.setLayoutY(15);
		
		Button button = new Button("확인"); 
		button.setLayoutX(336); // 모양을 잡아줌. 
		button.setLayoutY(104);
		button.setOnAction(e -> customStage.close()); // 람다식으로 표현함.
		
		Label label = new Label (message); //생성자를 호출해서 라벨에 넣음.
		label.setLayoutX(87);
		label.setLayoutY(33);
		label.setPrefHeight(15);  // 모양을 잡아줌
		label.setPrefWidth(290);
		
		ap.getChildren().add(imageView);
		ap.getChildren().add(button);
		ap.getChildren().add(label);
		
		Scene scene = new Scene(ap);
		customStage.setScene(scene); 
		customStage.show(); //윈도우에 띄워주는 역할.
		
	}

	public void messagePopup(String message) {

		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color : black; -fx-background-radius : 10;"); //팝업창 색상, 팝업창 겉면의 각? 지정 
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
		label.setStyle("-fx-text-fill: red;"); //글자색 지정
		
		//컨테이너에 컨트롤 담기.
		hbox.getChildren().add(imageView);
		hbox.getChildren().add(label);
		
		// 팝업창 생성하고 호출하기. 
		Popup popup = new Popup();

		popup.getContent().add(hbox);
		popup.setAutoHide(true); // 팝업창이 뜨고 다시 실행하면(?) 팝업창이 사라지게 만듬.

		popup.show(btnReg.getScene().getWindow()); // 등록 버튼을 입력했을 때, 팝업창을 띄워주는 역할

	} // messagePopup

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}

} // class