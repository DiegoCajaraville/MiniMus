package com.teleco.minimus.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FicheroSalida {

    private static final Log LOGGER = LogFactory.getLog(FicheroSalida.class);
    private static boolean delivery = false;
    private static String deliveryFile = null;

    public static boolean escritura(String contenido, String fileName, boolean override) {
        
        try {
            File file = new File("./src/main/resources/" + fileName);
            FileUtils.writeStringToFile(file, contenido, "UTF-8", override);
            
        } catch (IOException e) {
            LOGGER.error("Error en la escritura del fichero de salida");
            return false;
        } finally {
        }

        return true;
    } 

    public static boolean openDelivery(String fileName) {
        if(!delivery) {
            delivery = true;
            deliveryFile = fileName;
            return true;
        }
        else
            return false;
    }

    public static boolean escrituraDelivery(String contenido) {

        if(delivery) {
        
            try {
                FileUtils.writeStringToFile(new File("./src/main/resources/" + deliveryFile), contenido, "UTF-8");
            } catch (IOException e) {
                LOGGER.error("Error en la escritura del fichero de Delivery");
                return false;
            } finally {
            }

            return true;
        }
        else
            return false;
    } 

    public static boolean closeDelivery() {
        if(delivery) {
            delivery = false;
            return true;
        }
        else
            return false;
    }
}
