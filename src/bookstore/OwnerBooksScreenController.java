package bookstore;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OwnerBooksScreenController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, Double> priceColumn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void enterButton(ActionEvent event) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        Book b = new Book(name, price);
        if (!Main.getBookList().contains(b)) {
            Main.getBookList().add(b);
        }
    }

    @FXML
    private void deleteButton(ActionEvent event) {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        Main.getBookList().remove(selectedBook);
    }
    
    @FXML
    private void backButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OwnerStartScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        bookTable.setItems(Main.getBookList());
    }
}
