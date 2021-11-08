<%@page import="utils.StringUtil"%>
<%@page import="models.Order"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="models.Book"%>
<%@page import="models.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>CHI TIẾT ĐƠN HÀNG</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                        	<%
                        		if (request.getAttribute("objOrder") != null) {
                        			Order objOrder = (Order) request.getAttribute("objOrder");
                        	%>
                        	<div class="row">
                        		<span style="margin-left: 15px; font-weight: bold; font-size: 20px; color: red">Thông tin khách hàng</span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Mã đơn hàng:</span> #<%=objOrder.getId() %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Tên khách hàng:</span> <%=objOrder.getName() %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Số điện thoại:</span> <%=objOrder.getPhone() %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Email:</span> <%=objOrder.getEmail() %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Địa chỉ:</span> <%=objOrder.getAddress() %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Ngày đặt hàng:</span> <%=StringUtil.tachChuoi(objOrder.getDateCreate()) %></span><br /><br />
                        		<span style="margin-left: 15px"><span style="font-weight: bold">Ghi chú đơn hàng:</span> <%=objOrder.getOrderNote() %></span><br /><br />
                        	</div>
                        	<div class="row" style="margin-bottom: 20px">
                                <div class="col-sm-6">
                                	<form method="post" action="<%=request.getContextPath() %>/admin/order/detail">
                                		<input type="text" name="idOrder" value="<%=objOrder.getId() %>" style="display: none"/>
	                                	<input type="text" value="Trạng thái đơn hàng" class="btn btn-warning btn-sm" style="float:left" readonly="readonly"/>
	                                	<select name="trangthai" class="btn-sm" style="float:left; margin: 0px 10px">
	                                   		<option value="1" <% if (objOrder.getStatus() == 1) out.print("selected"); %>>Đang xử lý</option>
	                                   		<option value="2" <% if (objOrder.getStatus() == 2) out.print("selected"); %>>Đang giao hàng</option>
	                                   		<option value="3" <% if (objOrder.getStatus() == 3) out.print("selected"); %>>Giao hàng thành công</option>
	                                   		<option value="4" <% if (objOrder.getStatus() == 4) out.print("selected"); %>>Đã huỷ</option>
	                                   	</select>
	                                   	<input type="submit" name="submit" value="Cập nhật" class="btn btn-warning btn-sm" style="float:left" />
                                   	</form>
                                </div>
                                <div class="col-sm-6" style="text-align: right"></div>
                            </div>
							<%
                        		}
								if (request.getAttribute("listOrderDetail") != null) {
									ArrayList<OrderDetail> listOrderDetail = (ArrayList<OrderDetail>) request.getAttribute("listOrderDetail");
									if (listOrderDetail.size() > 0) {
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên sách</th>
                                        <th>Số lượng</th>
                                        <th>Giảm giá</th>
                                        <th>Giá bán</th>
                                        <th width="180px">Tổng tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		int thanhTien = 0, ship = 0;
                                		for (OrderDetail orderDetail : listOrderDetail) {
                                			Book book = GetObjUtil.getBook(orderDetail.getIdBook());
                                			int giaBan = (orderDetail.getPrice() * (100 - orderDetail.getSale())) / 100;
                                			int tongTien = orderDetail.getNumber() * giaBan;
                                			thanhTien += tongTien;
                                	%>
                                    <tr>
                                        <td><%=orderDetail.getId() %></td>
                                        <td class="center"><%=book.getName() %></td>
                                        <td class="center"><%=orderDetail.getNumber() %></td>
                                        <%
                                        	if (orderDetail.getSale() != 0) {
                                        %>
                                        <td class="center"><%=orderDetail.getSale() %> %</td>
                                        <%
                                        	} else {
                                        %>
                                        <td class="center">Không</td>
                                        <%
                                        	}
                                        %>
                                        <td align="right" class="center"><%=giaBan %> VNĐ</td>
                                        <td align="right" class="center"><%=tongTien %> VNĐ</td>
                                    </tr>
									<%
                                		}
                                		if (thanhTien < 300000) {
                                			ship = GlobalConstant.SHIP;
                                			thanhTien += ship;
                                		}
									%>
									<tr>
										<td colspan="5" align="right">Phí ship</td>
										<td align="right"><%=ship %> VNĐ</td>
									</tr>
									<tr>
										<td style="font-weight: bold" colspan="5" align="right">Thành tiền</td>
										<td style="font-weight: bold; color: red" align="right"><%=thanhTien %> VNĐ</td>
									</tr>
                                </tbody>
                            </table>
                            <%
									}
								}
                            %>
                            <a href="<%=request.getContextPath() %>/admin/order" title="" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Quay lại</a>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("order").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>