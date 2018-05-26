import React, { Component } from 'react'
import { Link } from 'react-router-dom'

class Location extends Component {
    constructor() {
        super()
    }

    render() {
        const item = this.props.city

        return (
            <div className="col-md-4 col-sm-4 col-xs-12">
                <div className="location-item">
                    <div className="location-item__overlay">
                        <div className="location-item__name">
                        <Link to={`/location/${item.id}`} style={{ textDecoration: 'none', color: '#FFF' }}>{ item.name }</Link>
                        </div>
                        <div className="location-item__description">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis velit lorem, pellentesque et nibh eu, porta interdum lacus.
                        </div>
                        <div className="row">
                            <div className="col-md-6 col-sm-6 col-xs-12 location-item__stat">
                                <i class="fas fa-eye"></i> 100 Visits
                            </div>
                            <div className="col-md-6 col-sm-6 col-xs-12 location-item__stat">
                                <i class="far fa-star"></i> 18 Reviews
                            </div>
                        </div>
                    </div>
                    <img src={ item.photoUrl } />
                </div>
            </div>
        )
    }
}

export default Location