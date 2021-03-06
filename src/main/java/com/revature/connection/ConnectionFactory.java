package com.revature.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionFactory 
{
	
	private BasicDataSource ds;
	private static final ConnectionFactory connection_factory = new ConnectionFactory();
	
	static { 
	
					try 
					{
						Class.forName("org.postgresql.Driver");
					} 
					catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
		
	        }
	
	private ConnectionFactory() 
	{
		ClassLoader classLoader = getClass().getClassLoader();
		Properties props = new Properties();
		try 
		{
			
			props.load(new FileReader(classLoader.getResource("application.properties").getFile()));
			ds = new BasicDataSource();
			ds.setUrl(props.getProperty("url"));
			ds.setUsername(props.getProperty("username"));
			ds.setPassword(props.getProperty("password"));
			ds.setDriverClassName("org.postgresql.Driver");
			ds.setMinIdle(5);
			ds.setDefaultAutoCommit(true);
			ds.setMaxOpenPreparedStatements(10000);
			
		} 
		catch (IOException e)
		{
			//log goes here, custom logger?
		}
	}
	
	
	public static ConnectionFactory getInstance() 
	{
		return connection_factory;
	}
	
	
	public Connection getConnection() 
	{
		
		try 
		{
			return ds.getConnection();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	

}