/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author mnanda021
 */
public class dbQueryDisplay extends SimpleTagSupport {

    Connection con=null;
    ResultSet rs=null;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        String BookISBN=null;
         try {
            rs=JDBCconnection.DisplayData(con);
            
        out.println("<HTML>");
            // Start on the body
            out.println("<head>");
		
		out.println("<meta charset='utf-8'>");
   out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
   out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
   out.println("<link rel='stylesheet' href='css/menu1.css'>");
   out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
   out.println("<script src='script.js'></script>");
   out.println("<link rel='stylesheet' href='css/table1_reset.css'>");
 out.println("<link rel='stylesheet' href='css/table1.css'>");

   //out.println("<title>Insert Data</title>");
	out.println("</head>");
                out.println("<body>");
               out.println("<div id='cssmenu'>");
out.println("<ul>");
   out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
   out.println("<li class='active has-sub'><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");
    
   out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
   out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");
   out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
out.println("</ul>");
out.println("</div>");
            out.println("<CENTER>");
          
           out.println("<section> ");
out.println("<h1>Data Table</h1>  ");
out.println("<div  class='tbl-header'>");
out.println("<table cellpadding='0' cellspacing='0' border='0'>");
 out.println(" <thead><tr>");
            out.println("<th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><th>FirstName</th><th>LastName</th><th colspan='2'>Action</th>");
           out.println("</tr> </thead>");
out.println("</table>");
out.println("</div>");
out.println("<div  class='tbl-content'>");
out.println("<table cellpadding='0' cellspacing='0' border='0'>");
 out.println(" <tbody>");
          while(rs.next()){
               BookISBN=rs.getString("ISBN");
               out.println("<tr>");
            out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
            out.print("<td>"+rs.getString("Title")+ "</td>");
            out.print("<td>"+rs.getString("EditionNumber")+ "</td>");
            out.print("<td>"+rs.getString("Copyright")+ "</td>");
            out.print("<td>"+rs.getString("FirstName")+ "</td>");
            out.print("<td>"+rs.getString("LastName")+ "</td>");
             out.print("<td>"+"<a href='dbUpdate.jsp?TYPE=edit&ISBN="+BookISBN+"' style='color:purple;text-decoration:none;font-size:16px;font-weight:bold;'>Edit</a> </td>" );
              out.print("<td>"+"<a href='dbUpdate.jsp?TYPE=delete&ISBN="+BookISBN+"' style='color:purple;text-decoration:none;font-size:16px;font-weight:bold;'>Delete</a>"+ "</td>");
            
            out.println("</tr>");
            
          }
        System.out.println("ISBN is"+BookISBN);
         out.println(" </tbody>");
       out.println("</table>");
       out.println("</div>");
       out.println("</section> ");
       out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

        out.println("<script src='js/index.js'></script>");
             out.println("</CENTER>");
             out.println("</BODY></HTML>") ; 
        
        }
         catch (java.io.IOException ex) {
            throw new JspException("Error in display1 tag", ex);
        } catch (SQLException ex) {
        Logger.getLogger(dbQueryDisplay.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
}
