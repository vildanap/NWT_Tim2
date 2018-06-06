import React, { Component } from 'react'
import axios from 'axios';

// API wrapper 
import * as api from '../api'
import * as auth from '../auth'

// components 
import RegistrationForm from '../components/auth/Registration'
import Loading from '../components/common/Loading'

class Registration extends Component {
    constructor() {
        super()

        this.state = {
            loading : false,
            email : '',
            firstName : '',
            lastName : '',
            username : '',
            password : '',
            role: {name:'user',id:'2'}
        }
    }

    componentWillMount() {
        auth.redirectIfAuthenticated()
    }


    componentDidMount() {
        setTimeout(() => this.setState({ loading: false }), 1200)
    }

    register = async () => {
        axios.post('http://localhost:8084/nwt2_ms_identity-service-client/users/new',{ email : this.state.email,
            firstName : this.state.firstName,
            lastName : this.state.lastName,
            username : this.state.username,
            password : this.state.password,
            role : this.state.role 
        })
        .then((result) => {
            alert('Account created!')
            window.location = "/login"
        })
        .catch(error => {
            alert('Invalid');
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
                {
                    this.state.loading && (
                        <Loading />
                    )
                }
                {
                    !this.state.loading && (
                         <RegistrationForm 
                            onChange={this.onChange}
                            register={this.register}
                            credentials={this.state}/>
                    )
                }
            </div>
        )
    }
}

export default Registration