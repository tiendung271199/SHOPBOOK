<%@page import="constants.GlobalConstant"%>
<%@page import="utils.CheckUtil"%>
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
	<%
    	User userLog = null;
		if (session.getAttribute("adminLogin") != null) {
			userLog = (User) session.getAttribute("adminLogin");
		}
		int roleLog = userLog.getRole();
		int idLog = userLog.getId();
    %>
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center">
                <img src="<%=request.getContextPath() %>/templates/admin/assets/img/find_user.png" class="user-image img-responsive" />
            </li>
            <li>
                <a id="index" href="<%=request.getContextPath() %>/admin"><i class="fa fa-home fa-3x"></i> Trang chủ</a>
            </li>
            <%
            	if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.CAT_MODULE)) {
            %>
            <li>
                <a id="category" href="<%=request.getContextPath() %>/admin/cat"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <%
            	}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.BOOK_MODULE)) {
            %>
            <li>
                <a id="book" href="<%=request.getContextPath() %>/admin/book"><i class="fa fa-book fa-3x"></i> Quản lý sách</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.USER_MODULE)) {
            %>
            <li>
                <a id="user" href="<%=request.getContextPath() %>/admin/user"><i class="fa fa-user fa-3x"></i> Quản lý người dùng</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.ORDER_MODULE)) {
            %>
            <li>
                <a id="order" href="<%=request.getContextPath() %>/admin/order"><i class="fa fa-shopping-cart fa-3x"></i> Quản lý đơn hàng</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.REVIEWS_MODULE)) {
            %>
            <li>
                <a id="reviews" href="<%=request.getContextPath() %>/admin/reviews"><i class="fa fa-comments fa-3x"></i> Quản lý đánh giá</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.SALE_MODULE)) {
            %>
            <li>
                <a id="sale" href="<%=request.getContextPath() %>/admin/sale"><i class="fa fa-credit-card fa-3x"></i> Quản lý sách sale</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.CONTACT_MODULE)) {
            %>
            <li>
                <a id="contact" href="<%=request.getContextPath() %>/admin/contact"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.WORD_MODULE)) {
            %>
            <li>
                <a id="forbiddenword" href="<%=request.getContextPath() %>/admin/forbiddenword"><i class="fa fa-ban fa-3x"></i> Quản lý từ cấm</a>
            </li>
            <%
           		}
           		if (roleLog == 2 || CheckUtil.checkRole(idLog, GlobalConstant.ROLE_MODULE)) {
            %>
            <li>
                <a id="role" href="<%=request.getContextPath() %>/admin/role"><i class="fa fa-tasks fa-3x"></i> Quản lý phân quyền</a>
            </li>
            <%
           		}
            %>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->