package fr.utbm.TeachMe.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.ClientService;
import fr.utbm.TeachMe.entity.Client;
import fr.utbm.TeachMe.services.CourseSessionService;


public class AddClient extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CourseSessionService css = new CourseSessionService();
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        CourseSession cs = css.getCoursesSessionById(request.getParameter("CourseSessionId"));
        Client newClient = new Client(lastname,firstname,address,phone,email,cs);
        PrintWriter out = response.getWriter();
        ClientService cliServ = new ClientService();
        cliServ.saveClient(newClient);
        out.println("<html><meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8080/TeachMe/\"/><body><h1>You just add : </h1></br>" +
                "<div>"+newClient.toString()+"</div> </br><p>You'll be redirected to homepage in 3sec</p></body></html>");
    }
}
