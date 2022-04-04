package Clone;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CloneMain {
    private final int kGramSize;
    private final String path1;
    private final String path2;
    ArrayList<String> preprocessedCode1 = new ArrayList<>();
    ArrayList<String> preprocessedCode2 = new ArrayList<>();
    ArrayList<String> kGrams1 = new ArrayList<>();
    ArrayList<String> kGrams2 = new ArrayList<>();
    private double cloneResult;

    PreprocessingForClone preprocessingForClone;
    KGram kGram;
    KGramHash kGramHash;
    ArrayList<Long> kGramHashes1 = new ArrayList<>();
    ArrayList<Long> kGramHashes2 = new ArrayList<>();
    Winnowing winnowing;
    ArrayList<Long> fingerprints1 = new ArrayList<>();
    ArrayList<Long> fingerprints2 = new ArrayList<>();
    SimilarityChecker similarityChecker;

    public CloneMain(int kGramSize, String path1, String path2){
        this.kGramSize = kGramSize;
        this.path1 = path1;
        this.path2 = path2;
    }

    public void cloneProcess() throws IOException {
        preprocess();
        generate_K_Grams();
        generate_hashValues_of_KGrams();
        generate_fingerprints();
        generate_result();
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
        kGramHash = new KGramHash(kGrams1);
        kGramHashes1 = kGramHash.rollingHash();
//        System.out.println(kGramHashes1);
//        System.out.println("k gram hash size:" + kGrams1.size());
        kGramHash = new KGramHash(kGrams2);
        kGramHashes2 = kGramHash.rollingHash();
        //System.out.println(kGramHashes2);
    }

    private void generate_fingerprints(){
        winnowing = new Winnowing(kGramHashes1, 3);
        fingerprints1 = winnowing.winnow();
//        System.out.println(fingerprints);
//        System.out.println(fingerprints.size());
        winnowing = new Winnowing(kGramHashes2, 3);
        fingerprints2 = winnowing.winnow();
    }

    private void generate_result(){
        similarityChecker = new SimilarityChecker(fingerprints1,fingerprints2);
        cloneResult = similarityChecker.getClone();
        DecimalFormat df = new DecimalFormat("0.000");
        //System.out.println("Clone between the two file is: " + df.format(cloneResult) + "%");
    }

    public double getCloneResult(){
        return cloneResult;
    }
}