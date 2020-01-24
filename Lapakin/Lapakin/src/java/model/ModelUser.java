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
import obj.User;

/**
 *
 * @author kiki
 */
public class ModelUser {

    private Database db;

    public boolean insert(User user) {
        db = new Database();
        String query = "insert into ";
        switch (user.getLevel()) {
            case "member":
                query += "t_member values ("
                        + "LEFT(UUID(), 12),"
                        + "'" + user.getEmail() + "',"
                        + "md5('" + user.getPassword() + "'),"
                        + "'" + user.getNama() + "',"
                        + "'" + user.getAlamat() + "',"
                        + "'" + user.getNo_hp() + "'"
                        + ")";
                break;
            case "toko":
                query += "t_toko values ("
                        + "LEFT(UUID(), 12),"
                        + "'" + user.getEmail() + "',"
                        + "md5('" + user.getPassword() + "'),"
                        + "'" + user.getNama() + "',"
                        + "'" + user.getPemilik()+ "',"
                        + "'" + user.getDeskripsi() + "',"
                        + "'" + user.getAlamat() + "',"
                        + "'" + user.getNo_hp() + "',"
                        + "'x'"
                        + ")";
                break;
            case "admin":
                query += "t_admin values ("
                        + "LEFT(UUID(), 12),"
                        + "'" + user.getEmail() + "',"
                        + "md5('" + user.getPassword() + "'),"
                        + "'" + user.getNama() + "',"
                        + "'" + user.getNo_hp() + "'"
                        + ")";
                break;
        }
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

    public boolean update(User user) {
        db = new Database();
        String query = "update ";
        switch (user.getLevel()) {
            case "member":
                query += "t_member set "
                        + "email = '" + user.getEmail() + "',"
                        + "nama = '" + user.getNama() + "',"
                        + "alamat = '" + user.getAlamat() + "',"
                        + "no_hp = '" + user.getNo_hp() + "'"
                        + "where id ='" + user.getId() + "'";
                break;
            case "toko":
                query += "t_toko set "
                        + "email = '" + user.getEmail() + "',"
                        + "nama = '" + user.getNama() + "',"
                        + "deskripsi = '" + user.getDeskripsi() + "',"
                        + "pemilik = '"+user.getPemilik()+"',"
//                        + "password = md5('"+user.getPassword()+"'),"
                        + "alamat = '" + user.getAlamat() + "',"
                        + "no_hp = '" + user.getNo_hp() + "'"
                        + "where id ='" + user.getId() + "'";
                break;
            case "admin":
                query += "t_admin set "
                        + "email = '" + user.getEmail() + "',"
                        + "nama = '" + user.getNama() + "'"
                        + "where id = '" + user.getId() + "'";
                break;
        }
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

    public User[] getDaftarMember() {
        User[] daftarUser = null;
        User tempUser = null;
        ArrayList<User> listUser = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_member";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempUser = new User();
                tempUser.setId(rs.getString("id"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setNama(rs.getString("nama"));
                tempUser.setAlamat(rs.getString("alamat"));
                tempUser.setNo_hp(rs.getString("no_hp"));
                listUser.add(tempUser);
            }
            daftarUser = new User[listUser.size()];
            listUser.toArray(daftarUser);
            return daftarUser;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User[] getDaftarToko() {
        User[] daftarUser = null;
        User tempUser = null;
        ArrayList<User> listUser = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_toko";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempUser = new User();
                tempUser.setId(rs.getString("id"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setNama(rs.getString("nama"));
                tempUser.setDeskripsi(rs.getString("deskripsi"));
                tempUser.setPemilik(rs.getString("pemilik"));
                tempUser.setAlamat(rs.getString("alamat"));
                tempUser.setNo_hp(rs.getString("no_hp"));
                listUser.add(tempUser);
            }
            daftarUser = new User[listUser.size()];
            listUser.toArray(daftarUser);
            return daftarUser;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User[] getDaftarAdmin() {
        User[] daftarUser = null;
        User tempUser = null;
        ArrayList<User> listUser = new ArrayList();
        ResultSet rs;
        db = new Database();
        String query = "select * from t_admin";
        try {
            db.connect();
            rs = db.executeQuery(query);
            while (rs.next()) {
                tempUser = new User();
                tempUser.setId(rs.getString("id"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setNama(rs.getString("nama"));
                tempUser.setNo_hp(rs.getString("no_hp"));
                listUser.add(tempUser);
            }
            daftarUser = new User[listUser.size()];
            listUser.toArray(daftarUser);
            return daftarUser;
        } catch (SQLException | ClassNotFoundException e) {
            return daftarUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User getDataMember(String id) {
        User dataUser = null;
        ResultSet rs;
        db = new Database();
        String query = "select * from t_member where id ='" + id + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            if (rs.next()) {
                dataUser = new User();
                dataUser.setId(rs.getString("id"));
                dataUser.setEmail(rs.getString("email"));
                dataUser.setNama(rs.getString("nama"));
                dataUser.setAlamat(rs.getString("alamat"));
                dataUser.setNo_hp(rs.getString("no_hp"));
            }
            return dataUser;
        } catch (SQLException | ClassNotFoundException e) {
            return dataUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User getDataToko(String id) {
        User dataUser = null;
        ResultSet rs;
        db = new Database();
        String query = "select * from t_toko where id ='" + id + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            if (rs.next()) {
                dataUser = new User();
                dataUser.setId(rs.getString("id"));
                dataUser.setEmail(rs.getString("email"));
                dataUser.setNama(rs.getString("nama"));
                dataUser.setPemilik(rs.getString("pemilik"));
                dataUser.setDeskripsi(rs.getString("deskripsi"));
                dataUser.setAlamat(rs.getString("alamat"));
                dataUser.setNo_hp(rs.getString("no_hp"));
            }
            return dataUser;
        } catch (SQLException | ClassNotFoundException e) {
            return dataUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User getDataAdmin(String id) {
        User dataUser = null;
        ResultSet rs;
        db = new Database();
        String query = "select * from t_admin where id ='" + id + "'";
        try {
            db.connect();
            rs = db.executeQuery(query);
            if (rs.next()) {
                dataUser = new User();
                dataUser.setId(rs.getString("id"));
                dataUser.setEmail(rs.getString("email"));
                dataUser.setNama(rs.getString("nama"));
                dataUser.setNo_hp(rs.getString("no_hp"));
            }
            return dataUser;
        } catch (SQLException | ClassNotFoundException e) {
            return dataUser;
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {

            }
        }
    }

    public User getLogin(String email, String pass) {
        db = new Database();
        String[] tb = {"t_admin","t_toko","t_member"};
        System.out.println(tb.length);
        ResultSet rs;
        User login = null;
        try{
            db.connect();
            for (int i = 0; i < tb.length; i++) {
                String query = "Select * from "+tb[i]+" where email = '"+email+"' and password = md5('"+pass+"')";
                rs = db.executeQuery(query);
                if(rs.next()){
                    login = new User();
                    switch(tb[i]){
                        case "t_member":
                            login.setAlamat(rs.getString("alamat"));
                            break;
                        case "t_toko":
                            login.setAlamat(rs.getString("alamat"));
                            login.setPemilik(rs.getString("pemilik"));
                            login.setDeskripsi(rs.getString("deskripsi"));
                            login.setKoordinat(rs.getString("koordinat"));
                            break;
                    }
                    login.setId(rs.getString("id"));
                    login.setNama(rs.getString("nama"));
                    login.setEmail(rs.getString("email"));
                    login.setNo_hp(rs.getString("no_hp"));
                    login.setLevel(tb[i].substring(2));
                    break;
                }
            }
            return login;
        }catch(SQLException |ClassNotFoundException e){
            return login;
        }finally{
            try{
                db.disconnect();
            }catch(SQLException e){
                
            }
        }
    }
}
