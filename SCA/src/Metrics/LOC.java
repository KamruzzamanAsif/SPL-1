package Metrics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class LOC {

    // variables
    int numberOfBlankLine;
    int numberOfPhysicalLine;
    int numberOfLogicalStatements;
    int onlyCommentLine;
    int commentAndStatementLines;
    int onlyStatementLines;

    // other necessary temporary variables
    int tempStatementCounter;
    boolean multipleLineCommentIndicator;

    // main function of this class that does every job
    public void LOC(String fp) throws IOException {
        initializeData();
        analyze(fp);
        printData();
    }

    private void initializeData() {
        numberOfBlankLine = 0;
        numberOfPhysicalLine = 0;
        numberOfLogicalStatements = 0;
        onlyCommentLine = 0;
        commentAndStatementLines = 0;
        onlyStatementLines = 0;
        multipleLineCommentIndicator = false;
    }

        // this function will analyze the source coed
    public void analyze(String fp) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            singleLineAnalyzer(line, 0, line.length());
        }
    }

        // analyze the source coed ine by line
    private void singleLineAnalyzer(String line, int startIndex, int finishingIndex){
        if(emptyLineCounter(line, startIndex, finishingIndex)==true){
            numberOfBlankLine++;
        }
        else{
            numberOfPhysicalLine++;
            boolean commentLineFlag = false;
            boolean statementLineFlag = false;

            if(onlyStatementCounter(line, startIndex, finishingIndex)>0){
                numberOfLogicalStatements += tempStatementCounter;
                statementLineFlag = true;
            }

            if(onlyCommentLineFinder(line, startIndex, finishingIndex)==true){
                onlyCommentLine++;
                commentLineFlag = true;
            }

            checkLineType(commentLineFlag, statementLineFlag);
        }
    }

    // count the number of empty lines
    private boolean emptyLineCounter(String line, int startIndex, int finishingIndex) {
        if (finishingIndex != 0) {
            for (int i = startIndex; i < finishingIndex; i++) {
                if (line.charAt(i) == ' ') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

        // this will count the logical statements
        // In C the logical statements are those which ends with a semicolon
    private int onlyStatementCounter(String line, int startIndex, int finishingIndex) {
        int len = finishingIndex;
        int StatementAfterCommentIndex = 0;
        tempStatementCounter = 0;

        StatementAfterCommentIndex = findTheIndexAfterComment(line, startIndex, finishingIndex);
        Stack<Character> stack = new Stack<>();

        for(int i=StatementAfterCommentIndex; i<len; i++){
            if(stack.empty()){
                if(line.charAt(i)==')'){
                    continue;
                }
                else if(line.charAt(i)=='('){
                    stack.push(line.charAt(i));
                }
                else if(line.charAt(i)=='"'){
                    stack.push(line.charAt(i));
                }
                else if(line.charAt(i)=='/' && line.charAt(i+1)=='*' && (i!=len-1)){
                    stack.push(line.charAt(i+1));
                }
                else if(line.charAt(i)=='/' && line.charAt(i+1)=='/' && (i!=len-1)){
                    return tempStatementCounter;
                }
                else if(line.charAt(i)==';'){
                    tempStatementCounter++;
                }
            }
            else{
                if(line.charAt(i)==')' && stack.peek()=='('){
                    stack.pop();
                }
                else if(line.charAt(i)=='"' && stack.peek()=='"'){
                    stack.pop();
                }
                else if(line.charAt(i)=='*' && line.charAt(i+1)=='/' && (i!=len-1) && stack.peek()=='*'){
                    stack.pop();
                }
            }
        }

        return tempStatementCounter;
    }

        // to find the end of a comment
    private int findTheIndexAfterComment(String line, int startIndex, int finishingIndex) {
        if(multipleLineCommentIndicator == true){
            for(int i=startIndex; i<finishingIndex; i++){
                if(line.charAt(i)=='*' && line.charAt(i+1)=='/'){
                    return i+2;
                }
            }
        }

        return 0;
    }

        // this will find only comment lines
    private boolean onlyCommentLineFinder(String line, int startIndex, int finishingIndex) {
        int len = finishingIndex - 1;
        boolean flag = false;
        boolean countAsCommentLine = false;

        if(multipleLineCommentIndicator == true){
            countAsCommentLine = true;
        }

        for(int i=startIndex; i<len; i++){
            if(line.charAt(i)==' '){
                continue;
            }
            else if(flag==false){
                if(line.charAt(i)=='/' && line.charAt(i+1)=='/'){
                    return true;
                }
                else if(line.charAt(i)=='/' && line.charAt(i+1)=='*'){
                    multipleLineCommentIndicator = true;
                    countAsCommentLine = true;
                }
                else if(line.charAt(i)=='*' && line.charAt(i+1)=='/'){
                    multipleLineCommentIndicator = false;
                }
                else if(line.charAt(i)=='"'){
                    flag = true;
                }
            }
            else if(line.charAt(i)=='"' && flag==true){
                flag = false;
            }
        }

        if(countAsCommentLine==true){
            return true;
        }

        return false;
    }

    // this will check the line
    // whether it contains both comment and statement
    private void checkLineType(boolean commentLineFlag, boolean statementLineFlag) {
        if(commentLineFlag == true && statementLineFlag == true){
            commentAndStatementLines++;
            onlyCommentLine--;
        }
        else if(statementLineFlag == true){
            onlyStatementLines++;
        }
    }

    private void printData() {
        System.out.println("Blank line: "+ numberOfBlankLine);
        System.out.println("Physical line: "+ numberOfPhysicalLine);
        System.out.println("Logical Statements: "+ numberOfLogicalStatements);
        System.out.println("Only Comment Line: "+ onlyCommentLine);
        System.out.println("Comment and Statement line: "+commentAndStatementLines);
        System.out.println("Only Statement line: "+ onlyStatementLines);
    }
}
