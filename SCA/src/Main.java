import Metrics.Halstead;
import Metrics.LOC;

import java.io.IOException;

public class Main {

    public static void main(String[]args) throws IOException {

        //String path = "G:/SCA/src/LOC test.c";
        String path = "G:/SCA/src/test/test1.c";
        //String path = "src/test/pointerArgTest.c";

        LOC loc = new LOC();
        loc.DoLOC(path);

        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();

    }

}
