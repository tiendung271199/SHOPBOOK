<%@page import="utils.CheckoutUtil"%>
<%@page import="constants.GlobalConstant"%>
<%@page import="models.SaleOff"%>
<%@page import="utils.StringUtil"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="models.Book"%>
<%@page import="models.CartDetail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="<%=request.getContextPath()%>/templates/public/img/banner6.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Thủ tục thanh toán</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath()%>/">Trang chủ</a>
                            <span>Checkout</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
            	<%
            		if (request.getParameter("msg") != null) {
            	%>
            	<div class="alert alert-success" role="alert">
					<span style="color: blue; font-style: italic">Chúc mừng bạn đã đặt hàng thành công!</span>
				</div>
				<%
					}
				%>
                <h4>Chi tiết thanh toán</h4>
                <%
                	if (request.getAttribute("errOrder") != null) {
                %>
                <div class="alert alert-danger" role="alert">
                     <span style="color: red; font-style: italic">${errOrder}</span>
                </div>
                <%
                	}
                                  	int thanhTien = 0;
                                  	if (session.getAttribute(kCart) != null) {
                                  		Cart objCart = (Cart) session.getAttribute(kCart);
                                  		ArrayList<CartDetail> listCartDetail = objCart.getList();
                                  		if (listCartDetail.size() > 0) {
                %>
                <form action="<%=request.getContextPath()%>/checkout" method="post">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <div class="shoping__cart__table">
		                        <table>
		                            <thead>
		                                <tr>
		                                    <th class="shoping__product">Tên sách</th>
		                                    <th>Giảm giá</th>
		                                    <th>Giá bán</th>
		                                    <th>Số lượng</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            	<%
		                            		for (CartDetail cartDetail : listCartDetail) {
		                            			Book bookCart = GetObjUtil.getBook(cartDetail.getIdBook());
		                            			SaleOff saleCart = GetObjUtil.getSale(bookCart.getId());
		                            			int sale = 0;
		                            			if (saleCart != null) {
		                            				sale = saleCart.getSale();
		                            			}
		                            			String picture = StringUtil.fileName(bookCart.getPicture());
		                            			int giaBan = (bookCart.getPrice() * (100 - sale)) / 100;
		                            			int tongTien = cartDetail.getNumber() * giaBan;
		                            			thanhTien += tongTien;
		                            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookCart.getName()) + "-" + bookCart.getId();
		                            	%>
		                                <tr>
		                                    <td class="shoping__cart__item">
		                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath()%>/uploads/images/<%=picture%>" alt="">
		                                        <h5><a class="a-cart" href="<%=urlDetail%>"><%=bookCart.getName()%></a></h5>
		                                    </td>
		                                    <%
		                                    	if (sale != 0) {
		                                    %>
		                                    <td class="shoping__cart__total">
		                                    	<%=sale%> %
		                                    </td>
		                                    <%
		                                    	} else {
		                                    %>
		                                    <td class="shoping__cart__total">Không</td>
		                                    <%
		                                    	}
		                                    %>
		                                    <td class="shoping__cart__price">
		                                        <%=giaBan%> VNĐ <%
		                                        	if (sale != 0) {
		                                        %><span style="text-decoration: line-through; color: gray"><%=bookCart.getPrice()%> VNĐ</span><%
		                                        	}
		                                        %>
		                                    </td>
		                                    <td class="shoping__cart__quantity">
												<input style="width: 50px; text-align: center" type="text" name="soluong" value="<%=cartDetail.getNumber()%>" readonly="readonly" />
		                                    </td>
		                                </tr>
		                                <%
		                                	}
		                                %>
		                            </tbody>
		                        </table>
		                    </div>
		                    <div class="checkout__input">
                                <p>Ghi chú<span>*</span></p>
                                <input type="text" name="ghichu" placeholder="Nhập ghi chú đơn hàng" required="required">
                            </div>
                            <%
                            	if (userLogin2 == null) {
                            %>
                            <a style="padding: 5px 10px; background-color: #CCC" href="<%=request.getContextPath()%>/register?checkout=1">Tạo tài khoản</a>
		                    <h4 style="margin-top: 20px">Nếu chưa có tài khoản vui lòng điền đủ thông tin dưới đây</h4>
		                    <div class="checkout__input">
                                <p>Tên<span>*</span></p>
                                <input type="text" name="name" placeholder="Nhập tên" required="required">
                            </div>
                            <div class="checkout__input">
                                <p>Số điện thoại<span>*</span></p>
                                <input type="text" name="phone" placeholder="Nhập số điện thoại" required="required">
                            </div>
                            <div class="checkout__input">
                                <p>Email<span>*</span></p>
                                <input type="text" name="email" placeholder="Nhập email" required="required">
                            </div>
                            <div class="checkout__input">
                                <p>Địa chỉ<span>*</span></p>
                                <input type="text" name="address" placeholder="Nhập địa chỉ" required="required">
                            </div>
                            <%
                            	}
                            %>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4>Đơn hàng</h4>
                                <div class="checkout__order__products">Sách <span>Tổng tiền</span></div>
                                <ul>
                                	<%
                                		for (CartDetail cartDetail2 : listCartDetail) {
	                            			Book bookCart2 = GetObjUtil.getBook(cartDetail2.getIdBook());
	                            			SaleOff saleCart2 = GetObjUtil.getSale(bookCart2.getId());
	                            			int sale2 = 0;
	                            			if (saleCart2 != null) {
	                            				sale2 = saleCart2.getSale();
	                            			}
	                            			int giaBan2 = (bookCart2.getPrice() * (100 - sale2)) / 100;
                                	%>
                                    <li><%=bookCart2.getName()%> <span><%=giaBan2 * cartDetail2.getNumber()%></span></li>
                                    <%
                                    	}
                                    %>
                                </ul>
                                <%
                                	int ship = 0;
   	                                if (thanhTien < 300000) {
   	                        			ship = GlobalConstant.SHIP;
   	                        			thanhTien += ship;
   	                        		}
                                %>
                                <div class="checkout__order__products">Phí ship <span><%=ship%> VNĐ</span></div>
                                <div class="checkout__order__total">Thành tiền <span><%=thanhTien%> VNĐ</span></div>
                                <input style="display: none" type="text" id="thanh-tien" value="<%=CheckoutUtil.usd(thanhTien)%>" />
                                <div id="paypal-button-container"></div>
                                <div style="background-color: yellow; border-radius: 10px; width: 150px; padding: 0px 10px">
                                	<a href="<%=request.getContextPath() %>/vnpay">Thanh toán Vnpay</a>
                                </div>
                                <button type="submit" name="submit" class="site-btn">ĐẶT HÀNG</button>
                            </div>
                        </div>
                    </div>
                </form>
                <%
                  		}
                  	}
                %>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
<script type="text/javascript">
	var x = $("#thanh-tien").val();
	paypal.Button.render({
		
		env: 'sandbox',
		
		client: {
			sandbox: 'AfdZOvZXqJ0aGfN6py9bjV_LZlKBWjIIxzIfQ3fuEgwwaRq-Dp7ALhGZw_b3Pj_O_sgMdLvkWhmK7apm',
			production: '<insert production client id>'
		},
		
		commit: true,
		
		payment: function(data, actions) {
			return actions.payment.create({
				payment: {
					transactions: [
						{
							amount: {total: x, currency: 'USD'}
						}
					]
				}
			});
		},
		
		onAuthorize: function(data, actions) {
			return actions.payment.execute().then(function() {
				window.alert('Payment Complete!');
			});
		}
    }, '#paypal-button-container');
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>