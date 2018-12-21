package servlets;

import accounts.DataBaseShop;
import accounts.ShopService;
import accounts.UserProfile;

import javax.servlet.ServletException;// добавление таблицы в файл, добавить pageGenerator, куки файлы, как определять пользователя по запросу, добавление товара
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.nio.file.Paths;
import java.util.Scanner;

public class AddServlet extends HttpServlet {

    ShopService shopService;

    public AddServlet(ShopService shopService) {
        this.shopService = shopService;
    }




    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fileInputStream = new FileInputStream("public_html/add.html");
        Scanner scanner = new Scanner(fileInputStream);
        String s = "";
        while(scanner.hasNext()){
            s += scanner.nextLine();
        }
        response.setContentType("text/html;charset=windows-1251");
        response.getWriter().println(s);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Part photoPart = request.getPart("photo");
        String fileName = "photos" + "//" + Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        InputStream inputStream = photoPart.getInputStream();
        int x;
        try {
            while ((x = inputStream.read()) != -1) {
                outputStream.write(x);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String name = request.getParameter("name");
        String count = request.getParameter("count");
        String cost = request.getParameter("cost");

        DataBaseShop dataBaseShop = new DataBaseShop(name, cost, count, fileName);
        try {
            shopService.createNewThing(dataBaseShop);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*UserProfile user = new UserProfile(login, password.hashCode(), selectValue.equals("Admin") && codeWord.equals("1234")); //todo: если выбран админ и ключевое слово не совпадает try again
        try {
            accountService.createNewUser(user);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }*/
    }
}
