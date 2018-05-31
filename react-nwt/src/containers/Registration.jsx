import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'
import * as auth from '../auth'
import axios from 'axios';
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
            password : '',
            role: {name:'user',id:'2'}
        }
    }

    componentWillMount() {
        //auth.redirectIfAuthenticated()
    }

    register = async () => {

        axios.post('http://localhost:8084/nwt2_ms_identity-service-client/users/new',{ email : this.state.email,
        firstName : this.state.firstName,
        lastName : this.state.lastName,
        username : this.state.username,
        password : this.state.password,
        role : this.state.role })
        .then((result) => {
          console.log(result);
          window.location = "/login"
        }).catch(error => {
            alert('Invalid');
    console.log(error.response)
});;

        
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