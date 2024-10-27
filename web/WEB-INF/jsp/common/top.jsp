<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>

<head>
    <title>PetStore</title>
    <link rel="StyleSheet" href="css/PetStore.css" type="text/css"
          media="screen" />
</head>

<body>
<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"> <img src="images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">

            <a href="#"><img align="middle" name="img_cart" src="images/cart.gif" /></a>

            <img align="middle" src="images/separator.gif" />
            <a href="#">Sign In</a>
            <a href="#">Sign Out</a>
            <img align="middle" src="images/separator.gif" />
            <a href="#">My Account</a>

           <img align="middle" src="images/separator.gif" />
            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="" method="post">
                <input type="text" name="keyword" size="14">
                <input type="submit" value="Search">
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href=""><img src="images/sm_fish.gif" /></a>
        <img src="images/separator.gif" />
        <a href=""><img src="images/sm_dogs.gif" /></a>
        <img src="images/separator.gif" />
        <a href=""><img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href=""><img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href=""><img src="images/sm_birds.gif" /></a>
</div>

<div id="Content"><stripes:messages />