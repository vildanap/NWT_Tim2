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
            email : '',
            password : ''
        }
    }

    componentWillMount() {
        auth.redirectIfAuthenticated()
    }

    login = async () => {
        try {
            let endpoint = "nwt2_ms_identity-service-client/oauth/token"

            let payload = {
                email : this.state.email, 
                password : this.state.password,
                grant_type : "password"
            }
            
            let headers = {
                Authorization : "Basic ZGV2Z2xhbi1jbGllbnQ6JDJhJDA0JGUvYzEvUmZzV3VUaGFXRkNyY0N1SmVveXZ3Q1YwVVJOLzZQbjlaRmxydElXYVUvdmovQmZH"
            }

            let response = await api.send(endpoint, payload, "POST", headers)

            let token = response.access_token

            // set the token
            localStorage.setItem('token', token)

            // redirect to home
            window.location = "/"
        } catch (err) {
            alert("Invalid credentials?")
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