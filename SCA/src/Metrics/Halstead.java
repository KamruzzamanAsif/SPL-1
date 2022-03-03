package Metrics;

import java.io.IOException;
import java.util.ArrayList;

public class Halstead {
    String path;
    PreprocessingForHalstead preprocessingForHalstead;
    ArrayList<String> preprocessedCodeForHalstead = new ArrayList<>();

    // halstead constructor
    public Halstead(String path){
        setPath(path);
    }

    public void setPath(String path){
        this.path = path;
        return;
    }

    public void DoHalstead() throws IOException {
        //initailizeHalsteadData();
        preProcessForHalstead();
        // tokenizing testing
        Tokenizer tok = new Tokenizer(preprocessedCodeForHalstead);
        tok.tokenize();
    }

    public void preProcessForHalstead() throws IOException {
        preprocessingForHalstead = new PreprocessingForHalstead();
        preprocessingForHalstead.setPath(this.path);
        preprocessingForHalstead.process();
        preprocessedCodeForHalstead = preprocessingForHalstead.getPreprocessedCodeForHalstead();
//        for(String line: preprocessedCodeForHalstead){
//            System.out.println(line);
//        }
    }

}
