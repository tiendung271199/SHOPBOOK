<%@page import="utils.GetObjUtil"%>
<%@page import="models.Book"%>
<%@page import="models.SaleOff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa sách sale</h2>
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
                                <form action="<%=request.getContextPath() %>/admin/sale/edit" role="form" method="post" id="form">
                                	<%
                                		if (request.getAttribute("err") != null) {
                                	%>
                                	<div class="alert alert-danger" role="alert">
                                		<span style="color: red; font-style: italic">${err}</span>
                                	</div>
                                	<%
                                		}
                                		if (request.getAttribute("objSale") != null) {
                                			SaleOff objSale = (SaleOff) request.getAttribute("objSale");
                                			Book objBook = GetObjUtil.getBook(objSale.getIdBook());
                                	%>
                                	<div class="form-group">
                                        <label for="idSale">ID</label>
                                        <input type="text" id="idSale" value="<%=objSale.getId() %>" name="idSale" class="form-control" readonly="readonly" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên sách</label>
                                        <input type="text" id="name" value="<%=objBook.getName() %>" name="name" class="form-control" readonly="readonly" />
                                    </div>
                                    <div class="form-group">
                                        <label for="sale">Phần trăm sale</label>
                                        <input type="text" id="sale" value="<%=objSale.getSale() %>" name="sale" class="form-control" placeholder="%" />
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
    document.getElementById("sale").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>