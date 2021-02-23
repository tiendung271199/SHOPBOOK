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
                        <h2>Liên hệ</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath() %>/">Trang chủ</a>
                            <span>Liên hệ</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_phone"></span>
                        <h4>Số điện thoại</h4>
                        <p>+84 905.555.888</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_pin_alt"></span>
                        <h4>Địa chỉ</h4>
                        <p>120 Nguyễn Lương Bằng - Liên Chiểu - Đà Nẵng</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_clock_alt"></span>
                        <h4>Thời gian mở cửa</h4>
                        <p>8:00 am to 22:00 pm</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_mail_alt"></span>
                        <h4>Email</h4>
                        <p>shopbook123@gmail.com</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Map Begin -->
    <div class="map">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d122648.57988692408!2d108.06621858289346!3d16.1292916252738!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218cee492bec9%3A0x5c15aba31f735c29!2zTGnDqm4gQ2hp4buDdSwgxJDDoCBO4bq1bmcsIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1605168874339!5m2!1svi!2s" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        <div class="map-inside">
            <i class="icon_pin"></i>
            <div class="inside-widget">
                <h4>Liên Chiểu</h4>
                <ul>
                    <li>Phone: +84 905.777.888</li>
                    <li>Add: 120 Nguyễn Lương Bằng</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- Map End -->

    <!-- Contact Form Begin -->
    <div class="contact-form spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="contact__form__title">
                        <h2>Liên hệ</h2>
                    </div>
                </div>
            </div>
            <form action="<%=request.getContextPath() %>/contact" method="post">
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
	            %>
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <input type="text" name="name" value="${name}" placeholder="Nhập tên">
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <input type="text" name="email" value="${email}" placeholder="Nhập email">
                    </div>
                    <div class="col-lg-12 text-center">
                        <textarea name="message" placeholder="Nhập nội dung">${message}</textarea>
                        <button type="submit" class="site-btn">GỬI LIÊN HỆ</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Contact Form End -->

<%@ include file="/templates/public/inc/footer.jsp" %>