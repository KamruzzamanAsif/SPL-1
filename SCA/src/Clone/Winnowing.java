package Clone;

import java.util.ArrayList;

public class Winnowing {
    ArrayList<Long> hashes;
    private final int windowSize;
    ArrayList<Long> fingerPrints = new ArrayList<>();

    public Winnowing(ArrayList<Long> hashes, int windowSize){
        this.hashes = hashes;
        this.windowSize = windowSize;
    }

    public ArrayList<Long> winnow(){
        int min_index = -1;
        long maxValue = (long)1e9+9;
        long min = maxValue;
        fingerPrints.clear();
        // At the end of each iteration, min holds the
        // position of the rightmost minimal hash in the
        // current window.
        for(int i=0; i<hashes.size()-windowSize+1; i++){
            if(min_index == i-1){
                // The previous minimum is no longer in this window.
                min = maxValue;
                for(int x=i; x<windowSize+i; x++){
                    if(min > hashes.get(x % hashes.size())){
                        min_index = x % hashes.size();
                        min = hashes.get(x % hashes.size());
                    }
                }
                fingerPrints.add(min);
            }
            else{
                // The previous minimum is in this window
                if(min > hashes.get((i+windowSize-1) % hashes.size())){
                    min_index = (i+windowSize-1) % hashes.size();
                    min = hashes.get((i+windowSize-1) % hashes.size());
                    fingerPrints.add(min);
                }
            }
        }
        return fingerPrints;
    }
}
