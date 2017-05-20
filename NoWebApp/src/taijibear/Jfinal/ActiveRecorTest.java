package taijibear.Jfinal;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.jfinal.json.JFinalJson;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ActiveRecorTest {
	
	// 链接
    private static Connection conn;
    
    // 数据源
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
	

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub	
		String driverClass = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:@192.168.248.161:1521:orcl";
        String user = "train";
        String password = "train88888";
		
        // 创建 C3P0 连接池
		C3p0Plugin c3p0Plugin = new C3p0Plugin(url, user, password,	driverClass);
	
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);

		arp.setDialect(new OracleDialect());
		//arp.setDialect(new MysqlDialect());
		arp.setShowSql(true);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		
		c3p0Plugin.start();
		arp.start();

		List<Record> recordList = Db.find( "select * from sw_sto_kpsqd ");
		

		System.out.println( JFinalJson.getJson().toJson(recordList) );
			
		Record rd = new Record(); 
		
		rd.toJson();
		
		System.out.println( recordList.size());
		System.out.println( recordList.get(0).get("F_CHDATE").toString()  );
		System.out.println("success" );
		
	}
	
	  public static Connection getConnection() {
	        try {

	    		/*
	    		 *  jdbc.driver=oracle.jdbc.driver.OracleDriver
	    		 *	jdbc.url=jdbc:oracle:thin:@10.80.163.157:1521:orcl
	    		 *	jdbc.user=cwbase8_9999
	    		 *	jdbc.password=ORA88888 
	    		 */
	    		
	        	
	            ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
	            ds.setJdbcUrl("jdbc:oracle:thin:@192.168.248.161:1521:orcl");
	            ds.setUser("train");
	            ds.setPassword("train88888");
	            conn = ds.getConnection();
	            
	            System.out.println("success" );
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (PropertyVetoException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }

}
