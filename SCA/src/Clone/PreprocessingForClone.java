package Clone;

import Preprocessing.*;

import java.io.IOException;
import java.util.ArrayList;

public class PreprocessingForClone {
    String path;
    ArrayList<String> finalPreprocessedCodeForClone = new ArrayList<>();

    CommentRemover commentRemover;
    BlankLineRemover blankLineRemover;
    HashDirectiveRemover hashDirectiveRemover;
    PunctuationAndSpaceRemover punctuationAndSpaceRemover;
    CapitalizationRemover capitalizationRemover;

    ArrayList<String> commentRemovedCode = new ArrayList<>();
    ArrayList<String> blankLineRemovedCode = new ArrayList<>();
    ArrayList<String> hashDirectiveRemovedCode = new ArrayList<>();
    ArrayList<String> punctuationAndSpaceRemovedCode = new ArrayList<>();
    ArrayList<String> capitalizationRemovedCode = new ArrayList<>();

    public PreprocessingForClone(String path){
        this.path = path;
    }

    public void process() throws IOException {
        commentRemover = new CommentRemover();
        commentRemovedCode = commentRemover.removeComment(path);
        blankLineRemover = new BlankLineRemover();
        blankLineRemovedCode = blankLineRemover.blankLineRemover(commentRemovedCode);
        hashDirectiveRemover = new HashDirectiveRemover();
        hashDirectiveRemovedCode = hashDirectiveRemover.removeHashDirective(blankLineRemovedCode);
        punctuationAndSpaceRemover = new PunctuationAndSpaceRemover();
        punctuationAndSpaceRemovedCode = punctuationAndSpaceRemover.removePunctuationAndSpace(hashDirectiveRemovedCode);
        capitalizationRemover = new CapitalizationRemover();
        capitalizationRemovedCode = capitalizationRemover.upperCaseToLowerCase(punctuationAndSpaceRemovedCode);
        finalPreprocessedCodeForClone = capitalizationRemovedCode;
    }

    public ArrayList<String> getFinalPreprocessedCodeForClone(){
        return finalPreprocessedCodeForClone;
    }
}
