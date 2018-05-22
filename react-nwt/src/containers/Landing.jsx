import React, { Component } from 'react'

import * as api from '../api'

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
                NWT 2 Frontend React project <br /><br />

                We have connected to the all location list: <br /><br />
                
                <pre>
                    { JSON.stringify(this.state.locations, null, 2) }
                </pre>
            </div>
        )
    }
}

export default Landing