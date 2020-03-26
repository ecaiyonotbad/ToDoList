package ui.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import network.Weather;
import network.WeatherMonitor;
import program.checkers.InputChecker;
import program.events.Event;
import program.events.RegularEvent;
import program.exceptions.DateValueException;
import program.exceptions.MonthValueException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


//Icon sources:
//https://www.iconfinder.com/icons/4831037/cancel_circle_close_delete_exit_quit_icon
//https://www.iconfinder.com/iconsets/weather-and-forecast-free
//https://www.iconfinder.com/icons/352303/delete_icon

//UI tutorial:
//Some code taken and modified from this repo
//https://github.com/buckyroberts/Source-Code-from-Tutorials/tree/master/JavaFX


public class MainGui extends Application {
    Weather weather = new Weather(new WeatherMonitor("Edward"));
    InputChecker checker = new InputChecker();
    GuiFIleIO fileIO = new GuiFIleIO();
    AlertBox alertBox = new AlertBox();

    Stage window;
    Scene mainScene;
    final int width = 1100;
    final int height = 563;
    final int colWidth = 100;

    TextField nameInput = makeTextField("event name", 100);
    TextField locationInput = makeTextField("location", 100);
    TextField yearInput = makeTextField("year", 70);
    TextField monthInput = makeTextField("month", 70);
    TextField dateInput = makeTextField("date", 70);

    TableView<Event> table;

    ArrayList<Event> eventList = new ArrayList<>();
    ObservableList<Event> observableList = FXCollections.observableList(eventList);


    public static void main(String[] args) {
        launch(args);
    }

    //MODIFIES: this
    //EFFECTS: run the GUI
    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            fileIO.load("myToDoList.txt", eventList);
            mainMenu();
            window.setOnCloseRequest(e -> {
                        e.consume();
                        quitProgram();
                    }
            );
            mainScene.getStylesheets().add("ui/gui/Cool.css");

        } catch (IOException e) {
            alertBox.display("IO Exception", "IO Exception caught");
        }
    }

    private void quitProgram() {
        try {
            fileIO.save("myToDoList.txt", eventList);
            window.close();
        } catch (IOException e) {
            alertBox.display("IO Exception", "IO Exception caught");
        }
    }

    private String tempIconUrl() {
        if (weather.getTemperature() == null) {
            return "data/Icon/MedTempIcon.png";
        }
        int temp = Integer.parseInt(weather.getTemperature());
        if (temp >= 30) {
            return "data/Icon/HotTempIcon.png";
        } else if (temp > 15 && temp < 30) {
            return "data/Icon/MedTempIcon.png";
        } else {
            return "data/Icon/ColdTempIcon.png";
        }
    }

    private boolean isNight() {
        Calendar cal = Calendar.getInstance();
        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay >= 18) {
            return true;
        } else if (hourOfDay >= 6) {
            return false;
        }
        return true;
    }

    private String weatherIconUrl() {
        String condition = weather.getDescription();
        if (condition == null) {
            return "data/Icon/SunnyIcon.png";
        } else if (condition.contains("clouds") && !isNight()) {
            return "data/Icon/CloudIcon.png";
        } else if (condition.contains("clouds") && isNight()) {
            return "data/Icon/CloudyMoonIcon.png";
        } else if (condition.contains("clear") && !isNight()) {
            return "data/Icon/SunnyIcon.png";
        } else if (condition.contains("clear") && isNight()) {
            return "data/Icon/ClearMoonIcon.png";
        } else if (condition.contains("snow")) {
            return "data/Icon/SnowIcon.png";
        } else if (condition.contains("rain")) {
            return "data/Icon/RainingIcon.png";
        } else {
            return "data/Icon/SunnyIcon.png";
        }
    }

    private Image displayTempIcon() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(tempIconUrl());
        Image img = new Image(inputStream, 32, 32, true, true);
        return img;
    }

    private Image displayWeatherIcon() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(weatherIconUrl());
        Image img = new Image(inputStream, 32, 32, true, true);
        return img;
    }


    private void displayWeatherIcon(GridPane gridPane) {
        try {
            Image tempIcon = displayTempIcon();
            Image conditionIcon = displayWeatherIcon();
            gridPane.add(new ImageView(tempIcon), 1, 0);
            gridPane.add(new ImageView(conditionIcon), 0, 0);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void makeWeatherWelcomeMessege(GridPane gridPane) {
        try {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            weather.loadWeather("Vancouver", "ca");
            Label weatherLabel = new Label("Temperature: " + weather.getTemperature()
                    + " degrees Celsius in " + weather.getCity());
            hbox.getChildren().add(weatherLabel);
            gridPane.add(hbox, 0, 1, 5, 1);
        } catch (IOException e) {
            alertBox.display("Error", "Connect to Wi-Fi to display weather");
        }
    }

    private TableColumn<Event, String> makeTableColumn(String name, String valueFactory) {
        TableColumn<Event, String> column = new TableColumn<>(name);
        column.setMinWidth(colWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(valueFactory));
        return column;
    }

    private void makeTable(GridPane gridPane) {
        //Table view
        table = new TableView<>();

        TableColumn<Event, String> nameCol = makeTableColumn("Name", "name");
        nameCol.setMinWidth(150);
        TableColumn<Event, String> locCol = makeTableColumn("Location", "location");
        TableColumn<Event, String> yearCol = makeTableColumn("Year", "year");
        TableColumn<Event, String> monthCol = makeTableColumn("Month", "month");
        TableColumn<Event, String> dateCol = makeTableColumn("Date", "date");

        table.getColumns().addAll(nameCol, locCol, yearCol, monthCol, dateCol);

        table.setItems(observableList);

        gridPane.add(table, 2, 2, 1, 5);
    }

    private void makeMainMenuVbox(GridPane gridPane) {
        try {
            Button quit = new Button("Quit");
            addIcon(quit, "data/Icon/QuitIcon.png");
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.getChildren().addAll(quit);
            quit.setOnAction(e -> quitProgram());
            gridPane.add(vbox, 0, 3, 1, 3);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private TextField makeTextField(String promptText, int maxWidth) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMaxWidth(maxWidth);
        return textField;
    }

    private void addIcon(Button btn, String url) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(url);
        Image img = new Image(inputStream, 16, 16, true, true);
        btn.setGraphic(new ImageView(img));
    }

    private void makeBottomHBox(GridPane gridPane) {
        try {
            //Hbox components
            Button addBtn = new Button("Add");
            addIcon(addBtn, "data/Icon/AddIcon.png");
            Button deleteBtn = new Button("Delete");
            addIcon(deleteBtn, "data/Icon/TrashIcon.png");
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(nameInput, locationInput, yearInput,
                    monthInput, dateInput, addBtn, deleteBtn);
            addBtn.setOnAction(e -> addEvent());
            deleteBtn.setOnAction(e -> deleteEvent());
            gridPane.add(hbox, 1, 8, 4, 1);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

    }

    private GridPane makeGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        return gridPane;
    }

    private void mainMenu() {
        GridPane gridPane = makeGridPane();
        gridPane.setAlignment(Pos.CENTER);
        window.setTitle("TodoList");

        //components
        makeTable(gridPane);
        makeMainMenuVbox(gridPane);
        makeBottomHBox(gridPane);
        makeWeatherWelcomeMessege(gridPane);
        displayWeatherIcon(gridPane);

        //set scenes
        mainScene = new Scene(gridPane, width, height);
        window.setScene(mainScene);
        window.show();
    }


    private void deleteEvent() {
        ObservableList<Event> list = table.getItems();
        ObservableList<Event> selected = table.getSelectionModel().getSelectedItems();
        selected.forEach(list::remove);

    }


    private ObservableList<Event> addEvent() {
        try {
            Event event = setNewEvent(nameInput.getText(), locationInput.getText(), yearInput.getText(),
                    monthInput.getText(), dateInput.getText());
            checkMonth(event.getMonth());
            checkDate(event.getDate());
            observableList.add(event);
            clearInput();
            return observableList;
        } catch (NumberFormatException e) {
            alertBox.display("Error", "Input can't be empty\n" + "Year, Month, Date has to be integer.");
        } catch (MonthValueException e) {
            alertBox.display("Error", "Month should be in between 1 to 12");
        } catch (DateValueException e) {
            alertBox.display("Error", "Date should be in between 1 to 30");
        }
        return null;
    }

    private Event setNewEvent(String name, String location, String year, String month, String date) {
        Event event = new RegularEvent();
        event.setName(name);
        event.setLocation(location);
        event.setYear(Integer.parseInt(year));
        event.setMonth(Integer.parseInt(month));
        event.setDate(Integer.parseInt(date));
        return event;
    }

    private void checkMonth(int month) throws MonthValueException {
        if (!checker.monthCheck(month)) {
            throw new MonthValueException();
        }
    }

    private void checkDate(int date) throws DateValueException {
        if (!checker.dateCheck(date)) {
            throw new DateValueException();
        }
    }


    private void clearInput() {
        nameInput.clear();
        locationInput.clear();
        yearInput.clear();
        monthInput.clear();
        dateInput.clear();
    }
}
