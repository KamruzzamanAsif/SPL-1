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
        //tokenizeOperators();
    }

    private void tokenizeOperands() {
        codeForOperatorTokenizing = codeForTokenizing;
        separateStrings();
        separateCharacters();
        separateNumbers();
    }

    private void substringRemover(int index, int start, int end){
        String line = codeForOperatorTokenizing.get(index);
        StringBuilder str = new StringBuilder();
        for(int i=0; i<line.length(); i++){
            if(!(i>=start && i<end)){
                str.append(line.charAt(i));
            }
        }
        codeForOperatorTokenizing.set(index,str.toString());
    }

    // string separation begins
    private void separateStrings() {
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWiseSeparateString(line, i);
        }
    }

    private void lineWiseSeparateString(String line, int index) {
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
                    substringRemover(index, startPosition, startPosition+totalCharacters);
                    totalCharacters=0;
                }
            }
        }
    }
    // String separation ends here

    // Character separation begins
    private void separateCharacters(){
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWiseSeparateCharacter(line, i);
        }
    }

    private void lineWiseSeparateCharacter(String line, int index) {
        int lineSize = line.length();
        boolean singleQuote = false;
        int position = 0;

        for(int i=0; i<lineSize; i++){
            if(line.charAt(i)== 39 && !singleQuote){
                singleQuote = true;
                position = i;
            }
            else if(singleQuote){
                if(line.charAt(i)==39){
                    singleQuote = false;
                    String temp = line.substring(position, position+1);
                    characterToken.add(temp);
                    substringRemover(index, position, position+1);
                }
            }
        }
    }
    // character separation ends here

    // Number separation begins
    private void separateNumbers(){

    }
}
