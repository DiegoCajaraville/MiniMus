package com.teleco.minimus.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FicheroSalida {

    private static final Log LOGGER = LogFactory.getLog(FicheroSalida.class);

    public static boolean escritura(String contenido, String fileName) {
        
        try {
            FileUtils.writeStringToFile(new File("./src/main/resources/results/" + fileName), contenido, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("Error en la escritura del fichero de salida");
            return false;
        } finally {
        }

        return true;
    } 
}
