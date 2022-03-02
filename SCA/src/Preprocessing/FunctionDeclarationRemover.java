package Preprocessing;

import java.util.ArrayList;

public class FunctionDeclarationRemover {
//    String returnTypes[] = {"void", "int", "short", "long", "long long",
//            "char", "string", "float", "double", "long double"};
    
    ArrayList<String> code;
    int indexOfMainFunction;

    public void removeFunctionDeclaration(ArrayList<String> code){
        this.code = code;
        indexOfMainFunction = findMainFunction();
        System.out.println(indexOfMainFunction);
    }

    public int findMainFunction() {
        String line;
        for(int i=0; i<code.size(); i++){
            line = code.get(i);

            StringBuilder str = new StringBuilder();
            char c;
            boolean mainFlag = false;
            boolean firstBracketLeft = false;
            //boolean firstBracketRight = false;
            for(int j=0; j<line.length(); j++){
                c = line.charAt(j);
                if(mainFlag) {
                    if (firstBracketLeft) {
                        if (c == ')') {
//                            if(line.charAt(j + 1) == '{' || line.charAt(j+2)=='{'){
//                                return i;
//                            }
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
                    if (c != ' ' && c != '(' && c != ')') {
                        str.append(c);
                    }
                    else {
                        System.out.println(str);
                        if (str.equals("main")) {
                            mainFlag = true;
                            str.delete(0, str.length());
                        } else {
                            str.delete(0, str.length());
                        }
                    }
                }
            }
        }
        return 0;
    }
}
