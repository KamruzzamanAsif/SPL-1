package Clone;

import java.io.File;
import java.io.IOException;

public class CloneParent {
    File[] files;
    String[] filePaths;
    private final int kGramSize = 3;

    public CloneParent(File[] files){
        this.files = files;
    }

    public void detectClone() throws IOException {
        copyFilePaths();
        detectAllFileClone();
    }

    public void copyFilePaths(){
        int i=0;
        for(File file: files){
            String path = file.getAbsolutePath();
            filePaths[i++] = path;
        }
    }

    public void detectAllFileClone() throws IOException {
        CloneMain cloneMain;
        for(int i=0; i<filePaths.length; i++){
            for(int j=0; j<filePaths.length; j++){
                if(j!=i){
                    cloneMain = new CloneMain(kGramSize, filePaths[i], filePaths[j]);
                    cloneMain.cloneProcess();
                    System.out.println(cloneMain.getCloneResult());
                }
            }
        }
    }
}
