package forlaba;
import datapackage.Disciplines;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "UpdateSelectDisciplines", urlPatterns = {"/UpdateSelectDisciplines"})
public class UpdateSelectDisciplines extends HttpServlet {
Connection conn = null; 
Disciplines disciplines = null; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");      
    }   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    try {     
        int disid = Integer.parseInt(request.getParameter("disid"));
        Class.forName("java.sql.Driver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/distlearn","root","123");   
        String SQL="SELECT * FROM DISCIPLINES WHERE DISID=?";
        PreparedStatement ps = conn.prepareStatement(SQL);        
        ps.setInt(1, disid);
        ResultSet rs = ps.executeQuery(); 
        if(rs.next()){
        int disId = rs.getInt(1);
        String discipline = rs.getString(2);        
        disciplines = new Disciplines(disId, discipline);
        }
        request.setAttribute("disciplines", disciplines);
        getServletContext().getRequestDispatcher("/updatedisciplines.jsp").forward(request, response);         
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(UpdateSelectLecturers.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(UpdateSelectLecturers.class.getName()).log(Level.SEVERE, null, ex);
    }    
                  }     
           @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
