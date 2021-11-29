package module15;

import java.io.*;

public class Precode {

    private static final String CURRENT_FOLDER = "src/module15";
    private static final String FILENAME_ONE = "file1.txt";
    private static final String FILENAME_TWO = "file2.txt";

    public static void main(String[] args) throws IOException {
        while (true) {
            copyFileUsingStream(new File(CURRENT_FOLDER, FILENAME_ONE),
                                new File(CURRENT_FOLDER, FILENAME_TWO));
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {

        InputStream fis = new FileInputStream(source);
        OutputStream fos = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
        fis.close();
        fos.close();
    }
}
