import React, { Component } from 'react'

class RegistrationForm extends Component {
    render() {
        return (
            <section className="login-form">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
                            <div className="form-group">
                                <label>First name:</label>
                                <input type="text" name="firstName" className="form-control" placeholer="First name" value={this.props.credentials.firstName} onChange={this.props.onChange}/>
                            </div>
                            
                            <div className="form-group">
                                <label>Last name:</label>
                                <input type="text" name="lastName" className="form-control" placeholer="Last name" value={this.props.credentials.lastName} onChange={this.props.onChange}/>
                            </div>

                            <div className="form-group">
                                <label>Username:</label>
                                <input type="text" name="username" className="form-control" placeholer="Username" value={this.props.credentials.username} onChange={this.props.onChange}/>
                            </div>

                            <div className="form-group">
                                <label>E-mail:</label>
                                <input type="email" name="email" className="form-control" placeholer="E-mail" value={this.props.credentials.email} onChange={this.props.onChange}/>
                            </div>

                            <div className="form-group">
                                <label>Password:</label>
                                <input type="password" name="password" className="form-control" placeholer="Password" value={this.props.credentials.password} onChange={this.props.onChange}/>
                            </div>

                            <a href="javascript:void(0)" className="btn btn-primary float-right" onClick={this.props.register}>
                                Register
                            </a>    
                        </div>
                    </div>
                </div>
            </section>
        )
    }

}

export default RegistrationForm