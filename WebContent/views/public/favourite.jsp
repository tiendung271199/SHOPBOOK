<%@page import="utils.StringUtil"%>
<%@page import="utils.CategoryUtil"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="models.SaleOff"%>
<%@page import="models.Book"%>
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
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-5">
                    <%@ include file="/templates/public/inc/sidebar.jsp" %>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Sách yêu thích</h2>
                        </div>
                    </div>
                    <div id="result-search">
                    <div class="row">
                    	<%
	                    	if (request.getAttribute("listBook") != null) {
	                			ArrayList<Book> listBook = (ArrayList<Book>) request.getAttribute("listBook");
                    			if (listBook.size() > 0) {
                    				for (Book objBook : listBook) {
                    					String picture = StringUtil.fileName(objBook.getPicture());
                    					String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(objBook.getName()) + "-" + objBook.getId();
                    	%>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/uploads/images/<%=picture %>">
                                    <ul class="product__item__pic__hover">
                                        <li><a onclick="like(<%=objBook.getId() %>)" href="javascript:void(0)" id="statuslike<%=objBook.getId() %>"><i style="color: red" class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a onclick="addToCart(<%=objBook.getId() %>)" href="javascript:void(0)"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="<%=urlDetail %>"><%=objBook.getName() %></a></h6>
                                    <h5><%=objBook.getPrice() %> VNĐ</h5>
                                </div>
                            </div>
                        </div>
                        <%
                    				}
                    			} else {
                    				out.print("Không có sản phẩm nào!");
                    			}
	                    	}                    				
                        %>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->
<script type="text/javascript">
	function addToCart(bId) {
		$.ajax({
			url: '<%=request.getContextPath() %>/cart/add',
			type: 'POST',
			cache: false,
			data: {
				aBId: bId,
			},
			success: function(data){
				$("#num-cart").html(data);
				alert('Đã thêm vào giỏ hàng');
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function like(bId) {
		$.ajax({
			url: '<%=request.getContextPath() %>/favourite/status',
			type: 'POST',
			cache: false,
			data: {
				aBIdLike: bId,
				aCheck: 1
			},
			success: function(data){
				$("#statuslike"+bId).html(data);
				location.reload();
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>