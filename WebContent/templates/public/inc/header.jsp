<%@page import="utils.StringUtil"%>
<%@page import="utils.GetObjUtil"%>
<%@page import="models.Cart"%>
<%@page import="models.User"%>
<%@page import="models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shop Book | Những cuốn sách hay</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/style.css" type="text/css">
    
    <script src="<%=request.getContextPath()%>/templates/public/js/jquery-3.3.1.min.js"></script>
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
    
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> shopbook123@gmail.com</li>
                                <li>Miễn phí ship với các đơn hàng trên 300000 VNĐ</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="https://www.facebook.com/boyit38"><i class="fa fa-facebook"></i></a>
                                <a href="https://www.instagram.com/tiendung.boy38"><i class="fa fa-instagram"></i></a>
                            </div>
                            <div class="header__top__right__language">
                                <img id="imglang" src="<%=request.getContextPath() %>/templates/public/img/language.png" alt="">
                                <div>VietNam</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <li><a href="#">VietNam</a></li>
                                    <li><a href="#">English</a></li>
                                </ul>
                            </div>
                            <div class="header__top__right__auth">
                            	<%
                            		User userLogin2 = null;
                            		String kCart = "cart";
                            		int logId = 0;
                            		if (session.getAttribute("userLogin") != null) {
                            			userLogin2 = (User) session.getAttribute("userLogin");
                            			kCart += userLogin2.getId();
                            			logId = userLogin2.getId();
                            	%>
                            	<a href="<%=request.getContextPath() %>/logout"><i class="fa fa-user"></i> Đăng xuất</a>
                            	<%
                            		} else {
                            	%>
                                <a href="<%=request.getContextPath() %>/login"><i class="fa fa-user"></i> Đăng nhập</a>
                                <%
                            		}
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="<%=request.getContextPath() %>/"><img src="<%=request.getContextPath() %>/templates/public/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                        	<%
                        		if (request.getAttribute("activePage") != null) {
                        			int activePage = (int) request.getAttribute("activePage");
                        	%>
                            <li <% if (activePage == 1) { %> class="active" <% } %>><a href="<%=request.getContextPath() %>/">Trang chủ</a></li>
                            <li <% if (activePage == 2) { %> class="active" <% } %>><a href="<%=request.getContextPath() %>/shop">Cửa hàng</a></li>
                            <!--
                            <li><a href="javascript:void(0)">Trang</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="">Shop Details</a></li>
                                    <li><a href="">Shoping Cart</a></li>
                                    <li><a href="">Check Out</a></li>
                                </ul>
                            </li>
                            -->
                            <li <% if (activePage == 3) { %> class="active" <% } %>><a href="<%=request.getContextPath() %>/contact">Liên hệ</a></li>
                            <%
                            	if (userLogin2 != null) {
                            %>
                            <li <% if (activePage == 5) { %> class="active" <% } %>><a href="<%=request.getContextPath() %>/order/user">Đơn hàng</a></li>
                            <%
                            	}
                        		}
                            %>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li>
                            	<a href="<%=request.getContextPath() %>/favourite"><i class="fa fa-heart"></i>
                            		<%
                            			if (userLogin2 != null) {
                            				if (request.getAttribute("favouriteUser") != null) {
                            					int favouriteUser = (int) request.getAttribute("favouriteUser");
                            		%>
	                            	<span id="num-favourite"><%=favouriteUser %></span>
	                            	<%
                            				}
                            			}
                            		%>
                            	</a>
                            </li>
                            <li>
                            	<a href="<%=request.getContextPath() %>/cart"><i class="fa fa-shopping-bag"></i>
                            		<%
                            			if (session.getAttribute(kCart) != null) {
                            				Cart objCart2 = (Cart) session.getAttribute(kCart);
                            		%>
                            		<span id="num-cart"><%=objCart2.getNumber() %></span>
                            		<%
                            			} else {
                            		%>
                            		<span id="num-cart">0</span>
                            		<%
                            			}
                            		%>
                            	</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>Danh mục sách</span>
                        </div>
                        <ul>
                        	<%
                        		if (request.getAttribute("listCat") != null) {
                        			ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
                        			if (listCat.size() > 0) {
                        				for (Category objCat : listCat) {
                        					if (objCat.getParentId() == 0) {
                        						String urlCat = request.getContextPath() + "/danh-muc-san-pham/" + StringUtil.makeSlug(objCat.getName()) + "-" + objCat.getId();
                        	%>
                            <li><a href="<%=urlCat %>"><%=objCat.getName() %></a></li>
                            <%
                        					}
                        				}
                        			}
                        		}
                            %>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="<%=request.getContextPath() %>/search" method="get">
                                <input type="text" name="sname" value="${sname}" placeholder="Tìm kiếm">
                                <button type="submit" class="site-btn">Tìm</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+84 905.555.888</h5>
                                <span>Hỗ trợ 24/7</span>
                            </div>
                        </div>
                    </div>