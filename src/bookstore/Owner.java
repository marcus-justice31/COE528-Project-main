package bookstore;

public class Owner extends Account {

    private static Owner instance;

    private Owner() {
        usernameProperty().set("admin");
        passwordProperty().set("admin");
    }

    public static Owner getInstance() {
        if (instance == null) {
            instance = new Owner();
        }
        return instance;
    }
}
