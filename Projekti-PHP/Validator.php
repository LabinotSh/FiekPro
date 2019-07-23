<?php



 // turn on error reporting to help potential debugging 
error_reporting(E_ALL);
ini_set('display_errors','1');
include_once('Validation.php');

// create default validation results
 $nameValid = new Validation("", "", "", true); 
 $emailValid = new Validation("", "", "", true);
 $subjectValid = new Validation("", "", "", true);
 $messageValid = new Validation("", "", "",true);

 
 
 if ($_SERVER["REQUEST_METHOD"] == "POST") {
 $nameValid = Validation::checkParameter("name",'[a-zA-Z] ','Enter your name!');
 $emailValid = Validation::checkParameter("mail",'/(.+)@([^\.].*)\.([a-z]{2,})/','Enter a valid email');
$subjectValid = Validation::checkParameter("subject",'[a-zA-Z] ','Enter the subject!');
$messageValid = Validation::checkParameter("message",'[a-zA-Z] ', 'Please write yoru message!');

 
   // if no validation errors redirect to another page 
   if ( $nameValid->isValid() && $emailValid->isValid() && 
   $subjectValid->isValid() &&  
   $messageValid->isValid() ) {
	   header( 'Location: contact-form-thank-you.html' );    
	   } 
 }
?>