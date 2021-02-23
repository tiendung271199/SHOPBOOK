<%@page import="utils.StringUtil"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="models.User"%>
<%@page import="models.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý đơn hàng</h2>
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
                            <div class="row">
                                <div class="col-sm-6">
                                	<form method="post" action="<%=request.getContextPath() %>/admin/order/export">
	                                	<input type="submit" name="xuatdonhang" value="Xuất đơn hàng" class="btn btn-warning btn-sm" style="float:left" />
	                                	<select name="month" class="btn-sm" style="float:left; margin: 0px 10px">
	                                   		<option value="0">--Chọn tháng--</option>
	                                   		<option value="1">Tháng 1</option>
	                                   		<option value="2">Tháng 2</option>
	                                   		<option value="3">Tháng 3</option>
	                                   		<option value="4">Tháng 4</option>
	                                   		<option value="5">Tháng 5</option>
	                                   		<option value="6">Tháng 6</option>
	                                   		<option value="7">Tháng 7</option>
	                                   		<option value="8">Tháng 8</option>
	                                   		<option value="9">Tháng 9</option>
	                                   		<option value="10">Tháng 10</option>
	                                   		<option value="11">Tháng 11</option>
	                                   		<option value="12">Tháng 12</option>
	                                   	</select>
	                                   	<input type="text" name="year" value="<%=StringUtil.getYearNow() %>" class="form-control input-sm" placeholder="Năm" style="float:left; width: 60px;" required="required" />
                                   	</form>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath() %>/admin/order">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="sname" value="${sname}" class="form-control input-sm" placeholder="Nhập tên người đặt hàng" style="float:right; width: 300px;" required="required" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
                            <%
                              	if (request.getAttribute("success") != null) {
                            %>
                            <div class="alert alert-success" role="alert">
                            	<span style="color: blue; font-style: italic">${success}</span>
                            </div>
                            <%
                            	}
								if (request.getAttribute("err") != null) {
                            %>
                            <div class="alert alert-danger" role="alert">
                              	<span style="color: red; font-style: italic">${err}</span>
                            </div>
							<%
								}
								int check = 0;
								int numberPage = GlobalConstant.NUMBER_PAGE2;
								int total = 0, pages = 1, totalPages = 1, first = 0, last = 0;
								if (request.getAttribute("pages") != null) {
							        pages = (int) request.getAttribute("pages");
							    }
								if (request.getAttribute("total") != null) {
							        total = (int) request.getAttribute("total");
							    }
								if (request.getAttribute("totalPages") != null) {
									totalPages = (int) request.getAttribute("totalPages");
							    }
								if (request.getAttribute("first") != null) {
									first = (int) request.getAttribute("first");
							    }
								if (request.getAttribute("last") != null) {
									last = (int) request.getAttribute("last");
							    }
								if (request.getAttribute("listOrder") != null) {
									ArrayList<Order> listOrder = (ArrayList<Order>) request.getAttribute("listOrder");
									if (listOrder.size() > 0) {
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Ngày đặt hàng</th>
                                        <th width="120px">Trạng thái</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for (Order order : listOrder) {
                                			String dateCreate = StringUtil.tachChuoi(order.getDateCreate());
                                	%>
                                    <tr>
                                        <td><%=order.getId() %></td>
                                        <td class="center"><%=order.getName() %></td>
                                        <td class="center"><%=dateCreate %></td>
                                        <td align="center" class="center">
                                        	<%
                                        		if (order.getStatus() == 1) {
                                        	%>
                                        	<span style="padding: 5px 10px; background-color: #FFFF00; font-size: 10px; border-radius: 8px">Đang xử lý</span>
											<%
                                        		} else if (order.getStatus() == 2) {
											%>
											<span style="padding: 5px 10px; background-color: #33FFFF; font-size: 10px; border-radius: 8px">Đang giao hàng</span>
											<%
                                        		} else if (order.getStatus() == 3) {
											%>
											<span style="padding: 5px 10px; color: white; background-color: #009933; font-size: 10px; border-radius: 8px">Hoàn thành</span>
											<%
                                        		} else {
											%>
											<span style="padding: 5px 10px; color: white; background-color: #990000; font-size: 10px; border-radius: 8px">Đã huỷ</span>
											<%
                                        		}
											%>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/order/detail?id=<%=order.getId() %>" title="" class="btn btn-primary"><i class="fa fa-eye "></i> Xem</a>
                                            <a href="<%=request.getContextPath() %>/admin/order/delete?id=<%=order.getId() %>" onclick="return confirm('Bạn có chắc muốn xoá đơn hàng thứ <%=order.getId() %> không?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%
                                		}
									%>
                                </tbody>
                            </table>
                            <%
									} else {
										check = 1;
							%>
								<div class="alert alert-success" role="alert">
      								<span style="color: blue; font-style: italic">Không có đơn hàng nào!</span>
      							</div>
							<%
									}
								}
								if (request.getAttribute("sname") != null) {
									check = 1;
								}
								if (check == 0) {
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <%
                                		if (total - first > numberPage) {
                                	%>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=first + 1 %> đến <%=first + last %> của <%=total %> đơn hàng</div>
                                	<%
                                		} else {
                                	%>
                                	<div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=first + 1 %> đến <%=total %> của <%=total %> đơn hàng</div>
                                	<%
                                		}
                                	%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <%
                                    		if (totalPages > 4) {
                                    	%>
                                        <ul class="pagination">
                                        	<%
												// Button previous
												if (pages > 1) {
													int back = pages - 1;
		   									%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=back %>">Trang trước</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order">Fi</a></li>
											<%
												}
                                        		if (pages > 2) {
                                        	%>
                                        	<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="javascript:void(0)">...</a></li>
                                        	<%
                                        		}
	                                        	// Button Number page
	                                            if (pages == totalPages - 2) {
	                		                    	for (int j = pages; j <= totalPages; j++) {
		                                        		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else if (pages == totalPages - 1) {
	                		                    	for (int j = pages - 1; j <= totalPages; j++) {
	                		                    		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else if (pages == totalPages) {
	                		                    	for (int j = pages - 2; j <= totalPages; j++) {
	                		                    		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else {
	                                            	for (int j = pages; j <= pages + 2; j++) {
	                                            		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            }
	                                            if (pages < totalPages - 2) {
	                                        %>
	                                        <li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="javascript:void(0)">...</a></li>
	                                        <li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=totalPages %>">La</a></li>
	                                        <%
	                                            }
		                                        //Button Next
		                                        if (pages < totalPages) {
		                                        	int next = pages + 1;
		                                     %>
		                                     <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=next %>">Trang tiếp</a></li>
		                                     <%
		                                        }
											 %>
                                        </ul>
                                        <%
                                    		} else {
                                        %>
                                        <ul class="pagination">
                                        	<%
												// Button previous
												if (pages > 1) {
													int back = pages - 1;
		   									%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=back %>">Trang trước</a></li>
                                           	<%
												}
	                                        	// Button Number page
	                                            for (int j = 1; j <= totalPages; j++) {
	                                        		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
                                            <%
	                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=j %>"><%=j %></a></li>
											<%
	                                        		}
	                                            }
		                                        //Button Next
		                                        if (pages < totalPages) {
		                                        	int next = pages + 1;
											%>
											<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/order?pages=<%=next %>">Trang tiếp</a></li>
								             <%
		                                        }
										     %>
                                        </ul>
                                        <%
                                    		}
                                        %>
                                    </div>
                                </div>
                            </div>
                            <%
								}
                            %>
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