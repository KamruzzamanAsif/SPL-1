package Metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Halstead {
    String path;
    PreprocessingForHalstead preprocessingForHalstead;
    ArrayList<String> preprocessedCodeForHalstead = new ArrayList<>();
    int [][]functionAreaIndex;

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
        detectMethodArea();
        // tokenizing testing
        Tokenizer tok = new Tokenizer(preprocessedCodeForHalstead, functionAreaIndex);
        tok.tokenize();
    }

    private void preProcessForHalstead() throws IOException {
        preprocessingForHalstead = new PreprocessingForHalstead();
        preprocessingForHalstead.setPath(this.path);
        preprocessingForHalstead.process();
        preprocessedCodeForHalstead = preprocessingForHalstead.getPreprocessedCodeForHalstead();
    }

    private void detectMethodArea(){
        FunctionArea functionArea = new FunctionArea();
        functionAreaIndex = functionArea.detectFunctionArea(preprocessedCodeForHalstead);
        //System.out.println(Arrays.deepToString(functionAreaIndex));
    }

}
