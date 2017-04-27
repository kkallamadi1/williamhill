package uk.william.hill.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
	
static String propsPath = System.getProperty("user.dir")+"//Properties//"+"env.properties";

public static Properties getProperties() throws IOException{
	File propsFile = new File(propsPath);
	FileInputStream inputStream = new FileInputStream(propsFile);
	Properties props = new Properties();
	props.load(inputStream);
	inputStream.close();
	return props;
}	
	
public static String getProperty(String key) throws IOException {
	return getProperties().getProperty(key);
}
	
	
public void setProperty(String property, String val) {
	File propsFile = new File(propsPath);
	Properties props = new Properties();  
    props.setProperty(property, val);
    
   
    FileOutputStream outputStream = null;
	try {
		outputStream = new FileOutputStream(propsFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		props.store(outputStream, "");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		outputStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

