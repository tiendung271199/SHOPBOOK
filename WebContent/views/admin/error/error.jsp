<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                        	<%
                        		if (request.getParameter("err") != null) {
                        			int err = Integer.parseInt(request.getParameter("err"));
                        			if (err == 1) {
                        	%>
							<div class="alert alert-danger" role="alert">
							  	<span style="color: red; font-style: italic">ERROR 404 - Không tìm thấy trang!</span>
							</div>
							<%
                        			} else if (err == 2) {
							%>
							<div class="alert alert-danger" role="alert">
							  	<span style="color: red; font-style: italic">ERROR Không có dữ liệu - Số trang không tồn tại!</span>
							</div>
							<%
                        			} else if (err == 3) {
                        	%>
                        	<div class="alert alert-danger" role="alert">
							  	<span style="color: red; font-style: italic">Bạn không có quyền!</span>
							</div>
                        	<%
                        			}
                        		}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/templates/admin/inc/footer.jsp"%>