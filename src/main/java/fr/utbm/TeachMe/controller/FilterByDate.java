package fr.utbm.TeachMe.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import  java.util.List;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.CourseSessionService;

public class FilterByDate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out =  response.getWriter();
        java.util.Date formatedDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = request.getParameter("selectedDate");
        try{
            formatedDate = format.parse(selectedDate);
        }catch (ParseException pe){
            pe.printStackTrace();
            //TODO: logger : error while parsing date
        }
        Date sqlDate = new Date(formatedDate.getTime());
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> responseList = null;
        responseList = css.getAllCoursesSessionByDate(sqlDate);
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
