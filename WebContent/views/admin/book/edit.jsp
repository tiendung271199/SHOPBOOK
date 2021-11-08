<%@page import="models.Book"%>
<%@page import="models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa sách</h2>
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
                                <form action="<%=request.getContextPath() %>/admin/book/edit" role="form" method="post" id="form">
                                    <%
                                		if (request.getAttribute("err") != null) {
                                	%>
                                	<div class="alert alert-danger" role="alert">
                                		<span style="color: red; font-style: italic">${err}</span>
                                	</div>
                                	<%
                                		}
                                  		if (request.getAttribute("objBook") != null) {
                                  			Book objBook = (Book) request.getAttribute("objBook");
                                	%>
                                	<div class="form-group">
                                        <label for="idBook">ID</label>
                                        <input type="text" id="idBook" value="<%=objBook.getId() %>" name="idBook" class="form-control" readonly="readonly" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên sách</label>
                                        <input type="text" id="name" value="<%=objBook.getName() %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="author">Tác giả</label>
                                        <input type="text" id="author" value="<%=objBook.getAuthor() %>" name="author" class="form-control" />
                                    </div>
                                    <%
                                    	if (request.getAttribute("listCat") != null) {
                                    		ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
                                    		if (listCat.size() > 0) {
                                    %>
                                    <div class="form-group">
                                        <label for="category">Thể loại</label>
                                        <select id="category" name="category" class="form-control">
                                        	<%
                                        		for (Category cat : listCat) {
                                        	%>
	                                        <option value="<%=cat.getId() %>" <% if (cat.getId() == objBook.getCat().getId()) out.print("selected"); %>><%=cat.getName() %></option>
											<%
                                        		}
											%>
                                        </select>
                                    </div>
                                    <%
                                    		}
                                    	}
                                    %>
                                    <div class="form-group">
                                        <label for="number">Số lượng</label>
                                        <input type="text" id="number" value="<%=objBook.getNumber() %>" name="number" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="price">Giá bán (VNĐ)</label>
                                        <input type="text" id="price" value="<%=objBook.getPrice() %>" name="price" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Mô tả</label>
                                        <textarea id="description" class="form-control" rows="3" name="description"><%=objBook.getDescription() %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" rows="5" name="detail"><%=objBook.getDetail() %></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                    <%
                                  		}
                                    %>
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
    document.getElementById("book").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>