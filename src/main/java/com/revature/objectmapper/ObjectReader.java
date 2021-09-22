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
import java.util.Properties;

import com.revature.util.ColumnField;
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

		String sql = "SELECT * FROM " + props.getProperty("DBschema") + "." + model.getTableName();

		Statement pstmt = null;
		try {
			pstmt = conn.createStatement();

			ResultSet rs;

			rs = pstmt.executeQuery(sql);

			List<ColumnField> Cols = model.getColumns();

			Object[] objz = new Object[Cols.size()+1];
			Class<?>[] typz = new Class<?>[Cols.size()+1];
			
			while (rs.next()) {
				objz = new Object[Cols.size()+1];
				int id = rs.getInt("id");
				objz[0] = id;
				typz[0] = int.class;

				for (int i = 0; i < Cols.size(); i++) {

					if (i < Cols.size()) {
						String columnSwitch = Cols.get(i).getStringType();
						
						switch(columnSwitch) {
						
						case "String":
							objz[i+1] = rs.getString(Cols.get(i).getColumnName());
							typz[i+1] = String.class;
							break;
						case "int":
							objz[i+1] = rs.getInt(Cols.get(i).getColumnName());
							typz[i+1] = int.class;
							break;
						default:
							break;
						}

					}
				}
				
				Class<?> c = Class.forName(model.getClassName());
				Constructor<?> cons = c.getDeclaredConstructor(typz);
				Object object = cons.newInstance(objz);
				terminal.add(object);
				
			}
			
			return terminal;

		} catch (SQLException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// add an exception here
			e.printStackTrace();
		}
		
		return null;

	}

}