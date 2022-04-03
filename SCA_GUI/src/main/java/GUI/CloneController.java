package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CloneController {
    public void clickOnCloneBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SCAInterface.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private AnchorPane cloneAnchorPane;
    @FXML
    private TextField cloneTextField;
    @FXML
    private Label cloneLabel1;
    @FXML
    private Label cloneLabel2;

    public void clickOnBrowseButton(){
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) cloneAnchorPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if(file!=null){
            cloneTextField.setText(file.getAbsolutePath());
        }
    }

    public void clickOnDetectClone(ActionEvent event) throws IOException {
        String directoryPath = cloneTextField.getText();
        if(directoryPath.isEmpty()){
            cloneLabel1.setTextFill(Paint.valueOf("RED"));
            return;
        }

        File[] files = new File(directoryPath).listFiles();
        assert files != null;
        if(files.length>10){
            cloneLabel2.setTextFill(Paint.valueOf("RED"));
            return;
        }
//        for (File file : files) {
//            System.out.println(file.getAbsolutePath());
//        }

        // Now we have to pass the file paths to detect clone
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cloneResult.fxml"));
        Parent root = loader.load();
        CloneResultController cloneResultController = loader.getController();
        cloneResultController.showCloneResult(files);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
