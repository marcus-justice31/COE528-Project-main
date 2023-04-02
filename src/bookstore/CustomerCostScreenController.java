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

public class CustomerCostScreenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label TotalCost;
    @FXML
    private Label PointsStatus;
    
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
       double cost = 0;
       int points = 0;
       for(int i = 0; i < Main.getBookList().size(); i++) {
            if((Main.getBookList().get(i).getSelect()).isSelected()){
                cost += Main.getBookList().get(i).priceProperty().get();
                Main.getBookList().remove(i);
            }
        } 
        
       points = ((int)cost) * 10;
       int currentPoints = ((Customer)(Main.getCurrentAccount())).pointsProperty().get();
       points += currentPoints;
       ((Customer)(Main.getCurrentAccount())).pointsProperty().set(points);
       
        TotalCost.setText("Total Cost: $" + cost);
        PointsStatus.setText("Points: " + points  + ", Status: " +  ((Customer)(Main.getCurrentAccount())).getStatus().getStatus());
       
    }
}
