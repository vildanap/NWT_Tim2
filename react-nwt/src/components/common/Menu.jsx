import React, { Component } from 'react'

import * as auth from '../../auth'

class Menu extends Component {
    constructor() {
        super()
    }

    render() {
        return (
            <nav className="navbar navbar-expand-md nav-bar-travel navbar-dark bg-dark container-fluid">
                <a className="navbar-brand logo-text" href="/">TravelReview</a>
                
                <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i className="fas fa-bars"></i>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ml-auto">
                        {
                            !auth.isAuthenticated() &&
                            (
                                <li class="nav-item">
                                    <a class="nav-link" href="/login">Login</a>
                                </li>
                            )
                        }
                         {
                            auth.isAuthenticated() && 
                            (
                                <li class="nav-item">
                                    <a class="nav-link" href="javascript:void(0)" onClick={auth.myreviews}>My reviews</a>
                                </li>
                            )
                        }
                        {
                            auth.isAuthenticated() &&
                            (
                                <li class="nav-item">
                                    <a class="nav-link" href="javascript:void(0)" onClick={auth.logout}>Logout</a>
                                </li>
                            )
                        }
                    </ul>
                </div>
            </nav>
        )
    }
}

export default Menu