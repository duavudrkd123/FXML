package moneyBook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// 실행시켜주는 클래스
public class MoneyMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
FXMLLoader loader = new FXMLLoader(getClass().getResource("MoneyList.fxml"));
BorderPane moneylist = loader.load();

//MoneyController controller = loader.getController();
//controller.setPrimaryStage(primaryStage);

Scene scene = new Scene(moneylist);
primaryStage.setTitle("가계부");
primaryStage.setScene(scene);
primaryStage.show();
primaryStage.setResizable(false);
}

public static void main(String[] args) {
Application.launch(args);
}

}