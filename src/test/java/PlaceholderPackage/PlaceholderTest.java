package PlaceholderPackage;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import com.revature.connection.ConnectionFactory;
import com.revature.objectmapper.ObjectCache;
import com.revature.util.MetaModel;

public class PlaceholderTest {

	
	ConnectionFactory Cf = ConnectionFactory.getInstance();
	Connection conn = Cf.getConnection();
	
	@Test
	public void testConnectionNotNull()
	{
		
		assertTrue(conn != null);
		
	}
	
	@Test
	public void testputObjInCache()
	{
		
		Object obj = new Object();
		
		boolean flag = ObjectCache.putObjInCache(obj , conn);
		
		assertTrue(flag == true);
		
	}
	
	
	
	
}
