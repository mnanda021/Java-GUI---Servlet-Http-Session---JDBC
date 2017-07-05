/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author mnanda021
 */
public class dbUpdate extends SimpleTagSupport {
    private String TYPE;
    private String ISBN;
    /*private String Title;
    private String edition;
    private String copyright;
    private String firstname;
    private String lastname;*/
    Connection con=null;
    ResultSet rs=null;

    private String STATUS;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

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
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext page=(PageContext) getJspContext();
             HttpServletRequest req=(HttpServletRequest)page.getRequest();
             HttpServletResponse res=(HttpServletResponse)page.getResponse();
      
        if(TYPE.equals("Insert")){
             
             out.clear();
            try{
               // boolean status=false;
              //  if(ISBN.isEmpty()){
               out.println("<html>");
                out.println("<head>");
		
		 out.println("<meta charset='utf-8'>");
   out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
   out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
   out.println("<link rel='stylesheet' href='css/menu1.css'>");
    out.println("<link rel='stylesheet' href='css/form.css'>");
   out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
   out.println("<script src='script.js'></script>");
   out.println("<title>Insert Data</title>");
	out.println("</head>");
                out.println("<body background-image='../images/books_office.jpg'>");
               out.println("<div id='cssmenu'>");
out.println("<ul>");
   out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
   out.println("<li class='active has-sub'><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");
    /* out.println(" <ul>");
    out.println("<li class='has-sub'><a href='#'><span>Product 1</span></a>");
           out.println(" <ul>");
           out.println(" <li><a href='#'><span>Sub Product</span></a></li>");
              out.println(" <li class='last'><a href='#'><span>Sub Product</span></a></li>");
           out.println(" </ul>");
         out.println("</li>");
         out.println("<li class='has-sub'><a href='#'><span>Product 2</span></a>");
           out.println(" <ul>");
            out.println("   <li><a href='#'><span>Sub Product</span></a></li>");
             out.println("  <li class='last'><a href='#'><span>Sub Product</span></a></li>");
            out.println("</ul>");
         out.println("</li>");
     out.println(" </ul>");
   out.println("</li>");*/
   out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
   out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");
   out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
out.println("</ul>");
out.println("</div>");
                out.println("<section class='container'>");
    out.println("<div class='login'>");
      out.println("<h1>Insert Book</h1>");
                out.println("<center>");
                out.println("<form method='post'>");
                out.print("<p><input type='text' name='ISBN'  placeholder='Please enter ISBN'/></p>");
                //out.println("<br>");
                 out.print (" <p><input type='text' name='Title' placeholder='Please enter Title'/></p>");
                 out.print ("<p><input type='text' name='EditionNumber' placeholder='Please enter EditionNumber'/></p>");
                //out.println("<br>");
                out.print ("<p><input type='text' name='Copyright' placeholder='Please enter Copyright of Book'/></p>");
               out.print ("<p><input type='text' name='firstname' placeholder='Please enter First Name of Author'/></p>");
              // out.println ("<br>");
               out.print("<p><input type='text' name='lastname' placeholder='Please enter Last Name of Author'/></p>");
                out.println("<p><input type='submit' value='Insert' /></p>");
                out.println("</form>");
               
                out.println ("</center> ");      
                   //  out.println(" </form>");
    out.println("</div>");
            out.println("</body>");
       out.println ("</html>");
               // }
                //else{
      // String isbn1=(String)page.findAttribute(TYPE)
       String isbn=(String) page.getRequest().getParameter("ISBN");
       String Title=(String)page.getRequest().getParameter("Title");
       String edition=(String)page.getRequest().getParameter("EditionNumber");
       String copyright=(String)page.getRequest().getParameter("Copyright");
       String firstname=(String)page.getRequest().getParameter("firstname");
       String lastname=(String)page.getRequest().getParameter("lastname");
      // out.println(isbn);
      // String Title=(String) page.getAttribute("Title");
      // String EditionNumber=(String) page.getAttribute("EditionNumber");
       //String Copyright=(String) page.getAttribute("Copyright");
       //String FirstName=(String) page.getAttribute("firstname");
       //String LastName=(String) page.getAttribute("lastname");
          boolean  status=JDBCconnection.AddData(isbn, Title, edition, copyright, firstname, lastname, con);
      
                if(status==true){
          res.sendRedirect("./dbUpdate.jsp");
      }
                
     // out.println(isbn);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
        }
        else if(TYPE.equals("delete")){
        //    PageContext page=(PageContext) getJspContext();
            try{
                
                boolean delete=JDBCconnection.deleteData(ISBN, con);
                if(delete==true){
                    out.println("Book deleted successfully");
                  res.sendRedirect("./dbUpdate.jsp");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        }else if(TYPE.equals("edit")){
      //       PageContext page=(PageContext) getJspContext();
             
             out.clear();
            try{
                //rs=JDBCconnection.query(con, ISBN);
               // String sql="select T.Title, T.EditionNumber, T.Copyright,A.FirstName,A.LastName from Titles as T  JOIN AuthorISBN as I on T.ISBN=I.ISBN JOIN Authors as A on A.AuthorID=I.AuthorID where T.ISBN='"+ISBN+"'";
               // PreparedStatement pst=con.prepareStatement(sql);
               rs=JDBCconnection.edit(con, ISBN);
                out.println("<html>");
                out.println("<head>");
		
		
	out.println("<meta charset='utf-8'>");
   out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
   out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
   out.println("<link rel='stylesheet' href='css/menu1.css'>");
   out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
   out.println("<script src='script.js'></script>");
    out.println("<link rel='stylesheet' href='css/form.css'>");
   out.println("<title>Update Data</title>");
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
              //out.println("<h1>Update Book</h1>");
                out.println("<section class='container'>");
    out.println("<div class='login'>");
      out.println("<h1>Update Book</h1>");
                out.println("<center>");
                out.println("<form method='post' ");
          while(rs.next()){
              
          String Title=rs.getString("Title");
          String EditionNumber=rs.getString("EditionNumber");
          String Copyright=rs.getString("Copyright");
          String FirstName=rs.getString("FirstName");
          String LastName=rs.getString("LastName");
       //   out.println(BookISBN);
            //   out.println("<tr>");
              // out.print("<td>");
            out.println("<p>ISBN: <input type='text' name='ISBN' value='"+ISBN+"' readonly/></p>");
            //out.println("</td>");
           // out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
           // out.println("</tr>");
           // out.println("<tr>");
            //out.print("<td>"+rs.getString("Title")+ "</td>");
             
          //  out.print("<td>");
            out.println("<p>Title: <input type='text' name='Title' size='35' value='"+Title+"' /></p>");
           // out.println("</td>");
            //out.println("</tr>");
            //out.println("<tr>");
            //out.print("<td>");
            out.println("<p>EditionNumber: <input type='text' name='EditionNumber' value='"+EditionNumber+"'/></p>");
            //out.println("</td>");
            //out.println("</tr>");
            //out.println("<tr>");
            //out.print("<td>");
            out.println("<p>Copyright: <input type='text' name='Copyright' value='"+Copyright+"' /></p>");
            //out.println("</td>");
            //out.println("</tr>");
            //out.println("<tr>");
            //out.print("<td>");
            out.println("<p>FirstName: <input type='text' name='FirstName' value='"+FirstName+"' /></p>");
           // out.println("</td>");
            //out.println("</tr>");
            //out.println("<tr>");
            //out.print("<td>");
            out.println("<p>LastName: <input type='text' name='LastName' value='"+LastName+"' /></p>");
            //out.println("</td>");
            //out.println("</tr>");
           
        //     out.print("<td>"+"<a href='TestAll.jsp?TYPE=edit&ISBN="+BookISBN+"'>Edit</a> </td>" );
        //      out.print("<td>"+"<a href='TestAll.jsp?TYPE=delete&ISBN="+BookISBN+"' >Delete</a>"+ "</td>");
            
          //out.println("<input type='hidden' name='STATUS' value='true' ><br><br>");
            
          }
         out.println("<p><input type='submit' value='Update' /></p>");
          out.println("</form>");
       // System.out.println("ISBN is"+BookISBN);
       //out.println("</table>");
         out.println ("</center> ");      
                    // out.println(" </form>");
    out.println("</div>");
             
             out.println("</BODY></HTML>") ;
           
        String isbn=(String) page.getRequest().getParameter("ISBN");
       String Title=(String)page.getRequest().getParameter("Title");
       String edition=(String)page.getRequest().getParameter("EditionNumber");
       String copyright=(String)page.getRequest().getParameter("Copyright");
       String firstname=(String)page.getRequest().getParameter("FirstName");
       String lastname=(String)page.getRequest().getParameter("LastName");
        boolean update=JDBCconnection.UpdateData(isbn, Title, edition, copyright, firstname, lastname, con);
           // boolean delete=JDBCconnection.deleteData(ISBN, con);
                if(update==true){
                    res.sendRedirect("./dbUpdate.jsp");
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
