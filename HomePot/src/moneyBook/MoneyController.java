package moneyBook;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// controller 제일 화나는것
public class MoneyController implements Initializable {
	@FXML
	Button btnAdd, btnChart, btnAadd, btnDel, btnModi, btnClear, btnExit;
	@FXML
	TableView<?> tableView;
	Connection conn = MConnection.getDB(); // 광히형이해줬어
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// add버튼

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();
				

			}

		});
		// chart버튼
		btnChart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnChartAction();
			}
			
		});
		

	}

	// Initializable
//추가 화면을 보여주는 거
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY); // 이게 머임
		stage.initModality(Modality.WINDOW_MODAL); // 이게 머임
		stage.initOwner(btnAdd.getScene().getWindow());
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("MoneyAdd.fxml"));
			Button btnExit = (Button) parent.lookup("#btnExit");
			
			

			Scene scene = new Scene(parent);
			stage.setTitle("추가화면");
			stage.setScene(scene);
			stage.show();
			btnExit.setOnAction(event->stage.close());
	
	}catch (IOException e) {
		e.printStackTrace();
	}}
	//차트 화면 보여주는거
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY); // 이게 머임
		stage.initModality(Modality.WINDOW_MODAL); // 이게 머임
		stage.initOwner(btnAdd.getScene().getWindow());
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("MoneyChart.fxml"));
			Button btnExit = (Button) parent.lookup("#btnExit"); //#은 id값을 찾아줄때
			
			Scene scene = new Scene(parent);
			stage.setTitle("차트");
			stage.setScene(scene);
			stage.show();
			
			btnExit.setOnAction(event->stage.close());
	}catch (IOException e) {
		e.printStackTrace();
	}
	}
		public void setPrimaryStage(Stage primaryStage) { //먼데이거?
		// TODO Auto-generated method stub
		
	}
}
