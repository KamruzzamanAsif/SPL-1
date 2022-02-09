import java.util.ArrayList;

public class FrontWhiteSpaceRemover {
    ArrayList<String> frontWhiteSpaceRemovedCode = new ArrayList<>();
    String line;
    String whiteSpaceRemovedLine;

    public ArrayList<String> removeFrontWhiteSpace(ArrayList<String> code){
        for(int i=0; i<code.size(); i++){
            line = code.get(i);
            lineWiseWhiteSpaceRemover(line, 0, line.length());
        }
        return frontWhiteSpaceRemovedCode;
    }

    private void lineWiseWhiteSpaceRemover(String line, int startIndex, int finishingIndex) {
        int i = startIndex;
        while(i<finishingIndex && line.charAt(i)==' '){
            i++;
        }
        whiteSpaceRemovedLine = line.substring(i);
        frontWhiteSpaceRemovedCode.add(whiteSpaceRemovedLine);
    }
}
