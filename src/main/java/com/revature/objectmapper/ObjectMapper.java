package com.revature.objectmapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ObjectMapper {

	
	protected void setStatement(PreparedStatement pstmt, ParameterMetaData pd, Method getter, Object obj, int index) {
		
		try {
			
			setPreparedStatementByType(pstmt, pd.getParameterTypeName(index), String.valueOf(getter.invoke(obj)), index);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @param prepares  statement to set
	 * @param parameter type
	 * @param input     that represents the value to be placed in the preapred
	 *                  statement
	 * @param index     to plave the value at
	 */
	protected void setPreparedStatementByType(PreparedStatement pstmt, String type, String input, int index) {

		// find some way to evalutate the Java type of the type param
		try {
			System.out.println(type+" "+input+" "+index);
			switch (type) {
			case "text":
			case "String":
			case "varchar":
				pstmt.setString(index, input);
				break;
			case "int":
				pstmt.setInt(index, Integer.parseInt(input));
				break;
			case "double":
				pstmt.setDouble(index, Double.parseDouble(input));
				break;
			case "float":
				pstmt.setFloat(index, Float.parseFloat(input));
				break;
			case "long":
				pstmt.setLong(index, Long.parseLong(input));
				break;
			case "boolean":
				pstmt.setBoolean(index, Boolean.parseBoolean(input));
				break;
			case "short":
				pstmt.setShort(index, Short.parseShort(input));
				break;
				// timestamp, float, etc...
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public String getRDBDataType(String s)
	{
		switch (s) {
		case "String":
			  return "varchar(255)";
		case "int":
			return "varchar";
			
		case "double":
			return "varchar";
			
		case "float":
			return "varchar";
			
		case "long":
			return "varchar";
			
		case "boolean":
			return "varchar";
			
		case "short":
			return "varchar";
			
		default:
			return "varchar";
			// timestamp, float, etc...
		}
	}
	
}
