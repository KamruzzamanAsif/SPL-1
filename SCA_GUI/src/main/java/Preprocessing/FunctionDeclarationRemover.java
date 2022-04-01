package Preprocessing;

import java.util.ArrayList;

public class FunctionDeclarationRemover {
    // variables
    ArrayList<String> code;
    int indexOfMainFunction;
    ArrayList<String> functionDeclarationRemovedCode = new ArrayList<String>();

    public ArrayList<String> removeFunctionDeclaration(ArrayList<String> code){
        this.code = code;
        indexOfMainFunction = findMainFunction(); // we are finding main function index because
                                                  // function declarations are above main function
        removeDeclarations(); // remove function declarations
        addRestOfTheCodes();  // then add rest of the codes from main to end of program
        return functionDeclarationRemovedCode;
    }

    // now remove function declarations
    private void removeDeclarations() {
        String tempLine;
        for(int i=0; i<indexOfMainFunction; i++){
            tempLine = code.get(i);
            lineAnalyzer(tempLine);
        }
    }

    // analyze line by line
    // a function declaration must have (type name, type name, ...);
    private void lineAnalyzer(String tempLine) {
        char ch;
        int firstBracket = 0;
        for(int i=0; i<tempLine.length(); i++){
            ch = tempLine.charAt(i);
            if(firstBracket==1){
                if(ch == ')' && tempLine.charAt(i+1)==';'){
                    return;  // don't add to arraylist, just return.
                }
            }
            else{
                if(ch == '('){
                    firstBracket++;
                }
            }
        }
        functionDeclarationRemovedCode.add(tempLine);
        return;
    }

    private void addRestOfTheCodes(){
        String tempLine;
        for(int i=indexOfMainFunction; i<code.size(); i++){
            tempLine = code.get(i);
            functionDeclarationRemovedCode.add(tempLine);
        }
        return;
    }

    // finding the index of main function
    private int findMainFunction() {
        String line;
        for(int i=0; i<code.size(); i++){
            line = code.get(i);
            StringBuilder str = new StringBuilder();
            char c;
            boolean mainFlag = false;
            boolean firstBracketLeft = false;

            for(int j=0; j<line.length(); j++){
                c = line.charAt(j);
                if(mainFlag) {
                    if (firstBracketLeft) {
                        if (c == ')') {
                            return i;
                        }
                    }
                    else {
                        if (c == '(') {
                            firstBracketLeft = true;
                        }
                    }
                }
                else {
                    if(c=='('){
                        firstBracketLeft = true;
                    }

                    if (c != ' ' && c != '(' && c != ')') {
                        str.append(c);
                    }
                    else {
                        String temp = str.toString();
                        if (temp.equals("main")) {
                            mainFlag = true;
                            str.delete(0, str.length());
                        }
                        else {
                            str.delete(0, str.length());
                        }
                    }
                }
            }
        }
        return 0;
    }
}
