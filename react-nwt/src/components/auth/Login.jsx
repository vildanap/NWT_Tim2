import React, { Component } from 'react'
import {Link} from 'react-router-dom';

class LoginForm extends Component {
    render() {
        return (
            <section className="login-form">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
                            <div className="form-group">
                                <label>Username:</label>
                                <input type="text" name="username" className="form-control" placeholer="E-mail" value={this.props.credentials.username} onChange={this.props.onChange}/>
                            </div>

                            <div className="form-group">
                                <label>Password:</label>
                                <input type="password" name="password" className="form-control" placeholer="Password" value={this.props.credentials.password} onChange={this.props.onChange}/>
                            </div>

                            <a href="javascript:void(0)" className="btn btn-primary float-right" onClick={this.props.login}>
                                Login
                            </a>   
			    <Link to="/registration"> Not registered yet?
			    </Link>
                        </div>
                    </div>
                </div>
            </section>
        )
    }

}

export default LoginForm