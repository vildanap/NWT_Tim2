import React, { Component } from 'react'
import Review from './Review'

import * as api from '../api'

class Location extends Component {
    constructor() {
        super()

        this.state = {
            location: [],
            reviews: []
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        try {
            let endpoint = "nwt2_ms_location-service-client/locations/"+this.props.match.params.id+"?access_token=e0f55961-6094-4862-a6ef-e384cb70608c"
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
                <div>
		            <div>
                    <h2>{this.state.location.name}</h2>
                    </div>
                </div>
                <div>
		            <div>
                    <img src="https://news.expats.cz/wp-content/uploads/2017/10/hp25-12.jpg"></img>
                    </div>
                </div>
                <div >
		            <div>
                    <p>{this.state.location.description}</p>
                    </div>
                </div>
                <div>
		            <div>
                    <h3>Reviews</h3>
                    </div>
                    {
                        this.state.reviews.map(
                            review => <Review value={review} />
                           )
                    }
                    
                </div>
            </div>
        )
    }
}

export default Location