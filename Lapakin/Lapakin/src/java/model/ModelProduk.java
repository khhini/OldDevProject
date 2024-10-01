/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import config.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obj.Produk;
/**
 *
 * @author kiki
 */
public class ModelProduk {
    private Database db;
    public boolean insert(Produk produk){
        db = new Database();
        String query = "insert into t_produk values("
                + "LEFT(UUID(), 10),"
                + "'" + produk.getId_toko()+ "',"
                + "'" + produk.getNama()+ "',"
                + "'" + produk.getDeskripsi()+ "',"
                + "'" + produk.getStok() + "',"
                + "'" + produk.getHarga() + "',"
                + "'" + produk.getSatuan() + "'"
                + ")";
        try{
            db.connect();
            return db.executeUpdate(query)>0;
            
            
        }catch(SQLException | ClassNotFoundException e){
            return false;
        }finally{
            try{
                db.disconnect();
            }catch(SQLException e){
                
            }
        }
    }
    public boolean update(Produk produk){
        db = new Database();
        String query = "update t_produk set "
                + "nama = '" + produk.getNama() +"',"
                + "deskripsi = '" + produk.getDeskripsi()+"',"
                + "stok = '" + produk.getStok()+"',"
                + "harga = '" + produk.getHarga()+"',"
                + "satuan = '" + produk.getSatuan()+"'"
                + "where id_produk = '" + produk.getId_produk()+"'";
        try{
            db.connect();
            return db.executeUpdate(query)>0;
        }catch(SQLException | ClassNotFoundException e){
            return false;
        }finally{
            try {
                db.disconnect();
            }
            catch (SQLException e){
            }
        }
    }
    public boolean delete(String id_produk){
        db = new Database();
        String query = "delete from t_produk where id_produk = '"+ id_produk+"'";
        try{
            db.connect();
            return db.executeUpdate(query)>0;
        }catch(SQLException | ClassNotFoundException e){
            return false;
        }finally{
            try{
                db.disconnect();
            }catch (SQLException e){
            }
        }
    }
    
    public Produk[] getDaftarProduk(String id_toko){
        Produk[] daftarProduk = null;
        Produk tempProduk = null;
        ArrayList<Produk> listProduk = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_produk where id_toko = '"+id_toko+"'";
        try{
            db.connect();
            rs = db.executeQuery(query);
            while(rs.next()){
                tempProduk = new Produk();
                tempProduk.setId_produk(rs.getString("id_produk"));
                tempProduk.setId_toko(rs.getString("id_toko"));
                tempProduk.setNama(rs.getString("nama"));
                tempProduk.setDeskripsi(rs.getString("deskripsi"));
                tempProduk.setStok(rs.getDouble("Stok"));
                tempProduk.setSatuan(rs.getString("satuan"));
                tempProduk.setHarga(rs.getDouble("harga"));
                listProduk.add(tempProduk);
            }
            daftarProduk = new Produk[listProduk.size()];
            listProduk.toArray(daftarProduk);
            return daftarProduk;
        }catch(SQLException | ClassNotFoundException e){
            return daftarProduk;
        }finally{
            try{
                db.disconnect();
            }catch (SQLException e){
                
            }
        }
        
    }
    public Produk getDataProduk(String id_produk){
        Produk dataProduk = null;
        ResultSet rs;
        db = new Database();
        String query = "select * from t_produk where id_produk = '"+id_produk+"'";
        try{
            db.connect();
            rs = db.executeQuery(query);
            if(rs.next()){
                dataProduk = new Produk();
                dataProduk.setId_produk(rs.getString("id_produk"));
                dataProduk.setId_toko(rs.getString("id_toko"));
                dataProduk.setNama(rs.getString("nama"));
                dataProduk.setDeskripsi(rs.getString("deskripsi"));
                dataProduk.setStok(rs.getDouble("stok"));
                dataProduk.setSatuan(rs.getString("satuan"));
                dataProduk.setHarga(rs.getDouble("harga"));
            }
            return dataProduk;
        }catch(SQLException | ClassNotFoundException e){
            return dataProduk;
        }finally{
            try{
                db.disconnect();
            }catch(SQLException e){
                
            }
            
        }
    }
    public static void main(String[] args) {
        Produk p = new Produk();
        ModelProduk mp = new ModelProduk();
        Produk [] dp = mp.getDaftarProduk("toko001");
        p.setId_produk("2be7eb2c-3");
        p.setId_toko("toko001");
        p.setNama("Produk");
        p.setDeskripsi("deskripsi ini");
        p.setStok(15);
        p.setSatuan("nope");
        p.setHarga((55000));
          
        System.out.println(mp.update(p));
//        for(int i =0; i < dp.length; i++){
//            System.out.println("Id : "+dp[i].getId_produk());
//            System.out.println("Nama : "+dp[i].getNama());
//            System.out.println("Deskripsi : "+dp[i].getDeskripsi());
//            System.out.println("Stock : "+dp[i].getStok());
//            System.out.println("Satuan : "+dp[i].getSatuan());
//            System.out.println("harga : "+dp[i].getHarga());
//            System.out.println("kategori : "+dp[i].getKategori());
//            System.out.println("");
//        }
        
    }
}
