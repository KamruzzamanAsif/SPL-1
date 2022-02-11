import Metrics.LOC;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[]args) throws IOException {
        //String path = "G:/SCA/src/editDistance.cpp";
        String path = "G:/SCA/src/LOC test.c";

        LOC loc = new LOC();
        loc.LOC(path);

        CommentRemover commentRemover = new CommentRemover();
        ArrayList<String> commentRemovedCode = new ArrayList();
        commentRemovedCode = commentRemover.removeComment(path);
        for(String line: commentRemovedCode){
            System.out.println(line);
        }

        FrontWhiteSpaceRemover frontWhiteSpaceRemover = new FrontWhiteSpaceRemover();
        ArrayList<String> frontWhiteSpaceRemovedCode = new ArrayList<>();
        frontWhiteSpaceRemovedCode = frontWhiteSpaceRemover.removeFrontWhiteSpace(commentRemovedCode);
        for(String line: frontWhiteSpaceRemovedCode){
            System.out.println(line);
        }

        BlankLineRemover blankLineRemover = new BlankLineRemover();
        ArrayList<String> blankLineRemovedCode = new ArrayList<>();
        blankLineRemovedCode = blankLineRemover.blankLineRemover(frontWhiteSpaceRemovedCode);
        for(String line: blankLineRemovedCode){
            System.out.println(line);
        }
    }

}
