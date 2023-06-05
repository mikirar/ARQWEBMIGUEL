package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    
    public static void insertLog(String message) {
        String formattedDateTime = getFormattedDateTime();
        String logMessage = String.format("[%s] %s", formattedDateTime, message);
        
        File folderFile = new File(PROJECT_PATH, "Logs");
        File logFile = new File(folderFile, "log.txt");
        
        if (!folderFile.exists()) {
            try {
                //File folderFile = new File(PROJECT_PATH,"Logs"); 
                folderFile.mkdirs(); // create a folder in your current work space
                //File logFile = new File(folderFile, "log.txt"); // put the file inside the folder
                logFile.createNewFile();
                //logFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de registro.");
                e.printStackTrace();
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al insertar el registro en el archivo de registro.");
            e.printStackTrace();
        }
    }

    private static String getFormattedDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}


