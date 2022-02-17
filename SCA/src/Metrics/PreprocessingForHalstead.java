package Metrics;

import Preprocessing.BlankLineRemover;
import Preprocessing.CommentRemover;
import Preprocessing.FrontWhiteSpaceRemover;
import Preprocessing.HashDirectiveRemover;

import java.io.IOException;
import java.util.ArrayList;

public class PreprocessingForHalstead {
    String path;
    ArrayList<String> finalProcessedCodeForHalstead;

    CommentRemover commentRemover;
    FrontWhiteSpaceRemover frontWhiteSpaceRemover;
    BlankLineRemover blankLineRemover;
    HashDirectiveRemover hashDirectiveRemover;

    ArrayList<String> commentRemovedCode;
    ArrayList<String> frontWhiteSpaceRemovedCode;
    ArrayList<String> blankLineRemovedCode;
    ArrayList<String> hashDirectiveRemovedCode;

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
    }

    public ArrayList<String> getPreprocessedCodeForHalstead(){
        finalProcessedCodeForHalstead = hashDirectiveRemovedCode;
        return finalProcessedCodeForHalstead;
    }
}
