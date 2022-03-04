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
        separateTypeSpecifiers();
//        for(String line: stringToken)
//            System.out.println(line);
//        for(String line: characterToken)
//            System.out.println(line);
//        for(String line: numberToken)
//            System.out.println(line);
        for(String line: typeSpecifierToken)
            System.out.println(line);
    }

//    private void substringRemover(int index, int start, int end){
//        String line = codeForOperatorTokenizing.get(index);
//        StringBuilder str = new StringBuilder();
//        for(int i=0; i<line.length(); i++){
//            if(!(i>=start && i<end)){
//                str.append(line.charAt(i));
//            }
//        }
//        codeForOperatorTokenizing.set(index,str.toString());
//    }

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
        int startPosition = -1;
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
                    //substringRemover(index, startPosition, startPosition+totalCharacters);
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
        int position = -1;
        int totalCharacters = 0;

        for(int i=0; i<lineSize; i++){
            if(line.charAt(i)== 39 && singleQuote == false){
                singleQuote = true;
                position = i;
                totalCharacters++;
            }
            else if(singleQuote == true){
                totalCharacters++;
                if(line.charAt(i)==39){
                    singleQuote = false;
                    String temp = line.substring(position, position+totalCharacters);
                    characterToken.add(temp);
                    //substringRemover(index, position, position+totalCharacters);
                    totalCharacters=0;
                    position = -1;
                }
            }
        }
    }
    // character separation ends here

    // Number separation begins
    private void separateNumbers(){
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWiseSeparateNumbers(line, i);
        }
    }

    private void lineWiseSeparateNumbers(String line, int index) {
        int startPosition = -1;
        int totalDigit_withDot=0;
        boolean flag = false;
        int lineSize = line.length();

        for(int i=0; i<lineSize; i++){
            boolean b = (line.charAt(i) >= 48 && line.charAt(i) <= 57) || line.charAt(i) == '.';
            if(!flag){
                if(b){
                    // to check these type:number1 or, temp4
                    if(i>0 && !(line.charAt(i-1)>=65 && line.charAt(i-1)<=90) &&  !(line.charAt(i-1)>=97 && line.charAt(i-1)<=122)){
                        startPosition = i;
                        totalDigit_withDot++;
                        flag = true;
                    }
                }
            }
            else if(flag){
                if(!b){
                    flag = false;
                    String temp = line.substring(startPosition, startPosition+totalDigit_withDot);
                    numberToken.add(temp);
                    startPosition = -1;
                    totalDigit_withDot = 0;
                }
                else{
                    totalDigit_withDot++;
                }
            }
        }
    }
    // Number separation ends here

    // Type specifier separation begins
    private void separateTypeSpecifiers(){
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWiseSeparateTypeSpecifiers(line, i);
        }
    }

    private void lineWiseSeparateTypeSpecifiers(String line, int index) {
        int lineSize = line.length();
        StringBuilder str =  new StringBuilder();

        int i=0;
        while(i<lineSize){
            if(line.charAt(i)!=' ' && line.charAt(i)!=','){
                str.append(line.charAt(i));
                i++;
            }
            else{
                isTypeSpecifier(str.toString());
                i++;
                str.setLength(0);
            }
        }
    }

    private void isTypeSpecifier(String str) {
        String []typeSpecifier = {"bool", "char", "double", "int", "float", "long",
                                   "short", "signed", "unsigned", "void"};
        for(String s: typeSpecifier){
            if(s.equals(str)){
                typeSpecifierToken.add(str);
            }
        }
    }
    // Type Specifier separation ends here




}
