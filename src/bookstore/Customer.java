package bookstore;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Customer extends Account {

    private IntegerProperty points;
    private Status status;

    public Customer(String username, String password) {
        usernameProperty().set(username);
        passwordProperty().set(password);
        pointsProperty().set(0);
        status = new SilverStatus();
    }

    public Customer(String username, String password, int points) {
        usernameProperty().set(username);
        passwordProperty().set(password);
        pointsProperty().set(points);
        updateStatus();
    }

    public final IntegerProperty pointsProperty() {
        if (points == null) {
            points = new SimpleIntegerProperty(this, "points", 0);
        }
        return points;
    }
    
    
    

    public final void updateStatus() {
        if (pointsProperty().get() < 1000) {
            status = new SilverStatus();
        } else {
            status = new GoldStatus();
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
