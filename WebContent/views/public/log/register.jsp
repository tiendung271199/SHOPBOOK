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

  <title>Shop Book - Đăng ký tài khoản</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Đăng ký tài khoản</div>
      <div class="card-body">
        <form action="<%=request.getContextPath() %>/register" method="post">
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
              <input type="text" id="Fullname" class="form-control" name="Fullname" value="${fullname}" placeholder="Fullname">
              <label for="Fullname">Fullname</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="Address" class="form-control" name="Address" value="${address}" placeholder="Address">
              <label for="Address">Address</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="email" id="Email" class="form-control" name="Email" value="${email}" placeholder="Email">
                  <label for="Email">Email</label>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="Phone" class="form-control" name="Phone" value="${phone}" placeholder="Phone">
                  <label for="Phone">Phone</label>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="Username" class="form-control" name="Username" value="${username}" placeholder="Username">
              <label for="Username">Username</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" id="inputPassword" class="form-control" name="Password" placeholder="Password">
                  <label for="inputPassword">Password</label>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" id="confirmPassword" class="form-control" name="RePassword" placeholder="Confirm password">
                  <label for="confirmPassword">Confirm password</label>
                </div>
              </div>
            </div>
          </div>
          <input class="btn btn-primary btn-block" type="submit" name="submit" value="Register" >
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="<%=request.getContextPath() %>/login">Login Page</a>
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
