<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Login - Shop Book</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form action="<%=request.getContextPath() %>/login" method="post">
          <%
          	  if (request.getAttribute("err") != null) {
          %>
          <div class="alert alert-danger" role="alert">
               <span style="color: red; font-style: italic">${err}</span>
          </div>
          <%
          	  }
          %>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username" value="${username}" required="required" autofocus="autofocus">
              <label for="inputUsername">Username</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required="required">
              <label for="inputPassword">Password</label>
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me">
                Remember Password
              </label>
            </div>
          </div>
          <input class="btn btn-primary btn-block" type="submit" name="submit" value="Login" />
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="<%=request.getContextPath() %>/register">Register an Account</a>
          <a class="d-block small" href="<%=request.getContextPath() %>/forgot-password">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery.min.js"></script>
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath() %>/templates/admin/vendor/jquery.easing.min.js"></script>

</body>
</html>
