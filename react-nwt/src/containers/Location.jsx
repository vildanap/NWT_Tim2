import React, { Component } from 'react'

import Loading from '../components/common/Loading'
import Review from '../components/Review'

import * as api from '../api'

class Location extends Component {
    constructor() {
        super()

        this.state = {
            loading: true,
            location: [],
            reviews: []
        }
    }

    componentWillMount() {
        this.initilize()
    }

    componentDidMount() {
        setTimeout(() => this.setState({ loading: false }), 1200)
    }

    initilize = async () => {
        try {
            let endpoint = "nwt2_ms_location-service-client/locations/"+this.props.match.params.id;
            let location = await api.send(endpoint)

            let endpointReviews="nwt2_ms_review-service-client/reviews/location/"+this.props.match.params.id;
            let reviews = await api.send(endpointReviews)

            this.setState({location : location.data, reviews : reviews.data})

            console.log(this.state.location)
            console.log(this.state.reviews)
        } catch (err) {
            console.log(err)
        }
    } 

    render() {
        return (
            <div>
                {
                    this.state.loading && (
                        <Loading />
                    )
                }
                {
                    !this.state.loading && (
                        <div className="location-background">
                            <div className="location-cover">
                                <div className="location-cover__overlay">
                                    <h2>{this.state.location.name}</h2>
                                </div>
                                <img src={ this.state.location.photoUrl } />
                            </div>
                            <div className="row">
                                {
                                    this.state.reviews.map(
                                        review => <Review value={review} />
                                    )
                                }
                            </div>
                        </div>
                    )
                }
            </div>
        )
    }
}

export default Location