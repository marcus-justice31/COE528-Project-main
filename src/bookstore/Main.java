package bookstore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Main extends Application {

    private static ObservableList<Customer> customerList;
    private static ObservableList<Book> bookList;
    private static TableView<Customer> customerTable;
    private static TableView<Book> bookTable;
    private static Account currentAccount;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            customerList = fetchCustomers();
        } catch (FileNotFoundException e) {
            System.out.println("customers.txt not found");
        }
        try {
            bookList = fetchBooks();
        } catch (FileNotFoundException e) {
            System.out.println("books.txt not found");
        }

        customerTable = new TableView<>(customerList);
        bookTable = new TableView<>(bookList);

        launch(args);

        try {
            saveCustomers();
        } catch (IOException e) {
            System.out.println("customer.txt saving error");
        }
        try {
            saveBooks();
        } catch (IOException e) {
            System.out.println("book.txt saving error");
        }
    }

    public static ObservableList<Customer> fetchCustomers() throws FileNotFoundException {

        ObservableList<Customer> tempList = FXCollections.observableArrayList();
        Scanner input = new Scanner(new File("customers.txt"));

        while (input.hasNextLine()) {
            try {
                tempList.add(new Customer(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine())));
            } catch (NoSuchElementException e) {
                System.out.println("customers.txt error");
            }
        }
        return tempList;
    }

    public static void saveCustomers() throws IOException {
        FileWriter fw = new FileWriter("customers.txt");
        for (Customer c : customerList) {
            fw.write(c.usernameProperty().get() + '\n' + c.passwordProperty().get() + '\n' + c.pointsProperty().get() + '\n');
        }
        fw.close();
    }

    public static ObservableList<Book> fetchBooks() throws FileNotFoundException {

        ObservableList<Book> tempList = FXCollections.observableArrayList();
        Scanner input = new Scanner(new File("books.txt"));

        while (input.hasNextLine()) {
            try {
                tempList.add(new Book(input.nextLine(), Double.parseDouble(input.nextLine())));
            } catch (NoSuchElementException e) {
                System.out.println("books.txt error");
            }
        }
        return tempList;
    }

    public static void saveBooks() throws IOException {
        FileWriter fw = new FileWriter("books.txt");
        for (Book b : bookList) {
            fw.write(b.nameProperty().get() + '\n' + b.priceProperty().get() + '\n');
        }
        fw.close();
    }

    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public static ObservableList<Book> getBookList() {
        return bookList;
    }

    public static TableView<Customer> getCustomerTable() {
        return customerTable;
    }

    public static TableView<Book> getBookTable() {
        return bookTable;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account acc) {
        currentAccount = acc;
    }
}
