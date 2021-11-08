<%@page import="utils.HighlightsUtil"%>
<%@page import="utils.CheckUtil"%>
<%@page import="utils.StringUtil"%>
<%@page import="models.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
                    <div style="margin-top: 30px" class="hero__item set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/hero/banner.jpg">
                        <div class="hero__text">
                            <span>Sách hay</span>
                            <h2>Sách <br />100% Chất lượng</h2>
                            <p>Nhận và giao hàng nhanh</p>
                            <a href="<%=request.getContextPath() %>/shop" class="primary-btn">Tới cửa hàng</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                	<%
                		if (request.getAttribute("listCat") != null) {
                			ArrayList<Category> listCat2 = (ArrayList<Category>) request.getAttribute("listCat");
                			if (listCat2.size() > 0) {
                				for (Category objCat2 : listCat2) {
                					if (objCat2.getParentId() == 0) {
                						String urlCat = request.getContextPath() + "/danh-muc-san-pham/" + StringUtil.makeSlug(objCat2.getName()) + "-" + objCat2.getId();
                	%>
                    <div class="col-lg-3">
                    	<%
                    		if (objCat2.getId() % 2 == 0) {
                    	%>
                        <div class="categories__item set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/sach.jpg">
                        <%
                    		} else {
                        %>
                        <div class="categories__item set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/sach2.jpg">
                        <%
                    		}
                        %>
                            <h5><a href="<%=urlCat %>"><%=objCat2.getName() %></a></h5>
                        </div>
                    </div>
                    <%
                					}
                				}
                			}
                		}
                    %>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Sách nổi bật</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">Tất cả</li>
                            <li data-filter=".muanhieunhat">Mua nhiều nhất</li>
                            <li data-filter=".moinhat">Mới nhất</li>
                            <li data-filter=".danhgiacaonhat">Đánh giá cao nhất</li>
                            <li data-filter=".yeuthichnhieunhat">Yêu thích nhiều nhất</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
            	<%
            		if (request.getAttribute("listHighlights") != null) {
            			ArrayList<HighlightsUtil> listHighlights = (ArrayList<HighlightsUtil>) request.getAttribute("listHighlights");
            			if (listHighlights.size() > 0) {
            				for (HighlightsUtil highLight : listHighlights) {
            					Book bookHighLight = GetObjUtil.getBook(highLight.getIdBook());
            					String picture = StringUtil.fileName(bookHighLight.getPicture());
            					int bMoi = highLight.getMoi();
            					int bMua = highLight.getMua();
            					int bDanhGia = highLight.getDanhGia();
            					int bYeuThich = highLight.getYeuThich();
            					String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookHighLight.getName()) + "-" + bookHighLight.getId();
            	%>
                <div class="col-lg-3 col-md-4 col-sm-6 mix <% if (bMoi == 1) { out.print("moinhat "); } if (bMua == 1) { out.print("muanhieunhat "); } if (bDanhGia == 1) { out.print("danhgiacaonhat "); } if (bYeuThich == 1) { out.print("yeuthichnhieunhat"); } %>">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/uploads/images/<%=picture %>">
                            <ul class="featured__item__pic__hover">
                               	<%
                            		if (userLogin2 != null) {
                            	%>
                                <li><a onclick="like(<%=bookHighLight.getId() %>)" href="javascript:void(0)" id="statuslike<%=bookHighLight.getId() %>"><i <% if (CheckUtil.checkFavouriteStatus(bookHighLight.getId(), userLogin2.getId())) { %> style="color: red" <% } %> class="fa fa-heart"></i></a></li>
                                <%
                            		} else {
                                %>
                                <li><a href="<%=request.getContextPath() %>/login"><i class="fa fa-heart"></i></a></li>
                                <%
                            		}
                                %>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a onclick="addToCart(<%=bookHighLight.getId() %>)" href="javascript:void(0)"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="<%=urlDetail %>"><%=bookHighLight.getName() %></a></h6>
                            <h5><%=bookHighLight.getPrice() %> VNĐ</h5>
                        </div>
                    </div>
                </div>
                <%
            				}
            			}
            		}
                %>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img style="width: 550px; height: 262px" src="<%=request.getContextPath() %>/templates/public/img/banner/banner101.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img style="width: 550px; height: 262px" src="<%=request.getContextPath() %>/templates/public/img/banner/banner102.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Banner End -->

    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Sách mới nhất</h4>
                        <%
                        	if (request.getAttribute("listBookNew") != null) {
                        		ArrayList<Book> listBookNew = (ArrayList<Book>) request.getAttribute("listBookNew");
                        		if (listBookNew.size() > 0) {
                        %>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            	<%
                            		int i = 1;
                            		for (Book objBook : listBookNew) {
                            			if (i > 3) {
                            				break;
                            			}
                            			String picture = StringUtil.fileName(objBook.getPicture());
                            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(objBook.getName()) + "-" + objBook.getId();
                            	%>
                                <a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=objBook.getName() %></h6>
                                        <span><%=objBook.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                                		i++;
                            		}
                                %>
                            </div>
                            <div class="latest-prdouct__slider__item">
                            	<%
                            		int j = 1;
                            		for (Book objBook2 : listBookNew) {
                            			if (j <= 3) {
                            				j++;
                            				continue;
                            			}
                            			String picture = StringUtil.fileName(objBook2.getPicture());
                            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(objBook2.getName()) + "-" + objBook2.getId();
                            	%>
                                <a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=objBook2.getName() %></h6>
                                        <span><%=objBook2.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                            		}
                                %>
                            </div>
                        </div>
                        <%
                        		}
                        	}
                        %>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Đánh giá cao nhất</h4>
                        <%
	                        if (request.getAttribute("listBookReviews") != null) {
	                    		ArrayList<Book> listBookReviews = (ArrayList<Book>) request.getAttribute("listBookReviews");
	                    		if (listBookReviews.size() > 0) {
                        %>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            	<%
		                        	int kRev = 1;
		                    		for (Book bookRev : listBookReviews) {
		                    			if (kRev > 3) {
		                    				break;
		                    			}
		                    			String picture = StringUtil.fileName(bookRev.getPicture());
		                    			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookRev.getName()) + "-" + bookRev.getId();
	                        	%>
                               	<a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=bookRev.getName() %></h6>
                                        <span><%=bookRev.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                                		kRev++;
		                    		}
                                %>
                            </div>
                            <div class="latest-prdouct__slider__item">
                                <%
                            		int kRev2 = 1;
                            		for (Book bookRev2 : listBookReviews) {
                            			if (kRev2 <= 3) {
                            				kRev2++;
                            				continue;
                            			}
                            			String picture = StringUtil.fileName(bookRev2.getPicture());
                            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookRev2.getName()) + "-" + bookRev2.getId();
                            	%>
                                <a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=bookRev2.getName() %></h6>
                                        <span><%=bookRev2.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                            		}
                                %>
                            </div>
                        </div>
                        <%
	                    		}
	                        }
                        %>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Yêu thích nhiều nhất</h4>
                        <%
	                        if (request.getAttribute("listBookFavourite") != null) {
	                    		ArrayList<Book> listBookFavourite = (ArrayList<Book>) request.getAttribute("listBookFavourite");
	                    		if (listBookFavourite.size() > 0) {
                        %>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            	<%
                            		int kFav = 1;
                            		for (Book bookFav : listBookFavourite) {
                            			if (kFav > 3) {
                            				break;
                            			}
                            			String picture = StringUtil.fileName(bookFav.getPicture());
                            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookFav.getName()) + "-" + bookFav.getId();
                            	%>
                               	<a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=bookFav.getName() %></h6>
                                        <span><%=bookFav.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                                		kFav++;
                            		}
                                %>
                            </div>
                            <div class="latest-prdouct__slider__item">
                                <%
                                	int kFav2 = 1;
                                	for (Book bookFav2 : listBookFavourite) {
                                		if (kFav2 < 4) {
                                			kFav2++;
                                			continue;
                                		}
                                		String picture = StringUtil.fileName(bookFav2.getPicture());
                                		String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(bookFav2.getName()) + "-" + bookFav2.getId();
                                		
                                %>
                                <a href="<%=urlDetail %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=bookFav2.getName() %></h6>
                                        <span><%=bookFav2.getPrice() %> VNĐ</span>
                                    </div>
                                </a>
                                <%
                                	}
                                %>
                            </div>
                        </div>
                        <%
	                    		}
	                        }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Latest Product Section End -->

    <!-- Blog Section Begin -->
    <section class="from-blog spad"></section>
    <!-- Blog Section End -->
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