package Preprocessing;

import java.util.ArrayList;

public class BlankLineRemover {
    ArrayList<String> blankLineRemovedCode = new ArrayList<>();
    String line;

    public ArrayList<String> blankLineRemover(ArrayList<String> code){
        for(int i=0; i<code.size(); i++){
            line = code.get(i);
            lineWiseBlankLineRemover(line, 0, line.length());
        }
        return blankLineRemovedCode;
    }

    private void lineWiseBlankLineRemover(String line, int startIndex, int finishingIndex) {
        int i = startIndex;
        while(i<finishingIndex && line.charAt(i)==' '){
            i++;
        }
        if(i!=finishingIndex){
            blankLineRemovedCode.add(line);
        }
    }
}
