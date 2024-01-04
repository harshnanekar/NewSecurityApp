<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>
<form method="post" id="myForm">
Enter the username: <input type="email" id="mail" name="username" /></br>
Enter the password: <input type="password" name="password" /></br>
<button id="clicks" type="submit">Submit</button>


</form>
</body>

<script>

const form = document.getElementById('myForm');

form.addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('mail').value;


    const formData = new FormData(form);

    fetch('/User/loginPage', {
        method: 'POST',
        headers: {
            'username': username,
            // Other headers if needed
        },
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        // Handle success response
        return response.json();
    })
    .then(data => {
        // Handle data returned by the server
        console.log(data);
    })
    .catch(error => {
        // Handle errors
        console.error('There was a problem with the fetch operation:', error);
    });
});

</script>
</html>