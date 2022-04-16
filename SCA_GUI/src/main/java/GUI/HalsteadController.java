package GUI;

import Metrics.Halstead;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class HalsteadController {

    @FXML
    private TextFlow halsteadTextFlow;

    public void showHalstead(String path) throws IOException {
        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();

        int numberOfDistinctOperators = halstead.getNumberOfDistinctOperators();
        Text text1 = new Text("Number of Distinct Operators, n1: " + numberOfDistinctOperators +"\n\n");
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int numberOfDistinctOperands = halstead.getNumberOfDistinctOperands();
        Text text2 = new Text("Number of Distinct Operands, n2: " + numberOfDistinctOperands + "\n\n");
        text2.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int totalNumberOfOperators = halstead.getTotalNumberOfOperators();
        Text text3 = new Text("Total number of Operators, N1: " + totalNumberOfOperators + "\n\n");
        text3.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int totalNumberOfOperands = halstead.getTotalNumberOfOperands();
        Text text4 = new Text("Total number of Operands, N2: " + totalNumberOfOperands + "\n\n");
        text4.setFont(Font.font("Verdana", FontWeight.BOLD, 13));


        int programVocabulary = halstead.getProgramVocabulary();
        Text text5 = new Text("Program Vocabulary, n: " + programVocabulary + "\n\n");
        text5.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int programLength = halstead.getProgramLength();
        Text text6 = new Text("Program Length, N: " + programLength + "\n\n");
        text6.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        double calculatedProgramLength = halstead.getCalculatedProgramLength();
        Text text7 = new Text("Calculated estimated Program Length, N_es: " + calculatedProgramLength + "\n\n");
        text7.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        double volume = halstead.getVolume();
        Text text8 = new Text("Volume, V: " + volume + "\n\n");
        text8.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        if(volume>8000 || volume<100)
            text8.setFill(Color.RED);

        double difficulty = halstead.getDifficulty();
        Text text9 = new Text("Difficulty, D: " + difficulty + "\n\n");
        text9.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        double effort = halstead.getEffort();
        Text text10 = new Text("Effort, E: " + effort + "\n\n");
        text10.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        double timeRequiredToProgram = halstead.getTimeRequiredToProgram();
        Text text11 = new Text("Time Required to Program, T: " + timeRequiredToProgram + " seconds" + "\n\n");
        text11.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        double numberOfDeliveredBugs = halstead.getNumberOfDeliveredBugs();
        Text text12 = new Text("Number of Delivered Bugs, B: " + numberOfDeliveredBugs);
        text12.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        if(numberOfDeliveredBugs>=2)
            text12.setFill(Color.RED);

        halsteadTextFlow.getChildren().addAll(text1, text2, text3, text4, text5, text6, text7,
                text8, text9, text10, text11, text12);



    }
}
