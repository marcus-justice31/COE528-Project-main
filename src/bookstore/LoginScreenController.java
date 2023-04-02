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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginScreenController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        Customer login = new Customer(usernameField.getText(), passwordField.getText());

        if (login.equals(Owner.getInstance())) {
            Main.setCurrentAccount(Owner.getInstance());

            root = FXMLLoader.load(getClass().getResource("OwnerStartScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (Main.getCustomerList().contains(login)) {
            Main.setCurrentAccount(Main.getCustomerList().get(Main.getCustomerList().indexOf(login)));

            root = FXMLLoader.load(getClass().getResource("CustomerStartScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < Main.getBookList().size(); i++) {
            Main.getBookList().get(i).setSelect(new CheckBox());
        }
    }
}
