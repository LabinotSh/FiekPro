<?php


$email = "";
$name = "";
$subject = "";
$message = "";


$emailError = "";
$nameError = "";
$subjectError = "";
$messageError = "";

if($_SERVER["REQUEST_METHOD"] == "POST") {
	
	if(empty($_POST['name'])) {
    $nameError = "Please Enter your Name!";
	}else{
		$name = test_input($_POST['name']);
	}
	
	if(empty($_POST['mail'])){
		$emailError = "Please enter your email!";
	}else{
		$email = test_input($_POST['mail']);
     if (!preg_match("/([\w\-]+\@[\w\-]+\.[\w\-]+)/",$email)) {
            $emailError = "You Entered An Invalid Email Format"; 
        }
	}
  
    if(empty($_POST['subject'])){
		$subjectError = "Please enter a subject!";
	}else{
		$subject = test_input($_POST['subject']);
	}
 
		
	 
	 if(empty($_POST['message'])){
		$messageError = "Please enter your message!";
	}else{
		$message = test_input($_POST['message']);
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
	
	
	



