package Clone;

import java.util.ArrayList;

public class SimilarityChecker {
    ArrayList<Long> fingerprints1;
    ArrayList<Long> fingerprints2;

    public SimilarityChecker(ArrayList<Long> fingerprints1, ArrayList<Long> fingerprints2){
        this.fingerprints1 = fingerprints1;
        this.fingerprints2 = fingerprints2;
    }

    public double getClone(){
//        double jaccardResult = jaccardSimilarity();
//        return jaccardResult;
        double diceCoefficientResult = diceCoefficient();
        return diceCoefficientResult;
    }

    private double jaccardSimilarity() {
        ArrayList<Long> jaccardIntersection = intersection();
        double jaccardCoefficient;
        int C = jaccardIntersection.size();
        int A = fingerprints1.size();
        int B = fingerprints2.size();

        jaccardCoefficient = (double) (C * 100) / ((A + B) - C);

        return jaccardCoefficient;
    }

    private double diceCoefficient(){
        ArrayList<Long> diceIntersection = intersection();
        double diceCoefficient;
        int C = diceIntersection.size();
        int A = fingerprints1.size();
        int B = fingerprints2.size();

        diceCoefficient = (double) (2*C)/(A+B);
        diceCoefficient *= 100;

        return  diceCoefficient;
    }

    private ArrayList<Long> intersection(){
        ArrayList<Long> tempList1 = new ArrayList<>();
        ArrayList<Long> tempList2 = new ArrayList<>();
        for(Long element: fingerprints1){
            if(!tempList1.contains(element)){
                tempList1.add(element);
            }
        }
        for(Long element: fingerprints2){
            if(tempList1.contains(element)){
                tempList2.add(element);
            }
        }
        return tempList2;
    }
}
