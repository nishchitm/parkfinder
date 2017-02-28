<?php


require 'config.php';
 $uid = isset($_POST['UID'])? $_POST['UID']: '';
 $address = isset($_POST['Address'])? $_POST['Address']: '';
 $price = isset($_POST['Price'])? $_POST['Price']: '';
 $description = isset($_POST['Description'])? $_POST['Description']: '';
 $lat = isset($_POST['Lat'])? $_POST['Lat']: '';
 $lon = isset($_POST['Lon'])? $_POST['Lon']: '';
 $parkingspace = isset($_POST['NoOfParkingPlaces'])? $_POST['NoOfParkingPlaces']: '';
 $length = isset($_POST['Length'])? $_POST['Length']: '';
 $breadth = isset($_POST['Breadth'])? $_POST['Breadth']: '';
 $height = isset($_POST['Height'])? $_POST['Height']: '';


 $sql_qry = "INSERT INTO parkplaces(UID,Address,Price,Description,Lat,Lon,NoOfParkingPlaces,Length,Breadth,Height) VALUES ('$uid','$address','$price','$description','$lat','$lon','$parkingspace','$length','$breadth','$height')";
 if($con->query($sql_qry)){
	echo "successfully registered";
 }else{
	
 	echo 'oops! Please try again!';
 
 }

 
 //require_once('config.php');