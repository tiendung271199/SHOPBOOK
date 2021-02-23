<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="<%=request.getContextPath() %>/admin/user/add" role="form" method="post" id="form">
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
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="${username}" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="repassword">RePassword</label>
                                        <input type="password" id="repassword" value="" name="repassword" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Fullname</label>
                                        <input type="text" id="fullname" value="${fullname}" name="fullname" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="address">Address</label>
                                        <input type="text" id="address" value="${address}" name="address" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" id="email" value="${email}" name="email" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="text" id="phone" value="${phone}" name="phone" class="form-control" />
                                    </div>
                                    <%
                                    	int role = 0;
                                    	if (request.getAttribute("role") != null) {
                                    		role = (int) request.getAttribute("role");
                                    	}
                                    %>
                                    <div class="form-group">
                                        <label for="role">Role</label>
                                        <select id="role" name="role" class="form-control">
	                                        <option value="0" <% if (role == 0) out.print("selected"); %>>User</option>
											<option value="1" <% if (role == 1) out.print("selected"); %>>Mod</option>
											<%
												if (adminLogin.getRole() == 2) {
											%>
											<option value="2" <% if (role == 2) out.print("selected"); %>>Admin</option>
											<%
												}
											%>
                                        </select>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>