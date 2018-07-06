package Controles;

import DAOs.DAOEmpresa;
import Entidades.Empresa;
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
@WebServlet(name = "EmpresaServlet", urlPatterns = {"/empresa"})
public class EmpresaServlet extends HttpServlet {

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
        String nomeEmpresa = "";

        try (PrintWriter out = response.getWriter()) {
            nomeEmpresa = request.getParameter("Empresa");

            String resultado = "";
            if (nomeEmpresa == null || nomeEmpresa.equals("")) {
                resultado = listaEmpresasCadastrados();
            } else {
                resultado = listaEmpresaNome(nomeEmpresa);
            }
            request.getSession().setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/paginas/empresa.jsp");
        }
    }

    protected String listaEmpresaNome(String nomeEmpresa) {
        DAOEmpresa Empresa = new DAOEmpresa();
        String tabela = "";
        List<Empresa> lista = Empresa.listByNome(nomeEmpresa);
        for (Empresa l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdEmpresa()+ "</td>"
                    + "<td>" + l.getNomeEmpresa() + "</td>"
                    + "<td>" + l.getLogoEmpresa()+ "</td>"
                    + "<td>" + l.getRamoEmpresa()+ "</td>"
                    + "<td>" + l.getEnderecoEmpresa()+ "</td>"
                    + "<td>" + l.getDataCadastroEmpresa()+ "</td>"
                    + "</tr>";
        }
        
        return tabela;
    }

    protected String listaEmpresasCadastrados() {
        DAOEmpresa Empresa = new DAOEmpresa();
        String tabela = "";
        List<Empresa> lista = Empresa.listInOrderNome();
        for (Empresa l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdEmpresa()+ "</td>"
                    + "<td>" + l.getNomeEmpresa() + "</td>"
                    + "<td>" + l.getLogoEmpresa()+ "</td>"
                    + "<td>" + l.getRamoEmpresa()+ "</td>"
                    + "<td>" + l.getEnderecoEmpresa()+ "</td>"
                    + "<td>" + l.getDataCadastroEmpresa()+ "</td>"
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