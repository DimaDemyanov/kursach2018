package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SessionsServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionsServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get logged user profile
    /*public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("!!!");
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            System.out.println("!!!");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }*/

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

     if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
     }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null || !(profile.getPass() == pass.hashCode())) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

       /* if(profile.getLogin() == "admin"){
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Anastasiia/Desktop/Java/stepic_java_webserver/L2.1 Authorization/public_html/profileAdmin.html");
            Scanner scanner = new Scanner(fileInputStream);
            String s = "";
            while(scanner.hasNext()){
                s += scanner.nextLine();
            }
            response.setContentType("text/html;charset=windows-1251");
            response.getWriter().println(s);
        }
        else{
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Anastasiia/Desktop/Java/stepic_java_webserver/L2.1 Authorization/public_html/catalog.html");
            Scanner scanner = new Scanner(fileInputStream);
            String s = "";
            while(scanner.hasNext()){
                s += scanner.nextLine();
            }
            response.setContentType("text/html;charset=windows-1251");
            response.getWriter().println(s);
        }*/

    }

    //sign out
   /* public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }*/
}
