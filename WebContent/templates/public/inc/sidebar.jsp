<%@page import="models.Book"%>
<%@page import="utils.CategoryUtil"%>
<%@page import="utils.StringUtil"%>
<%@page import="models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Danh mục</h4>
                            <ul>
                            	<%
                            		if (request.getAttribute("listCat") != null) {
                            			ArrayList<Category> listCat2 = (ArrayList<Category>) request.getAttribute("listCat");
                            			if (listCat2.size() > 0) {
                            				for (Category objCat2 : listCat2) {
                            					if (objCat2.getParentId() == 0) {
                            						String urlCat = request.getContextPath() + "/danh-muc-san-pham/" + StringUtil.makeSlug(objCat2.getName()) + "-" + objCat2.getId();
                            	%>
                                <li><a href="<%=urlCat %>"><%=objCat2.getName() %></a>
                                <%
                               						CategoryUtil.showCat(request, out, objCat2.getId(), "|---");
                            					}
                            				}
                            			}
                            		}
                                %>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Giá bán</h4>
                            <div class="price-range-wrap">
                            	<form action="<%=request.getContextPath() %>/search" method="get">
                            		<p>Chọn khoảng giá</p>
	                                <input style="width: 100px; margin: 0px 5px" type="text" name="minprice" placeholder="VNĐ" /> - 
	                                <input style="width: 100px; margin: 0px 5px" type="text" name="maxprice" placeholder="VNĐ" />
	                                <p><input style="width: 50px; margin-top: 8px" type="submit" value="Ok" /></p>
                                </form>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <h4>Giá bán</h4>
                            <div class="price-range-wrap">
                            	<form>
	                                <input id="gia1" type="radio" name="gia" onclick="search()" value="1" /> Tất cả <br />
	                                <input id="gia2" type="radio" name="gia" onclick="search()" value="2" /> Dưới 100000 VNĐ <br />
	                                <input id="gia3" type="radio" name="gia" onclick="search()" value="3" /> Từ 100000 - 200000 VNĐ <br />
	                                <input id="gia4" type="radio" name="gia" onclick="search()" value="4" /> Từ 200000 - 300000 VNĐ <br />
	                                <input id="gia5" type="radio" name="gia" onclick="search()" value="5" /> Trên 300000 VNĐ
                                </form>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <h4>Thể loại nổi bật</h4>
                            <div class="price-range-wrap">
                            	<form>
                            		<%
                            			if (request.getAttribute("listCatNoiBat") != null) {
                            				ArrayList<Category> listCatNoiBat = (ArrayList<Category>) request.getAttribute("listCatNoiBat");
                            				if (listCatNoiBat.size() > 0) {
                            					int sttTheLoai = 1;
                            					for (Category catNoiBat : listCatNoiBat) {
                            		%>
	                                <input id="theloai<%=sttTheLoai++ %>" type="radio" onclick="search()" name="theloai" value="<%=catNoiBat.getId() %>" /> <%=catNoiBat.getName() %> <br />
	                                <%
                            					}
                            				}
                            			}
	                                %>
                                </form>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Sách mới nhất</h4>
                                <%
	                                if (request.getAttribute("listBookNew") != null) {
	                        			ArrayList<Book> listBook2 = (ArrayList<Book>) request.getAttribute("listBookNew");
	                        			if (listBook2.size() > 0) {
                                %>
                                <div class="latest-product__slider owl-carousel">
                                    <div class="latest-prdouct__slider__item">
                                    	<%
                                    		int i = 1;
                                    		for (Book objBook2 : listBook2) {
                                    			if (i > 3) {
                                    				break;
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
                                        		i++;
                                    		}
                                        %>
                                    </div>
                                    <div class="latest-prdouct__slider__item">
                                    	<%
	                                    	int j = 1;
	                                		for (Book objBook3 : listBook2) {
	                                			if (j <= 3) {
	                                				j++;
	                                				continue;
	                                			}
	                                			String picture = StringUtil.fileName(objBook3.getPicture());
	                                			String urlDetail = request.getContextPath() + "/chi-tiet-san-pham/" + StringUtil.makeSlug(objBook3.getName()) + "-" + objBook3.getId();
                                    	%>
                                        <a href="<%=urlDetail %>" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img style="width: 110px; height: 110px" src="<%=request.getContextPath() %>/uploads/images/<%=picture %>" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6><%=objBook3.getName() %></h6>
                                                <span><%=objBook3.getPrice() %> VNĐ</span>
                                            </div>
                                        </a>
                                        <%
                                        		j++;
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
<script type="text/javascript">
	function search() {
		var theloai = ""; // id cat
		var gia = "";
		for (var i = 1; i <= 5; i++) {
			if(document.getElementById('theloai'+i).checked) {
			     theloai = $("#theloai"+i).val();
			}
			if(document.getElementById('gia'+i).checked) {
			     gia = $("#gia"+i).val();
			}
		}
		$.ajax({
			url: '<%=request.getContextPath() %>/search',
			type: 'POST',
			cache: false,
			data: {
				aTheLoai: theloai,
				aGia: gia
			},
			success: function(data){
				$("#result-search").html(data);
			},
			error: function (){
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
</script>