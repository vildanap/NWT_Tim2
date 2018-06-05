import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'

// components 
import Loading from '../components/common/Loading'
import Cover from '../components/landing/Cover'
import Locations from '../components/landing/Locations'
import Reviews from '../components/landing/Reviews'

class Landing extends Component {
    constructor() {
        super()

        this.state = {
            loading: true,
            locations: [],
            recent: [],
            keyword: ''
        }
    }

    componentWillMount() {
        this.initilizeLocations()
        this.initilizeReviews()
    }
    
    componentDidMount() {
        setTimeout(() => this.setState({ loading: false }), 1200)
    }

    /**
     * Initilize method
     */
    initilizeLocations = async () => {
        try {
            let endpoint = "nwt2_ms_location-service-client/locations/all"
            let locations = await api.send(endpoint);

            if(locations.data) {
                this.setState({locations : locations.data});
            }            
        } catch (err) {
            console.log(err.response)
        }
    } 

    initilizeReviews = async () => {
        try {
            let endpointReviews = "nwt2_ms_review-service-client/reviews/recent/";
            let reviews = await api.send(endpointReviews)

            if(reviews.data) {
                this.setState({ recent : reviews.data })
            }
        } catch (err) {
            console.log(err.response)
        }
    }

    /**
     *  Live seaarch method
     */
    search = async (e) => {
        try {
            this.setState({[e.target.name]:e.target.value})

            let endpoint = "nwt2_ms_location-service-client/locations/all"

            if(e.target.value != "") {
                endpoint = "nwt2_ms_location-service-client/locations/search/"+encodeURIComponent(e.target.value)
            }

            let locations = await api.send(endpoint);
            
            if(locations.data) {
                this.setState({locations : locations.data});
            } else {
                this.setState({locations : []});
            }
        } catch (err) {
            console.log(err.response)
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
                        <div>
                            <Cover 
                                search={this.search}
                                keyword={this.state.keyword}
                                />
                            <Locations locations={this.state.locations} />
                        </div>
                    )
                }  
            </div>
        )
    }
}

export default Landing