package fr.utbm.TeachMe.controller;
import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.CourseSessionService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ApplyServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CourseSessionService css = new CourseSessionService();
        String id = request.getParameter("CourseSessionId");
        PrintWriter out = response.getWriter();
        CourseSession cs = css.getCoursesSessionById(id);
        String recap = "<h1>You are applying to "+ cs.getCourse().getTitle()+" at "+cs.getLocation().getCity()+" from the " + cs.getStartDate().toString() + " to the "+cs.getEndDate().toString()+"</h1> </br>";
        String htmlForm = "<form action=\"addClient\" method=\"post\">" +
                "    Last name : <input type=\"text\" name=\"lastname\" maxlength=\"45\"></br>" +
                "    Frist name : <input type=\"text\" name=\"firstname\" maxlength=\"45\"></br>" +
                "    Address : <input type=\"text\" name=\"address\" maxlength=\"45\"></br>" +
                "    Phone : <input type=\"text\" name=\"phone\" maxlength=\"45\"></br>" +
                "    Email : <input type=\"text\" name=\"email\" maxlength=\"45\"></br>" +
                "    <input style=\"display: none\" name=\"CourseSessionId\" value=\""+id+"\">" +
                "    <input type=\"submit\" value=\"Apply\">" +
                "</form>";
        out.println(recap + htmlForm);
    }
}
