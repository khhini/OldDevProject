/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.*;
/**
 *
 * @author kiki
 */
public class Database {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private String host = "jdbc:mysql://localhost:3306/lapakin";
    private String user = "root";
    private String pass = "";
    
    public Database(){
        
    }
    
    public Database(String host, String user, String pass){
        this.host = host;
        this.user = user;
        this.pass = pass;
    }
    
    public Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(host,user,pass);
        stmt = con.createStatement();
        return con;
    }
    
    public ResultSet executeQuery(String query) throws SQLException{
        this.rs = stmt.executeQuery(query);
        return rs;
    } 
    
    public int executeUpdate(String query) throws SQLException{
        return stmt.executeUpdate(query);
    }
    
    public void disconnect() throws SQLException{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
        if(con != null) con.close();
    }
    
    public ResultSet getResultSet(){
        return rs;
    }
    
//    public static void main(String [] args) throws SQLException, ClassNotFoundException{
//        Database db = new Database();
//        db.connect();
//        ResultSet rs = db.executeQuery("select * from t_admin");
//        System.out.println(rs.next());
//    }
}
