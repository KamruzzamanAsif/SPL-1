package GUI;

import Metrics.Halstead;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MetricsController implements Initializable {

    public void clickOnMetricsBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SCAInterface.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private AnchorPane metricsAnchorPane;
    @FXML
    private TextField metricsTextField;
    @FXML
    private Label metricsLabel;

    public void clickOnBrowseButton(){
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) metricsAnchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){
            metricsTextField.setText(file.getAbsolutePath());
        }
    }

    public void clickOnLOCMetricsButton(ActionEvent event) throws IOException {
        String path = metricsTextField.getText();
        if(path.isEmpty()){
            metricsLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loc.fxml"));
        Parent root = loader.load();
        LOCController locController = loader.getController();
        locController.showLOC(path);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickOnHalsteadMetricsButton(ActionEvent event) throws IOException {
        String path = metricsTextField.getText();
        if(path.isEmpty()){
            metricsLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("halstead.fxml"));
        Parent root = loader.load();
        HalsteadController halsteadController = loader.getController();
        halsteadController.showHalstead(path);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
