package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Clase de utilidad para la gestión de registros (logs).
 */
public class Log {
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    
    /**
     * Inserta un mensaje de registro en el archivo de log.
     * @param message mensaje de registro a insertar.
     */
    public static void insertLog(String message) {
        String formattedDateTime = getFormattedDateTime();
        String logMessage = String.format("[%s] %s", formattedDateTime, message);
        
        File folderFile = new File(PROJECT_PATH, "Logs");
        File logFile = new File(folderFile, "log.txt");
        
        if (!folderFile.exists()) {
            try {
                folderFile.mkdirs(); // crea una carpeta en nuestro espacio de trabajo (tomcat)
                logFile.createNewFile();
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


