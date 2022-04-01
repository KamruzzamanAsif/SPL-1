package Metrics;

import Preprocessing.*;

import java.io.IOException;
import java.util.ArrayList;

public class PreprocessingForHalstead {
    String path;
    ArrayList<String> finalProcessedCodeForHalstead;

    CommentRemover commentRemover;
    FrontWhiteSpaceRemover frontWhiteSpaceRemover;
    BlankLineRemover blankLineRemover;
    HashDirectiveRemover hashDirectiveRemover;
    FunctionDeclarationRemover functionDeclarationRemover;

    ArrayList<String> commentRemovedCode;
    ArrayList<String> frontWhiteSpaceRemovedCode;
    ArrayList<String> blankLineRemovedCode;
    ArrayList<String> hashDirectiveRemovedCode;
    ArrayList<String> functionDeclarationRemovedCode;

    public void setPath(String path){
        this.path = path;
        return;
    }

    public void process() throws IOException {
        commentRemover = new CommentRemover();
        commentRemovedCode = commentRemover.removeComment(path);
        frontWhiteSpaceRemover = new FrontWhiteSpaceRemover();
        frontWhiteSpaceRemovedCode = frontWhiteSpaceRemover.removeFrontWhiteSpace(commentRemovedCode);
        blankLineRemover = new BlankLineRemover();
        blankLineRemovedCode = blankLineRemover.blankLineRemover(frontWhiteSpaceRemovedCode);
        hashDirectiveRemover = new HashDirectiveRemover();
        hashDirectiveRemovedCode = hashDirectiveRemover.removeHashDirective(blankLineRemovedCode);
        functionDeclarationRemover = new FunctionDeclarationRemover();
        functionDeclarationRemovedCode = functionDeclarationRemover.removeFunctionDeclaration(hashDirectiveRemovedCode);
    }

    public ArrayList<String> getPreprocessedCodeForHalstead(){
        finalProcessedCodeForHalstead = functionDeclarationRemovedCode;
        return finalProcessedCodeForHalstead;
    }
}
