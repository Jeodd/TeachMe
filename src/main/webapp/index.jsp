<%--
    Document   : Page
    Created on : 21 oct. 2018, 21:46:22
    Author     : Rémi Pereira & Jacques Martinelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha256-3edrmyuQ0w65f8gfBsqowzjJe2iM6n0nKciPUp8y+7E="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</head>
<jsp:include page="fragments/header.html" />
<%--//.size sur la liste de retour de la requete--%>
<body id="page-top" class="sidebar-toggled">

<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container" style="margin-left: 0;">
        <a class="navbar-brand mr-1" href="index.jsp">Maven Web Application - LO54 Project</a>
    </div>
</nav>

<header class="masthead" style="background-image: url('img/header.jpg');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1 style="color: white;">TeachMe</h1>
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
                <form action = "filterByDate" class="col-md-3">
                    <div class="form-row align-items-center">
                        <div class="col-md-6 my-4">
                            <input required id="dateInput" type=date class="form-control mb-4" min='2019-01-01' placeholder="Enter the session date" aria-label="dateInput" name="selectedDate">
                        </div>
                        <div class="col-md-4 my-2">
                            <button type="submit" class="btn btn-primary mb-4">Search by date</button>
                        </div>
                    </div>
                </form>

                <form action = "filterByLocation" class="col-md-3">
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

                <form action = "filterByTitle" class="col-md-3">
                    <div class="form-row align-items-center">
                        <div class="col-md-6 my-4">
                            <input required id="titleInput" name="titleString" type="text" class="form-control mb-4" placeholder="Enter the session title" aria-label="titleInput">
                        </div>
                        <div class="col-md-4 my-2">
                            <button type="submit" class="btn btn-primary mb-4">Search by title</button>
                        </div>
                    </div>
                </form>
                <div class="form-row align-items-center ml-auto">
                    <form action="index.jsp" class="col-md-3">
                        <button type="submit" class="btn btn-primary mb-4" style="width: auto;" ><i class="fas fa-sync-alt"></i></button>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 table-responsive">
                <table id="courseSessionTable"class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Number</th>
                        <th scope="col">Formation</th>
                        <th scope="col">Location</th>
                        <th scope="col">Start Date</th>
                        <th scope="col">End Date</th>
                        <th scope="col">Maximum</th>
                        <th scope="col">Remaining</th>
                        <th scope="col" style="width: 235px;">Apply</th>
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
</div>
<div>
    <jsp:include page="fragments/footer.html" />
</div>
</body>
<script type="text/javascript">
    $('#courseSessionTable').DataTable({
        "scrollY": "450px",
        "scrollCollapse": true,
        "searching": false,
        "paging":   false,
        "info":     false
    });
</script>
</html>
