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
<body id="page-top">

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="index.jsp">LO54 Project</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

</nav>

<div id="wrapper">

    <jsp:include page="fragments/menu.html" />
    <div id="content-wrapper">

        <div class="container-fluid">

            <h2>Formation Catalogue :</h2>
            <%@ page import="fr.utbm.TeachMe.services.CourseSessionService" %>
            <%@ page import="fr.utbm.TeachMe.services.LocationService" %>
            <%@ page import="fr.utbm.TeachMe.entity.CourseSession" %>
            <%@ page import="fr.utbm.TeachMe.entity.Location" %>
            <%@ page import="java.util.List" %>
            <%
                List<CourseSession> listToDisplay = null;
                List<Location> locationList = null;
                LocationService ls = new LocationService();
                CourseSessionService css = new CourseSessionService();

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
                                        </br><%-- //TODO : without a tag (like br or p or whatever) before  option tag a city is missing (first one)--%>
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
                                <th scope="col">Apply</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            for(CourseSession item : listToDisplay) {
                                %>
                                    <tr>
                                        <td><%=item.getId()%></td>
                                        <td><%=item.getCourse().getTitle()%></td>
                                        <td><%=item.getLocation().getCity()%></td>
                                        <td><%=item.getStartDate().toString()%></td>
                                        <td><%=item.getEndDate().toString()%></td>
                                        <td><%=item.getMax().toString()%></td>
                                        <td>
                                            <form action = "applyServlet">
                                                <input style="display: none" name="CourseSessionId" value="<%=item.getId()%>">
                                                <button type="submit" class="btn btn-dark">Apply</button>
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

    <jsp:include page="fragments/js_include.html" />

</body>
</html>
