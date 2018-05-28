import React, { Component } from 'react'

import Location from './Location'
import * as api from '../../api'

class Locations extends Component {
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
            let endpoint = "nwt2_ms_location-service-client/locations/all?access_token=8b79b001-4343-4d70-99a9-a6ecd7edfb2c";
            let locations = await api.send(endpoint);
            this.setState({locations : locations.data});
        } catch (err) {
            console.log(err)
        }
    } 
    
    render() {
        const cities = [
            {
                name : 'Istanbul',
                image : 'https://www.popustplus.hr/upload/groupbuydeal/92025/istanbul-4-dana-popustplus0_5a9fd73f8d842.jpg'
            },
            {
                name : 'New York',
                image : 'https://images.fineartamerica.com/images-medium-large-5/new-york-nights-linda-karlin.jpg'
            },
            {
                name : 'London',
                image : 'https://travel.home.sndimg.com/content/dam/images/travel/fullset/2015/05/28/big-ben-london-england.jpg.rend.hgtvcom.1280.960.suffix/1491582155388.jpeg'
            }   
        ]
        return ( 
            <section className="locations-wrapper">
                <div className="container pull-up-wrapper">
                    <div className="row">
                    {
                        this.state.locations.map(
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