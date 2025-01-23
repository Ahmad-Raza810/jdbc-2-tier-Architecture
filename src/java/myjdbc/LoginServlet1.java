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

public class LoginServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        String dbURL = "jdbc:mysql://localhost:3306/ahmad";
        String dbUser = "root";
        String dbPassword = "";
                   


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, "");

                      

            String query = "SELECT * FROM users WHERE name = ? AND password = ?";
                       
            PreparedStatement pstmt = conn.prepareStatement(query);
                      

            pstmt.setString(1, username);
                        

            pstmt.setString(2, password);
            

            ResultSet rs = pstmt.executeQuery();

              // Iterate through the ResultSet and print the data
            
                // Get values by column name or index
                while(rs.next())
                {
                  
                  int id=rs.getInt("id");
                  String username2 = rs.getString("name");
                  String pass = rs.getString("password");
                  int age=rs.getInt("age");
                  
                // Print the values
                  out.println("<h1>User Id:"+id+"</h1>");
                  out.println("<h1>Username: " + username2+"</h1>");
                  out.println("<h1>password: " + pass+"</h1>");
                  out.println("<h1>Age:"+age+"</h1>");
                  
   
            
                
                }
               

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Error Connecting to Database</h1>");
        }
    }
}
