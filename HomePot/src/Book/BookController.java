package Book;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable {
	@FXML
	TableView<Book> tableView;
	@FXML
	Button btnAdd, btnBarChart;

	ObservableList<Book> list;

	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Book, ?> tc = tableView.getColumns().get(0);// 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("title"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("author"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));

		// 성적저장
		list = FXCollections.observableArrayList();

		tableView.setItems(list);

		// 자동추가
		list.add(new Book("동백이 필꽃", "나상실", "환상의 짝수", 12000));
		list.add(new Book("학기말인데 김치말일세", "맛있는노루", "동물천국", 10110));
		list.add(new Book("우리들의 일그러짐", "깡텅장", "코코아뱅K", 3000));

		// 추가버튼
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();
			}
		});
	}// end of initialize

	public void handleDoubleClickAction(String title) {
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			
			AnchorPane ap = new AnchorPane();
			ap.setPrefSize(210, 230);
			
			Label lTitle, lAuthor, lPublisher, lPrice;
			TextField  tTitle, tAuthor, tPublisher, tPrice;
			
			lTitle = new Label("제목");
			lTitle.setLayoutX(35);
			lTitle.setLayoutY(35);
			
			lAuthor = new Label("저자");
			lAuthor.setLayoutX(35);
			lAuthor.setLayoutY(75);
			
			lPublisher = new Label("출판사");
			lPublisher.setLayoutX(35);
			lPublisher.setLayoutY(115);
			
			lPrice = new Label("가격");
			lPrice.setLayoutX(35);
			lPrice.setLayoutY(155);
			
			
			tTitle = new TextField();
			tTitle.setPrefWidth(110);
			tTitle.setLayoutX(72);
			tTitle.setLayoutY(70);
			
			tAuthor = new TextField();
			tAuthor.setPrefWidth(110);
			tAuthor.setLayoutX(72);
			tAuthor.setLayoutY(110);
			
			tPublisher = new TextField();
			tPublisher.setPrefWidth(110);
			tPublisher.setLayoutX(72);
			tPublisher.setLayoutY(150);
			
			tPrice = new TextField();
			tPrice.setPrefWidth(110);
			tPrice.setLayoutX(72);
			tPrice.setLayoutY(190);
			
			Button btnUpdate = new Button("수정");
			btnUpdate.setLayoutX(85);
			btnUpdate.setLayoutY(184);
			btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					for(int i = 0; i< list.size(); i++) {
						if(list.get(i).getTitle().equals(title)) {
							Book book = new Book(txtTitle.getText, txtAuthor.getText, txtPublisher.getText,  
									Integer.parseInt(tPrice.getText())); 
							list.set(i, book);
							stage.close();
						}
					}
				}
				
			});
			
			//이름기준으로 국어, 수학, 영어 점수를 화면에 입력해주기
			for(Book book : list) {
				if(book.getTitle).equals(title)) {
					tAuthor.setText(String.valueOf(book.getAuthor()));
					tPublisher.setText(String.valueOf(book.getPublisher()));
					tPrice.setText(String.valueOf(book.getPrice()));
				}
			}
			
			ap.getChildren().addAll(tTitle, tAuthor, tPublisher, tPrice, lTitle, lAuthor, lPublisher, lPrice, btnUpdate);
			
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();
			
		}

	// 추가화면(AddForm) 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));

			Scene scene = new Scene(parent);
			stage.setTitle("AddForm");
			stage.setScene(scene);
			stage.show();

			// 추가화면의 컨트롤 사용하기
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TextField txtTitle = (TextField) parent.lookup("#txtTitle");
					TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
					TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");
					Book book = new Book(txtTitle.getText(), txtAuthor.getText(), txtPublisher.getText(),
							Integer.parseInt(txtPrice.getText()));

					list.add(book);

					stage.close();
				}
			});

			// 추가화면의 취소버튼
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtTitle = (TextField) parent.lookup("#txtTitle");
				TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
				TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
				TextField txtPrice = (TextField) parent.lookup("#txtPrice");

				txtTitle.clear();
				txtAuthor.clear();
				txtPublisher.clear();
				txtPrice.clear();

			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}