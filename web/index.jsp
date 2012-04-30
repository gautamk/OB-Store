<%@page import="model.Books"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.BooksDAO"%>
<%@page import="model.Users"%>
<%@page import="controller.Login"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Copy to all pages -->
        <%
            ServletContext context = getServletContext();
            String contextPath = context.getContextPath();

        %>
        <link rel="stylesheet" href="<%= contextPath%>/assets/css/bootstrap.min.css" />
        <style type="text/css">
            body {
                padding: 60px;
                
            }

        </style>
        <link rel="stylesheet" href="<%= contextPath%>/assets/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%= contextPath%>/assets/css/obstore.css" />
        <script src="<%= contextPath%>/assets/js/jquery-1.7.2.min.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap.min.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-alert.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-button.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-carousel.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-collapse.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-dropdown.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-modal.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-popover.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-scrollspy.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-tab.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-tooltip.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-transition.js" ></script>
        <script src="<%= contextPath%>/assets/js/bootstrap-typeahead.js" ></script>
        <script src="<%= contextPath%>/assets/js/navbar.js" ></script>


    </head>
    <body>
        
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">OBStore</a>
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li class="active"><a href="<%= contextPath%>">Home</a></li>
                            <% if(Login.isLoggedIn(session)) { 
                                Users user = (Users) session.getAttribute(Login.USER_SESSION_KEY) ;
                             %>
                                <li><a href="<%= contextPath %>/logout.jsp">Logout from <%= user.getEmail() %></a></li>
                            <% } else { %>
                                <li><a href="<%= contextPath %>/login.jsp">Login</a></li>
                            <% } %>
                            
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="row">
        <div class="container">
            <% 
                List<Books> books = BooksDAO.getRandomBooks(6);
                for(Books book:books){
            %>
            <div class="span4">
                <h2><%= book.getName() %></h2>
                <h4>Rs.<%= book.getPrice() %>/-</h4>
                <p>
                    <%= book.getDescription() %>
                </p>
                <p>
                    <a href="#" class="btn btn-success" >View and Buy &rArr;</a>
                </p>
            </div>
            <% } %>
        </div>
            <footer>
                <p>&copy; OBStore 2012</p>
            </footer>

        </div> <!-- /container -->
</html>
