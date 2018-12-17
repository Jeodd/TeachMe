<%--
    Document   : Page
    Created on : 21 oct. 2018, 21:46:22
    Author     : Rémi Pereira & Jacques Martinelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="fragments/header.html" />
<%--//.size sur la liste de retour de la requete--%>
<body id="page-top" class="sidebar-toggled">

<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand mr-1" href="index.jsp">Maven Application - LO54 Project</a>
    </div>
</nav>

<header class="masthead" style="background-image: url('img/header.jpg');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1 style="color: white;">Courses</h1>
                    <span style="color: white;" class="subheading">Subscribe to a course</span>
                </div>
            </div>
        </div>
    </div>
</header>

<div id="wrapper">
    <div id="content-wrapper">

        <div class="container-fluid">

            <%@ page import="fr.utbm.TeachMe.services.CourseSessionService" %>
            <%@ page import="fr.utbm.TeachMe.services.LocationService" %>
            <%@ page import="fr.utbm.TeachMe.entity.CourseSession" %>
            <%@ page import="fr.utbm.TeachMe.entity.Location" %>
            <%@ page import="java.util.List" %>
            <%@ page import="java.text.SimpleDateFormat"%>
            <%@ page import="fr.utbm.TeachMe.entity.Course" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
            <%
                List<CourseSession> listToDisplay = null;
                List<Location> locationList = null;
                LocationService ls = new LocationService();
                CourseSessionService css = new CourseSessionService();
                Integer t = 1010;

                pageContext.setAttribute("t",t);
                locationList = ls.getAllLocation();

                if (request.getAttribute("data") != null){
                    listToDisplay = (List<CourseSession>)request.getAttribute("data");
                }else{
                    listToDisplay = css.getAllCoursesSession();
                }
            %>
            <div class="row">
                <div class="col-md-4">
                    <form action = "filterByDate">
                        <div class="form-row align-items-center">
                            <div class="col-md-6 my-4">
                                <input id="dateInput" type=date class="form-control mb-4" placeholder="Enter the session date" aria-label="dateInput" name="selectedDate">
                            </div>
                            <div class="col-md-4 my-2">
                                <button type="submit" class="btn btn-primary mb-4">Search by date</button>
                            </div>
                        </div>
                    </form>

                    <form action = "filterByLocation">
                        <div class="form-row align-items-center">
                            <div class="col-md-6 my-4">
                                <select class="form-control mb-4" name="selectedLocation"
                                <%
                                    for(int i = 0; i < locationList.size(); i++){
                                        %>
                                        </br>
                                        <option name="selectedLocation" value = "<%=locationList.get(i).getCity()%>"><%=locationList.get(i).getCity()%></option>
                                        <%
                                    }
                                %>
                                </select>
                            </div>
                            <div class="col-md-4 my-2">
                                <button type="submit" class="btn btn-primary mb-4">Search by location</button>
                            </div>
                        </div>
                    </form>

                    <form action = "filterByTitle">
                        <div class="form-row align-items-center">
                            <div class="col-md-6 my-4">
                                <input id="titleInput" name="titleString" type="text" class="form-control mb-4" placeholder="Enter the session title" aria-label="titleInput">
                            </div>
                            <div class="col-md-4 my-2">
                                <button type="submit" class="btn btn-primary mb-4">Search by title</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-md-8 table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Number</th>
                                <th scope="col">Location</th>
                                <th scope="col">Formation</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                                <th scope="col">Maximum</th>
                                <th scope="col">Remaining</th>
                                <th scope="col">Apply</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            for(CourseSession item : listToDisplay) {
                                Float maxPlace = item.getMax().floatValue();
                                Float placeAvailable = css.getPlaceAvailable(item).floatValue();
                                String progressBarClass = "";
                                String button = "";
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                String startDate = format.format(item.getStartDate());
                                String endDate = format.format(item.getEndDate());
                                float percentageFull = placeAvailable / maxPlace *100;
                                float progressBarPercentage = 100 - percentageFull;
                                if(percentageFull >= 50){
                                    progressBarClass = "class=\"progress-bar bg-success\"";
                                }else if (percentageFull < 50 && percentageFull > 20 ){
                                    progressBarClass = "class=\"progress-bar bg-warning\"";
                                }else{
                                    progressBarClass =  "class=\"progress-bar bg-danger\"";
                                }
                                if (placeAvailable == 0){
                                    button = "<button style=\"margin-left: 15%;\" type=\"submit\" disabled class=\"btn btn-danger\"> Full </button>";
                                }else{
                                    button ="<button style=\"margin-left: 15%;\" type=\"submit\" class=\"btn btn-success\">Apply</button>";
                                }

                                %>
                                    <tr>
                                        <td><%=item.getId()%></td>
                                        <td><%=item.getCourse().getTitle()%></td>
                                        <td><%=item.getLocation().getCity()%></td>
                                        <td><%=startDate%></td>
                                        <td><%=endDate%></td>
                                        <td><%=maxPlace.toString()%></td>
                                        <td>
                                            <div class="progress">
                                                <div <%=progressBarClass%> role="progressbar" style="width: <%=progressBarPercentage%>%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"><%=placeAvailable.toString()%></div>
                                            </div>
                                        </td>
                                        <td>
                                            <form action = "applyServlet">
                                                <input style="display: none" name="CourseSessionId" value="<%=item.getId()%>">
                                                <%=button%>
                                            </form>
                                        </td>
                                    </tr>
                                <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div>
        <jsp:include page="fragments/footer.html" />
    </div>
</body>
</html>
