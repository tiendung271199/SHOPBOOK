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

  <title>SB Admin - Forgot Password</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath() %>/templates/admin/vendor/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          <h4>Forgot your password?</h4>
          <p>Enter your email address and we will send you instructions on how to reset your password.</p>
        </div>
        <form>
          <div class="form-group">
            <div class="form-label-group">
              <input type="email" id="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Enter email address</label>
            </div>
          </div>
          <input type="submit" name="submit" value="Reset Password" class="btn btn-primary btn-block" />
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="<%=request.getContextPath() %>/register">Register an Account</a>
          <a class="d-block small" href="<%=request.getContextPath() %>/login">Login Page</a>
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
