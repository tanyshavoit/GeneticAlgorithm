/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tetiana
 */
import java.io.*;
import java.util.Collection;
import java.util.Collections;

public class TaskLogger {

    private static final File logFile = new File("tasks.csv");

    static {
        clearLogFile();
    }

    public static void writeToFile(Collection<String> rows, boolean override) throws IOException {

        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, !override)))){
            for (String row : rows) {
                bw.write(row);
                bw.newLine();
            }
        }
    }


    public static void clearLogFile(){
        if(logFile.exists()){
            try {
                writeToFile(Collections.EMPTY_LIST, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
