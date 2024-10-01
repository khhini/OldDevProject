/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelUser;
import obj.User;

/**
 *
 * @author kiki
 */
@WebServlet(name = "DAdminTambahTokoServlet", urlPatterns = {"/DAdminTambahTokoServlet"})
public class DAdminTambahTokoServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("login") != null) {
            control = getServletContext().getRequestDispatcher("/admin/tambah_toko.jsp");
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
        ModelUser mu = new ModelUser();
        User newUser = new User();
        newUser.setNama(request.getParameter("nama"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPemilik(request.getParameter("pemilik"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setAlamat(request.getParameter("alamat"));
        newUser.setDeskripsi(request.getParameter("deskripsi"));
        newUser.setNo_hp(request.getParameter("no_hp"));
        newUser.setLevel("toko");
        if (mu.insert(newUser)) {
            request.setAttribute("status", "berhasil");
        } else {
            request.setAttribute("status", "gagal");
        }
        RequestDispatcher control;
        control = getServletContext().getRequestDispatcher("/admin/after_tambah_toko.jsp");
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
