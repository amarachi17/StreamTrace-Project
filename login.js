// Authentication

const loginForm = document.getElementById('loginForm')

loginForm.addEventListener('submit', function (event) {
  event.preventDefault()

  const username = document.getElementById('username').value
  const password = document.getElementById('password').value
  const phone = document.getElementById('phone').value
  const age = document.getElementById('age').value
  const email = document.getElementById('email').value

  const data = {
    username,
    password,
    age,
    email,
    phone,
  }
  console.log('data: ', data)
  // Use the Fetch API to send data to the backend
  fetch('http://localhost:8080/api/users/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(response => response.json())
    .then(result => {
      // Handle the response from the backend
      console.log('result: ', result)
      if (result.success) {
        alert('Login successful!')
        location.href('/')
      } else {
        alert('Login failed. Please check your credentials.')
      }
    })
    .catch(error => {
      console.error('Error:', error)
    })
})
