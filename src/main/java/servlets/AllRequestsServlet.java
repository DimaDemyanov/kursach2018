package servlets;

import DB.DataBase;
import generate.GeneratePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AllRequestsServlet extends HttpServlet {

    DataBase dataBase;

    public AllRequestsServlet(DataBase dataBase) {
        this.dataBase = dataBase;
    }





    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
      //  pageVariables.put("message", "");
        //response.setContentType("text/html;charset=windows-1251");
        response.getWriter().println(GeneratePage.instance().getPage("catalog.html", pageVariables));
        response.setContentType("text/html;charset=windows-1251");

        response.setStatus(HttpServletResponse.SC_OK);

    }

   /* public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=windows-1251");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(GeneratePage.instance().getPage("catalog.html", pageVariables));
    }*/

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        StringBuilder html = new StringBuilder();
        // html.append( "<!doctype html>" );
        // html.append( "<html lang='en'>" );

        //html.append( "<head>" );
        //html.append( "<meta charset='utf-8'>" );
        ///html.append( "<title>Report of Reports</title>" );
        //html.append( "</head>" );

        //html.append( "<body>" );
        html.append("<table style=\"margin: auto;\" border=\"1\">");
        html.append( "<tbody>");
        html.append( "<tr>");
        html.append("<th>Наименование товара</th>");
        html.append("<th>Количество</th>");
        html.append("<th>Цена</th>");
        html.append("<th>Подробнее</th>");
        html.append(" </tr>");
        int i = 0;
        String[] all = dataBase.selectAll();
        while(i != all.length ){
            html.append("<tr>");
            String [] all2 = all[i].split(" ");
            html.append("<td style=\"width: 300px;\">"+ all2[0] + "</td>");
            html.append("<td style=\"width: 150px;\">" + all2[1] +"</td>");
            html.append("<td style=\"width: 80px;\">" + all2[2] +"</td>");
            html.append("<td style=\"width: 150px;\">" + all2[3] + "</td>");
            html.append("</tr>");

            i++;
        }
        html.append(" </tbody>");
        html.append("</table>");
        //html.append( "</body>" );

        //html.append( "</html>" );

        //html.toString();
        pageVariables.put("method", html.toString() );

       /* pageVariables.put("method", "<table style=\"margin: auto;\" border=\"1\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <th>Наименование товара</th>\n" +
                "                <th>Количество</th>\n" +
                "                <th>Цена</th>\n" +
                "                <th>Подробнее</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td style=\"width: 300px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 80px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td style=\"width: 300px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 80px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>");*/
      //  pageVariables.put("URL", request.getRequestURL().toString());
     //   pageVariables.put("pathInfo", request.getPathInfo());
       // pageVariables.put("sessionId", request.getSession().getId());
      //  pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
