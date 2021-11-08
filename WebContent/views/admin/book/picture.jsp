<%@page import="utils.StringUtil"%>
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
                <h2>Hình ảnh</h2>
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
                                <form action="<%=request.getContextPath() %>/admin/book/picture" role="form" method="post" enctype="multipart/form-data" id="form">
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
                                    	if (request.getAttribute("objBook") != null) {
                                    		Book objBook = (Book) request.getAttribute("objBook");
                                	%>
                                	<div class="form-group">
                                        <label for="idBook">ID</label>
                                        <input type="text" id="idBook" value="<%=objBook.getId() %>" name="idBook" class="form-control" readonly="readonly" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên sách</label>
                                        <input type="text" id="name" value="<%=objBook.getName() %>" name="name" class="form-control" readonly="readonly" />
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" multiple="multiple" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                                <%
                                 	ArrayList<String> listFileName = StringUtil.getFileName(objBook.getPicture());
                                    if (listFileName.size() > 0) {
                                %>
                                <div class="table-responsive">
                                	<p>Danh sách hình ảnh</p>
	                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
		                                <thead>
		                                    <tr>
		                                        <th width="5%">STT</th>
		                                        <th>Hình ảnh</th>
		                                        <th width="100px">Chức năng</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<%
		                                		int stt = 1;
		                                		for (String fileName : listFileName) {
		                                	%>
		                                    <tr>
		                                        <td><%=stt++ %></td>
		                                        <td class="center">
													<img width="200px" height="140px" src="<%=request.getContextPath() %>/uploads/images/<%=fileName %>" />
		                                        </td>
		                                        <td class="center">
		                                            <a href="<%=request.getContextPath() %>/admin/book/picture/delete?id=<%=objBook.getId() %>&filename=<%=fileName %>" onclick="return confirm('Bạn có chắc muốn xoá hình thứ <%=stt - 1 %> không?')" title="" class="btn btn-primary"><i class="fa fa-trash-alt "></i> Xoá</a>
		                                        </td>
		                                    </tr>
											<%
		                                		}
											%>
		                                </tbody>
		                            </table>
		                            <a href="<%=request.getContextPath() %>/admin/book" title="" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Quay lại</a>
	                            </div>
	                             <%
                                    }
                                    	}
	                            %>
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