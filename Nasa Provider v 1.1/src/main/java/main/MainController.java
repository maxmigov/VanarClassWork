package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import space.Asteroid;
import space.NASADataProvider;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainController {

    @FXML
    public TextField startDateInput;
    @FXML
    public TextField endDateInput;
    @FXML
    public TextArea textArea;

    @FXML
    public void getData() {

        List<Asteroid> GUIAsteroids = null;
        try {
            GUIAsteroids = new NASADataProvider().getNeoAsteroids(startDateInput.getText(), endDateInput.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (Asteroid asteroid : GUIAsteroids) {
            textArea.appendText(asteroid.getDate().format(formatter) + "  " +
                    String.valueOf((asteroid.getDistance()/1000000)) + " " + "mln km from Earth;  " +
                    String.valueOf(asteroid.getDiameter()*100) + " " + "km diameter;  " +
                    String.valueOf(asteroid.getHazardous()  ? "It is hazardous!" : "It is not hazardous!") + "\n");
        }

    }
    @FXML
    public void clearData() {
        textArea.clear();
    }

    @FXML
    public void helpWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/secondwindow.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Help");
        stage.setResizable(false);
        Image image = new Image("/info-logo.png");
        stage.getIcons().add(image);
        stage.show();
    }

}