package Preprocessing;

import java.util.ArrayList;

public class Capitalization {
    ArrayList<String> upperCaseToLowerCaseCode = new ArrayList<>();
    String line;

    public ArrayList<String> upperCaseToLowerCase(ArrayList<String> code){
        for (String s : code) {
            line = s;
            lineWiseUpperCaseToLowerCase(line, line.length());
        }
        return upperCaseToLowerCaseCode;
    }

    private void lineWiseUpperCaseToLowerCase(String line, int length) {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<length; i++){
            char c = line.charAt(i);
            int ascii = c;
            if(ascii>=65 && ascii<=90){
                ascii += 32;
                c = (char)ascii;
                str.append(c);
            }
            else{
                str.append(c);
            }
        }
        upperCaseToLowerCaseCode.add(str.toString());
    }

}
