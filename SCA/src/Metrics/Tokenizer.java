package Metrics;

import java.util.ArrayList;

public class Tokenizer {
    //here preprocessed code will be passed
    // and this code will be tokenized
    ArrayList<String> codeForTokenizing = new ArrayList<>();

    // operator token lists
    ArrayList<String> codeForOperatorTokenizing = new ArrayList<>();
    ArrayList<String> stringToken = new ArrayList<>();
    ArrayList<String> characterToken = new ArrayList<>();
    ArrayList<String> numberToken = new ArrayList<>();
    ArrayList<String> typeSpecifierToken = new ArrayList<>();


    // through constructor set the codeForTokenizing
    public Tokenizer(ArrayList<String> codeForTokenizing){
        this.codeForTokenizing = codeForTokenizing;
    }

    public void tokenize(){
        tokenizeOperands();
        //operators();
    }

    private void tokenizeOperands() {
        codeForOperatorTokenizing = codeForTokenizing;
        separateStrings();
        //separateCharacters();
        //seperateNumbers();
    }

    // string separation begins
    private void separateStrings() {
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWiseSeparateString(line);
        }
    }

    private void lineWiseSeparateString(String line) {
        int lineSize = line.length();
        boolean doubleQuote = false;
        int startPosition = 0;
        int totalCharacters = 0;

        for(int i=0; i<lineSize; i++){
            if(line.charAt(i)=='"' && !doubleQuote){
                doubleQuote = true;
                startPosition = i;
                totalCharacters++;
            }
            else if(doubleQuote){
                totalCharacters++;
                if(line.charAt(i)=='"'){
                    doubleQuote = false;
                    String temp = line.substring(startPosition, startPosition+totalCharacters);
                    stringToken.add(temp);
                    totalCharacters=0;
                }
            }
        }
    }
}
