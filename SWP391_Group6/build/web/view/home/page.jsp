<%-- 
    Document   : page
    Created on : Sep 16, 2024, 6:58:05 PM
    Author     : KEISHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LAN Perfume</title>
    <link rel="stylesheet" href="../../css/homePage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    
</head>
<body>

<!-- Navigation Bar -->
<nav class="navbar">
    <div class="logo">Group6 Perume</div>
    <ul class="nav-links">
        <li><a href="#">Trang chủ</a></li>        
        <li><a href="#">Bộ sưu tập nước hoa</a></li>
        <li><a href="#">Thương hiệu</a></li>
        <li><a href="#">Tin tức</a></li>
        <li><a href="#">Liên hệ</a></li>
    </ul>
    <div class="nav-icons">
        <a href="#"><i class="fa-solid fa-magnifying-glass"></i></a>
        <a href="#"><i class="fa-regular fa-user"></i></a>
        <a href="#"><i class="fa-solid fa-cart-shopping"></i></a>
        <button class="btn-contact">Liên hệ tư vấn</button>
    </div>
</nav>

<!-- Main Content with Slideshow -->
<div class="slideshow-container">
    <div class="mySlides fade">
        <a>    <img src="../../img/slider/slider1.jpg" style="width:100%"> </a>
    </div>

    <div class="mySlides fade">
        <img src="../../img/slider/slider2.jpg" style="width:100%"> 
    </div>

    <div class="mySlides fade">
        <img src="../../img/slider/slider3.png" style="width:100%"> 
    </div>

    <!-- Dots for navigation -->
    <div class="dots-container" style="text-align:center">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>
</div>

<script src="../../js/homePage.js"></script>
</body>
</html>
