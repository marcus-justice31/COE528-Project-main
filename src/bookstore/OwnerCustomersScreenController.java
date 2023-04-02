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

public class OwnerCustomersScreenController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer, String> passwordColumn;
    @FXML
    private TableColumn<Customer, Integer> pointsColumn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void enterButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Customer c = new Customer(username, password);
        if (!Main.getCustomerList().contains(c)) {
            Main.getCustomerList().add(c);
        }
    }

    @FXML
    private void deleteButton(ActionEvent event) throws IOException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        Main.getCustomerList().remove(selectedCustomer);
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
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        customerTable.setItems(Main.getCustomerList());
    }
}
