package taijibear.util;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class ActiveRecordUtil {

	/***
	 * 初始化数据库
	 */
		public static void initDb (){
			
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
		

	}

}
