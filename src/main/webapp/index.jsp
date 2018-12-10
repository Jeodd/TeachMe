<html>
<body>
<h2>Hello World!</h2>
<%@page import="fr.utbm.TeachMe.entity.Course"%>
<%@page import="fr.utbm.TeachMe.services.CourseService"%>
<%@ page import="fr.utbm.TeachMe.services.CourseSessionService" %>
<%@ page import="fr.utbm.TeachMe.entity.CourseSession" %>
<%
    CourseService cs = new CourseService();
    Course c = new Course();
    c.setCode("INF1");
    c = cs.getCourse(c);

    CourseSessionService css = new CourseSessionService();
    CourseSession courseSession = new CourseSession();
    courseSession.setId(1);
    courseSession = css.getCoursesSession(courseSession);
%>
<b>Course :  <%= c.getTitle()%></b>
</br>
<b>Courses Session ID :  <%= courseSession.getId()%></b>
</br>
<b>Courses Session :  <%= courseSession.getStartDate()%></b>
</body>
</html>
