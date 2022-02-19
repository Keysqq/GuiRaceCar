module com.example.guiracecar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guiracecar to javafx.fxml;
    exports com.example.guiracecar;
}