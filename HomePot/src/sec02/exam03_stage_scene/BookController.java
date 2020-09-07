package com.yedam.book;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable {
   @FXML TableView<Book> tableView;
   @FXML Button btnAdd;
   
   ObservableList<Book> list;
   
   @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
      TableColumn<Book, ?> tc = tableView.getColumns().get(0);//첫번째칼럼
      tc.setCellValueFactory(new PropertyValueFactory<>("title"));
      
      tc = tableView.getColumns().get(1);
      tc.setCellValueFactory(new PropertyValueFactory<>("author"));
      
      tc = tableView.getColumns().get(2);
      tc.setCellValueFactory(new PropertyValueFactory<>("publisher"));
      
      tc = tableView.getColumns().get(3);
      tc.setCellValueFactory(new PropertyValueFactory<>("price"));
      
      list = FXCollections.observableArrayList();
      tableView.setItems(list);
      

		list.add(new Book("동백꽃 필까?", "민소매", "팝출판사", 3000));
		list.add(new Book("필까? 굽힐까?", "헬스랑", "더보이즈", 4500));
		list.add(new Book("필까? 끊을까?", "폐", "금연동", 12050));

      
      btnAdd.setOnAction(event -> {
            handleBtnAddAction();
      });
      
      tableView.setOnMouseClicked(event -> {
            
            if(event.getClickCount() == 2) { //더블클릭이면
               String selectedTitle = tableView.getSelectionModel().getSelectedItem().getTitle();
               handleDoubleClickAction(selectedTitle);
            }
      });
   } //end of initialize
   
   public void handleDoubleClickAction(String title) {
      Stage stage = new Stage(StageStyle.UTILITY);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.initOwner(btnAdd.getScene().getWindow());
      
      try {
         Parent parent = FXMLLoader.load(getClass().getResource("BookUpdate.fxml"));
         
         Scene scene = new Scene(parent);
         stage.setTitle("도서수정");
         stage.setScene(scene);
         stage.show();
         
         Button btnFormModify = (Button) parent.lookup("#btnFormModify");
         TextField txtTitle = (TextField) parent.lookup("#txtTitle");
         TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
         TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
         TextField txtPrice = (TextField) parent.lookup("#txtPrice");
         
         for(Book bookList : list) {
            if(bookList.getTitle().equals(title)) {
            	txtTitle.setText(String.valueOf(bookList.getTitle()));
               txtAuthor.setText(String.valueOf(bookList.getAuthor()));
               txtPublisher.setText(String.valueOf(bookList.getPublisher()));
               txtPrice.setText(String.valueOf(bookList.getPrice()));
            }
         }
         
         btnFormModify.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               for(int i = 0; i< list.size(); i++) {
                  if(list.get(i).getTitle().equals(title)) {
                     Book book = new Book(txtTitle.getText(), 
                           txtAuthor.getText(), 
                           txtPublisher.getText(), 
                           Integer.parseInt(txtPrice.getText()));
                     list.set(i, book);
                     stage.close();
                  }
               }
            }
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void handleBtnAddAction() {
      Stage stage = new Stage(StageStyle.UTILITY);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.initOwner(btnAdd.getScene().getWindow());
      
      try {
         Parent parent = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
         
         Scene scene = new Scene(parent);
         stage.setTitle("도서등록");
         stage.setScene(scene);
         stage.show();
         
         Button btnFormAdd = (Button) parent.lookup("#btnAdd");
         btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               TextField txtTitle = (TextField) parent.lookup("#txtTitle");
               TextField txtAuthor = (TextField) parent.lookup("#txtAuthor");
               TextField txtPublisher = (TextField) parent.lookup("#txtPublisher");
               TextField txtPrice = (TextField) parent.lookup("#txtPrice");
               Book book = new Book(
                     txtTitle.getText(), 
                     txtAuthor.getText(), 
                     txtPublisher.getText(), 
                     Integer.parseInt(txtPrice.getText()));
               
               list.add(book);
               
               stage.close();
            }
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }

public void setPrimaryStage(Stage primaryStage) {
	// TODO Auto-generated method stub
	
}
}