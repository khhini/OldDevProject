/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import obj.Transaksi;
import obj.Produk;
import model.ModelProduk;

/**
 *
 * @author kiki
 */
public class ModelTransaksi {

    Database db;

    public boolean insert(Transaksi tr) {
        db = new Database();
        String query = "insert into t_transaksi values("
                + "'" + tr.getNo_transaksi() + "',"
                + "'" + tr.getId_toko() + "',"
                + "'" + tr.getId_user() + "',"
                + "CURRENT_DATE,"
                + "'baru'"
                + ")";
        try {
            db.connect();
            return db.executeUpdate(query) > 0 && insertDetTransaksi(tr);
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }
    public boolean update(String no_tr) {
        db = new Database();
        String query = "update t_transaksi set status = 'selesai' where no_transaksi ='"+no_tr+"'";
        try {
            db.connect();
            return db.executeUpdate(query) > 0;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public boolean insertDetTransaksi(Transaksi tr) {
        boolean test = false;
        for (int i = 0; i < tr.getItems().size(); i++) {
            db = new Database();
            String query = "insert into t_det_transaksi values ("
                    + "'" + tr.getNo_transaksi() + "',"
                    + "'" + tr.getItems().get(i).getId_produk() + "',"
                    + "'" + tr.getItems().get(i).getJumlah() + "'"
                    + ")";
            try {
                db.connect();
                test = db.executeUpdate(query) > 0;
            } catch (SQLException | ClassNotFoundException e) {
                return false;
            } finally {
                try {
                    db.disconnect();
                } catch (SQLException e) {

                }
            }
        }
        return test;
    }

    public Transaksi[] getDaftarTransaksi(String id_toko) {
        Transaksi[] daftarTransaksi = null;
        Transaksi tempTransaksi = null;
        ArrayList<Transaksi> listTransaksi = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_transaksi where id_toko = '" + id_toko + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempTransaksi = new Transaksi();
                tempTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
                tempTransaksi.setId_toko(rs.getString("id_toko"));
                tempTransaksi.setId_user(rs.getString("id_member"));
                tempTransaksi.setTgl_transaksi(rs.getString("tgl_transaksi"));
                tempTransaksi.setItems(getListItems(rs.getString("no_transaksi")));
                tempTransaksi.setStatus(rs.getString("status"));
                listTransaksi.add(tempTransaksi);
            }
            daftarTransaksi = new Transaksi[listTransaksi.size()];
            listTransaksi.toArray(daftarTransaksi);
            return daftarTransaksi;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarTransaksi;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }
    public Transaksi[] getTransaksiBaru(String id_toko) {
        Transaksi[] daftarTransaksi = null;
        Transaksi tempTransaksi = null;
        ArrayList<Transaksi> listTransaksi = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_transaksi where id_toko = '" + id_toko + "' and status = 'baru'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempTransaksi = new Transaksi();
                tempTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
                tempTransaksi.setId_toko(rs.getString("id_toko"));
                tempTransaksi.setId_user(rs.getString("id_member"));
                tempTransaksi.setTgl_transaksi(rs.getString("tgl_transaksi"));
                tempTransaksi.setItems(getListItems(rs.getString("no_transaksi")));
                tempTransaksi.setStatus(rs.getString("status"));
                listTransaksi.add(tempTransaksi);
            }
            daftarTransaksi = new Transaksi[listTransaksi.size()];
            listTransaksi.toArray(daftarTransaksi);
            return daftarTransaksi;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarTransaksi;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }
    public Transaksi[] getTransaksiUser(String id_user) {
        Transaksi[] daftarTransaksi = null;
        Transaksi tempTransaksi = null;
        ArrayList<Transaksi> listTransaksi = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_transaksi where id_member = '" + id_user + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempTransaksi = new Transaksi();
                tempTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
                tempTransaksi.setId_toko(rs.getString("id_toko"));
                tempTransaksi.setId_user(rs.getString("id_member"));
                tempTransaksi.setTgl_transaksi(rs.getString("tgl_transaksi"));
                tempTransaksi.setItems(getListItems(rs.getString("no_transaksi")));
                tempTransaksi.setStatus(rs.getString("status"));
                listTransaksi.add(tempTransaksi);
            }
            daftarTransaksi = new Transaksi[listTransaksi.size()];
            listTransaksi.toArray(daftarTransaksi);
            return daftarTransaksi;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarTransaksi;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public Transaksi getDataTransaksi(String no_transaksi) {
        Transaksi dataTransaksi = null;
        ResultSet rs;
        db = new Database();
        String query = "Select * from t_transaksi where no_transaksi = '" + no_transaksi + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            if (rs.next()) {
                dataTransaksi = new Transaksi();
                dataTransaksi.setNo_transaksi(rs.getString("no_transaksi"));
                dataTransaksi.setId_toko(rs.getString("id_toko"));
                dataTransaksi.setTgl_transaksi(rs.getString("tgl_transaksi"));
                dataTransaksi.setId_user(rs.getString("id_member"));
                dataTransaksi.setStatus(rs.getString("status"));
                dataTransaksi.setItems(getListItems(no_transaksi));
                
            }
            return dataTransaksi;
        } catch (SQLException | ClassNotFoundException e) {
            return dataTransaksi;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public Produk[] getDaftarItems(String no_transaksi) {
        Produk[] daftarItems = null;
        Produk tempItems = null;
        ArrayList<Produk> listItems = new ArrayList();
        ResultSet rs;
        ModelProduk mp = new ModelProduk();
        db = new Database();
        String query = "select * from t_det_transaksi where no_transaksi = '" + no_transaksi + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempItems = mp.getDataProduk(rs.getString("id_produk"));
                tempItems.setJumlah(rs.getDouble("jumlah"));
                listItems.add(tempItems);
            }
            daftarItems = new Produk[listItems.size()];
            listItems.toArray(daftarItems);
            return daftarItems;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarItems;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public List<Produk> getListItems(String no_transaksi) {
        Produk tempItems = null;
        List<Produk> listItems = new ArrayList();
        ResultSet rs;
        ModelProduk mp = new ModelProduk();
        db = new Database();
        String query = "select * from t_det_transaksi where no_transaksi = '" + no_transaksi + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempItems = mp.getDataProduk(rs.getString("id_produk"));
                tempItems.setJumlah(rs.getDouble("jumlah"));
                listItems.add(tempItems);
            }

            return listItems;
        } catch (SQLException | ClassNotFoundException e) {
            return listItems;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

//    public static void main(String[] args) {
//        List<Produk> p = new ArrayList<Produk>();
//        ModelProduk mp = new ModelProduk();
//        p.add(mp.getDataProduk("p001"));
//        p.add(mp.getDataProduk("p0011"));
//
//        Transaksi tr = new Transaksi();
//
//        tr.setNo_transaksi("asdfads002");
//        tr.setId_toko("T001");
//        tr.setId_user("M001");
//        tr.setItems(p);
////        for (int i = 0; i < p.size(); i++) {
////            System.out.println(p.get(i).getId_produk());
////        }
//        ModelTransaksi mtr = new ModelTransaksi();
////        System.out.println(mtr.insert(tr));
////        Transaksi [] dtr = mtr.getDaftarTransaksi("toko001");
//        Transaksi ctr = mtr.getDataTransaksi("6276edf1-e");
//        List<Produk> lp = mtr.getDataTransaksi("6276edf1-e").getItems();
//        System.out.println(ctr.getStatus());
////        p = mtr.getDaftarItems("nota001");
////        for (int i = 0; i < dtr.length; i++) {
////            System.out.println("No Tr : "+dtr[i].getNo_transaksi());
////            System.out.println("Id Toko : "+dtr[i].getId_toko());
////            System.out.println("Id User : "+dtr[i].getId_user());
////            System.out.println("Id tgl : "+dtr[i].getTgl_transaksi());
////            System.out.println("Items : ");
////            for (int j = 0; j < tr.getItems().length; j++) {
////                System.out.println("id : "+tr.getItems()[j].getId_produk());
////                System.out.println("jumlah : "+tr.getItems()[j].getJumlah());
////            }
////        }
//    }
}
