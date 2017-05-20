package taijibear.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;


public class StartActiveRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StartActiveRecord() {
        // TODO Auto-generated constructor stub
    }
    
   
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		System.out.println( "start init active record" );
		
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
		
		/*
		List<Record> recordList = Db.find( "select * from sw_sto_kpsqd ");
			
		Record rd = new Record(); 
		
		rd.toJson();
		
		System.out.println( recordList.size());
		
		System.out.println( recordList.get(0).toJson()    );
		
		*/
		System.out.println("init active record" );
	
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // 设置响应内容类型
	      response.setContentType("text/html");

	      // 实际的逻辑是在这里
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + "success" + "</h1>");
	      
	      out.close();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println( "destroy active record  success" );
	}
	
	
	

}
