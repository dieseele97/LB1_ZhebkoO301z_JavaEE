package forlaba;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteDiscipline", urlPatterns = {"/DeleteDiscipline"})
public class DeleteDiscipline extends HttpServlet {
Connection conn = null; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
        int disid = Integer.parseInt(request.getParameter("disid"));
        Class.forName("java.sql.Driver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/distlearn","root","123");   
        String SQL="DELETE FROM DISCIPLINES WHERE DISID=?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, disid);
        ps.executeUpdate();
        response.sendRedirect(request.getContextPath() + "/ShowDisciplines");        
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DeleteLecturers.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DeleteLecturers.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
