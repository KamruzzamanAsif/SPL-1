package Metrics;

import java.io.IOException;
import java.util.ArrayList;

public class Halstead {
    String path;
    PreprocessingForHalstead preprocessingForHalstead;
    ArrayList<String> preprocessedCodeForHalstead = new ArrayList<>();
    int [][]functionAreaIndex;
    HalsteadCounter halsteadCounter;

    /*** Halstead data ***/
    int totalNumberOfOperands;
    int totalNumberOfOperators;
    int numberOfDistinctOperands;
    int numberOfDistinctOperators;

    int programVocabulary;
    int programLength;
    double calculatedProgramLength;
    double volume;
    double difficulty;
    double effort;
    double timeRequiredToProgram;
    double numberOfDeliveredBugs;


    // halstead constructor
    public Halstead(String path){
        setPath(path);
    }

    public void setPath(String path){
        this.path = path;
        return;
    }

    // main function of this class
    public void DoHalstead() throws IOException {
        initializeHalsteadData();
        preProcessForHalstead();
        detectMethodArea();
        countHalstead();
        printData();
    }

    private void initializeHalsteadData() {
        this.totalNumberOfOperands = 0;
        this.totalNumberOfOperators = 0;
        this.numberOfDistinctOperands = 0;
        this.numberOfDistinctOperators = 0;

        this.programVocabulary = 0;
        this.programLength = 0;
        this.calculatedProgramLength = 0.0;
        this.volume = 0.0;
        this.difficulty = 0.0;
        this.effort = 0.0;
        this.timeRequiredToProgram = 0.0;
        this.numberOfDeliveredBugs = 0.0;
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
    }

    private void countHalstead(){
        halsteadCounter = new HalsteadCounter(preprocessedCodeForHalstead, functionAreaIndex);
        halsteadCounter.countHalsteadMetrics();
        this.totalNumberOfOperands = halsteadCounter.getTotalNumberOfOperands();
        this.totalNumberOfOperators = halsteadCounter.getTotalNumberOfOperators();
        this.numberOfDistinctOperands = halsteadCounter.getNumberOfDistinctOperands();
        this.numberOfDistinctOperators = halsteadCounter.getNumberOfDistinctOperators();

        //*** now calculate ***//
        int n1 = numberOfDistinctOperators;
        int n2 = numberOfDistinctOperands;
        int N1 = totalNumberOfOperators;
        int N2 = totalNumberOfOperands;

        programVocabulary = n1+n2;
        programLength = N1+N2;
        calculatedProgramLength = (n1*(Math.log((double) n1)/Math.log((double)2))) + (n2*(Math.log((double) n2)/Math.log((double)2)));
        volume = programLength*(Math.log((double)programVocabulary)/Math.log((double)2));
        difficulty =  ((double)n1/(double)2) * ((double)N2/(double)n2);
        effort = volume * difficulty;
        timeRequiredToProgram = effort/18;
        numberOfDeliveredBugs = volume/3000;
    }

    private void printData(){
        System.out.println("///*** Halstead Metrics  ***///");
        System.out.println("Number of Distinct Operators, n1: "+numberOfDistinctOperators);
        System.out.println("Number of Distinct Operands, n2: "+numberOfDistinctOperands);
        System.out.println("Total number of Operators, N1: "+totalNumberOfOperators);
        System.out.println("Total number of Operands, N2: "+totalNumberOfOperands);
        System.out.println("Program Vocabulary, n: "+programVocabulary);
        System.out.println("Program Length, N: "+programLength);
        System.out.println("Calculated estimated Program Length, N_es: "+calculatedProgramLength);
        System.out.println("Volume, V: "+volume);
        System.out.println("Difficulty, D: "+difficulty);
        System.out.println("Effort, E: "+effort);
        System.out.println("Time Required to Program, T: "+timeRequiredToProgram+" seconds");
        System.out.println("Number of Delivered Bugs, B: "+numberOfDeliveredBugs);
    }

}
