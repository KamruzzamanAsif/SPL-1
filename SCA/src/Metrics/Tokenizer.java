package Metrics;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class Tokenizer {
    //here preprocessed code will be passed
    // and this code will be tokenized
    ArrayList<String> codeForTokenizing = new ArrayList<>();
    int [][]functionArea;

    // operand token lists
    //ArrayList<String> codeForOperandTokenizing = new ArrayList<>();
    Vector<String> codeForOperandTokenizing = new Vector<>();
    ArrayList<String> stringToken = new ArrayList<>();
    ArrayList<String> characterToken = new ArrayList<>();
    ArrayList<String> numberToken = new ArrayList<>();
    ArrayList<String> typeSpecifierToken = new ArrayList<>();
    ArrayList<String> identifierToken = new ArrayList<>();
    ArrayList<String> tempUniqueIdentifier = new ArrayList<>(); // for method level temp arrayList

    // operator token lists
    //ArrayList<String> codeForOperatorTokenizing = new ArrayList<>();
    Vector<String> codeForOperatorTokenizing = new Vector<>();
    ArrayList<String> reservedWordToken = new ArrayList<>();
    ArrayList<String> loopingStatementToken = new ArrayList<>();
    ArrayList<String> controlStatementToken = new ArrayList<>();
    ArrayList<String> bracketToken = new ArrayList<>();
    ArrayList<String> singleOperatorToken = new ArrayList<>();
    ArrayList<String> doubleOperatorToken = new ArrayList<>();

    // other necessary things
    Stack<Character> bracketStack = new Stack<>();


    // through constructor set the codeForTokenizing
    public Tokenizer(ArrayList<String> codeForTokenizing, int [][]functionArea){
        this.codeForTokenizing = codeForTokenizing;
        this.functionArea = functionArea;
    }

    public void tokenize(){
        tokenizeOperands();
        tokenizeOperators();
    }

    /****  Operand Tokenization Starts here   ****/

    private void tokenizeOperands() {
        codeForOperandTokenizing.addAll(codeForTokenizing);
        //codeForOperandTokenizing = codeForTokenizing;
        separateStrings();
        separateCharacters();
//        for(String s: codeForOperandTokenizing)
//            System.out.println(s);
        separateNumbers();
        //separateTypeSpecifiers();      //type specifiers shouldn't count according to many
        separateIdentifiers();
//        for(String line: stringToken)
//            System.out.println(line);
//        for(String line: characterToken)
//            System.out.println(line);
//        for(String line: numberToken)
//            System.out.println(line);
//        for(String line: typeSpecifierToken)
//            System.out.println(line);
//        for(String line: identifierToken)
//            System.out.println(line);
    }

    private void substringRemover(int index, int start, int end) throws IndexOutOfBoundsException{
        String line = codeForOperandTokenizing.get(index);
        StringBuilder str = new StringBuilder();
        for(int i=0; i<line.length(); i++){
            if(!(i>=start && i<end)){
                str.append(line.charAt(i));
            }
        }
        codeForOperandTokenizing.set(index,str.toString());
    }

    //*** string separation begins ***///
    private void separateStrings() {
        for(int i = 0; i< codeForOperandTokenizing.size(); i++){
            String line = codeForOperandTokenizing.get(i);
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
                    substringRemover(index, startPosition,startPosition+totalCharacters);
                    totalCharacters=0;
                }
            }
        }
    }
    //*** String separation ends here ***///

    //*** Character separation begins ***//
    private void separateCharacters(){
        for(int i = 0; i< codeForOperandTokenizing.size(); i++){
            String line = codeForOperandTokenizing.get(i);
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
                    substringRemover(index, position, position+totalCharacters);
                    totalCharacters=0;
                    position = -1;
                }
            }
        }
    }
    //*** character separation ends here ***//

    //*** Number separation begins ***///
    private void separateNumbers(){
        for(int i = 0; i< codeForOperandTokenizing.size(); i++){
            String line = codeForOperandTokenizing.get(i);
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
    ///*** Number separation ends here ***///

    ///*** Type specifier separation begins ***///
//    private void separateTypeSpecifiers(){
//        for(int i = 0; i< codeForOperandTokenizing.size(); i++){
//            String line = codeForOperandTokenizing.get(i);
//            lineWiseSeparateTypeSpecifiers(line, i);
//        }
//    }
//
//    private void lineWiseSeparateTypeSpecifiers(String line, int index) {
//        int lineSize = line.length();
//        StringBuilder str =  new StringBuilder();
//
//        int i=0;
//        while(i<lineSize){
//            if(line.charAt(i)!=' ' && line.charAt(i)!=','){
//                str.append(line.charAt(i));
//                i++;
//            }
//            else{
//                isTypeSpecifier(str.toString());
//                i++;
//                str.setLength(0);
//            }
//        }
//    }
//
//    private void isTypeSpecifier(String str) {
//        String []typeSpecifier = {"bool", "char", "double", "int", "float", "long",
//                                   "short", "signed", "unsigned", "void"};
//        for(String s: typeSpecifier){
//            if(s.equals(str)){
//                typeSpecifierToken.add(str);
//            }
//        }
//    }
    ///*** Type Specifier separation ends here ***///


    //*** To handle variable token we must go through function area wise ***//
    // Because: Global variables used in different modules of the same program are counted as multiple occurrences of the same variable.
    //          Local variables with the same name in different functions are counted as unique operands

    // Variable tokenization begins
    private void separateIdentifiers(){
        // 1. Handle global identifiers
        int mainFunctionIndex = findMainFunction();
        findUniqueIdentifier(0, mainFunctionIndex-1);
        findIdentifierOccurrences(0, codeForOperandTokenizing.size()-1);
        identifierToken.add("BreakPoint");

        // 2. Handle function wise identifier
        for(int i=0; i<100; i++){
            if(functionArea[i][0]!=0 && functionArea[i][1]!=0){
                functionWiseIdentifierAnalyzer(functionArea[i][0], functionArea[i][1]);
            }
        }
    }

    private int findMainFunction() {
        String line;
        for(int i=0; i<codeForOperandTokenizing.size(); i++){
            line = codeForOperandTokenizing.get(i);
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

    private void functionWiseIdentifierAnalyzer(int start_index, int finish_index) {
        findUniqueIdentifier(start_index, finish_index);
        // we will find the unique identifiers and then check their occurrences
        findIdentifierOccurrences(start_index, finish_index); // here find them and push them into main token list
        // inserting Breakpoint for after inserting each function result
        // because identifier token is our main token list
        identifierToken.add("BreakPoint");
    }

    private void findUniqueIdentifier(int start_index, int finish_index) {
        tempUniqueIdentifier.clear(); // clear at every function
        for(int i = start_index; i<= finish_index; i++){
            String line = codeForOperandTokenizing.get(i);
            lineWiseUniqueIdentifier(line);
        }
    }

    private void lineWiseUniqueIdentifier(String line) {
        int lineSize = line.length();
        boolean typeFlag = false; // if we got a variable type or not
        StringBuilder str = new StringBuilder(); // type str
        StringBuilder ind_str = new StringBuilder(); // identifier str

        int i=0;
        while(i<lineSize){
            if(!typeFlag) {
                while (i < lineSize) {
                    if (line.charAt(i) != ' ' && line.charAt(i) != ',' && line.charAt(i)!= '(') {
                        str.append(line.charAt(i));
                        i++;
                    }
                    else {
                        if (isType(str.toString())) {
                            i++;
                            str.setLength(0);
                            typeFlag = true;
                            break;
                        } else {
                            str.setLength(0);
                            i++;
                        }
                    }
                }
            }
            else {
                while(i<lineSize){
                    if(line.charAt(i)!= ' ' && line.charAt(i)!= ',' && line.charAt(i)!=';'
                            && line.charAt(i)!= '(' && line.charAt(i)!=')' && line.charAt(i)!='['
                            && line.charAt(i)!=']' && line.charAt(i)!='{' && line.charAt(i)!='}'
                            && line.charAt(i)!='='){
                        ind_str.append(line.charAt(i));
                        i++;
                    }
                    else{
                        String t = ind_str.toString();
                        if(!isType(t) && !isBlankStr(t) && !isNumber(t) && !isString(t)){
                            tempUniqueIdentifier.add(t);
                        }
                        else{
                            if(isType(t)){
                                typeFlag = true;
                            }
                        }

                        // for checking struct and other types of identifier
                        if(line.charAt(i) == '{' && line.charAt(i) == ';'){
                            typeFlag = false;
                            break;
                        }
                        i++;
                        ind_str.setLength(0);
                    }
                }
            }
        }

    }

    private boolean isType(String str) {
        String []typeSpecifier = {"bool", "char", "double", "int", "float", "long",
                "short", "signed", "unsigned", "void", "struct", "union", "string", "vector"};
        for(String s: typeSpecifier){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

    private boolean isBlankStr(String str){
        if(str.length()==0){
            return true;
        }

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)==' '){
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String str){
        for(int i=0; i<str.length(); i++){
            if(!(str.charAt(i)>='0' && str.charAt(i)<='9')){
                return false;
            }
        }
        return true;
    }

    private boolean isString(String str){
        if(str.charAt(0)=='"' && str.charAt(str.length()-1)=='"'){
            return true;
        }
        else{
            return false;
        }
    }

    private void findIdentifierOccurrences(int start_index, int finish_index){
        for(int i=start_index; i<=finish_index; i++){
            String line = codeForOperandTokenizing.get(i);
            findLineWiseIdentifierOccurrences(line);
        }
    }

    private void findLineWiseIdentifierOccurrences(String line) {
        int lineSize = line.length();
        StringBuilder str = new StringBuilder();

        int i=0;
        while(i<lineSize){
            char c = line.charAt(i);
            if(isNameChar(c)){
                str.append(c);
                i++;
            }
            else{
                if(isThisIsInUniqueOperands(str.toString())){
                    identifierToken.add(str.toString());
                }
                i++;
                str.setLength(0);
            }
        }
    }

    private boolean isNameChar(char c){
        char []ch ={' ', ',', ';', '+', '-', '*', '/', '=', '{', '}', '^', '%', '[', ']', '.', '>', '(', ')', '&'};
        for(char x: ch){
            if(x==c){
                return false;
            }
        }
        return true;
    }

    private boolean isThisIsInUniqueOperands(String str){
        for(String s: tempUniqueIdentifier){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

    //*** Identifier separation ends here ***//

    /****    Operand Tokenization ends here    ****/



    /****   Operator Tokenization starts here   ****/

    private void tokenizeOperators(){
        codeForOperatorTokenizing = codeForOperandTokenizing;
        separate_Reserved_Loop_Control();
        separate_Bracket_otherOperators();
    }

    ///***   Separation of Reserved Words, Loop Statements, and Control Statements begins  ***///
    private void separate_Reserved_Loop_Control(){
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWise_separate_Reserved_Loop_Control(line);
        }

//        for(String str: reservedWordToken)
//            System.out.println(str);
//        for(String str: loopingStatementToken)
//            System.out.println(str);
//        for(String str: controlStatementToken)
//            System.out.println(str);
    }

    private void lineWise_separate_Reserved_Loop_Control(String line) {
        int lineSize = line.length();
        StringBuilder str = new StringBuilder();

        int i=0;
        while(i<lineSize){
            char c = line.charAt(i);
            if(!isStopChar(c)){
                str.append(c);

                // for checking the last word in a line
                if(i==lineSize-1){
                    String temp = str.toString();
                    if(isReservedWord(temp)){
                        reservedWordToken.add(temp);
                    }
                    else if(isLoopStatement(temp)){
                        loopingStatementToken.add(temp);
                    }
                    else if(isControlStatement(temp)){
                        controlStatementToken.add(temp);
                    }

                    i++;
                    str.setLength(0);
                }

                i++;
            }
            else{
                String temp = str.toString();
                if(isReservedWord(temp)){
                    reservedWordToken.add(temp);
                }
                else if(isLoopStatement(temp)){
                    loopingStatementToken.add(temp);
                }
                else if(isControlStatement(temp)){
                    controlStatementToken.add(temp);
                }

                i++;
                str.setLength(0);
            }
        }
    }


    private boolean isStopChar(char c) {
        char []ch ={' ', '\n', ',', ';', '+', '-', '*', '/', '=', '{', '}', '^', '%', '[', ']', '.', '>', '(', ')'};
        for(char x: ch){
            if(x==c){
                return true;
            }
        }
        return false;
    }

    private boolean isReservedWord(String temp) {
        String []reservedWords = {"auto", "break", "const", "continue",
                "default", "register", "return", "short",
                "signed", "sizeof", "static", "goto", "typedef",
                "unsigned", "volatile", "enum", "extern", "main",
                "scanf", "printf", "int", "bool", "char", "double",
                "int", "float", "long", "short", "signed", "unsigned",
                "void", "struct", "union", "string", "vector"};

        for(String str: reservedWords){
            if(str.equals(temp)){
                return true;
            }
        }
        return false;
    }

    private boolean isLoopStatement(String temp) {
        String []loopStatements = {"for", "while", "do"};

        for(String str: loopStatements){
            if(str.equals(temp)){
                return true;
            }
        }
        return false;
    }

    private boolean isControlStatement(String temp) {
        String []controlStatements = {"if", "else", "switch", "case"};

        for(String str: controlStatements){
            if(str.equals(temp)){
                return true;
            }
        }
        return false;
    }

    ///***   Separation of Reserved Words, Loop Statements, and Control Statements ends here  ***///


    ///***   Separation of Brackets and other Operators begins  ***///
    private void separate_Bracket_otherOperators(){
        for(int i=0; i<codeForOperatorTokenizing.size(); i++){
            String line = codeForOperatorTokenizing.get(i);
            lineWise_separate_Bracket_otherOperators(line);
        }

//        for(String str: bracketToken)
//            System.out.println(str);
//        for(String str: singleOperatorToken)
//            System.out.println(str);
//        for(String str: doubleOperatorToken)
//            System.out.println(str);
    }

    private void lineWise_separate_Bracket_otherOperators(String line){
        int lineSize = line.length();
        StringBuilder str = new StringBuilder();
        int i=0;
        while(i<lineSize){
            char c = line.charAt(i);
            if(isBracket(c)){
                if(c=='(' || c=='{' || c=='['){
                    bracketStack.push(c);
                    i++;
                }
                else if(c==')'){
                    if(!bracketStack.empty() && bracketStack.peek()=='(') {
                        bracketStack.pop();
                        str.append(c);
                        bracketToken.add(str.toString());
                        str.setLength(0);
                        i++;
                    }
                    else i++;
                }
                else if(c=='}'){
                    if(!bracketStack.empty() && bracketStack.peek()=='{') {
                        bracketStack.pop();
                        str.append(c);
                        bracketToken.add(str.toString());
                        str.setLength(0);
                        i++;
                    }
                    else i++;
                }
                else if(c==']'){
                    if(!bracketStack.empty() && bracketStack.peek()=='[') {
                        bracketStack.pop();
                        str.append(c);
                        bracketToken.add(str.toString());
                        str.setLength(0);
                        i++;
                    }
                    else i++;
                }
            }
            else if(operator(c)){
                str.append(c);
                if(i<lineSize-1 && operator(line.charAt(i+1))){
                    str.append(c);
                    doubleOperatorToken.add(str.toString());
                    i=i+2;
                    str.setLength(0);
                }
                else{
                    singleOperatorToken.add(str.toString());
                    i++;
                    str.setLength(0);
                }
            }
            else{
                i++;
                str.setLength(0);
            }
        }
    }

    private boolean isBracket(char c){
        char []brackets = {'(', ')', '{', '}', '[',']'};
        for(char ch: brackets){
            if(ch==c){
                return true;
            }
        }
        return false;
    }

    private boolean operator(char c){
        char []operator = {'+', '-', '*', '/', '=', '%', '!', '<', '>', '|',
                '&', '~', '^', '.', ':', '?', ',', ';'};

        for(char ch: operator){
            if(ch==c){
                return true;
            }
        }
        return false;
    }

    ///***  Separation of Brackets and other Operators ends here  ***///

    /****   Operator Tokenization ends here   ****/


    /** return operand tokens **/
    public ArrayList<String> getStringToken(){
        return stringToken;
    }

    public ArrayList<String> getCharacterToken(){
        return characterToken;
    }

    public ArrayList<String> getNumberToken(){
        return numberToken;
    }

    public ArrayList<String> getTypeSpecifierToken(){
        return typeSpecifierToken;
    }

    public ArrayList<String> getIdentifierToken(){
        return identifierToken;
    }

    /** return operator tokens **/
    public ArrayList<String> getReservedWordToken(){
        return reservedWordToken;
    }

    public ArrayList<String> getLoopingStatementToken(){
        return loopingStatementToken;
    }

    public ArrayList<String> getControlStatementToken(){
        return controlStatementToken;
    }

    public ArrayList<String> getBracketToken(){
        return bracketToken;
    }

    public ArrayList<String> getSingleOperatorToken(){
        return singleOperatorToken;
    }

    public ArrayList<String> getDoubleOperatorToken(){
        return doubleOperatorToken;
    }
}
