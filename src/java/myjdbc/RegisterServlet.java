package myjdbc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String age=request.getParameter("age");

        String dbURL = "jdbc:mysql://localhost:3306/ahmad";
       
        String dbUser = "root";

        try {
            out.println("test 1 passed !");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, "");
    
               
                
                    out.println("test 2 passed !");

            String query = "INSERT INTO users (name, password,age) VALUES (?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3,age);
                    out.println("test 3 passed !");

            
            out.println("test 4 passed !");

            if (pstmt.executeUpdate()>0){
                            out.println("test 5 passed !");

                out.println("<h1>registered successfully Successful!</h1>");
            }
            else
                out.println("<h1>No row affected</h1>");
             out.println("test 6 passed !");

             conn.close();
         } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Error Connecting to Database</h1>");
        }
    }
}
