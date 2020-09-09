package moneyBook;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// controller 제일 화나는것
public class MoneyController implements Initializable {
	@FXML
	Button btnAdd, btnChart, btnAadd, btnDel, btnModi, btnClear, btnExit;
	@FXML
	TableView<Money> tableView;

	ObservableList<Money> list;
	Connection conn = MConnection.getDB(); // 광히형이해줬어

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TableColumn<Money, ?> tc = tableView.getColumns().get(0);// 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("whendate"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("describe"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("used"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("pay"));

		// 데이터불러오기
		listAdd();

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

	public void listAdd() {
		String sql = "select * from MONEY order by WhenDate"; // sql명령문
		try {
			list = FXCollections.observableArrayList();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Money money = new Money(rs.getString("WHENDATE"), rs.getString("DESCRIBE"), rs.getInt("USED"),
						rs.getString("PAY"));
				list.add(money);
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableView.setItems(list);
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
			btnExit.setOnAction(event -> stage.close());
			
			Button btnAadd = (Button) parent.lookup("#btnAadd");
			btnAadd.setOnAction(event-> {
				DatePicker DATE = (DatePicker) parent.lookup("#DATE");
				TextField DESC = (TextField) parent.lookup("#DESC");
				TextField USED = (TextField) parent.lookup("#USED");
				TextField PAY = (TextField) parent.lookup("#PAY");
				
				String sql = "insert into MONEY values(?,?,?,?)";
				
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setNString(1, String.valueOf(DATE.getValue()));
					pstmt.setNString(2, DESC.getText());
					pstmt.setNString(3, USED.getText());
					pstmt.setNString(4, PAY.getText());
					pstmt.executeUpdate();
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
						stage.close();
						listAdd();
				
			});
			
			Button btnClear = (Button) parent.lookup("#btnClear");
			btnClear.setOnAction(event-> {
				TextField DESC = (TextField) parent.lookup("#DESC");
				TextField USED = (TextField) parent.lookup("#USED");
				TextField PAY = (TextField) parent.lookup("#PAY");
				
				DESC.clear();
				USED.clear();
				PAY.clear();
			});
			} catch (IOException e) {
				e.printStackTrace();
							}
	}
	
		
	// 차트 화면 보여주는거
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY); // 이게 머임
		stage.initModality(Modality.WINDOW_MODAL); // 이게 머임
		stage.initOwner(btnAdd.getScene().getWindow());
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("MoneyChart.fxml"));
			Button btnExit = (Button) parent.lookup("#btnExit"); // #은 id값을 찾아줄때

			Scene scene = new Scene(parent);
			stage.setTitle("차트");
			stage.setScene(scene);
			stage.show();

			btnExit.setOnAction(event -> stage.close());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPrimaryStage(Stage primaryStage) { // 먼데이거?
		// TODO Auto-generated method stub

	}
}
