package GUI;

import Metrics.LOC;
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

public class LOCController {
    public void clickOnLocBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("metrics.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextArea locTextArea;
    public void showLOC(String path) throws IOException {
        LOC loc = new LOC();
        loc.DoLOC(path);

        int numberOfBlankLine = loc.getNumberOfBlankLine();
        locTextArea.setText("Total Blank lines: " + numberOfBlankLine + "\n");
        int numberOfPhysicalLine = loc.getNumberOfPhysicalLine();
        locTextArea.appendText("\nTotal Physical lines: " + numberOfPhysicalLine + "\n");
        int numberOfLogicalLine = loc.getNumberOfLogicalStatements();
        locTextArea.appendText("\nTotal Logical Statements: " + numberOfLogicalLine + "\n");
        int onlyCommentLine = loc.getOnlyCommentLine();
        locTextArea.appendText("\nNumber of Only Comment Lines: " + onlyCommentLine + "\n");
        int commentAndStatementLines = loc.getCommentAndStatementLines();
        locTextArea.appendText("\nNumber of Comment and Statement lines: " + commentAndStatementLines + "\n");
        int onlyStatementLines = loc.getOnlyStatementLines();
        locTextArea.appendText("\nNumber of Only Statement line: " + onlyStatementLines + "\n");

        locTextArea.setEditable(false);
        locTextArea.setMouseTransparent(true);
        locTextArea.setFocusTraversable(false);
    }
}
