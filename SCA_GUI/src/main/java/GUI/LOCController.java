package GUI;

import Metrics.LOC;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class LOCController {

    @FXML
    private TextFlow locTextFlow;
    public void showLOC(String path) throws IOException {
        LOC loc = new LOC();
        loc.DoLOC(path);

        int numberOfBlankLine = loc.getNumberOfBlankLine();
        Text text1 = new Text("Total Blank lines: " + numberOfBlankLine + "\n");
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int numberOfPhysicalLine = loc.getNumberOfPhysicalLine();
        Text text2 = new Text("\nTotal Physical lines: " + numberOfPhysicalLine + "\n");
        text2.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int numberOfLogicalLine = loc.getNumberOfLogicalStatements();
        Text text3 = new Text("\nTotal Logical Statements: " + numberOfLogicalLine + "\n");
        text3.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int onlyCommentLine = loc.getOnlyCommentLine();
        Text text4 = new Text("\nNumber of Only Comment Lines: " + onlyCommentLine + "\n");
        text4.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int commentAndStatementLines = loc.getCommentAndStatementLines();
        Text text5 = new Text("\nNumber of Comment and Statement lines: " + commentAndStatementLines + "\n");
        text5.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        int onlyStatementLines = loc.getOnlyStatementLines();
        Text text6 = new Text("\nNumber of Only Statement line: " + onlyStatementLines);
        text6.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

       locTextFlow.getChildren().addAll(text1,text2,text3,text4,text5,text6);
    }
}
