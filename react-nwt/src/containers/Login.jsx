import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'

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
        
    }

    login = async () => {
        try {
            let endpoint = "--"
            let response = await api.send(endpoint, { email : this.state.email, password : this.state.password })

            let token = response.token

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