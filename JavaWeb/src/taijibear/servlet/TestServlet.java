package taijibear.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;


public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		System.out.println( "TestServlet init success" );
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		doPost(request,response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // 设置响应内容类型

		
		List<Record> recordList = Db.find( "select * from sw_sto_kpsqd ");
		
		Record rd = new Record(); 
		rd.toJson();
		
		System.out.println( recordList.size());
		response.setContentType("text/html");

	      // 实际的逻辑是在这里
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + "success" + "</h1>");
	      out.close();
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println( "TestServlet destroy success" );
	}
	
	
	

}
