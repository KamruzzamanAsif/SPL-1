import Metrics.Halstead;
import Metrics.LOC;

import java.io.IOException;

public class Main {

    public static void main(String[]args) throws IOException {

        //String path = "SCA/src/LOC test.c";
        String path = "SCA/src/test/test1.c";
        //String path = "SCA/src/test/pointerArgTest.c";

        LOC loc = new LOC();
        loc.DoLOC(path);

        Halstead halstead = new Halstead(path);
        halstead.DoHalstead();

    }

}
