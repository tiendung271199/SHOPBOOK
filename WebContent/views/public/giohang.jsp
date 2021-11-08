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
    <section class="breadcrumb-section set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/banner6.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Giỏ hàng</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath() %>/">Trang chủ</a>
                            <span>Giỏ hàng</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
    	<%
            int thanhTien = 0;
            if (session.getAttribute(kCart) != null) {
            	Cart objCart = (Cart) session.getAttribute(kCart);
                ArrayList<CartDetail> listCartDetail = objCart.getList();
                if (listCartDetail.size() > 0) {
        %>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="shoping__product">Tên sách</th>
                                    <th>Giảm giá</th>
                                    <th>Giá bán</th>
                                    <th>Số lượng</th>
                                    <th>Tổng tiền</th>
                                    <th>Del</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            		for (CartDetail cartDetail : listCartDetail) {
                            			Book bookCart = GetObjUtil.getBook(cartDetail.getIdBook());
                            			int sale = 0;
                            			SaleOff saleCart = GetObjUtil.getSale(bookCart.getId());
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
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                        <h5><a class="a-cart" href="<%=urlDetail %>"><%=bookCart.getName() %></a></h5>
                                    </td>
                                    <%
                                    	if (sale != 0) {
                                    %>
                                    <td class="shoping__cart__total">
                                        <%=sale %> %
                                    </td>
                                    <%
                                    	} else {
                                    %>
                                    <td class="shoping__cart__total">Không</td>
                                    <%
                                    	}
                                    %>
                                    <td class="shoping__cart__price">
                                        <%=giaBan %> VNĐ <% if (sale != 0) { %><span style="text-decoration: line-through; color: gray"><%=bookCart.getPrice() %> VNĐ</span><% } %>
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <a style="padding: 3px 10px; background-color: #CCC" class="a-cart" onclick="incCart(<%=cartDetail.getIdBook() %>,<%=bookCart.getNumber() %>)" href="javascript:void(0)">+</a>
										<input style="width: 50px; text-align: center; margin: 0px 5px" type="text" id="numDetail<%=cartDetail.getIdBook() %>" name="soluong" value="<%=cartDetail.getNumber() %>" readonly="readonly"/>
										<a style="padding: 3px 10px; background-color: #CCC" class="a-cart" onclick="decCart(<%=cartDetail.getIdBook() %>)" href="javascript:void(0)">-</a>
                                    </td>
                                    <td class="shoping__cart__total">
                                        <%=tongTien %> VNĐ
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <span onclick="delCart(<%=cartDetail.getIdBook() %>)" class="icon_close"></span>
                                    </td>
                                </tr>
                                <%
                            		}
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="<%=request.getContextPath() %>/cat" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>
                    </div>
                </div>
                <div class="col-lg-6">
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Thành tiền</h5>
                        <ul>
                            <li>Tổng <span><%=thanhTien %> VNĐ</span></li>
                        </ul>
                        <a href="<%=request.getContextPath() %>/checkout" class="primary-btn">TIẾN HÀNH KIỂM TRA ĐẶT HÀNG</a>
                    </div>
                </div>
            </div>
        </div>
        <%
                } else {
        %>
        <div style="text-align: center" class="col-lg-12">
       		<img style="width: 190px" src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="empty__img">
       		<p class="empty__note">Không có sản phẩm nào trong giỏ hàng của bạn</p>
            <div class="shoping__cart__btns">
                <a href="<%=request.getContextPath() %>/shop" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div style="text-align: center" class="col-lg-12">
       		<img style="width: 190px" src="https://salt.tikicdn.com/desktop/img/mascot@2x.png" alt="" class="empty__img">
       		<p class="empty__note">Không có sản phẩm nào trong giỏ hàng của bạn</p>
            <div class="shoping__cart__btns">
                <a href="<%=request.getContextPath() %>/shop" class="primary-btn cart-btn">TIẾP TỤC MUA SẮM</a>
            </div>
        </div>
        <%
            }
        %>
    </section>
    <!-- Shoping Cart Section End -->
<script type="text/javascript">
	function incCart(bId,total) {
		var num = $("#numDetail"+bId).val();
		if (num == total) {
			alert("Số lượng tối đa là " + total);
			return;
		}
		$.ajax({
			url: '<%=request.getContextPath() %>/cart',
			type: 'POST',
			cache: false,
			data: {
				aBIdInc: bId
			},
			success: function(data){
				$("#numDetail"+bId).val(data);
				location.reload();
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	};
	function decCart(bId) {
		var num = $("#numDetail"+bId).val();
		if (num == 1) {
			alert("Số lượng tối thiểu là 1");
			return;
		}
		$.ajax({
			url: '<%=request.getContextPath() %>/cart',
			type: 'POST',
			cache: false,
			data: {
				aBIdDec: bId
			},
			success: function(data){
				$("#numDetail"+bId).val(data);
				location.reload();
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	};
	function delCart(bId) {
		$.ajax({
			url: '<%=request.getContextPath() %>/cart',
			type: 'POST',
			cache: false,
			data: {
				aBIdDel: bId
			},
			success: function(data){
				$("#num-cart").html(data);
				location.reload();
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	};
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>