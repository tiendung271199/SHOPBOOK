<%@page import="models.OrderManageDetail"%>
<%@page import="models.OrderManage"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="utils.StringUtil"%>
<%@page import="models.OrderDetail"%>
<%@page import="models.Order"%>
<%@page import="models.SaleOff"%>
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
                            <span>Chi tiết đơn hàng</span>
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
    		if (request.getAttribute("orderManage") != null) {
    			OrderManage orderManage = (OrderManage) request.getAttribute("orderManage");
    			ArrayList<OrderManageDetail> list = orderManage.getList();
    			if (list.size() > 0) {
    	%>
    	<div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                    	<h4 style="margin-bottom: 20px">Chi tiết đơn hàng #<%=orderManage.getId() %></h4>
                        <div>
                        	<p>Ngày mua: <%=orderManage.getDateCreate() %></p>
                        	<p>Thông tin người nhận: <span style="color: red"><%=orderManage.getName() %></span></p>
                        	<p>Địa chỉ: <%=orderManage.getAddress() %></p>
                        	<p>Số điện thoại: <%=orderManage.getPhone() %></p>
                        	<p>Email: <%=orderManage.getEmail() %></p>
                        	<p>Ghi chú đơn hàng: <%=orderManage.getOrderNote() %></p>
                        	<%
                        		if (orderManage.getStatus() == 1) {
                        	%>
                        	<p>Trạng thái đơn hàng: Đang xử lý</p>
                        	<%
                        		} else if (orderManage.getStatus() == 2) {
                        	%>
                        	<p>Trạng thái đơn hàng: Đang giao hàng</p>
                        	<%
                        		} else if (orderManage.getStatus() == 3) {
                        	%>
                        	<p>Trạng thái đơn hàng: Giao hàng thành công</p>
                        	<%
                        		} else {
                        	%>
                        	<p>Trạng thái đơn hàng: Đã huỷ</p>
                        	<%
                        		}
                        	%>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Tên sách</th>
                                    <th>Số lượng</th>
                                    <th>Giá bán</th>
                                    <th>Giảm giá</th>
                                    <th>Tổng tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            		int thanhTien = 0;
                            		for (OrderManageDetail objOrderDetail : list) {
                            			Book objBook = GetObjUtil.getBook(objOrderDetail.getIdBook());
                            			int sale = (objOrderDetail.getPrice() * objOrderDetail.getSale()) / 100;
                            			int tongTien = objOrderDetail.getNumber() * (objOrderDetail.getPrice() - sale);
                            			thanhTien += tongTien;
                            	%>
                                <tr>
                                    <td><%=objBook.getName() %></td>
                                    <td><%=objOrderDetail.getNumber() %></td>
                                    <td><%=objOrderDetail.getPrice() %> VNĐ</td>
                                    <%
                                    	if (sale == 0) {
                                    %>
                                    <td align="right">Không</td>
                                    <%
                                    	} else {
                                    %>
                                    <td align="right">-<%=sale %> VNĐ</td>
                                    <%
                                    	}
                                    %>
                                    <td align="right"><%=tongTien %> VNĐ</td>
                                </tr>
                                <%
                            		}
                            		int ship = 0;
                            		if (thanhTien < 300000) {
                            			ship = GlobalConstant.SHIP;
                            			thanhTien += ship;
                            		}
                                %>
                                <tr>
                                	<td colspan="4" align="right">Phí ship</td>
                                	<td align="right"><%=ship %> VNĐ</td>
                                </tr>
                                <tr>
                                	<td style="font-weight: bold" colspan="4" align="right">Thành tiền</td>
                                	<td style="font-weight: bold; color: red" align="right"><%=thanhTien %> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                        <div style="margin-top: 20px">
                        	<a style="padding: 5px 10px; background-color: #CCC" href="<%=request.getContextPath() %>/order/user">Quay lại đơn hàng của tôi</a>
                        	<%
                        		if (orderManage.getStatus() != 4 && orderManage.getStatus() != 3) {
                        	%>
                        	<a onclick="return confirm('Bạn có chắc muốn huỷ đơn hàng không?')" style="padding: 5px 10px; background-color: red; color: white; margin-left: 7px" href="<%=request.getContextPath() %>/order/user/cancel?id=<%=orderManage.getId() %>">Huỷ đơn hàng</a>
                    		<%
                        		}
                    		%>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
        <%
    			} else {
    	%>
    	<div style="text-align: center" class="col-lg-12">
       		<img style="width: 190px" src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="empty__img">
       		<p class="empty__note">Chi tiết đơn hàng rỗng</p>
            <div class="shoping__cart__btns">
                <a href="<%=request.getContextPath() %>/shop" class="primary-btn cart-btn">MUA SẮM NGAY</a>
            </div>
        </div>
    	<%
    			}
    		}
        %>
    </section>
    <!-- Shoping Cart Section End -->
<%@ include file="/templates/public/inc/footer.jsp" %>