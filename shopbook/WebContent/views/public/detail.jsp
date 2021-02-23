<%@page import="models.SaleOff"%>
<%@page import="daos.SaleOffDao"%>
<%@page import="utils.PublicUtil"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="utils.CheckUtil"%>
<%@page import="utils.StringUtil"%>
<%@page import="models.User"%>
<%@page import="models.Reviews"%>
<%@page import="models.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/public/inc/header.jsp" %>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

	<%
		User userLogin = null;
		if (session.getAttribute("userLogin") != null) {
			userLogin = (User) session.getAttribute("userLogin");
		}
	%>

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/banner6.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Book Detail</h2>
                        <div class="breadcrumb__option">
                            <a href="<%=request.getContextPath() %>/">Trang chủ</a>
                            <span>Chi tiết</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
	<%
		if (request.getAttribute("objBook") != null) {
			Book objBook = (Book) request.getAttribute("objBook");
			ArrayList<String> listPicture = StringUtil.getFileName(objBook.getPicture());
	%>
    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img id="main-img-id" class="product__details__pic__item--large" src="<%=request.getContextPath() %>/uploads/images/<%=listPicture.get(0) %>" alt="">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                        	<%
                        		if (listPicture.size() > 1) {
                        			int pic = 1;
                        			for (String picture : listPicture) {
                        	%>
                            <img onclick="pictureBook(<%=pic %>)" id="picture<%=pic %>" style="height: 160px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                            <%
                            			pic++;
                        			}
                        		}
                            %>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3><%=objBook.getName() %></h3>
                        <h5 style="margin-bottom: 10px">Tác giả: <%=objBook.getAuthor() %></h5>
                        <h5 style="margin-bottom: 20px">Thể loại: <%=objBook.getCat().getName() %></h5>
                        <%
                        	ArrayList<Reviews> listReviewsBook = GetObjUtil.getReviews(objBook.getId());
                        	double numStar = PublicUtil.starTB(listReviewsBook);
                        %>
                        <div class="product__details__rating">
                        	<!-- 
                        	<i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-half-o"></i>
                        	-->
                            <span><%=numStar %> <i class="fa fa-star"></i> (${reviewsCount} reviews) - Lượt mua: <%=objBook.getPurchases() %></span>
                        </div>
                        <%
                        	SaleOffDao objSaleDao = new SaleOffDao();
                        	if (objSaleDao.checkSale(objBook.getId())) {
                        		SaleOff objSale = objSaleDao.getSale(objBook.getId());
                        		int giaSale = (objBook.getPrice() * (100 - objSale.getSale())) / 100;
                        %>
                        <div class="product__details__price"><%=giaSale %> VNĐ (-<%=objSale.getSale() %>%) <span style="text-decoration: line-through; color: grey; margin-left: 20px"><%=objBook.getPrice() %> VNĐ</span></div>
                        <%
                        	} else {
                        %>
                        <div class="product__details__price"><%=objBook.getPrice() %> VNĐ</div>
                        <%
                        	}
                        %>
                        <p><%=objBook.getDescription() %></p>
                        <div class="product__details__quantity">
                            <div class="quantity">
                                <div class="pro-qty">
                                    <input id="number-book-cart" type="text" value="1">
                                </div>
                            </div>
                        </div>
                        <a onclick="addToCart(<%=objBook.getId() %>)" href="javascript:void(0)" class="primary-btn">THÊM VÀO GIỎ HÀNG</a>
                        <%
                        	if (userLogin != null) {
                        %>
                        <a onclick="likeDetail(<%=objBook.getId() %>)" href="javascript:void(0)" class="heart-icon" id="like-status-<%=objBook.getId() %>" ><span <% if (CheckUtil.checkFavouriteStatus(objBook.getId(), userLogin.getId())) { %> style="color: red" <% } %> class="icon_heart_alt"></span><span style="margin-left: 5px">${favouritesCount}</span></a>
                        <%
                        	} else {
                        %>
                        <a href="<%=request.getContextPath() %>/login" class="heart-icon"><span class="icon_heart_alt"></span></a>
                        <%
                        	}
                        %>
                        <ul>
                            <li><b>Số lượng</b> <span>Còn hàng (<%=objBook.getNumber() %> còn lại)</span></li>
                            <li><b>Thời gian</b> <span>Giao hàng từ 3 - 5 ngày </span></li>
                            <li><b>Miễn phí giao hàng</b> <span>Áp dụng với các đơn hàng trên 300000 VNĐ </span></li>
                        </ul>
                    </div>
                </div>
                <%
                	ArrayList<Reviews> listReviews = new ArrayList<Reviews>();
                	if (request.getAttribute("listReviews") != null) {
                		listReviews = (ArrayList<Reviews>) request.getAttribute("listReviews");
                	}
                %>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                                    aria-selected="true">Chi tiết</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab"
                                    aria-selected="false">Đánh giá <span>(<%=listReviews.size() %>)</span></a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <h6>Chi tiết</h6>
                                    <p><%=objBook.getDetail() %></p>
                                </div>
                            </div>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <div class="product__details__tab__desc">
                                	<%
                                		if (userLogin != null) {
                                	%>
                                    <h6>Đánh giá</h6>
                                    <div id="form-danhgia" style="border-bottom: 1px solid #CCC; margin-bottom: 10px">
                                    	<form>
                                    		<p>
                                    			<label style="width: 120px; float: left">Số sao: </label>
                                    			<input type="text" id="star-reviews" /> <i class="fa fa-star"></i>
                                    		</p>
                                    		<p>
                                    			<label style="width: 120px; float: left">Nội dung đánh giá: </label>
                                    			<textarea id="comment-reviews" rows="5" cols="60"></textarea>
                                    		</p>
                                    		<p>
                                    			<a style="padding: 5px 10px; background-color: #CCC" href="javascript:void(0)" onclick="reviews(<%=objBook.getId() %>)">Đánh giá</a>
                                    		</p>
                                    	</form>
                                    </div>
                                    <%
                                		} else {
                                    %>
                                    <p style="font-style: italic">Vui lòng đăng nhập để đánh giá</p>
                                    <%
                                		}
                                	%>
                                	<div id="list-user-reviews" style="margin-bottom: 10px">
	                                	<%
	                                    	if (listReviews.size() > 0) {
	                                    %>
	                                    <h6>Một số đánh giá</h6>
	                                    <%
	                                    		for (Reviews objReviews : listReviews) {
	                                    			User objUser = GetObjUtil.getUser(objReviews.getIdUser());
	                                    			double star = objReviews.getStar();
	                                    %>
	                                    <div style="border-bottom: 1px solid #CCC; margin-bottom: 10px">
		                                    <p>Tên: <%=objUser.getFullname() %> </p>
		                                    <p>Nhận xét vào <%=StringUtil.tachChuoi(objReviews.getDateCreate()) %></p>
		                                    <p><%=star %> <i class="fa fa-star"></i> - 
		                                    <%
												if (star <= 1.0d) {
													out.print("Rất không hài lòng");
												} else if (star > 1.0d && star <= 2.0d) {
													out.print("Không hài lòng");
												} else if (star > 2.0d && star <= 3.5d) {
													out.print("Bình thường");
												} else if (star > 3.5d && star < 4.5d) {
													out.print("Hài lòng");
												} else {
													out.print("Cực kỳ hài lòng");
												}
		                                    %>
		                                    </p>
		                                    <p>Nội dung đánh giá: <%=objReviews.getComment() %></p>
		                                    <p>
		                                    	<%
		                                    		if (logId == objReviews.getIdUser()) {
		                                    	%>
		                                    	<a onclick="editReviews(<%=objReviews.getId() %>)" style="padding: 5px 10px; background-color: #CCC" href="javascript:void(0)">Sửa</a>
		                                    	<a onclick="deleteReviews(<%=objReviews.getId() %>)" style="padding: 5px 10px; background-color: #CCC; margin-left: 5px" href="javascript:void(0)">Xoá</a>
		                                    	<%
		                                    		}
		                                    	%>
		                                    </p>
	                                    </div>
	                                    <%
	                                    		}
	                                    	} else {
	                                    %>
	                                    <h6>Chưa có đánh giá, hãy là người đánh giá đầu tiên</h6>
	                                    <%
	                                    	}
	                                    %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->
	<%
		if (request.getAttribute("listBookRelated") != null) {
			ArrayList<Book> listBookRelated = (ArrayList<Book>) request.getAttribute("listBookRelated");
			if (listBookRelated.size() > 0) {
	%>
    <!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>Sách liên quan</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            	<%
            		int i = 1;
            		for (Book book : listBookRelated) {
            			if (i > 4) {
            				break;
            			}
            			if (book.getId() == objBook.getId()) {
            				continue;
            			}
            			String picture2 = StringUtil.fileName(book.getPicture());
            			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(book.getName()) + "-" + book.getId();
            	%>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/uploads/images/<%=picture2 %>">
                            <ul class="product__item__pic__hover">
                            	<%
                            		if (userLogin != null) {
                            	%>
                                <li><a onclick="like(<%=book.getId() %>)" href="javascript:void(0)" id="statuslike<%=book.getId() %>" ><i <% if (CheckUtil.checkFavouriteStatus(book.getId(), userLogin.getId())) { %> style="color: red" <% } %> class="fa fa-heart"></i></a></li>
                                <%
                            		} else {
                                %>
                                <li><a href="<%=request.getContextPath() %>/login"><i class="fa fa-heart"></i></a></li>
                                <%
                            		}
                                %>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a onclick="addToCart2(<%=book.getId() %>)" href="javascript:void(0)"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="<%=urlDetail %>"><%=book.getName() %></a></h6>
                            <h5><%=book.getPrice() %> VNĐ</h5>
                        </div>
                    </div>
                </div>
                <%
                		i++;
            		}
                %>
            </div>
        </div>
    </section>
    <%
			}
		}
		}
    %>
    <!-- Related Product Section End -->
<script type="text/javascript">
	function pictureBook(pic) {
		var img = $("#picture"+pic).attr("src");
		$.ajax({
			url: '<%=request.getContextPath() %>/detail',
			type: 'POST',
			cache: false,
			data: {
				aImg: img
			},
			success: function(data){
				$("#main-img-id").attr("src",data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function addToCart(bId) {
		var num = $("#number-book-cart").val();
		$.ajax({
			url: '<%=request.getContextPath() %>/cart/add',
			type: 'POST',
			cache: false,
			data: {
				aBId: bId,
				aNum: num
			},
			success: function(data){
				$("#num-cart").html(data);
				$("#number-book-cart").val("1");
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
	function addToCart2(bId) {
		$.ajax({
			url: '<%=request.getContextPath() %>/cart/add',
			type: 'POST',
			cache: false,
			data: {
				aBId: bId
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
	function likeDetail(bId) {
		$.ajax({
			url: '<%=request.getContextPath() %>/favourite/status',
			type: 'POST',
			cache: false,
			data: {
				aBIdLike: bId
			},
			success: function(data){
				$("#like-status-"+bId).html(data);
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
<script type="text/javascript">
	function reviews(bId) {
		var star = $("#star-reviews").val();
		var cmt = $("#comment-reviews").val();
		$.ajax({
			url: '<%=request.getContextPath() %>/reviews/status',
			type: 'POST',
			cache: false,
			data: {
				aBIdReviews: bId,
				aStar: star,
				aCmt: cmt
			},
			success: function(data){
				$("#list-user-reviews").html(data);
				$("#star-reviews").val("");
				$("#comment-reviews").val("");
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function editReviews(id) {
		$.ajax({
			url: '<%=request.getContextPath() %>/detail',
			type: 'POST',
			cache: false,
			data: {
				aIdEdit: id
			},
			success: function(data){
				$("#form-danhgia").html(data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function xuLyEditReviews(id) {
		var star = $("#star-reviews").val();
		var cmt = $("#comment-reviews").val();
		$.ajax({
			url: '<%=request.getContextPath() %>/reviews/status',
			type: 'POST',
			cache: false,
			data: {
				aIdEdit: id,
				aStar: star,
				aCmt: cmt
			},
			success: function(data){
				$("#list-user-reviews").html(data);
				$("#star-reviews").val("");
				$("#comment-reviews").val("");
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function deleteReviews(id) {
		$.ajax({
			url: '<%=request.getContextPath() %>/reviews/status',
			type: 'POST',
			cache: false,
			data: {
				aIdDelete: id
			},
			success: function(data){
				$("#list-user-reviews").html(data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>
<script type="text/javascript">
	function huyEdit() {
		location.reload();
	}
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>