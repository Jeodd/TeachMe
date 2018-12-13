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
            <form action = "filterByDate">
                <input type = date name="selectedDate">
                <input type="submit" value="Search by date">
            </form>
            <form action = "filterByLocation">
            <select name="selectedLocation"
            <%
                for(int i = 0; i < locationList.size(); i++){
                    %>
                    </br><%-- //TODO : without a tag (like br or p or whatever) before  option tag a city is missing (first one)--%>
                    <option name="selectedLocation" value = "<%=locationList.get(i).getCity()%>"><%=locationList.get(i).getCity()%></option>
                    <%
                }
            %>
            </select>
            <input type="submit" value="Search by location">
            </form>
            <form action = "filterByTitle">
                <input name="titleString" type="text">
                <input type="submit" value="Search by title">
            </form>
                <table >
                <thead>
                    <tr>
                        <td>Number</td>
                        <td>Formation</td>
                        <td>Location</td>
                        <td>Start Date</td>
                        <td>End Date</td>
                        <td>Maximum</td>
                        <td>Apply</td>
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
                                        <input type="submit" value="Apply">
                                    </form>
                                </td>
                            </tr>
                        <%
                    }
                %>
                </tbody>
            </table>
            <%--</br>--%>
            <%--<b>Courses Session ID :  <%= courseSession.getId()%></b>--%>
            <%--</br>--%>
            <%--<b>Courses Session :  <%= courseSession.getStartDate()%></b>--%>
            <%--</br>--%>
            <%--<b>Courses by Date : <%= cs.getallCoursesByCourseSessionDate("2019-01-14") %></b>--%>
            <%--</br>--%>
            <%--<b>Courses by Location : <%= cs.getAllCoursesByLocation("Montbeliard") %></b>--%>
            <%--</br>--%>
            <%--<b>Courses by Key word : <%= cs.getAllCoursesByKeyWord("IN") %></b>--%>
            <%--</br>--%>
            <%-->--%>
                <%--<%--%>
                    <%--for(int i = 0; i < list.size(); i++) {--%>
                <%--%>--%>
                <%--
                <%----%>
                <%--<% } %>--%>
            <%--</select>--%>
            <%--<div>--%>
                <%--<%--%>
                <%--for(int i = 0; i < list.size(); i++) {--%>
                <%--%>--%>
                <%--<p><%=list.get(i)%>--%>
                <%--</p>--%>
                <%--<% } %>--%>
            <%--</div>--%>
        </div>
    </div>

</div>
<div>
    <jsp:include page="fragments/footer.html" />
</div>

<jsp:include page="fragments/js_include.html" />

</body>
</html>
