<?php
include 'config.php';

$Lat=$_POST['Lat'];
$Lon=$_POST['Lon'];
$table="parkplaces";
$sql="select * from $table where Lat like '$Lat' and Lon like '$Lon'" ;

$result=$con->query($sql);

$return_array = array();
while ($row=mysqli_fetch_array($result)) {
  # code...
  $row_array['Serial']=$row['Serial'];	
  $row_array['UID']=$row['UID'];
  $row_array['Address']=$row['Address'];
  $row_array['Lat']=$row['Lat'];
  $row_array['Lon']=$row['Lon'];
  $row_array['Price']=$row['Price'];
  $row_array['Description']=$row['Description'];
  $row_array['Length']=$row['Length'];
  $row_array['Breadth']=$row['Breadth'];
  $row_array['Height']=$row['Height'];
  $row_array['NoOfParkingPlaces']=$row['NoOfParkingPlaces'];


  array_push($return_array,$row_array);
}

echo json_encode($return_array);
