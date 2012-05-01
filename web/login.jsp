<%@page import="model.Users"%>
<%@page import="controller.Login"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Copy to all pages -->
        <%
            ServletContext context = getServletContext();
            String contextPath = context.getContextPath();
            if (Login.isLoggedIn(session)) {
                response.sendRedirect(contextPath);
            }
        %>
        <link rel="stylesheet" href="<%= contextPath%>/assets/css/bootstrap.min.css" />
        <style type="text/css">
            body {
                padding: 60px;

            }
            #LoginForm input{
                display: block;
                height: 50px;
                font-size: 25px;
            }
            #LoginForm button{
                font-size: 35px;
            }
            #RegisterForm input {
                display: block;
                height: 30px;
                font-size: 20px;
            }
            #RegisterForm textarea{
                height: 50px;
                font-size: 20px;
            }
            #RegisterForm button{
                font-size: 35px;
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
                            <li ><a href="<%= contextPath%>">Home</a></li>
                        </ul>
                        <%-- LOGIN --%>
                        <ul class="nav pull-right">
                            <% if (Login.isLoggedIn(session)) {
                                    Users user = (Users) session.getAttribute(Login.USER_SESSION_KEY);
                            %>
                            <li class="active"  ><a href="<%= contextPath%>/logout.jsp">Logout from <%= user.getEmail()%></a></li>
                            <% } else {%>
                            <li  class="active" ><a href="<%= contextPath%>/login.jsp">Login</a></li>
                            <% }%>
                        </ul><%-- /LOGIN --%>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <div class="container">

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit span4">
                <h1>Login</h1>
                <p>
                    <%
                        if (session.getAttribute(Login.LOGIN_ERRORS_SESSION_KEY) != null) {%>
                <div class="alert alert-block alert-error">
                    <a class="close" data-dismiss="alert" href="#">×</a>
                    <h4 class="alert-heading">Error !</h4>
                    <%= session.getAttribute(Login.LOGIN_ERRORS_SESSION_KEY)%>
                </div>
                <% }
                    session.setAttribute(Login.LOGIN_ERRORS_SESSION_KEY, null);
                %>
                <form id="LoginForm" class="form-vertical" action="<%= contextPath%>/login" method="post">
                    <input type="email" class="span4" name="email" placeholder="Email ID" />
                    <input type="password" class="span4" name="password" placeholder="Password" />
                    <button type="submit"  class="pull-right btn btn-success btn-large " >Login</button>
                </form>
                </p>
            </div>
            <div class="hero-unit span4">
                <h1>Register</h1>                  
                <p>
                <form id="RegisterForm" class="span4" method="post" action="<%= contextPath %>/register" >
                    <input type="email" class="span4" placeholder="Your email address" name="email" required />
                    <input type="password" class="span4" name="password" placeholder="The password you wish to have" required/>
                    <textarea type="address" class="span4" name="address" placeholder="Your Shipping address" required= ></textarea>
                    <input type="number" class="span4" name="phone" placeholder="Your contact number" required/>
                    <button type="submit" class="pull-right btn btn-primary btn-large" >Register</button>
                </form>
                </p>
                
            </div>

            <!-- Example row of columns -->





        </div> <!-- /container -->
        <footer>
            <p>&copy; OBStore 2012</p>
        </footer>
</html>
