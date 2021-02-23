<%@page import="utils.CheckUtil"%>
<%@page import="utils.StringUtil"%>
<%@page import="constants.GlobalConstant"%>
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
                	<%
                		if (request.getAttribute("listSale") != null) {
                			ArrayList<SaleOff> listSale = (ArrayList<SaleOff>) request.getAttribute("listSale");
                			if (listSale.size() > 0) {
                	%>
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Giảm giá</h2>
                        </div>
                        <div class="row">
                            <div class="product__discount__slider owl-carousel">
                                <%
                                	for (SaleOff objSaleOff : listSale) {
                                		Book bookSale = GetObjUtil.getBook(objSaleOff.getIdBook());
                                		int sale = (bookSale.getPrice() * (100 - objSaleOff.getSale())) / 100;
                                		String picture = StringUtil.fileName(bookSale.getPicture());
                                		String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookSale.getName()) + "-" + bookSale.getId();
                                %>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="<%=request.getContextPath() %>/uploads/images/<%=picture %>">
                                            <div class="product__discount__percent">-<%=objSaleOff.getSale() %>%</div>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Shop Book</span>
                                            <h5><a href="<%=urlDetail %>"><%=bookSale.getName() %></a></h5>
                                            <div class="product__item__price"><%=sale %> VNĐ <span><%=bookSale.getPrice() %> VNĐ</span></div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                	}
                                %>
                            </div>
                        </div>
                    </div>
                    <%
                			}
                		}
                    %>
                    <div class="filter__item">
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort">
                                    <span>Sắp xếp theo</span>
                                    <select>
                                   	 	<option value="0">Mặc định</option>
                                        <option value="0">Giá bán</option>
                                        <option value="0">Lượt mua</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found">
                                    <h6><span>${total}</span> sản phẩm</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <span class="icon_grid-2x2"></span>
                                    <span class="icon_ul"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="result-search">
                    <div class="row">
                    	<%
                    		int check = 0;
                    		int numberPage = GlobalConstant.NUMBER_PAGE3;
	                    	int total = 0, pages = 1, totalPages = 1;
	            			if (request.getAttribute("pages") != null) {
	            		        pages = (int) request.getAttribute("pages");
	            		    }
	            			if (request.getAttribute("total") != null) {
	            		        total = (int) request.getAttribute("total");
	            		    }
	            			if (request.getAttribute("totalPages") != null) {
	            				totalPages = (int) request.getAttribute("totalPages");
	            		    }
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
                                        <%
		                            		if (userLogin2 != null) {
		                            	%>
		                                <li><a onclick="like(<%=objBook.getId() %>)" href="javascript:void(0)" id="statuslike<%=objBook.getId() %>"><i <% if (CheckUtil.checkFavouriteStatus(objBook.getId(), userLogin2.getId())) { %> style="color: red" <% } %> class="fa fa-heart"></i></a></li>
		                                <%
		                            		} else {
		                                %>
		                                <li><a href="<%=request.getContextPath() %>/login"><i class="fa fa-heart"></i></a></li>
		                                <%
		                            		}
		                                %>
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
                    				check = 1;
                    				out.print("Không có sản phẩm nào!");
                    			}
	                    	}                    				
                        %>
                    </div>
                    <%
                    	if (check == 0) {
                    %>
                    <div class="product__pagination">
                    	<%
							if (pages > 1) {
								int back = pages - 1;
								String urlPage = request.getContextPath() + "/shop/" + back;
					    %>
					    <a href="<%=urlPage %>"><i class="fa fa-long-arrow-left"></i></a>
					    <%
							}
	                        for (int j = 1; j <= totalPages; j++) {
	                        	String urlPage = request.getContextPath() + "/shop/" + j;
	                    		if (pages == j) {
					    %>
                        <a class="active" href="<%=urlPage %>"><%=j %></a>
                        <%
	                    		} else {
	                    %>
	                    <a href="<%=urlPage %>"><%=j %></a>
	                    <%
	                    		}
	                        }
	                        if (pages < totalPages) {
	                        	int next = pages + 1;
	                        	String urlPage = request.getContextPath() + "/shop/" + next;
                        %>
                        <a href="<%=urlPage %>"><i class="fa fa-long-arrow-right"></i></a>
                        <%
	                        }
                        %>
                    </div>
                    <%
                    	}
                    %>
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
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>