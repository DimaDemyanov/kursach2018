package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fileInputStream = new FileInputStream("C:/Users/Anastasiia/Desktop/Java/stepic_java_webserver/L2.1 Authorization/public_html/auth.html");
        Scanner scanner = new Scanner(fileInputStream);
        String s = "";
        while(scanner.hasNext()){
            s += scanner.nextLine();
        }
        response.setContentType("text/html;charset=windows-1251");
        response.getWriter().println(s);
    }
}
