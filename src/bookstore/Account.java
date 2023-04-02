package bookstore;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {

    protected StringProperty username;
    protected StringProperty password;

    public final StringProperty usernameProperty() {
        if (username == null) {
            username = new SimpleStringProperty(this, "username");
        }
        return username;
    }

    public final StringProperty passwordProperty() {
        if (password == null) {
            password = new SimpleStringProperty(this, "password");
        }
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account a = (Account) o;
        return usernameProperty().get().equals(a.usernameProperty().get()) && passwordProperty().get().equals(a.passwordProperty().get());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        return hash;
    }
}
