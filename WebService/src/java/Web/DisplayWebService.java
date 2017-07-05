/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mnanda021
 */
@WebService(serviceName = "DisplayWebService")
public class DisplayWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "display")
    public ArrayList<String[]> display(){
        
        ArrayList<String[]> arr=new ArrayList<>();
        //ConnectionDB();
        ResultSet rs=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Assignment","root","hubby");
            String sql="SELECT T.ISBN, T.Title, T.EditionNumber,T.Copyright,A.FirstName,A.LastName from Titles as T, AuthorISBN as I,Authors as A WHERE T.ISBN = I.ISBN AND I.AuthorID = A.AuthorID";
            PreparedStatement pst=con.prepareStatement(sql);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String[] ab=new String[6];
                
                ab[0]=rs.getString("ISBN");
                ab[1]=rs.getString("Title");
                ab[2]=rs.getString("EditionNumber");
                ab[3]=rs.getString("Copyright");
                ab[4]=rs.getString("FirstName");
                ab[5]=rs.getString("LastName");
             arr.add(ab);
            }
            return arr;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return arr;
        
        
    }
}
