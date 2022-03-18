package Clone;

import Preprocessing.*;

public class PreprocessingForClone {
    String path;

    CommentRemover commentRemover;
    BlankLineRemover blankLineRemover;
    HashDirectiveRemover hashDirectiveRemover;
    PunctuationAndSpaceRemover punctuationAndSpaceRemover;
    Capitalization capitalization;

    public PreprocessingForClone(String path){
        this.path = path;
    }
}
