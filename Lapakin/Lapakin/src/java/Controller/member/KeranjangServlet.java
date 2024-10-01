/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ModelProduk;
import obj.Produk;

/**
 *
 * @author kiki
 */
@WebServlet(name = "KeranjangServlet", urlPatterns = {"/KeranjangServlet"})
public class KeranjangServlet extends HttpServlet {

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
            control = getServletContext().getRequestDispatcher("/keranjang.jsp");
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
        String action = request.getParameter("action");
        RequestDispatcher control;
        if (request.getSession().getAttribute("login") != null) {
            if (action == null) {
                processRequest(request, response);
            } else {
                if (action.equals("beli")) {
                    doGet_Beli(request, response);
                } else if (action.equals("hapus")) {
                    doGet_Hapus(request, response);
                } else if (action.equals("kurang")) {
                    doGet_Kurang(request, response);
                }
            }
        } else {
            control = getServletContext().getRequestDispatcher("/LoginServlet");
            control.forward(request, response);
        }
    }

    protected void doGet_Kurang(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelProduk mp = new ModelProduk();
        HttpSession session = request.getSession();

        List<Produk> cart = (List<Produk>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        if (index != -1 && cart.get(index).getJumlah() != 1) {
            double jumlah = cart.get(index).getJumlah() - 1;
            cart.get(index).setJumlah(jumlah);
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("KeranjangServlet");
    }

    protected void doGet_Hapus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Produk> cart = (List<Produk>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        response.sendRedirect("KeranjangServlet");
    }

    protected void doGet_Beli(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelProduk mp = new ModelProduk();
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            List<Produk> cart = new ArrayList<Produk>();
            Produk addChart = mp.getDataProduk(request.getParameter("id"));
            addChart.setJumlah(1);
            cart.add(addChart);
            session.setAttribute("cart", cart);
        } else {
            List<Produk> cart = (List<Produk>) session.getAttribute("cart");
            if (cart.size() == 0) {
                Produk addChart = mp.getDataProduk(request.getParameter("id"));
                addChart.setJumlah(1);
                cart.add(addChart);
//                session.setAttribute("cart", cart);
            } else {
                int index = isExisting(request.getParameter("id"), cart);
                if (index == -1) {
                    Produk addChart = mp.getDataProduk(request.getParameter("id"));
                    if (isSatuToko(addChart.getId_toko(), cart)) {
                        addChart.setJumlah(1);
                        cart.add(addChart);
                    } else {
                        RequestDispatcher control;
                        control = getServletContext().getRequestDispatcher("/pesan.jsp");
                        control.forward(request, response);
                    }
                } else {
                    double jumlah = cart.get(index).getJumlah() + 1;
                    cart.get(index).setJumlah(jumlah);
                }
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("KeranjangServlet");
    }

    private int isExisting(String id, List<Produk> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId_produk().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isSatuToko(String id, List<Produk> cart) {
//        for (int i = 0; i < cart.size(); i++) {
        if (cart.get(0).getId_toko().equals(id)) {
            return true;
        }
//        }
        return false;
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
