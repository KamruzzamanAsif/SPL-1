package Metrics;

import java.util.ArrayList;
import java.util.Stack;

public class FunctionArea {
    ArrayList<String> code = new ArrayList<>();
    int [][]functionArea = new int[100][2];
    public int[][] detectFunctionArea(ArrayList<String> code){
        this.code = code;
        Stack<Character> secondBracket = new Stack<>();
        Stack<Character> firstBracket = new Stack<>();
        int x = 0; // array index
        int start=-1;
        int finish;

        for(int i=0; i<code.size(); i++){
            String line;
            line = code.get(i);

            for(int j=0; j<line.length(); j++){
                if(firstBracket.size()==2 && secondBracket.empty() && line.charAt(j)=='{'){
                    secondBracket.push(line.charAt(j));
                    start = i;
                }
                else if(firstBracket.size()==2 && secondBracket.size()==1 && line.charAt(j)=='}'){
                    finish = i;
                    functionArea[x][0]=start;
                    functionArea[x][1]=finish;
                    x++;
                    secondBracket.pop();
                    firstBracket.pop();
                    firstBracket.pop();
                }
                else if(line.charAt(j)=='{'){
                    secondBracket.push(line.charAt(j));
                }
                else if(line.charAt(j)=='}'){
                    secondBracket.pop();
                }
                else if(firstBracket.empty() && secondBracket.empty() && line.charAt(j)=='('){
                    firstBracket.push(line.charAt(j));
                }
                else if(firstBracket.size()==1 && secondBracket.empty() && line.charAt(j)==')'){
                    firstBracket.push(line.charAt(j));
                }
            }
        }

        return functionArea;
    }
}
