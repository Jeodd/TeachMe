package fr.utbm.TeachMe.controller;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.CourseSessionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilterByLocation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        String selectedLocation = request.getParameter("selectedLocation");
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> responseList = null;
        responseList = css.getCourseSessionByLocation(selectedLocation);
        if (responseList != null){
            request.setAttribute("data", responseList);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        else{
            out.println("<meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8080/TeachMe/\"/><body>Error while retrieving corresponding Session</body>");
            //TODO : Logger
        }
    }
}
