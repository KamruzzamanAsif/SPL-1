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
//        for(String line: commentRemovedCode){
//            System.out.println(line);
//        }

        frontWhiteSpaceRemover = new FrontWhiteSpaceRemover();
        frontWhiteSpaceRemovedCode = frontWhiteSpaceRemover.removeFrontWhiteSpace(commentRemovedCode);
//        for(String line: frontWhiteSpaceRemovedCode){
//            System.out.println(line);
//        }

        blankLineRemover = new BlankLineRemover();
        blankLineRemovedCode = blankLineRemover.blankLineRemover(frontWhiteSpaceRemovedCode);
//        for(String line: blankLineRemovedCode){
//            System.out.println(line);
//        }

        hashDirectiveRemover = new HashDirectiveRemover();
        hashDirectiveRemovedCode = hashDirectiveRemover.removeHashDirective(blankLineRemovedCode);
//        for(String line: hashDirectiveRemovedCode){
//            System.out.println(line);
//        }

        functionDeclarationRemover = new FunctionDeclarationRemover();
        functionDeclarationRemover.removeFunctionDeclaration(hashDirectiveRemovedCode);
    }

    public ArrayList<String> getPreprocessedCodeForHalstead(){
        finalProcessedCodeForHalstead = hashDirectiveRemovedCode;
        return finalProcessedCodeForHalstead;
    }
}
