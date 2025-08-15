package baseclassUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configmanager {
private static Properties prop =new Properties();
	
	static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.property");
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static String getProperty(String key) {
		// TODO Auto-generated method stub
		return prop.getProperty(key);
	}
}
