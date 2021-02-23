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
                        <h2>Book Shop</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath() %>/">Trang chủ</a>
                            <span>Error</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <div style="text-align: center; margin: 30px 0px">
		<%
			if (request.getParameter("err") != null) {
				int err = Integer.parseInt(request.getParameter("err"));
				if (err == 1) {
					out.print("<h2>Lỗi đường dẫn!</h2>");
				} else if (err == 2) {
					out.print("<h2>ID không tồn tại!</h2>");
				} else if (err == 3) {
					out.print("<h2>Số trang không tồn tại!</h2>");
				}
			}
		%>
	</div>
    
<%@ include file="/templates/public/inc/footer.jsp" %>