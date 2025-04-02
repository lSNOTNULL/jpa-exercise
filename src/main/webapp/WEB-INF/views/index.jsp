<%@ page import="org.example.jpaexercise.model.dto.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>가보자</title>
</head>
<body>
    <code> 실습 </code>
    <section>
        <%
            for (Student s : (List<Student>)request.getAttribute("students")){
                %>
                <ul>
                    <li> <%= s.getId()%></li>
                    <li> <%= s.getName()%></li>
                </ul>
        <%
            }
        %>


    </section>
</body>
</html>