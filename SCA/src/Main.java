import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[]args) throws IOException {
        //String path = "G:/SCA/src/editDistance.cpp";
        String path = "src/LOC test.c";

        LOC loc = new LOC();
        loc.LOC(path);

        CommentRemover commentRemover = new CommentRemover();
        ArrayList<String> commentRemovedCode = new ArrayList();
        commentRemovedCode = commentRemover.removeComment(path);
        for(String line: commentRemovedCode){
            System.out.println(line);
        }
    }

}
