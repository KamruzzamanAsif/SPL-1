package Clone;

import java.io.IOException;
import java.util.ArrayList;

public class CloneMain {
    private int kGramSize;
    private final String path1;
    private final String path2;
    ArrayList<String> preprocessedCode1 = new ArrayList<String>();
    ArrayList<String> preprocessedCode2 = new ArrayList<String>();
    ArrayList<String> kGrams1 = new ArrayList<String>();
    ArrayList<String> kGrams2 = new ArrayList<String>();

    PreprocessingForClone preprocessingForClone;
    KGram kGram;

    public CloneMain(int kGramSize, String path1, String path2){
        this.kGramSize = kGramSize;
        this.path1 = path1;
        this.path2 = path2;
    }

    public void cloneProcess() throws IOException {
        preprocess();
        generate_K_Grams();
        generate_hashValues_of_KGrams();
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
        kGram = new KGram(kGramSize, preprocessedCode1);
        kGrams1 = kGram.generateKGrams();
//        System.out.println(kGrams1);
        kGram = new KGram(kGramSize, preprocessedCode2);
        kGrams2 = kGram.generateKGrams();
//        System.out.println(kGrams2);
    }

    private void generate_hashValues_of_KGrams(){

    }
}
