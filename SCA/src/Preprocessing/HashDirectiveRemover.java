package Preprocessing;

import java.util.ArrayList;

public class HashDirectiveRemover {
    String []hashDir = {"#include", "#define", "#pragma", "#error",
            "#if", "#ifdef", "#ifndef", "#else", "#elif",
            "#endif", "#warning", "#undef", "#line"};

    ArrayList<String> hashDirectives = new ArrayList<>();
    ArrayList<String> hashDirectiveRemovedCode = new ArrayList<>();
    String line;

    public ArrayList<String> removeHashDirective(ArrayList<String> code){
        addHashDirToArrayList();
        for(int i=0; i<code.size(); i++){
            line = code.get(i);
            lineWiseHashDirectiveRemover(line, 0, line.length());
        }
        return hashDirectiveRemovedCode;
    }

    private void addHashDirToArrayList() {
        for(String str: hashDir){
            hashDirectives.add(str);
        }
    }

    public void lineWiseHashDirectiveRemover(String line, int statIndex, int finishingIndex){
        StringBuffer x = new StringBuffer();
        int i=statIndex;
        while(i<finishingIndex && line.charAt(i)!=' '){
            x.append(line.charAt(i));
            i++;
        }

        String str = x.toString();
        boolean flag = false;
        for(String directive: hashDirectives){
            if(str.equals(directive)){
                flag = true;
            }
        }

        if(flag==false){
            hashDirectiveRemovedCode.add(line);     // just adding not containing hashDirective line
        }
    }
}
