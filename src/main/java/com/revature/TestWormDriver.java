package com.revature;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestWormDriver {

	public static void main(String[] args) {
		/**
		 * Driver for testing different functionality
		 * 
		 */
		//////////////////////////////////////////////////////////
		//testing retrieving classes from application.properties//
		Properties props = new Properties();
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String[] tokens=s.split(",");  
		String[] classes = (props.getProperty("classes")).split(",");
		for (String clazz : classes) {
			System.out.println(clazz);
		}
		//////////////////////////////////////////////////////////
		
		
		
		
	}

}
