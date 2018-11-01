package io.github.fernandasj.domain;

import io.github.fernandasj.infra.BandaEmJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernanda
 */
@WebServlet(name = "ControladorDeBandas", urlPatterns = {"/ControladorDeBandas"})
public class ControladorDeBandas extends HttpServlet {
    
    private BandaEmJDBC bandas = new BandaEmJDBC();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDeBandas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem De Bandas </h1>");
            imprimir(out);
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {     
            
        String nome = request.getParameter("nome");
        String localDeOrigem = request.getParameter("localDeOrigem");
        String integrantes = request.getParameter("integrantes");
        
        Banda banda = new Banda(nome, localDeOrigem, integrantes);
        
        this.bandas.salvar(banda);
        
        response.sendRedirect(request.getRequestURI());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>// </editor-fold>// </editor-fold>// </editor-fold>// </editor-fold>// </editor-fold>// </editor-fold>// </editor-fold>
    
    private void imprimir(PrintWriter out) {
        this.bandas.todosOsObjetos().
                forEach(a -> out.println("<h4>" + a.getNome() + "</h4><form action='DeletarBanda' method='POST'><input type='hidden' name='idBanda' value='"+a.getIdBanda()+"'><input type='submit' value='excluir'></form>"));
    }
}
