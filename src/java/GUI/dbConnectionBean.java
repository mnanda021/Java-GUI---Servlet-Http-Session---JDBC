/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author mnanda021
 */
public class dbConnectionBean {
    private String username;
    private String password1;
    Connection con=null;
    ResultSet rs=null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    public Connection DBConnection(){
        try{
            con=JDBCconnection.DBConnection();
            return con;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    return con;
    }
    public boolean LoginValidation(){
        
    if(this.username.equals("Mahananda")&&this.password1.equals("Manu123")){
            
            return true;
        }
        else{
            return false;
        }
    }
}
