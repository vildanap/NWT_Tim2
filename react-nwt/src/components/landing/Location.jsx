import React, { Component } from 'react'
import { Link } from 'react-router-dom'

import * as api from '../../api'

class Location extends Component {
    constructor() {
        super()

        this.state = {
            number: 0,
            grade: 0
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        this.getInfo()
    } 

    

    getInfo = async () => {
        let endpoint = "nwt2_ms_review-service-client/reviews/location/info/"+this.props.city.id;
        let response = await api.send(endpoint)
        this.setState({ number: response.data.numberOfReviews, grade: response.data.averageGrade});
    }

    render() {
        const item = this.props.city

        return (
            <div className="col-md-6 col-sm-6">
                <div className="location-item">
                    <div className="location-item__overlay">
                        <div className="location-item__name">
                        <Link to={`/location/${item.id}`} style={{ textDecoration: 'none', color: '#FFF' }}>{ item.name }</Link>
                        </div>
                        <div className="location-item__description">
                            {item.description}
                        </div>
                        <div className="row">
                            <div className="col-md-6 col-sm-6 col-xs-12 location-item__stat">
                                <i class="fas fa-eye"></i> {this.state.number} Reviews
                            </div>
                            <div className="col-md-6 col-sm-6 col-xs-12 location-item__stat">
                                <i class="far fa-star"></i> {this.state.grade} 
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