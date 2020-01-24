/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.toko;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelProduk;
import obj.Produk;

/**
 *
 * @author kiki
 */
@WebServlet(name = "DTokoTambahProdukServlet", urlPatterns = {"/DTokoTambahProdukServlet"})
public class DTokoTambahProdukServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher control;
        if (request.getSession().getAttribute("login")!= null) {
        control = getServletContext().getRequestDispatcher("/pemilik_toko/tambah_barang.jsp");
        control.forward(request, response);
        } else {
            control = getServletContext().getRequestDispatcher("/LoginServlet");
            control.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Produk newP = new Produk();
        ModelProduk mp = new ModelProduk();
        newP.setId_toko(request.getParameter("id_toko"));
        newP.setNama(request.getParameter("nama"));
        newP.setDeskripsi(request.getParameter("deskripsi"));
        newP.setStok(Double.parseDouble(request.getParameter("stok")));
        newP.setSatuan(request.getParameter("satuan"));
        newP.setHarga(Double.parseDouble(request.getParameter("harga")));
        RequestDispatcher control;
        if (mp.insert(newP)) {
            request.setAttribute("status", "tambah berhasil");
        }else{
            request.setAttribute("status", "tambah gagal");
        }
        control = getServletContext().getRequestDispatcher("/pemilik_toko/status.jsp");
        control.forward(request, response);
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
