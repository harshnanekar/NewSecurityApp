<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login2</title>
</head>
<body>
<div>
Enter the username: <input id="mail" type="email" /></br>
Enter the password: <input id="pass" type="password" /></br>
<input id="click" type="submit"/>
</div>
</body>

<script>

document.getElementById("click").addEventListener("click",() => {
 let user = document.getElementById("mail").value;
 let pass = document.getElementById("pass").value;
 
 let obj = { user , pass};
 
 const base64Credentials = btoa(`${user}:${pass}`);
 
 insertData('/insert','POST',obj,base64Credentials)
	
});

  function insertData(url,method,obj,base64Credentials){
	  
	  
	const a = {
	 method:method || 'GET',
	 headers:{
		 'Content-Type':'application/json'
		 'Authorization':``
	 }
	}  
	  
	  
  }



</script>
</html>