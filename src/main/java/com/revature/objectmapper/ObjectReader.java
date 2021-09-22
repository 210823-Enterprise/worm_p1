package com.revature.objectmapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.revature.util.ColumnField;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectReader extends ObjectMapper {

	public ObjectReader() {
		super();
	}

	ClassLoader classLoader = getClass().getClassLoader();
	Properties props = new Properties();

	public List<Object> getObjectsFromDB(Object obj, Connection conn) {

		List<Object> terminal = new ArrayList<Object>();

		try {
			props.load(new FileReader(classLoader.getResource("application.properties").getFile()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MetaModel<?> model = MetaModel.of(obj.getClass());
		IdField Pk = model.getIdField();

		String sql = "SELECT * FROM " + props.getProperty("DBschema") + "." + model.getTableName();

		System.out.println(sql);
		Statement pstmt = null;
		try {
			pstmt = conn.createStatement();

			ResultSet rs;

			rs = pstmt.executeQuery(sql);

			List<ColumnField> Cols = model.getColumns();
			System.out.println(model.getSimpleClassName());
			//instantiate this object
			//model.getSimpleClassName() instance; 
			
			
			/*try {
				Object object = obj.getClass().forName(model.getSimpleClassName()).getConstructor(null);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			String inputParam = "";
			
			while (rs.next()) {
				int id = rs.getInt("id");
				inputParam += id;
				System.out.println("id: " + id);
				for (int i = 0; i < Cols.size(); i++) {

					if (i < Cols.size()) {
						String columnSwitch = Cols.get(i).getStringType();
						
						switch(columnSwitch) {
						
						case "String":
							System.out.println(rs.getString(Cols.get(i).getColumnName()));
							break;
						case "int":
							System.out.println(rs.getInt(Cols.get(i).getColumnName()));
							break;
						default:
							break;
						}

					}
				}
				System.out.println("---------------");
			}
			
			
			Class<?> c = Class.forName(model.getClassName());

			Constructor<?> cons = c.getDeclaredConstructor();
			Object object = cons.newInstance();
			Object object2 = cons.newInstance();
			terminal.add(object);
			terminal.add(object2);
			
			for (Object objs : terminal) {
				System.out.println(objs.getClass());
				
			}
			
			return terminal;

		} catch (SQLException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// add an exception here
			e.printStackTrace();
		}
		
		return null;

	}

}