import React, { Component } from 'react'

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
                        
                    </ul>
                </div>
            </nav>
        )
    }
}

export default Menu