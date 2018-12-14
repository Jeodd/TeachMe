package fr.utbm.TeachMe.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.services.ClientService;
import fr.utbm.TeachMe.entity.Client;
import fr.utbm.TeachMe.services.CourseSessionService;


public class AddClient extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        CourseSessionService css = new CourseSessionService();
        PrintWriter out = response.getWriter();
        ClientService cliServ = new ClientService();
        Boolean clientExist = false;
        String redirectString = "<html><head><meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8080/TeachMe/\"/></head>";

        //Retrieving parameters
        String lastname = request.getParameter("lastname").toUpperCase();
        String firstname = request.getParameter("firstname").toLowerCase();
        String address = request.getParameter("address").toLowerCase();
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").toLowerCase();
        CourseSession session = css.getCoursesSessionById(request.getParameter("CourseSessionId"));

        //Create new client
        Client newClient = new Client(lastname,firstname,address,phone,email,session);

        //Check if client already exist
        List<Client> existingClient = cliServ.getClientBySessionId(session.getId());
        for (Client c : existingClient) {
                clientExist = newClient.equals(c);
            if (clientExist){
                break;
            }
        }
        if(clientExist){
            out.println(redirectString+"<body><div>Client already existing, redirecting to home page ...</div></body></html>");

        }
        else if(css.getPlaceAvailable(session) > 0){
            //Add Client to db
            cliServ.saveClient(newClient);
            out.println(redirectString+"<body><h1>You just add : </h1></br>" +
                    "<div>"+newClient.toString()+"</div> </br><p>You'll be redirected to homepage in 3sec</p></body></html>");
        }
        else{
            out.println(redirectString + "<body><p>Sorry, no more place available</p></body></html>");
        }
    }
}
