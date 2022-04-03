package GUI;

import Clone.CloneMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CloneResultController {
    File[] files;
    ArrayList<String> filePaths = new ArrayList<>();
    private final int kGramSize = 3;

    public void clickOnCloneBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clone.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showCloneResult(File[] files) throws IOException {
        this.files = files;
        copyFilePaths();
        detectAllFileClone();
    }

    public void copyFilePaths(){
        for(File file: files){
            String path = file.getAbsolutePath();
            filePaths.add(path);
        }
    }

    public void detectAllFileClone() throws IOException {
        CloneMain cloneMain;
        for(int i=0; i<filePaths.size(); i++){
            for(int j=0; j<filePaths.size(); j++){
                if(j!=i){
                    cloneMain = new CloneMain(kGramSize, filePaths.get(i), filePaths.get(j));
                    cloneMain.cloneProcess();
                    System.out.println(cloneMain.getCloneResult());
                }
            }
        }
    }
}
