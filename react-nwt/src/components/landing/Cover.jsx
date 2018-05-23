import React, { Component } from 'react'

class Cover extends Component {
    constructor() {
        super()
    }

    render() {
        return (
            <section className="landing-cover">
                <div className="landing-cover__overlay"></div>
                <div className="container h-100">
                    <div className="row">
                        <div className="col-md-6  offset-md-3 col-sm-10 offset-sm-1 col-sm-12">
                            <div className="landing-cover__title">
                                Find your next adventure review!
                            </div>
                            <div className="search-box">
                                <input className="search-keyword" type="text" placeholder="Where are you heading next?"/>
                                <button className="search-button" type="submit"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        )
    }

}

export default Cover