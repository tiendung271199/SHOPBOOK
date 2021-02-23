<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>TRANG QUẢN TRỊ VIÊN</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <%
        	int roleLogin = adminLogin.getRole();
        	if (roleLogin == 2) {
        %>
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-shopping-cart"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/order" title="">Quản lý đơn hàng</a></p>
                        <p class="text-muted">Có ${newOrder} đơn hàng mới</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-book"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/book" title="">Quản lý sách</a></p>
                        <p class="text-muted">Có ${numBook} sản phẩm</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-user"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/user" title="">Quản lý người dùng</a></p>
                        <p class="text-muted">Có ${numUser} người dùng</p>
                    </div>
                </div>
            </div>
        </div>
		<%
        	} else {
		%>
		<div class="row">
			<%
				if (request.getAttribute("numCat") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-list"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/cat" title="">Quản lý danh mục</a></p>
                        <p class="text-muted">Có ${numCat} danh mục</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("numBook") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-book"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/book" title="">Quản lý sách</a></p>
                        <p class="text-muted">Có ${numBook} sản phẩm</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("numUser") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-user"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/user" title="">Quản lý người dùng</a></p>
                        <p class="text-muted">Có ${numUser} người dùng</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("newOrder") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-shopping-cart"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/order" title="">Quản lý đơn hàng</a></p>
                        <p class="text-muted">Có ${newOrder} đơn hàng mới</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("newReviews") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-comments"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/reviews" title="">Quản lý đánh giá</a></p>
                        <p class="text-muted">Có ${newReviews} đánh giá mới</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("numSale") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-credit-card"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/sale" title="">Quản lý sách sale</a></p>
                        <p class="text-muted">Có ${numSale} sách sale</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("newContact") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-envelope"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/contact" title="">Quản lý liên hệ</a></p>
                        <p class="text-muted">Có ${newContact} liên hệ mới</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("numWord") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-ban"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/forbiddenword" title="">Quản lý từ cấm</a></p>
                        <p class="text-muted">Có ${numWord} từ cấm</p>
                    </div>
                </div>
            </div>
			<%
				}
				if (request.getAttribute("numRole") != null) {
			%>
			<div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-tasks"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/role" title="">Quản lý phân quyền</a></p>
                        <p class="text-muted">Có ${numRole} quyền được cấp</p>
                    </div>
                </div>
            </div>
			<%
				}
			%>
		</div>
		<%
        	}
		%>
    </div>
</div>
<script>
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>