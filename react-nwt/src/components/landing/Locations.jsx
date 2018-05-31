import React, { Component } from 'react'

import Location from './Location'
import * as api from '../../api'

class Locations extends Component {
    constructor() {
        super()
    }

    render() {
        return ( 
            <section className="locations-wrapper">
                <div className="container pull-up-wrapper">
                    <div className="row">
                    {
                        this.props.locations.map(
                            location => <Location city={location} />
                           )
                    }
                    </div>
                </div>
            </section>
        )
    }
}

export default Locations