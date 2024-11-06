package org.example.week3homeassignment;

import java.util.Locale;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/homeassignment3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @FXML private Button saveButton;
    @FXML private ChoiceBox<String> selectLang;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private Label selectLanguageLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emailLabel;

    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        selectLang.getItems().addAll("English", "Farsi", "Japanese");
        selectLang.setOnAction(event -> updateLanguage(selectLang.getValue()));
        updateLanguage("English");
    }

    private void updateLanguage(String lang) {
        Locale locale;
        switch (lang) {
            case "Farsi":
                locale = new Locale("fa");
                break;
            case "Japanese":
                locale = new Locale("ja");
                break;
            case "English":
            default:
                locale = new Locale("en");
                break;
        }
        bundle = ResourceBundle.getBundle("langSelection", locale);
        selectLanguageLabel.setText(bundle.getString("selectLanguage"));
        firstNameLabel.setText(bundle.getString("firstName"));
        lastNameLabel.setText(bundle.getString("lastName"));
        emailLabel.setText(bundle.getString("email"));
        saveButton.setText(bundle.getString("save"));
    }

    public void saveEmployee() {
        String selectedLang = selectLang.getValue().toString();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        String tableName = getTableName(selectedLang);

        String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTableName(String lang) {
        switch (lang) {
            case "English":
                return "employee_en";
            case "Farsi":
                return "employee_fa";
            case "Japanese":
                return "employee_ja";
            default:
                throw new IllegalArgumentException("Unsupported language: " + lang);
        }
    }
}