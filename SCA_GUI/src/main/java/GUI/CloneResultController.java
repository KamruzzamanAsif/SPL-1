package GUI;

import Clone.CloneMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CloneResultController{
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
        csvOutputFileWriting(); // making csv file
        makeGridPane(); // making a GridPane to show the result in scene
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


    @FXML
    private GridPane cloneGridPane;
    private void makeGridPane(){
        // We are filling GridPane from our CSV File
        String CsvFile = "src/main/resources/result/cloneResult.csv";
        String FieldDelimiter = ",";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            int x = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                int y = 0;
                for(String str: fields){
                    Label label = new Label(str);
                    label.setWrapText(true);
                    label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
                    label.setAlignment(Pos.CENTER);
                    GridPane.setConstraints(label,y++, x);
                    GridPane.setHalignment(label, HPos.CENTER);
                    GridPane.setValignment(label, VPos.CENTER);
                    if(str.contains("High")){
                        label.setTextFill(Paint.valueOf("RED"));
                    }
                    else if(str.contains("Moderate")){
                        label.setTextFill(Paint.valueOf("Blue"));
                    }
                    else if(str.contains("Very Low") || str.contains("Low")){
                        label.setTextFill(Paint.valueOf("Green"));
                    }
                    cloneGridPane.getChildren().add(label);
                }
                x++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label cloneResultPrintSuccessfulMessage;
    public void clickOnPrintResultButton() throws IOException {
        csvOutputFileWriting();
        cloneResultPrintSuccessfulMessage.setDisable(false);
        cloneResultPrintSuccessfulMessage.setVisible(true);
    }

}
