import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommentRemover {
    public ArrayList<String> arrayList = new ArrayList<>();
    boolean multiLineComment = false;

    public ArrayList<String> removeComment(String path) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null){
            lineAnalyzer(line);
        }

        return arrayList;
    }

    private void lineAnalyzer(String line) {
        int startIndex = 0;
        int finishingIndex = line.length();
        boolean doubleQuote = false;

        for(int i=startIndex; i<finishingIndex; i++){
            if(doubleQuote==false){
                if(line.charAt(i)=='"'){
                    doubleQuote = true;
                }
                else if(multiLineComment==true){
                    if(i!=(finishingIndex-1) && line.charAt(i)=='*' && line.charAt(i+1)=='/'){
                        line = line.substring(0,i)+' '+line.substring(i+1);
                        line = line.substring(0,i+1)+' '+line.substring(i+2);
                        multiLineComment = false;
                    }
                    else{
                        line = line.substring(0,i)+' '+line.substring(i+1);
                    }
                }
                else if(multiLineComment==false){
                    if(i!=(finishingIndex-1) && line.charAt(i)=='/' && line.charAt(i+1)=='*'){
                        multiLineComment = true;
                        line = line.substring(0,i)+' '+line.substring(i+1);
                        line = line.substring(0,i+1)+' '+line.substring(i+2);
                    }
                    else if(i!=(finishingIndex-1) && line.charAt(i)=='/' && line.charAt(i+1)=='/'){
                        for(int j=i; j<finishingIndex; j++){
                            line = line.substring(0,j)+' '+line.substring(j+1);
                        }
                        break;
                    }
                }
            }
            else if(doubleQuote==true){
                if(line.charAt(i)=='"'){
                    doubleQuote = false;
                }
                else{
                    continue;
                }
            }
        }

        arrayList.add(line);
    }

}
