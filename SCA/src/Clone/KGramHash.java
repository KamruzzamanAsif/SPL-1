package Clone;

import java.util.ArrayList;

public class KGramHash {
    private final int base = 101;
    private final long m = (long)1e9+9;
    private int kGramSize;
    ArrayList<String> kGrams = new ArrayList<>();
    ArrayList<Long> kGramHashes = new ArrayList<>();

    public KGramHash(ArrayList<String> kGrams){
        this.kGrams = kGrams;
    }

    public ArrayList<Long> rollingHash(){
        String currentString = kGrams.get(0);
        long currentHash = hornersRule(currentString);
        kGramHashes.add(currentHash);
        // now do the remaining in O(1)
        kGramSize = currentString.length();
        long offset = 1;
        for(int i=0; i<kGramSize; i++){
            offset = (offset*base)%m;
        }

        // now we have to calculate the remaining hases form current hash
        //for(int j=1; )



        return kGramHashes;
    }

    private long hornersRule(String currentString) {
        long hashValue = 0;
        for(int i=0; i<currentString.length(); i++){
            hashValue = ((base*hashValue) + currentString.charAt(i)) % m;
        }
        return hashValue;
    }
}
