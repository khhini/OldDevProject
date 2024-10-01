/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelTransaksi;
import obj.Produk;
import obj.Transaksi;
import obj.User;

/**
 *
 * @author kiki
 */
@WebServlet(name = "PembayaranServlet", urlPatterns = {"/PembayaranServlet"})
public class PembayaranServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        User login = (User) request.getSession().getAttribute("login");
        if (login != null) {
            List<Produk> lp = (List<Produk>) request.getSession().getAttribute("cart");

            Transaksi tr = new Transaksi();
            tr.setNo_transaksi(UUID.randomUUID().toString().substring(0, 10));
            tr.setId_toko(lp.get(0).getId_toko());
            tr.setId_user(login.getId());
            tr.setItems(lp);
            ModelTransaksi mt = new ModelTransaksi();
            if(mt.insert(tr)){
                request.setAttribute("transaksi", tr);
                control = getServletContext().getRequestDispatcher("/pembayaran.jsp");
                control.forward(request, response);
            }else{
                out.println("<h1>Gagal</h1>");
            }
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
        processRequest(request, response);
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
