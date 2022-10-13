// Elements
const registerForm = document.getElementById('register-form');
const registerUsername = document.getElementById('register-username');
const registerPassword = document.getElementById('register-password');

// Headers for every request
const headers = {
    'Content-Type':'application/json'
}

// base url
const baseUrl = 'http://localhost:8080/api/v1/users';

// form handler
const handleSubmit = async (e) => {
    /* prevent default behavior */
    e.preventDefault()

    /* define an obj for the form values */
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    /* post request*/
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    /* assign the response to a variable*/
    const responseArr = await response.json()

    /* if the status is OK, redirect the user to the login page
       assigned in the UserServiceImpl register response (responseArr)*/
    if (response.status === 200){
        window.location.replace(responseArr[0]);
    }
}

/* Event Listener*/
registerForm.addEventListener("submit", handleSubmit);
