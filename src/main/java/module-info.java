module com.pckoala.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pckoala.javafx to javafx.fxml;
    exports com.pckoala.javafx;
}