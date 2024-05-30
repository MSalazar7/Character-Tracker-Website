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

import cs3220.model.Character;
import cs3220.model.House;

@WebServlet("/AddCharacter")
public class AddCharacter extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddCharacter()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/AddCharacter.jsp" )
            .forward( request, response );
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        Character c = new Character( request.getParameter( "name" ),
            request.getParameter( "father" ),
            request.getParameter( "mother" ) );

        List<House> houses = (List<House>) getServletContext()
            .getAttribute( "houses" );
        House house = houses.get( 0 );
        String name = request.getParameter( "house" );
        if( name != null ) for( House h : houses )
            if( h.getName().equals( name ) )
            {
                house = h;
                break;
            }
        int id = 0;
        Connection con = null;
    	try {
    		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu26";
    		String username = "cs3220stu26";
    		String  password = "2B4HkOdPv2Ot";
    		
    		con = DriverManager.getConnection(url,username, password);
    		
    		String sql = "insert into characters (name, father, mother, house_name) values(?,?,?,?)";
    		PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		pstmt.setString(1, c.getName());
    		pstmt.setString(2,c.getFather());
    		pstmt.setString(3,c.getMother());
    		pstmt.setString(4, house.getName());
    		pstmt.executeUpdate();
    		ResultSet rs = pstmt.getGeneratedKeys();
    		
    		if(rs.next()) id = rs.getInt(1);
    		pstmt.close();

    		
    
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
        
        house.getCharacters().add( c );

        response.sendRedirect( "ListCharacters?house=" + house.getName() );
    }

}
