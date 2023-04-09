package Utils;

import io.cucumber.java.ja.然し;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
   static Properties prop;
    public static Properties readProperties(){
        try {
            FileInputStream file = new FileInputStream(Constants.PROPERTY_FILE_PATH);
          prop =new Properties();
          prop.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //in this method we are reading prop
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
   public static String getPropertyValue(String propKey){ //this method is for to get prop
       return prop.getProperty(propKey); //getproperty is a method
   }

}
