/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundryinaja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kiki
 */
public class Database {
    String getNota(String tanggal){
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String nota = "";
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "select notaPesanan from pesanan where notaPesanan like 'PS-%' order by "
                + "notaPesanan desc";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            if(result.first()){
                nota = result.getString("notaPesanan");
            }else{
                nota = "";
            }
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
                result.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        tanggal = tanggal.replace("-", "");
        if(nota.length()>0){
            if(nota.substring(3,9).equals(tanggal)){
                int tmp = Integer.parseInt(nota.substring(10,12))+1;
                if(tmp < 10){
                    nota = nota.substring(0,10)+"0"+tmp;
                }
            }else{
                nota = nota.substring(0,3)+tanggal+"-"+"01";
            }
        }else{
            nota = "PS-"+tanggal+"-01";
        }
        //*/
        return nota;
    }
    String getNotaPengeluaran(String tanggal){
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String nota = "";
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "select notaPesanan from pesanan where notaPesanan like 'PK-%' order "
                + "by notaPesanan desc";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            if(result.first()){
                nota = result.getString("notaPesanan");
            }else{
                nota = "";
            }
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
                result.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        tanggal = tanggal.replace("-", "");
        if(nota.length()>0){
            if(nota.substring(3,9).equals(tanggal)){
                int tmp = Integer.parseInt(nota.substring(10,12))+1;
                if(tmp < 10){
                    nota = nota.substring(0,10)+"0"+tmp;
                }
            }else{
                nota = nota.substring(0,3)+tanggal+"-"+"01";
            }
        }else{
            nota = "PK-"+tanggal+"-01";
        }
        //*/
        return nota;
    }
    void insertPesanan(String notaPesan, String namaPemesan, double jumlah, String tipePesanan, 
            double harga, String tglAntar, String tglSelesai, String catatan){
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "insert into pesanan values('"+notaPesan+"','"+namaPemesan+"','"+jumlah+"','"
                +tipePesanan+"','"+harga+"','"+tglAntar+"','"+tglSelesai+"','"+catatan+"','Waiting')";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    void updateStatus(String nota,String status){
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "update pesanan set status = '"+status+"' where notaPesanan = '"+nota+"'";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    void insertLaporan(String nota,String tanggal,String deskripsi, double biaya){
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "";
        if(nota.substring(0,2).equals("PK")){
            sql = "insert into laporan values('"+nota+"','"+tanggal+"','"+deskripsi+"','"
                +biaya+"',null)";
        }else{
            sql = "insert into laporan values('"+nota+"','"+tanggal+"','"+deskripsi+"',null,'"
                +biaya+"')";
        }
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
