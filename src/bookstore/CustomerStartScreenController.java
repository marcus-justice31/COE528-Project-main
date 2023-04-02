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
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerStartScreenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TableView<Book> buyTable;
    
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, Double> priceColumn;
    @FXML
    private TableColumn<Customer, CheckBox> boxColumn;
    
    @FXML
    private Label WelcomeMessage;
    
    @FXML
    private void buyButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerCostScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void redeemButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerBuyAndRedeemScreen.fxml"));
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
        WelcomeMessage.setText("Welcome " + Main.getCurrentAccount().usernameProperty().get() +
                ". You have " + ((Customer)(Main.getCurrentAccount())).pointsProperty().get() +
                " points. Your status is " + ((Customer)(Main.getCurrentAccount())).getStatus().getStatus());
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        boxColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        buyTable.setItems(Main.getBookList());
    }
    
}