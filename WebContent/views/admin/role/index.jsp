<%@page import="utils.CheckUtil"%>
<%@page import="models.Modules"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý phân quyền</h2>
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
                        	<form action="<%=request.getContextPath() %>/admin/role/manage" method="post">
                        	<div style="margin-bottom: 20px" class="row">
                                <div class="col-sm-6">
                                	<%
                                		int idUser = 0;
	                                	if (request.getAttribute("idUser") != null) {
	                                		idUser = (int) request.getAttribute("idUser");
	    								}
	                                	ArrayList<User> listUser = new ArrayList<User>();
	    								if (request.getAttribute("listUser") != null) {
	    									listUser = (ArrayList<User>) request.getAttribute("listUser");
	    								}
                                	%>
                                    <label class="btn btn-warning btn-sm" style="float:left">Người dùng</label>
                                   	<select id="mod-role" name="mod" class="btn-sm" style="float:left; margin: 0px 10px" onchange="getRole()" >
                                   		<option value="0">--Chọn mod--</option>
                                   		<%
                                  			if (listUser.size() > 0) {
                                  				for (User user : listUser) {
                                   		%>
                                   		<option value="<%=user.getId() %>" <% if (idUser == user.getId()) { out.print("selected"); } %> ><%=user.getUsername() %></option>
                                   		<%
                                   				}
                                   			}
                                   		%>
                                   	</select>
                                    <div style="clear:both"></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                	<input type="submit" name="submit" value="Cập nhật" class="btn btn-warning btn-sm" style="float:right" />
                                    <div style="clear:both"></div>
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
								if (request.getAttribute("listModules") != null) {
									ArrayList<Modules> listModules = (ArrayList<Modules>) request.getAttribute("listModules");
									if (listModules.size() > 0) {
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên module</th>
                                        <th width="160px">Cho phép</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		for (Modules objModules : listModules) {
                                	%>
                                    <tr>
                                        <td><%=objModules.getId() %></td>
                                        <td class="center"><%=objModules.getName() %></td>
                                        <td class="center">
                                        	<input type="checkbox" name="role<%=objModules.getId() %>" value="<%=objModules.getId() %>" <% if (CheckUtil.checkRole(idUser, objModules.getId())) { out.print("checked"); } %> />
                                        </td>
                                    </tr>
                                    <%
                                		}
                                    %>
                                </tbody>
                            </table>
                            <%
									}
								}
                            %>
                            </form>
                        </div>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	function getRole() {
		var id = $("#mod-role").val();
		$.ajax({
			url: '<%=request.getContextPath() %>/admin/role',
			type: 'POST',
			cache: false,
			data: {
				aId: id
			},
			success: function(data){
				$("body").html(data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script>
    document.getElementById("role").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>