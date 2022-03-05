package Metrics;

import java.util.ArrayList;

public class HalsteadCounter {
    ArrayList<String> preprocessedCodeForHalstead = new ArrayList<>();
    int [][]functionAreaIndex;

    Tokenizer tokenizer;
    // operand tokens
    ArrayList<String> stringToken = new ArrayList<>();
    ArrayList<String> characterToken = new ArrayList<>();
    ArrayList<String> numberToken = new ArrayList<>();
    ArrayList<String> typeSpecifierToken = new ArrayList<>();
    ArrayList<String> identifierToken = new ArrayList<>();
    // operator tokens
    ArrayList<String> reservedWordToken = new ArrayList<>();
    ArrayList<String> loopingStatementToken = new ArrayList<>();
    ArrayList<String> controlStatementToken = new ArrayList<>();
    ArrayList<String> bracketToken = new ArrayList<>();
    ArrayList<String> singleOperatorToken = new ArrayList<>();
    ArrayList<String> doubleOperatorToken = new ArrayList<>();

    // a temp arrayList for calculation
    ArrayList<String> temp = new ArrayList<>();

    // Halstead Measures
    int totalNumberOfOperands=0;
    int totalNumberOfOperators=0;
    int numberOfDistinctOperands=0;
    int numberOfDistinctOperators=0;


    // constructor of this class
    public HalsteadCounter(ArrayList<String> preprocessedCodeForHalstead, int [][]functionAreaIndex){
        this.preprocessedCodeForHalstead = preprocessedCodeForHalstead;
        this.functionAreaIndex = functionAreaIndex;
    }

    public void countHalsteadMetrics(){
        tokenizer = new Tokenizer(preprocessedCodeForHalstead, functionAreaIndex);
        tokenizer.tokenize();
        // get the tokens from tokenizer
        getTokens();
        // now calculate the measures
        calculate();
    }

    private void getTokens() {
        this.stringToken = tokenizer.getStringToken();
        this.characterToken = tokenizer.getCharacterToken();
        this.numberToken = tokenizer.getNumberToken();
        this.typeSpecifierToken = tokenizer.getTypeSpecifierToken();
        this.identifierToken = tokenizer.getIdentifierToken();

        this.reservedWordToken = tokenizer.getReservedWordToken();
        this.loopingStatementToken = tokenizer.getLoopingStatementToken();
        this.controlStatementToken = tokenizer.getControlStatementToken();
        this.bracketToken = tokenizer.getBracketToken();
        this.singleOperatorToken = tokenizer.getSingleOperatorToken();
        this.doubleOperatorToken = tokenizer.getDoubleOperatorToken();
    }

    private void calculate(){
        /** count operand tokens **/

        //count string tokens
        temp.clear();
        totalNumberOfOperands += stringToken.size();
        for(int i=0; i<stringToken.size(); i++){
            String str = stringToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperands += temp.size();

        //count char tokens
        temp.clear();
        totalNumberOfOperands += characterToken.size();
        for(int i=0; i<characterToken.size(); i++){
            String str = characterToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperands += temp.size();

        //count number tokens
        temp.clear();
        totalNumberOfOperands += numberToken.size();
        for(int i=0; i<numberToken.size(); i++){
            String str = numberToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperands += temp.size();

        //count type specifier token
        temp.clear();
        totalNumberOfOperands += typeSpecifierToken.size();
        for(int i=0; i<typeSpecifierToken.size(); i++){
            String str = typeSpecifierToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperands += temp.size();

        //count identifier token(special case: BreakPoint)
        temp.clear();
        for(int i=0; i<identifierToken.size(); i++){
            String str = identifierToken.get(i);
            if(str.equals("BreakPoint")){
                numberOfDistinctOperands += temp.size();
                temp.clear();
            }
            else{
                totalNumberOfOperands++;
                if(!temp.contains(str)){
                    temp.add(str);
                }
            }
        }

        /** count operator tokens **/
        // count reserved word tokens
        temp.clear();
        totalNumberOfOperators += reservedWordToken.size();
        for(int i=0; i<reservedWordToken.size(); i++){
            String str = reservedWordToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();

        // count looping statement tokens
        temp.clear();
        totalNumberOfOperators += loopingStatementToken.size();
        for(int i=0; i<loopingStatementToken.size(); i++){
            String str = loopingStatementToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();

        // count control statement tokens
        temp.clear();
        totalNumberOfOperators += controlStatementToken.size();
        for(int i=0; i<controlStatementToken.size(); i++){
            String str = controlStatementToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();

        // count bracket token
        temp.clear();
        totalNumberOfOperators += bracketToken.size();
        for(int i=0; i<bracketToken.size(); i++){
            String str = bracketToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();

        //count single operator tokens
        temp.clear();
        totalNumberOfOperators += singleOperatorToken.size();
        for(int i=0; i<singleOperatorToken.size(); i++){
            String str = singleOperatorToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();

        // count double operator tokens
        temp.clear();
        totalNumberOfOperators += doubleOperatorToken.size();
        for(int i=0; i<doubleOperatorToken.size(); i++){
            String str = doubleOperatorToken.get(i);
            if(!temp.contains(str)){
                temp.add(str);
            }
        }
        numberOfDistinctOperators += temp.size();
    }

    //*** return Halstead measures  ***//
    public int getTotalNumberOfOperands(){
        return totalNumberOfOperands;
    }

    public int getTotalNumberOfOperators(){
        return totalNumberOfOperators;
    }

    public int getNumberOfDistinctOperands(){
        return numberOfDistinctOperands;
    }

    public int getNumberOfDistinctOperators(){
        return numberOfDistinctOperators;
    }
}
