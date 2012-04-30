<%@page import="controller.Login"%>
<%
    Login.logoutUser(session);
    response.sendRedirect(getServletContext().getContextPath());
%>