package Clone;

import java.io.IOException;
import java.util.ArrayList;

public class CloneMain {
    private final String path1;
    private final String path2;
    ArrayList<String> preprocessedCode1 = new ArrayList<String>();
    ArrayList<String> preprocessedCode2 = new ArrayList<String>();
    ArrayList<String> kGrams1 = new ArrayList<String>();
    ArrayList<String> kGrams2 = new ArrayList<String>();

    PreprocessingForClone preprocessingForClone;
    KGram kGram;

    public CloneMain(String path1, String path2){
        this.path1 = path1;
        this.path2 = path2;
    }

    public void cloneProcess() throws IOException {
        preprocess();
        generate_K_Grams();
    }

    private void preprocess() throws IOException {
        preprocessingForClone = new PreprocessingForClone(path1);
        preprocessingForClone.process();
        preprocessedCode1 = preprocessingForClone.getFinalPreprocessedCodeForClone();
        preprocessingForClone = new PreprocessingForClone(path2);
        preprocessingForClone.process();
        preprocessedCode2 = preprocessingForClone.getFinalPreprocessedCodeForClone();
    }

    private void generate_K_Grams(){
        kGram = new KGram(3, preprocessedCode1);
        kGrams1 = kGram.generateKGrams();
        kGram = new KGram(3, preprocessedCode2);
        kGrams2 = kGram.generateKGrams();
        //System.out.println(kGrams2);
    }
}
