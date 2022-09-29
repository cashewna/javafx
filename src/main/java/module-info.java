module com.pckoala.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.pckoala.javafx to javafx.fxml;
    exports com.pckoala.javafx;
    exports com.pckoala.javafx.chapter14;
    exports com.pckoala.javafx.chapter15;
    opens com.pckoala.javafx.chapter14 to javafx.fxml;
    exports com.pckoala.javafx.chapter10;
    exports com.pckoala.javafx.tutorial4;
    exports com.pckoala.javafx.tutorial5;
    exports com.pckoala.javafx.chapter31;
    opens com.pckoala.javafx.chapter31 to javafx.fxml;
}