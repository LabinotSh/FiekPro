





function valName(uname)  
{   
var len = uname.value.length; 
if(len==0)
{  
alert('Write Your Name Please');
uname.focus();
return false;  
}  
return true; 
}  


function ValidateEmail(e)  
{  
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
if(e.value !="" ||  mailformat.test(e.value))  
{  
return true;  
}  
else  
{  
alert("Please Enter Your Email . You have entered an invalid email address!");  
e.focus();  
return false;  
}  
}  

function passid_validation(passid,mx,my)  
{  
var passid_len = passid.value.length;  
if (passid_len == 0 ||passid_len >= my || passid_len < mx)  
{  
alert("Password should not be empty / length be between "+mx+" to "+my);  
passid.focus();  
return false;  
}  
return true;  
}  


function countryselect(ucountry)  
{  
if(ucountry.value == "Default")  
{  
alert('Select your country from the list');  
ucountry.focus();  
return false;  
}  
else  
{  
return true;  
}  
}  

function bValid(birth){
if(birth.value=="")    
{  
window.alert('Enter Your Birthday!');  
birth.focus();  
return false; 

}	
}

function isChecked(){
 var checkedM = document.getElementById('ma').checked;
 var checkedF = document.getElementById('fe').checked;
 
 if(checkedM == false && checkedF == false){
 alert('You need to select an option!');
 return false;
 }
 else{
 return true;
 }
 }


 
 function validateAll(){
var uname=document.form1.name;
var e=document.form1.email;
var passid=document.form1.pass;
var ucountry=document.form1.country;
var birth=document.form1.birthday; 


if(valName(uname))
{
if(ValidateEmail(e))
{
if(passid_validation(passid,7,15))
{
if(countryselect(ucountry))
{
if(bValid(birth))
{	
if(isChecked())
{
}
}	
}	
}	
}
}	
return false;
}
 


function clickCounter() {
    if(typeof(Storage) !== "undefined") {
        if (localStorage.clickcount) {
            localStorage.clickcount = Number(localStorage.clickcount)+1;
        } else {
            localStorage.clickcount = 1;
        }
        document.getElementById("click").innerHTML = "You have clicked the button " + localStorage.clickcount + " time(s).";
    } else {
        document.getElementById("click").innerHTML = "Sorry, your browser does not support web storage...";
    }
}








