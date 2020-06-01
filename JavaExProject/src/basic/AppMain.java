package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application { // 생성자 호출 -> init 실행

	public AppMain() { // 매게값이 없는 생성자
		System.out.println(Thread.currentThread().getName() + " : Appmain() 실행"); // 생성자 실행.

	}

	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init() 실행");
	}

	@Override
	public void start(Stage primaryStage) throws Exception { // 추상메소드 정의 (ui)만드는 부분
		System.out.println(Thread.currentThread().getName() + " : start() 실행");
		VBox root = new VBox(); // vbox 임포트 해줘야함.
		root.setPrefWidth(350); // 팝업창 크기 지정
		root.setPrefHeight(150); // 팝업창 크기 지정
		root.setAlignment(Pos.CENTER); // 내용이 중앙에 올 수 있게 지정
		root.setSpacing(20); // ? 이건 뭔지 기억 안 남.

		Label label = new Label(); // 임포트 해줘야함
		label.setText("Hello javFX"); // 안 에 들어갈 내용 지정
		label.setFont(new Font(50)); // 내용의 폰트 크기 지정

		Button button = new Button(); // 임포트 해줘야 함
		button.setText("확인"); // 팝업창에 들어가는 버튼버튼의 텍스트 내용 지정
		
		
		button.setOnAction(event -> Platform.exit()); //람다 표현식
		
//		button.setOnAction(new EventHandler<ActionEvent>() { // 버튼을 누르면 이벤트가 나오게 지정. //임포트 해주고, handle 정의 해줘야함.
//			@Override
//			public void handle(ActionEvent arg0) {
//				Platform.exit(); // 임포트 해줘야함
//			}
//		});

		root.getChildren().add(label); //팝업창에 label을  넣어 줌.
		root.getChildren().add(button); // 팝업창에 들어갈 button을 넣어 줌.

		Scene scene = new Scene(root); // 임포트 해줘야함.
		primaryStage.setScene(scene);
		primaryStage.show(); // 화면을 보여주는 것
	}
	@Override
	public void stop() throws Exception { // 오버라이드 해줘야함.
		System.out.println(Thread.currentThread().getName() + " : stop() 실행");
	}
	public static void main(String[] args) { // 실행시키는 부분
		Application.launch(args);
	}
}
