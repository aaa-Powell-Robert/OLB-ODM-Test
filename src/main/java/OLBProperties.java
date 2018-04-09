package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OLBProperties {
	
	private static Properties properties;

	public static String getOLBPropertyLocation() {
		String propertyFilePath = System.getProperty("OLBPropertyFile", "main//resources//OLBproperties.properties");
		return propertyFilePath;
	}
	
	public Properties readOLBProperties() {
		Properties p;
		BufferedReader reader;
		String propertyFilePath = getOLBPropertyLocation();
		p = new Properties();
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				p.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			//throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			System.err.println("Configuration.properties not found at " + propertyFilePath);
		}		
		return p;
	}
	
	public static Properties getProperties() {
		if (properties == null) {
			OLBProperties o = new OLBProperties();
			properties = o.readOLBProperties();
		}
		return properties;
	}


	public static String getODMEndpoint() {
		String s = OLBProperties.getProperties().getProperty("ODM.endpoint", "http://localhost:9081");
		System.out.println("ODM.endpoint="+s);
		return s;
	}
}
