import Metrics.Halstead;
import Metrics.LOC;

import java.io.IOException;

public class Main {

    public static void main(String[]args) throws IOException {

        //String path = "G:/SCA/src/LOC test.c";
        String path = "G:/SCA/src/test/test1.c";
        //String path = "G:/SCA/src/test/loc1.c";


        LOC loc = new LOC();
        loc.DoLOC(path);

//        CommentRemover commentRemover = new CommentRemover();
//        ArrayList<String> commentRemovedCode = new ArrayList();
//        commentRemovedCode = commentRemover.removeComment(path);
//        for(String line: commentRemovedCode){
//            System.out.println(line);
//        }
//
//        FrontWhiteSpaceRemover frontWhiteSpaceRemover = new FrontWhiteSpaceRemover();
//        ArrayList<String> frontWhiteSpaceRemovedCode = new ArrayList<>();
//        frontWhiteSpaceRemovedCode = frontWhiteSpaceRemover.removeFrontWhiteSpace(commentRemovedCode);
//        for(String line: frontWhiteSpaceRemovedCode){
//            System.out.println(line);
//        }
//
//        BlankLineRemover blankLineRemover = new BlankLineRemover();
//        ArrayList<String> blankLineRemovedCode = new ArrayList<>();
//        blankLineRemovedCode = blankLineRemover.blankLineRemover(frontWhiteSpaceRemovedCode);
//        for(String line: blankLineRemovedCode){
//            System.out.println(line);
//        }
//
//        HashDirectiveRemover hashDirectiveRemover = new HashDirectiveRemover();
//        ArrayList<String> hashDirectiveRemovedCode = new ArrayList<>();
//        hashDirectiveRemovedCode = hashDirectiveRemover.removeHashDirective(blankLineRemovedCode);
//        for(String line: hashDirectiveRemovedCode){
//            System.out.println(line);
//        }


//        PreprocessingForHalstead preprocessingForHalstead = new PreprocessingForHalstead();
//        preprocessingForHalstead.setPath(path);
//        preprocessingForHalstead.process();
//        ArrayList<String> preprocessedCodeForHalstead = new ArrayList<>();
//        preprocessedCodeForHalstead = preprocessingForHalstead.getPreprocessedCodeForHalstead();
//        for(String line: preprocessedCodeForHalstead){
//            System.out.println(line);


        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();

    }

}
