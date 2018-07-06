/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOTipoproduto;
import Entidades.Tipoproduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaque
 */
@WebServlet(name = "TipoprodutoServlet", urlPatterns = {"/tipoproduto"})
public class TipoprodutoServlet extends HttpServlet {

    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);

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
        String nomeTipoproduto = "";

        try (PrintWriter out = response.getWriter()) {
            nomeTipoproduto = request.getParameter("Tipoproduto");

            String resultado = "";
            if (nomeTipoproduto == null || nomeTipoproduto.equals("")) {
                resultado = listaTipoprodutosCadastrados();
            } else {
                resultado = listaTipoprodutoNome(nomeTipoproduto);
            }
            request.getSession().setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/paginas/tipoproduto.jsp");
        }
    }

    protected String listaTipoprodutoNome(String nomeTipoproduto) {
        DAOTipoproduto Tipoproduto = new DAOTipoproduto();
        String tabela = "";
        List<Tipoproduto> lista = Tipoproduto.listByNome(nomeTipoproduto);
        for (Tipoproduto l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdTipoProduto()+ "</td>"
                    + "<td>" + l.getTipoProduto() + "</td>"
                    + "</tr>";
        }
        
        return tabela;
    }

    protected String listaTipoprodutosCadastrados() {
        DAOTipoproduto Tipoproduto = new DAOTipoproduto();
        String tabela = "";
        List<Tipoproduto> lista = Tipoproduto.listInOrderNome();
        for (Tipoproduto l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdTipoProduto()+ "</td>"
                    + "<td>" + l.getTipoProduto() + "</td>"
                    + "</tr>";
        }

        return tabela;
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
        System.out.println("teste doget");
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
        System.out.println("teste dopost");
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
