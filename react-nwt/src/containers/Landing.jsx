import React, { Component } from 'react'

// API wrapper 
import * as api from '../api'

// components 
import Cover from '../components/landing/Cover'
import Locations from '../components/landing/Locations'

class Landing extends Component {
    constructor() {
        super()

        this.state = {
            locations: []
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        try {
            let endpoint = "nwt2_ms_location-service-client/locations/all"
            let locations = await api.send(endpoint)

            this.setState({locations : locations.data})

            console.log(this.state.locations)
        } catch (err) {
            console.log(err)
        }
    } 

    render() {
        return (
            <div>
                <Cover />
                <Locations />
                
            </div>
        )
    }
}

export default Landing