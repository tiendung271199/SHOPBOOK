<%@page import="constants.GlobalConstant"%>
<%@page import="models.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
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
                                <div class="col-sm-6"></div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath() %>/admin/contact">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="sname" value="${sname}" class="form-control input-sm" placeholder="Nhập tên người liên hệ" style="float:right; width: 300px;" required="required" />
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
								if (request.getAttribute("listContact") != null) {
									ArrayList<Contact> listContact = (ArrayList<Contact>) request.getAttribute("listContact");
									if (listContact.size() > 0) {
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Message</th>
                                        <th width="100px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for (Contact objContact : listContact) {
                                	%>
                                    <tr>
                                        <td><%=objContact.getId() %></td>
                                        <td class="center"><%=objContact.getName() %></td>
                                        <td class="center"><%=objContact.getEmail() %></td>
                                        <td class="center"><%=objContact.getMessage() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contact/delete?id=<%=objContact.getId() %>" onclick="return confirm('Bạn có chắc muốn xoá liên hệ của <%=objContact.getName() %> không?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
      								<span style="color: blue; font-style: italic">Không có liên hệ nào!</span>
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
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=first + 1 %> đến <%=first + last %> của <%=total %> liên hệ</div>
                                	<%
                                		} else {
                                	%>
                                	<div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=first + 1 %> đến <%=total %> của <%=total %> liên hệ</div>
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
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=back %>">Trang trước</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact">Fi</a></li>
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
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else if (pages == totalPages - 1) {
	                		                    	for (int j = pages - 1; j <= totalPages; j++) {
	                		                    		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else if (pages == totalPages) {
	                		                    	for (int j = pages - 2; j <= totalPages; j++) {
	                		                    		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            } else {
	                                            	for (int j = pages; j <= pages + 2; j++) {
	                                            		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
                                            <%
		                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
											<%
		                                        		}
		                                            }
	                                            }
	                                            if (pages < totalPages - 2) {
	                                        %>
	                                        <li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="javascript:void(0)">...</a></li>
	                                        <li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=totalPages %>">La</a></li>
	                                        <%
	                                            }
		                                        //Button Next
		                                        if (pages < totalPages) {
		                                        	int next = pages + 1;
		                                     %>
		                                     <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=next %>">Trang tiếp</a></li>
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
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=back %>">Trang trước</a></li>
                                           	<%
												}
	                                        	// Button Number page
	                                            for (int j = 1; j <= totalPages; j++) {
	                                        		if (pages == j) {
                                           	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
                                            <%
	                                        		} else {
                                            %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=j %>"><%=j %></a></li>
											<%
	                                        		}
	                                            }
		                                        //Button Next
		                                        if (pages < totalPages) {
		                                        	int next = pages + 1;
											%>
											<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/contact?pages=<%=next %>">Trang tiếp</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>