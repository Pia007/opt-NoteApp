/* Elements */
let loginForm = document.getElementById('login-form')
let loginUsername = document.getElementById('login-username')
let loginPassword = document.getElementById('login-password')

/* headers */
const headers = {
    'Content-Type':'application/json'
}

/* baseUrl */
const baseUrl = 'http://localhost:8080/api/v1/users'

/* form handler */
const handleSubmit = async (e) =>{
    e.preventDefault()

    /* define an obj for the form values */
    let bodyObj = {
        username: loginUsername.value,
        password: loginPassword.value
    }

    /* post request*/
    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    /* assign the response to a variable*/
    const responseArr = await response.json()

    /* if the status is OK,
        create JS cookie to store the user's id for more request once they are logged in,
        redirect the user to the login page
        assigned in the UserServiceImpl login response (responseArr)
    */
    if (response.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        window.location.replace(responseArr[0])
    }
}

loginForm.addEventListener("submit", handleSubmit)