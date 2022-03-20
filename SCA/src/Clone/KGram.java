package Clone;

import java.util.ArrayList;

public class KGram {
    private final int kGramSize;
    private String codeStr;
    ArrayList<String> kGrams = new ArrayList<>();
    ArrayList<String> code;

    public KGram(int kGramSize, ArrayList<String> code){
        this.kGramSize = kGramSize;
        this.code = code;
    }

    public ArrayList<String> generateKGrams(){
        makeCodeStr();
        StringBuilder str = new StringBuilder();
        for(int i=0; i<codeStr.length(); i++){
            for(int j = i; j<i+kGramSize; j++){
                if(j<codeStr.length())
                    str.append(codeStr.charAt(j));
            }
            kGrams.add(str.toString());
            str.setLength(0);
        }
        return kGrams;
    }

    private void makeCodeStr() {
        StringBuilder str = new StringBuilder();
        for(String s: code){
            str.append(s);
        }
        codeStr = str.toString();
    }
}
