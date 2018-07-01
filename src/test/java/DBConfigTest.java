import com.epam.internet_provider.connection.DbConnectionPool;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBConfigTest extends Assert {

    @Test
    public void testCreating() throws Exception {
        DbConnectionPool connectionPool = DbConnectionPool.getInstance();

        Connection connection = connectionPool.getConnection();
        Connection connection1 = connectionPool.getConnection();

        Assert.assertNotEquals(connection,connection1);
    }
}
