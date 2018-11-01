package io.github.fernandasj.domain;

import io.github.fernandasj.infra.AlbumEmJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernanda
 */
@WebServlet(name = "ControladorDeAlbuns", urlPatterns = {"/ControladorDeAlbuns"})
public class ControladorDeAlbuns extends HttpServlet {
    
    private AlbumDAO albuns = new AlbumEmJDBC();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDeAlbuns</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem De Albuns </h1>");
            imprimir(out);
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Estilo estilo = Estilo.valueOf(request.getParameter("estilo"));
        int banda = Integer.parseInt(request.getParameter("banda"));
        LocalDate anoDeLancamento = LocalDate.parse(request.getParameter("anoDeLancamento"));
        
        Album album = new Album(estilo, banda, anoDeLancamento);
        
        this.albuns.salvar(album);
        
        response.sendRedirect(request.getRequestURI());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void imprimir(PrintWriter out) {
        this.albuns.todosOsAlbuns().
                forEach(a -> out.println("<p>" + a.getEstilo().name()+ "</p>"));
    }

}
