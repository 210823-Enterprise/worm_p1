package PlaceholderPackage;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import com.revature.connection.ConnectionFactory;
import com.revature.dummymodels.Goblin;
import com.revature.objectmapper.ObjectCache;

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
		
		Goblin g = new Goblin();
		
		boolean flag = ObjectCache.putObjInCache(g , conn);
		
		assertTrue(flag == true);
		
	}
	
	@Test
	public void testSaveCache()
	{
		assertTrue(ObjectCache.saveCache(conn));
		
	}
	
	
}
