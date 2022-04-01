package GUI;

import Metrics.Halstead;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HalsteadController {
    public void clickOnHalsteadBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("metrics.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextArea halsteadTextArea;
    public void showHalstead(String path) throws IOException {
        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();

        int numberOfDistinctOperators = halstead.getNumberOfDistinctOperators();
        halsteadTextArea.setText("Number of Distinct Operators, n1: " + numberOfDistinctOperators + "\n\n");
        int numberOfDistinctOperands = halstead.getNumberOfDistinctOperands();
        halsteadTextArea.appendText("Number of Distinct Operands, n2: " + numberOfDistinctOperands + "\n\n");
        int totalNumberOfOperators = halstead.getTotalNumberOfOperators();
        halsteadTextArea.appendText("Total number of Operators, N1: " + totalNumberOfOperators + "\n\n");
        int totalNumberOfOperands = halstead.getTotalNumberOfOperands();
        halsteadTextArea.appendText("Total number of Operands, N2: " + totalNumberOfOperands + "\n\n");


        int programVocabulary = halstead.getProgramVocabulary();
        halsteadTextArea.appendText("Program Vocabulary, n: " + programVocabulary + "\n\n");
        int programLength = halstead.getProgramLength();
        halsteadTextArea.appendText("Program Length, N: " + programLength + "\n\n");
        double calculatedProgramLength = halstead.getCalculatedProgramLength();
        halsteadTextArea.appendText("Calculated estimated Program Length, N_es: " + calculatedProgramLength + "\n\n");
        double volume = halstead.getVolume();
        halsteadTextArea.appendText("Volume, V: " + volume + "\n\n");
        double difficulty = halstead.getDifficulty();
        halsteadTextArea.appendText("Difficulty, D: " + difficulty + "\n\n");
        double effort = halstead.getEffort();
        halsteadTextArea.appendText("Effort, E: " + effort + "\n\n");
        double timeRequiredToProgram = halstead.getTimeRequiredToProgram();
        halsteadTextArea.appendText("Time Required to Program, T: " + timeRequiredToProgram + " seconds" + "\n\n");
        double numberOfDeliveredBugs = halstead.getNumberOfDeliveredBugs();
        halsteadTextArea.appendText("Number of Delivered Bugs, B: " + numberOfDeliveredBugs + "\n\n");

        halsteadTextArea.setEditable(false);
        halsteadTextArea.setFocusTraversable(false);
    }
}
