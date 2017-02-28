<?php
include 'config.php';
$Email=$_POST['Email'];
$Password=$_POST['Password'];

$sql="select * from user where Email like '$Email' and Password like '$Password'";

$result=$con->query($sql);

if(mysqli_num_rows($result)>0){
	$return_array=array();
	$row=mysqli_fetch_array($result);
	$row_array['UID']=$row['UID'];
	$row_array['Email']=$row['Email'];
	$row_array['Fname']=$row['Fname'];
	$row_array['Lname']=$row['Lname'];
	$row_array['PhoneNumber']=$row['PhoneNumber'];

	array_push($return_array,$row_array);

	echo json_encode($return_array);
}

else {

  echo "invalid Email or Password";
}




?>
