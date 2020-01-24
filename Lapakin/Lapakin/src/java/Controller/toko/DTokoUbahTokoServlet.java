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
import model.ModelUser;
import obj.User;

/**
 *
 * @author kiki
 */
@WebServlet(name = "DTokoUbahTokoServlet", urlPatterns = {"/DTokoUbahTokoServlet"})
public class DTokoUbahTokoServlet extends HttpServlet {

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
            control = getServletContext().getRequestDispatcher("/pemilik_toko/ubah_toko.jsp");
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
        RequestDispatcher control;
        User edit = new User();
        edit.setId(request.getParameter("id"));
        edit.setNama(request.getParameter("nama"));
        edit.setEmail(request.getParameter("email"));
        edit.setPemilik(request.getParameter("pemilik"));
        edit.setDeskripsi(request.getParameter("deskripsi"));
        edit.setAlamat(request.getParameter("alamat"));
        edit.setNo_hp(request.getParameter("no_hp"));
        edit.setLevel("toko");
        if (mu.update(edit)) {
            request.setAttribute("status", "edit berhasil");
            request.getSession().setAttribute("login", edit);
        }else{
            request.setAttribute("status", "edit gagal");
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
