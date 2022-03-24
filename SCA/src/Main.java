import Clone.CloneMain;
import Clone.PreprocessingForClone;
import Metrics.Halstead;
import Metrics.LOC;

import java.io.IOException;

public class Main {

    public static void main(String[]args) throws IOException {

        String path1 = "SCA/src/test/LOC test.c";
        String path2 = "SCA/src/test/test1.c";
        //String path = "SCA/src/test/pointerArgTest.c";

//        LOC loc = new LOC();
//        loc.DoLOC(path);
//
//        Halstead halstead = new Halstead(path);
//        halstead.DoHalstead();

        CloneMain cloneMain = new CloneMain(3,path1, path2);
        cloneMain.cloneProcess();

    }

}
