package Preprocessing;

import java.util.ArrayList;

public class PunctuationAndSpaceRemover {
    ArrayList<String> punctuationAndSpaceRemovedCode = new ArrayList<>();
    char[] punctuations = {',', ':', ';', '.'};
    String line;

    public ArrayList<String> removePunctuationAndSpace(ArrayList<String> code){
        for (String s : code) {
            line = s;
            lineWisePunctuationAndSpaceRemover(line, line.length());
        }
        return punctuationAndSpaceRemovedCode;
    }

    private void lineWisePunctuationAndSpaceRemover(String line, int finishingIndex) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i<finishingIndex; i++){
            if(!isPunctuation(line.charAt(i)) && line.charAt(i)!=' '){
                str.append(line.charAt(i));
            }
        }
        punctuationAndSpaceRemovedCode.add(str.toString());
    }

    private boolean isPunctuation(char c){
        for(char x: punctuations){
            if(x==c){
                return true;
            }
        }
        return false;
    }
}
