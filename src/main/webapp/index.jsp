<%--
    Document   : Page
    Created on : 21 oct. 2018, 21:46:22
    Author     : Rémi Pereira & Jacques Martinelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="fragments/header.html" />

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

            <h2>Hello World!</h2>
            <%@page import="fr.utbm.TeachMe.entity.Course"%>
            <%@page import="fr.utbm.TeachMe.services.CourseService"%>
            <%@ page import="fr.utbm.TeachMe.services.CourseSessionService" %>
            <%@ page import="fr.utbm.TeachMe.entity.CourseSession" %>
            <%@ page import="java.util.List" %>
            <%@ page import="java.util.ArrayList" %>
            <%
                CourseService cs = new CourseService();
                Course c = new Course();
                c.setCode("INF1");
                c = cs.getCourse(c);

                CourseSessionService css = new CourseSessionService();
                CourseSession courseSession = new CourseSession();
                courseSession.setId(1);
                courseSession = css.getCoursesSession(courseSession);

                List<String> list = new ArrayList();
                for(CourseSession item : css.getAllCoursesSession()) { list.add(item.toString()); }

            %>
            <b>Course :  <%= c.getTitle()%></b>
            </br>
            <b>Courses Session ID :  <%= courseSession.getId()%></b>
            </br>
            <b>Courses Session :  <%= courseSession.getStartDate()%></b>
            </br>

            <select name="mySelect">
                <%
                    for(int i = 0; i < list.size(); i++) {
                %>
                <option value = "<%list.get(i);%>"><%=list.get(i)%>
                </option>
                <% } %>
            </select>
        </div>
    </div>

</div>


<div>
    <jsp:include page="fragments/footer.html" />
</div>

<jsp:include page="fragments/js_include.html" />

</body>
</html>
