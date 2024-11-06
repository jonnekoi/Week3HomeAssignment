module org.example.week3homeassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.week3homeassignment to javafx.fxml;
    exports org.example.week3homeassignment;
}