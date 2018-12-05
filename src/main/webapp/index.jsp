<html>
<body>
<h2>Hello World!</h2>
<%@page import="fr.utbm.TeachMe.entity.Course"%>
<%@page import="fr.utbm.TeachMe.services.CourseService"%>
<%
    CourseService cs = new CourseService();
    Course c = new Course();
    c.setCode("INF1");
    c = cs.getCourse(c);
%>
<b>Course :  <%= c.getTitle()%></b>
</body>
</html>
