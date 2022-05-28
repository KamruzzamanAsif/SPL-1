package Clone;

import java.util.ArrayList;

public class KGramHash {
    private final int base = 101;
    private final long m = (long)1e9+9;
    ArrayList<String> kGrams;
    ArrayList<Long> kGramHashes = new ArrayList<>();

    public KGramHash(ArrayList<String> kGrams){
        this.kGrams = kGrams;
    }

    public ArrayList<Long> rollingHash(){
        String currentString = kGrams.get(0);
        long currentHash = hornersRule(currentString);
        kGramHashes.add(currentHash);
        // now do the remaining in O(1)
        int kGramSize = currentString.length();
        long offset = 1;
        for(int i = 0; i< kGramSize -1; i++){
            offset = (offset*base);
        }
        // now we have to calculate the remaining hashes form current hash
        long nextHash;
        String nextString;
        for(int j=1; j<kGrams.size(); j++){
            nextString = kGrams.get(j);
            nextHash = (base*(currentHash - offset*currentString.charAt(0)) + nextString.charAt(nextString.length()-1));
            kGramHashes.add(nextHash);
            currentHash = nextHash;
            currentString = nextString;
        }

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
