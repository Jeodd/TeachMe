package fr.utbm.TeachMe.controller;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.CourseSessionService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilterByTitle extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FilterByLocation.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        String titleString = request.getParameter("titleString");
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> responseList = null;
        responseList = css.getCourseSessionByTitle(titleString);
        if (responseList != null){
            request.setAttribute("data", responseList);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        else{
            out.println("<meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8080/TeachMe/\"/><body>Error while retrieving corresponding Session</body>");
            logger.fatal("Error while retrieving corresponding Session");
        }
    }
}
