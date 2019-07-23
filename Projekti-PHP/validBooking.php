<?php

$name = "";
$email = "";
$location = "";
$phone = "";
$startDate = "";
$endDate = "";
$nrPersons = "";


$nameError = "";
$emailError = "";
$locationError = "";
$phoneError = "";
$startDateError = "";
$endDateError = "";
$nrPersonsError = "";

if(isset($_POST['submit'])) {
	$name = trim($_POST['firstname']);
	$email = trim($_POST['email']);
	$location = trim($_POST['location']);
	$phone = trim($_POST['phone']);
	$startDate = trim($_POST['start']);
	$endDate = trim($_POST['end']);
	$nrPersons = trim($_POST['people']);
	
	function checkName($name){
		global $nameError;
		if(empty($name)){
			$nameError="Please Enter Your Name";
			return false;
		}else{
			return true;
		}
	}
	function checkEmail($email){
		global $emailError;
		if(empty($email)){
			$emailError="Please Enter you Address!";
			return false;
		}else if(!preg_match("/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/",$email)){
			$emailError = "Email is Invalid!";
			return false;
	    }else{
			return true;
			
		}
		
	}
	function checkPhoneNumber($phone){
        global $phoneError;
        if (empty($phone)) {
            $phoneError = "Phone number is empty";
            return false;
        } else if(!preg_match("/^[0-9]+$/", $phone)) {
            $phoneError = "Please enter a valid phone number";
            return false;
        }else{
            return true;
		}
    } 	
	
	
	  function checkLocation($location){
		global $locationError;
		if($location==""){
			$locationError = "please select a location!";
			return false;
		}else{
			return true;
		}
	  }
		
		function checkStartDate($startDate){
			global $startDateError;
			if(empty($startDate)){
				$startDateError = "Please fill a date!";
				return false;
			}else{
				return true;
			}
		}
	
		function checkStartDate($startDate){
			global $startDateError;
			if(empty($startDate)){
				$startDateError = "Please fill a date!";
				return false;
			}else{
				return true;
			}
		}
		
		function checkEndDate($endDate){
			global $endDateError;
			if(empty($endDate)){
				$endDateError = "Please fill a date!";
				return false;
			}else{
				return true;
			}
		}
		
		function checkPeople($nrPersons){
		global $nrPersonsError;
		if($nrPersons==""){
			$nrPersonsError = "please select a number!";
			return false;
		}else{
			return true;
		}
	  }
	  
	  
	  function checkAll($name,$email,$phone,$location,$startDate,$endDate,$nrPersons){
		  if(checkName($name) & checkEmail($email) & checkPhoneNumber($phone)
			  &checkLocation($location) & checkStartDate($startDate) & checkEndDate($endDate)
		      & checkPeople($nrPersons)){
				  return true;
			  }else{
				  echo "Failed";
				  return false;
			  }
    	  }
	  checkAll($name,$email,$phone,$location,$startDate,$endDate,$nrPersons);
	  
	  
		
		
	
}






?>