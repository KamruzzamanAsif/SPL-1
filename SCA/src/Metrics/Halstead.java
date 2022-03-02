package Metrics;

import java.io.IOException;
import java.util.ArrayList;

public class Halstead {
    String path;
    PreprocessingForHalstead preprocessingForHalstead;

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
        //HalsteadresultGenerator();
    }

    public void preProcessForHalstead() throws IOException {
        preprocessingForHalstead = new PreprocessingForHalstead();
        preprocessingForHalstead.setPath(this.path);
        preprocessingForHalstead.process();
        ArrayList<String> preprocessedCodeForHalstead = new ArrayList<String>();
        preprocessedCodeForHalstead = preprocessingForHalstead.getPreprocessedCodeForHalstead();
        for(String line: preprocessedCodeForHalstead){
            System.out.println(line);
        }
    }

}
