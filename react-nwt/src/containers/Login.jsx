import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'
import * as auth from '../auth'

// components 
import LoginForm from '../components/auth/Login'

class Login extends Component {
    constructor() {
        super()

        this.state = {
            username : '',
            password : ''
        }
    }

    componentWillMount() {
        auth.redirectIfAuthenticated()
    }

    login = async () => {
        try {
            let endpoint = "/nwt2_ms_identity-service-client/oauth/token"

         var details = {
    'username': this.state.username,
    'password': this.state.password,
    'grant_type': 'password'
};

var formBody = [];
for (var property in details) {
  var encodedKey = encodeURIComponent(property);
  var encodedValue = encodeURIComponent(details[property]);
  formBody.push(encodedKey + "=" + encodedValue);
}
formBody = formBody.join("&");

let response = fetch('http://localhost:8084/nwt2_ms_identity-service-client/oauth/token', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    'Authorization':'Basic ZGV2Z2xhbi1jbGllbnQ6ZGV2Z2xhbi1zZWNyZXQ='
  },
  body: formBody
})

            let token = response.access_token
            console.log(response.access_token)
            // set the token
            localStorage.setItem('token', token)

            // redirect to home
            window.location = "/"
        } catch (err) {
            alert("Invalid credentials?")
            console.log(err);
        }
    } 

    onChange = (e) => {
        this.setState({[e.target.name]:e.target.value});
    }

    render() {
        return (
            <div>
                <LoginForm 
                    onChange={this.onChange}
                    login={this.login}
                    credentials={this.state}/>
            </div>
        )
    }
}

export default Login