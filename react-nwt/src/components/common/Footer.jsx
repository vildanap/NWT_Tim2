import React, { Component } from 'react'

class Footer extends Component {
    constructor() {
        super()
    }

    render() {
        return (
            <section id="footer">
                <section className="footer-up">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 col-sm-8 col-xs-12">
                                <span className="footer-logo-text">
                                    TravelReview
                                </span>
                                <span className="footer-logo-slogan">
                                    Find reviews for your next adventure
                                </span>
                            </div>
                            <div className="col-md-2 col-sm-2 col-xs-12">
                                <span className="footer-up_heading">
                                    Website
                                </span>

                                <ul>
                                    <li>
                                        <a href="#">Home</a>
                                    </li>
                                    <li>
                                        <a href="#">How it works</a>
                                    </li>
                                    <li>
                                        <a href="#">Products</a>
                                    </li>
                                    <li>
                                        <a href="#">Newsletter</a>
                                    </li>
                                </ul>
                            </div>
                            <div className="col-md-2 col-sm-2 col-xs-12">
                                <span className="footer-up_heading">
                                    Fund us on...
                                </span>

                                <ul>
                                    <li>
                                        <a href="#">Facebook</a>
                                    </li>
                                    <li>
                                        <a href="#">Angle list</a>
                                    </li>
                                    <li>
                                        <a href="#">Product Hunt</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </section>
                <section className="footer-down">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 col-sm-12 col-xs-12">
                                <span className="footer-down_text">
                                    Copyright @ TravelReview. All rights reserved.
                                </span>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
        )
    }
}

export default Footer