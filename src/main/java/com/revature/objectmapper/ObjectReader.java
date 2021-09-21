package com.revature.objectmapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
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

		List<Object> terminal;

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

			while (rs.next()) {
				int id = rs.getInt("id");
				for (int i = 0; i < Cols.size(); i++) {

					if (i < Cols.size()) {
						System.out.println(Cols.get(i).getStringType());
						if (Cols.get(i).getStringType() == "String") {
							rs.getString(Cols.get(i).getColumnName());
						}
						System.out.println(Cols.get(i).getColumnName());
					}
				}
			}

		} catch (SQLException e) {
			// add an exception here
			e.printStackTrace();
		}
		
		return null;

	}

}