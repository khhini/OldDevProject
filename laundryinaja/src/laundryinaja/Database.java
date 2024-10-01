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
    void Konfirmasi(String nota,double harga){
        Connection conn = null;
        Statement stmt = null;
        
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            String sql = "update pesanan set total = '"+harga+"', status = 'Baru' where notaPesanan = '"+nota+"'";
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
    String getNota(String tanggal){
        this.hapusNota();
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
    public void hapusNota(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sqlNota = "select * from pesanan where status is null or status = ''";
        String nota ="";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sqlNota);
            if(result.first()){
                nota = result.getString("notaPesanan");
            }
            String sql = "delete from subpesanan where notaPesanan = '"+nota+"'";
            stmt.executeUpdate(sql);
            sql = "delete from pesanan where status is null or status = ''";
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
    String getSubNota(String nota){
            Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String subNota = "";
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "select noSubPesanan from subpesanan where noSubPesanan like '"+nota+"%' order by "
                + "noSubPesanan desc";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host,user,pass);
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            if(result.first()){
                subNota = result.getString("noSubPesanan");
            }else{
                subNota = "";
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
        if(subNota.length()>0){
            if(subNota.substring(0, 12).equals(nota)){
                int tmp = Integer.parseInt(subNota.substring(13))+1;
                if(tmp <  10){
                    subNota = subNota.substring(0,12)+"0"+tmp;
                }else{
                    subNota = subNota.substring(0,12)+tmp;
                }
            }else{
                subNota = subNota.substring(0,12)+"01";
            }
        }else{
            subNota = nota+"01";
        }
        
        return subNota;
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
    void insertPesanan(String notaPesan, String namaPemesan, String tglAntar, String tglSelesai){
        
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "update pesanan set namaPemesan = '"+namaPemesan+"',tanggalAntar ='"+tglAntar+"',"
                + "tanggalSelesai ='"+tglSelesai+"' where  notaPesanan = '"+notaPesan+"' and "
                + "(tanggalSelesai <'"+tglSelesai+"' or tanggalSelesai is null)";
        
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
    void insertPesanan(String notaPesan){
        
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "insert into pesanan (notaPesanan) values('"+notaPesan+"')";
        
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
    void insertSubPesnan(String noSubPesanan, String notaPesanan, 
            double jumlah, String jenis,double harga ,String catatan){
        Connection conn = null;
        Statement stmt = null;
        
        String host = "jdbc:mysql://localhost:3306/projectPBO";
        String user = "root";
        String pass = null;
        String sql = "insert into subpesanan values('"+noSubPesanan+"','"+notaPesanan+"',"
                + "'"+jumlah+"','"+jenis+"','"+harga+"','"+catatan+"')";
        
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
    public static void main(String[] args) {

    }
}
