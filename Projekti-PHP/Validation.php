<?php

class Validation{
	
	
	 private $value;
	 // user input value to be validated  
	 private $cssClassName;
     // css class name for display
	 private $errorMessage;    
	 // error message to be displayed
	 private $isValid = true;
	 
public function __construct($cssClassName, $value, $errorMessage,  
                                $isValid) 
			{  
			$this->cssClassName = $cssClassName;
			$this->value = $value;
			$this->errorMessage = $errorMessage;
			$this->isValid = $isValid; 
			} 
 
 
 public function getCssClassName()
 {
	 return $this->cssClassName; 
 }   
 
 public function getValue() 
 {
	 return $this->value;
} 
 
 public function getErrorMessage()
 {
 return $this->errorMessage;
 }

 public function isValid()
 {
return $this->isValid;

 }
 
 
 
 static public function checkParameter($queryName, $pattern, $errMsg) 
 {
 $error = "";
 $errClass = "";
 $value = ""; 
 $isValid = true; 	
 $pattern ;
 
  if (empty($_POST[$queryName])) { 
  $error = $errMsg;  
  $errClass = "error";
  $isValid = false; 
   
  }else{
	   $value = $_POST[$queryName];
	   if (!preg_match("[a-zA-Z]", $value) )
	{  
	$error = $errMsg;
	$errClass = "error";
	$isValid = false;
	}     
  }
  return new Validation($errClass, $value, $error, $isValid);
 }
}
  

?>