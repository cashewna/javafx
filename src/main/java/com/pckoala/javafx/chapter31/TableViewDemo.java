package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;

public class TableViewDemo extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    TableView<Country> tableView = new TableView<>();

    ObservableList<Country> data =
        FXCollections.observableArrayList(
            new Country("USA", "Washington DC", 280, true),
            new Country("Canada", "Ottawa", 32, true),
            new Country("United Kingdom", "London", 60, true),
            new Country("Germany", "Berlin", 83, true),
            new Country("France", "Paris", 60, true));
    tableView.setItems(data);

    TableColumn<Country, String> countryColumn = new TableColumn<>("Country");
    countryColumn.setMinWidth(100);
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

    TableColumn<Country, String> capitalColumn = new TableColumn<>("Capital");
    capitalColumn.setMinWidth(100);
    capitalColumn.setCellValueFactory(new PropertyValueFactory<>("capital"));

    TableColumn<Country, Double> populationColumn = new TableColumn<>("Population (million)");
    populationColumn.setMinWidth(200);
    populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));

    TableColumn<Country, Boolean> democraticColumn = new TableColumn<>("Is Democratic?");
    democraticColumn.setMinWidth(200);
    democraticColumn.setCellValueFactory(new PropertyValueFactory<>("democratic"));

    //noinspection unchecked
    tableView.getColumns().addAll(countryColumn, capitalColumn, populationColumn, democraticColumn);

    // Allow user to add data to the table
    FlowPane flowPane = new FlowPane(3, 3);
    TextField tfCountry = new TextField();
    TextField tfCapital = new TextField();
    TextField tfPopulation = new TextField();
    CheckBox chkDemocratic = new CheckBox("Is Democratic?");
    Button btAddRow = new Button("Add new row");
    tfCountry.setPrefColumnCount(5);
    tfCapital.setPrefColumnCount(5);
    tfPopulation.setPrefColumnCount(5);
    flowPane
        .getChildren()
        .addAll(
            new Label("Country: "),
            tfCountry,
            new Label("Capital: "),
            tfCapital,
            new Label("Population: "),
            tfPopulation,
            chkDemocratic,
            btAddRow);

    btAddRow.setOnAction(
        event -> {
          data.add(
              new Country(
                  tfCountry.getText(),
                  tfCapital.getText(),
                  Double.parseDouble(tfPopulation.getText()),
                  chkDemocratic.isSelected()));
          tfCountry.clear();
          tfCapital.clear();
          tfPopulation.clear();
        });

    // Allow table data to be edited
    tableView.setEditable(true);
    countryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    countryColumn.setOnEditCommit(
        event ->
            event
                .getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setCountry(event.getNewValue()));

    capitalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    capitalColumn.setOnEditCommit(
        event ->
            event
                .getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setCapital(event.getNewValue()));

    // Convert double to string
    populationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    populationColumn.setOnEditCommit(
        event ->
            event
                .getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setPopulation(event.getNewValue()));

    // Convert boolean to string
    democraticColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
    democraticColumn.setOnEditCommit(
        event ->
            event
                .getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setDemocratic(event.getNewValue()));

    BorderPane pane = new BorderPane();
    pane.setCenter(tableView);
    pane.setBottom(flowPane);
    Scene scene = new Scene(pane, 600, 300);
    primaryStage.setTitle("TableView Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static class Country {
    private final SimpleStringProperty country;
    private final SimpleStringProperty capital;
    private final SimpleDoubleProperty population;
    private final SimpleBooleanProperty democratic;

    // Default constructor
    private Country(String country, String capital, double population, boolean democratic) {
      this.country = new SimpleStringProperty(country);
      this.capital = new SimpleStringProperty(capital);
      this.population = new SimpleDoubleProperty(population);
      this.democratic = new SimpleBooleanProperty(democratic);
    }

    @SuppressWarnings("unused")
    public String getCapital() {
      return capital.get();
    }

    public void setCapital(String capital) {
      this.capital.set(capital);
    }

    @SuppressWarnings("unused")
    public double getPopulation() {
      return population.get();
    }

    @SuppressWarnings("unused")
    public void setPopulation(double population) {
      this.population.set(population);
    }

    @SuppressWarnings("unused")
    public boolean isDemocratic() {
      return democratic.get();
    }

    @SuppressWarnings("unused")
    public void setDemocratic(boolean democratic) {
      this.democratic.set(democratic);
    }

    @SuppressWarnings("unused")
    public SimpleStringProperty countryProperty() {
      return country;
    }

    @SuppressWarnings("unused")
    public SimpleStringProperty capitalProperty() {
      return capital;
    }

    @SuppressWarnings("unused")
    public SimpleDoubleProperty populationProperty() {
      return population;
    }

    @SuppressWarnings("unused")
    public SimpleBooleanProperty democraticProperty() {
      return democratic;
    }

    @SuppressWarnings("unused")
    public String getCountry() {
      return country.get();
    }

    public void setCountry(String country) {
      this.country.set(country);
    }
  }
}
