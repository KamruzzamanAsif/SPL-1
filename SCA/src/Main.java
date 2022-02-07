import java.io.IOException;

public class Main {

    public static void main(String[]args) throws IOException {
        //String path = "G:/SCA/src/editDistance.cpp";
        String path = "src/LOC test.c";

        LOC loc = new LOC();
        loc.LOC(path);
    }
}
