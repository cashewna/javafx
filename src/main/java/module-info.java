module com.pckoala.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pckoala.javafx to javafx.fxml;
    exports com.pckoala.javafx;
    exports com.pckoala.javafx.chapter14;
    exports com.pckoala.javafx.chapter15;
    opens com.pckoala.javafx.chapter14 to javafx.fxml;
}