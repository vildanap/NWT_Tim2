import React, { Component } from 'react'
import { withScriptjs, withGoogleMap, GoogleMap, Marker } from "react-google-maps"

import Loading from '../components/common/Loading'
import Review from '../components/Review'
import { Link } from 'react-router-dom'

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

                            <Mapa 
                                isMarkerShown={false} 
                                googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places"
                                loadingElement={<div style={{ height: `100%` }} />}
                                containerElement={<div style={{ height: `400px` }} />}
                                mapElement={<div style={{ height: `100%` }} />}
                            />

                            <div className="row"> 
                            <button>
                            <Link to={{pathname: '/review/create', state: { cityId: this.state.location.id}}} >ADD REVIEW</Link>
                            </button>
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

const Mapa = withScriptjs(withGoogleMap((props) => 
    <GoogleMap
        defaultZoom={8}
        defaultCenter={{ lat: -34.397, lng: 150.644 }}
    >
    </GoogleMap>
))

export default Location