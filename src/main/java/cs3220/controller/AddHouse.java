package cs3220.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.House;

@WebServlet("/AddHouse")
public class AddHouse extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddHouse()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/AddHouse.jsp" )
            .forward( request, response );
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	  int id = 0;
          Connection con = null;
      
    	try {
    		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu26";
    		String username = "cs3220stu26";
    		String  password = "2B4HkOdPv2Ot";
    		
    	
    		List<House> houses = (List<House>) getServletContext()
            .getAttribute( "houses" );
        
      
    		con = DriverManager.getConnection(url,username, password);
    		
    		String sql = "insert into houses(name) values(?)";
    		PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		pstmt.setString(1, request.getParameter( "house" ));
    		
    		pstmt.executeUpdate();
    		ResultSet rs = pstmt.getGeneratedKeys();
    		
    		if(rs.next()) id = rs.getInt(1);
    		pstmt.close();

    		houses.add( new House( request.getParameter( "house" ) ) );
    
    	}
    	catch(SQLException e) {
    		throw new ServletException(e);
    	}
       finally {
    	   try {
    		   if(con != null) {
    			   con.close();
    		   }
    	   }
    	   catch(SQLException e) {
       		throw new ServletException(e);
       	}
       }
        
        
     //  houses.add( new House( request.getParameter( "house" ) ) );
        
        
        response.sendRedirect( "ListCharacters" );
    }

}
