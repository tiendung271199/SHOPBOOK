<%@page import="constants.GlobalConstant"%>
<%@page import="models.OrderManageDetail"%>
<%@page import="models.OrderManage"%>
<%@page import="models.Order"%>
<%@page import="models.SaleOff"%>
<%@page import="utils.StringUtil"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="models.Book"%>
<%@page import="models.CartDetail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/banner6.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Theo dõi đơn hàng</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath() %>/">Trang chủ</a>
                            <span>Đơn hàng</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
    	<%
    		String kOrder = "order" + userLogin2.getId();
    		if (session.getAttribute(kOrder) != null) {
    			ArrayList<OrderManage> listOrderManage = (ArrayList<OrderManage>) session.getAttribute(kOrder);
    			if (listOrderManage.size() > 0) {
    	%>
    	<div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                    	<%
		            		if (request.getParameter("msg") != null) {
		            	%>
		            	<div class="alert alert-success" role="alert">
							<span style="color: blue; font-style: italic">Chúc mừng bạn đã đặt hàng thành công!</span>
						</div>
						<%
		            		}
                    		if (request.getParameter("cancel") != null) {
						%>
						<div class="alert alert-success" role="alert">
							<span style="color: blue; font-style: italic">Huỷ đơn hàng thành công!</span>
						</div>
						<%
		            		}
                    		if (request.getParameter("err") != null) {
						%>
						<div class="alert alert-danger" role="alert">
                          	<span style="color: red; font-style: italic">Có lỗi xảy ra, vui lòng thử lại!</span>
                        </div>
						<%
                    		}
						%>
                    	<h4 style="margin-bottom: 20px">Đơn hàng của tôi</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Ngày mua</th>
                                    <th>Số lượng</th>
                                    <th>Tổng tiền</th>
                                    <th>Trạng thái đơn hàng</th>
                                    <th>Chi tiết</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            		for (int i = listOrderManage.size() - 1; i >= 0; i--) {
                            			OrderManage objOrderManage = listOrderManage.get(i);
                            			int soLuong = 0, tongTien = 0;
                            			ArrayList<OrderManageDetail> list = objOrderManage.getList();
                            			if (list.size() > 0) {
                            				for (OrderManageDetail obj : list) {
                            					soLuong += obj.getNumber();
                            					tongTien += obj.getNumber() * ((obj.getPrice() * (100 - obj.getSale())) / 100);
                            				}
                            				if (tongTien < 300000) {
                            					tongTien += GlobalConstant.SHIP;
                            				}
                            			}
                            			String urlOrder = request.getContextPath() + "/don-hang-cua-toi/" + objOrderManage.getId();
                            	%>
                                <tr>
                                    <td><%=objOrderManage.getId() %></td>
                                    <td><%=objOrderManage.getDateCreate() %></td>
                                    <td><%=soLuong %></td>
                                    <td align="right"><%=tongTien %> VNĐ</td>
                                    <%
                                    	if (objOrderManage.getStatus() == 1) {
                                    %>
                                    <td>Đang xử lý</td>
                                    <%
                                    	} else if (objOrderManage.getStatus() == 2) {
                                    %>
                                    <td>Đang giao hàng</td>
                                    <%
                                    	} else if (objOrderManage.getStatus() == 3) {
                                    %>
                                    <td>Giao hàng thành công</td>
                                    <%
                                    	} else {
                                    %>
                                    <td>Đã huỷ</td>
                                    <%
                                    	}
                                    %>
                                    <td><a style="padding: 5px 10px; background-color: #CCC" href="<%=urlOrder %>">Xem</a></td>
                                </tr>
                                <%
                            		}
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%
    			} else {
    	%>
    	<div style="text-align: center" class="col-lg-12">
       		<img style="width: 190px" src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="empty__img">
       		<p class="empty__note">Bạn chưa có đơn hàng nào</p>
            <div class="shoping__cart__btns">
                <a href="<%=request.getContextPath() %>/shop" class="primary-btn cart-btn">MUA SẮM NGAY</a>
            </div>
        </div>
    	<%
    			}
    		} else {
        %>
        <div style="text-align: center" class="col-lg-12">
       		<img style="width: 190px" src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="empty__img">
       		<p class="empty__note">Bạn chưa có đơn hàng nào</p>
            <div class="shoping__cart__btns">
                <a href="<%=request.getContextPath() %>/shop" class="primary-btn cart-btn">MUA SẮM NGAY</a>
            </div>
        </div>
        <%
    		}
        %>
    </section>
    <!-- Shoping Cart Section End -->
<%@ include file="/templates/public/inc/footer.jsp" %>