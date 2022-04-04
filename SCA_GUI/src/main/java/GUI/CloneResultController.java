package GUI;

import Clone.CloneMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CloneResultController {
    File[] files;
    ArrayList<String> filePaths = new ArrayList<>();
    private final int kGramSize = 3;
    private double[][] cloneResults;
    private String[] fileNames;

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
        copyFileNames();
        detectAllFileClone();
        csvOutputFileWriting();
    }

    public void copyFilePaths(){
        for(File file: files){
            String path = file.getAbsolutePath();
            filePaths.add(path);
        }
    }

    public void copyFileNames(){
        fileNames = new String[filePaths.size()];
        int i=0;
        for(File file: files){
            String name = file.getName();
            fileNames[i++]=name;
        }
    }

    public void detectAllFileClone() throws IOException {
        cloneResults = new double[filePaths.size()][filePaths.size()];
        CloneMain cloneMain;
        for(int i=0; i<filePaths.size(); i++){
            for(int j=0; j<filePaths.size(); j++){
                cloneMain = new CloneMain(kGramSize, filePaths.get(i), filePaths.get(j));
                cloneMain.cloneProcess();
                //System.out.println(cloneMain.getCloneResult());
                cloneResults[i][j] = cloneMain.getCloneResult();
            }
        }
    }


    public void csvOutputFileWriting() throws IOException {
        FileWriter csvWriter= new FileWriter("src/main/resources/result/" + "cloneResult" + ".csv");

        for (int i=0; i<=fileNames.length; i++)
        {
            if(i==0) csvWriter.append("file");
            else csvWriter.append(fileNames[i-1]);
            if(i!= fileNames.length) csvWriter.append(",");
        }

        csvWriter.append("\n");

        for(int i=0; i<fileNames.length; i++)
        {
            csvWriter.append(fileNames[i]);
            csvWriter.append(",");
            for (int j = 0; j < fileNames.length; j++)
            {
                String similarity;

                if(cloneResults[i][j]>=80)
                    similarity= "High";
                else if(cloneResults[i][j]>=65)
                    similarity= "Moderate";
                else if(cloneResults[i][j]>=50)
                    similarity= "Low";
                else
                    similarity= "Very Low";

                DecimalFormat df = new DecimalFormat("0.000");
                csvWriter.append(String.valueOf(df.format(cloneResults[i][j]))).append("%    ").append(similarity);
                if(j!= fileNames.length-1)
                    csvWriter.append(",");
            }

            csvWriter.append("\n");
        }
        csvWriter.close();
    }
}
