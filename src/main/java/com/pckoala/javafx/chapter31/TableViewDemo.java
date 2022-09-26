package com.pckoala.javafx.chapter31;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    Pane pane = new Pane();
    pane.getChildren().add(tableView);
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public void setCountry(String country) {
      this.country.set(country);
    }
  }
}
