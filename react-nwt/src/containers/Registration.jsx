import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'
import * as auth from '../auth'

// components 
import RegistrationForm from '../components/auth/Registration'

class Registration extends Component {
    constructor() {
        super()

        this.state = {
            email : '',
            firstName : '',
            lastName : '',
            username : '',
            password : ''
        }
    }

    componentWillMount() {
        //auth.redirectIfAuthenticated()
    }

    register = async () => {
        try {
            let endpoint = "nwt2_ms_identity-service-client/users/new"

            let payload = {
                email : this.state.email,
                firstName : this.state.firstName,
                lastName : this.state.lastName,
                username : this.state.username,
                password : this.state.password
            }
let headers = {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
            
            let response = await api.send(endpoint, payload, "POST",headers,true)

            console.log(response)

            //return

            // redirect to home
            window.location = "/login"
        } catch (err) {
            alert("Invalid credentials?")
        }
    } 

    onChange = (e) => {
        this.setState({[e.target.name]:e.target.value});
    }

    render() {
        // authentication middleware
        auth.redirectIfAuthenticated()
        return (
            <div>
                <RegistrationForm 
                    onChange={this.onChange}
                    register={this.register}
                    credentials={this.state}/>
            </div>
        )
    }
}

export default Registration