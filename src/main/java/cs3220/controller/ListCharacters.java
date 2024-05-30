package cs3220.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.House;
import cs3220.model.Character;

@WebServlet(urlPatterns = "/ListCharacters", loadOnStartup = 1)
public class ListCharacters extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ListCharacters()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
    	super.init( config );
        List<House> houses = new ArrayList<House>();
    	Connection c = null;
    	try {
    		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu26";
    		String username = "cs3220stu26";
    		String  password = "2B4HkOdPv2Ot";
    		
    		c = DriverManager.getConnection(url,username, password);
    		Statement stmt = c.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from houses");
    		
    		while(rs.next()) {
    			houses.add(new House(rs.getString("name")));
    			
    		}
    		
   
    	}
    	catch(SQLException e) {
    		throw new ServletException(e);
    	}
       finally {
    	   try {
    		   if(c != null) {
    			   c.close();
    		   }
    	   }
    	   catch(SQLException e) {
       		throw new ServletException(e);
       	}
       }
    	
       
        getServletContext().setAttribute( "houses", houses );
    }

    @SuppressWarnings("unchecked")
    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        List<House> houses = (List<House>) getServletContext().getAttribute( "houses" );
        House house = houses.get( 0 );
        String name = request.getParameter( "house" );
        if( name != null ) for( House h : houses )
            if( h.getName().equals( name ) )
            {
                house = h;
                break;
            }
        
        Connection c = null;
    	try {
    		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu26";
    		String username = "cs3220stu26";
    		String  password = "2B4HkOdPv2Ot";
    		
    		c = DriverManager.getConnection(url,username, password);
    		Statement stmt = c.createStatement();
    		String sql = "select * from characters where house_name = ?";
    		PreparedStatement pstmt = c.prepareStatement(sql);
    		pstmt.setString(1, house.getName());
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			Character ch = new Character();
    			ch.setName(rs.getString("name"));
    			ch.setFather(rs.getString("father"));
    			ch.setMother(rs.getString("mother"));
    			house.getCharacters().add(ch);
    		}
   
    	}
    	catch(SQLException e) {
    		throw new ServletException(e);
    	}
       finally {
    	   try {
    		   if(c != null) {
    			   c.close();
    		   }
    	   }
    	   catch(SQLException e) {
       		throw new ServletException(e);
       	}
       }
    	
       
        request.setAttribute( "house", house );
        request.getRequestDispatcher( "/WEB-INF/ListCharacters.jsp" )
            .forward( request, response );
    }
}
