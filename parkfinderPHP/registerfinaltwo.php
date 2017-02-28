<?php

include 'config.php';

$Fname = $_POST['Fname'];
$Lname  = $_POST['Lname'];
$Email = $_POST['Email'];
$Password= $_POST['Password'];
$SecurityQuestion = $_POST['SecurityQuestion'];
$Answer = $_POST['Answer'];
$PhoneNumber = $_POST['PhoneNumber'];
$UID= uniqid('', true);
$sqls = "select * from user where Email like'$Email'";
$result = $con->query($sqls);
if(mysqli_num_rows($result)>0)
{
	echo "Email Already Register";
}
else
{
$sql = "INSERT INTO user(Fname,Lname,Email,Password,SecurityQuestion,Answer,UID,PhoneNumber) VALUES('$Fname','$Lname','$Email','$Password','$SecurityQuestion','$Answer','$UID','$PhoneNumber')";
$con->query($sql);
echo "successful";

}
$con->close();



?>