module com.pckoala.javafx {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;
  requires java.sql;

  opens com.pckoala.javafx to
      javafx.fxml;

  exports com.pckoala.javafx;
  exports com.pckoala.javafx.chapter14;
  exports com.pckoala.javafx.chapter15;

  opens com.pckoala.javafx.chapter14 to
      javafx.fxml;

  exports com.pckoala.javafx.chapter10;
  exports com.pckoala.javafx.tutorial4;
  exports com.pckoala.javafx.tutorial5;
  exports com.pckoala.javafx.chapter31;
  exports com.pckoala.javafx.chapter34;

  opens com.pckoala.javafx.chapter31 to
      javafx.fxml;

  exports com.pckoala.javafx.tutorial6;
  exports com.pckoala.javafx.chapter32;
}
