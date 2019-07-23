<?php

$email = "";
$location = "";
$password = "";
$message = "";


$emailError = "";
$locationError = "";
$passwordError = "";
$messageError = "";

if($_SERVER["REQUEST_METHOD"] == "POST") {
	
	if(empty($_POST['email'])){
		$emailError = "Please enter your email!";
	}else{
		$email = test_input($_POST['email']);
     if (!preg_match("/([\w\-]+\@[\w\-]+\.[\w\-]+)/",$email)) {
            $emailErr = "You Entered An Invalid Email Format"; 
        }
    }
  
     if(empty($_POST["password"])) {
    $passwordError = "Please Enter your Password!";
    }elseif (strlen($_POST["password"]) <= '8') {
        $passwordError = "Your Password Must Contain At Least 8 Characters!";
    
	
	}elseif(!preg_match("#[0-9]+#",$password)) {
        $passwordError = "Your Password Must Contain At Least 1 Number!";
    
    } else {
        $password = test_input($_POST["password"]);
    }
 
		if($_POST["country"] == "")
		{
			$locationError = "please select a location!";
		}else{
			$location = test_input($_POST["country"]);
		}
	 
	 if(empty($_POST['why'])){
		$messageError = "Please enter your message!";
	}else{
		$message = test_input($_POST['why']);
    }

}

function test_input($data)
 {
	 $data = " ";
     $data = trim($data);
     $data = stripslashes($data);
     $data = htmlspecialchars($data);
     return $data;
}


?>