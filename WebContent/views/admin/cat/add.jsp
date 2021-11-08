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
                <h2>Thêm danh mục</h2>
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
                                <form action="<%=request.getContextPath() %>/admin/cat/add" role="form" method="post" id="form">
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
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" id="name" value="${name}" name="name" class="form-control" />
                                    </div>
                                    <%
                                    	int parentId = 0;
                                    	if (request.getAttribute("parentId") != null) {
                                    		parentId = (int) request.getAttribute("parentId");
                                    	}
                                    	if (request.getAttribute("listCat") != null) {
                                    		ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
                                    		if (listCat.size() > 0) {
                                    %>
                                    <div class="form-group">
                                        <label for="catParent">Danh mục cha</label>
                                        <select id="catParent" name="catParent" class="form-control">
	                                        <option value="0"> --- </option>
	                                        <%
	                                        	for (Category catParent : listCat) {
	                                        %>
											<option value="<%=catParent.getId() %>" <% if (parentId == catParent.getId()) out.print("selected"); %> ><%=catParent.getName() %></option>
											<%
	                                        	}
											%>
                                        </select>
                                    </div>
                                    <%
                                    		}
                                    	}
                                    %>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>