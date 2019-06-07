package Utilities;

import java.io.*;
import java.util.Properties;


/**
 * EnvironmentPropertiesReader is to set the environment variable declaration
 * mapping for config properties in the UI test
 */
public class EnvironmentPropertiesReaderForConfig {

	public static EnvironmentPropertiesReaderForConfig envProperties=null;
	private Properties properties;

	public EnvironmentPropertiesReaderForConfig() {
		properties = PropertiesFile();		
	}
	public Properties PropertiesFile() {
		File file = new File(System.getProperty("user.dir")+"/src/main/java/Config/Config.properties");
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
			//fileInput.close();
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
		
	public static EnvironmentPropertiesReaderForConfig getInstance() {
		if (envProperties == null) {
			envProperties = new EnvironmentPropertiesReaderForConfig();
		}
		return envProperties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
