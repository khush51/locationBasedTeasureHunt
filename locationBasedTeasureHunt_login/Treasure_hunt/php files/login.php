<?php
require "conn.php";

$user_name = $_POST["user"];
$user_pass= $_POST["pass"];
$mysql_qry = "select * from user where user like '$user_name' and pass='$user_pass'";


$result = mysqli_query($conn ,$mysql_qry);
   if(mysqli_num_rows($result) > 0) {
  echo "login success"; 
	    }
   
   else{
	   
	   echo "login not success";
   }
 

?>