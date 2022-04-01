package GUI;

import Metrics.Halstead;
import Metrics.LOC;
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

    public void clickOnLOCMetricsButton() throws IOException {
        String path = metricsTextField.getText();
        if(path.isEmpty()){
            metricsLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }
        LOC loc = new LOC();
        loc.DoLOC(path);
    }

    public void clickOnHalsteadMetricsButton() throws IOException {
        String path = metricsTextField.getText();
        if(path.isEmpty()){
            metricsLabel.setTextFill(Paint.valueOf("RED"));
            return;
        }
        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
