package Controles;

import DAOs.DAOProduto;
import Entidades.Produto;
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
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/produto"})
public class ProdutoServlet extends HttpServlet {

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
        String nomeProduto = "";

        try (PrintWriter out = response.getWriter()) {
            nomeProduto = request.getParameter("Produto");

            String resultado = "";
            if (nomeProduto == null || nomeProduto.equals("")) {
                resultado = listaProdutosCadastrados();
            } else {
                resultado = listaProdutoNome(nomeProduto);
            }
            request.getSession().setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/paginas/produto.jsp");
        }
    }

    protected String listaProdutoNome(String nomeProduto) {
        DAOProduto Produto = new DAOProduto();
        String tabela = "";
        List<Produto> lista = Produto.listByNome(nomeProduto);
        for (Produto l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdProduto()+ "</td>"
                    + "<td>" + l.getNomeDoProduto() + "</td>"
                    + "<td>" + l.getImagemDoProduto()+ "</td>"
                    + "<td>" + l.getPrecoProduto()+ "</td>"
                    + "<td>" + l.getDescricaoProduto()+ "</td>"
                    + "<td>" + l.getEmpresa().getNomeEmpresa()+ "</td>"
                    + "<td>" + l.getTipoProduto().getTipoProduto()+ "</td>"
                    + "</tr>";
        }
        
        return tabela;
    }

    protected String listaProdutosCadastrados() {
        DAOProduto Produto = new DAOProduto();
        String tabela = "";
        List<Produto> lista = Produto.listInOrderNome();
        for (Produto l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdProduto()+ "</td>"
                    + "<td>" + l.getNomeDoProduto() + "</td>"
                    + "<td>" + l.getImagemDoProduto()+ "</td>"
                    + "<td>" + l.getPrecoProduto()+ "</td>"
                    + "<td>" + l.getDescricaoProduto()+ "</td>"
                    + "<td>" + l.getEmpresa().getNomeEmpresa()+ "</td>"
                    + "<td>" + l.getTipoProduto().getTipoProduto()+ "</td>"
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