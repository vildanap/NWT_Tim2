import React, { Component } from 'react'
import Review from './Review'

import * as api from '../api'

class Location extends Component {
    constructor() {
        super()

        this.state = {
            location: []
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        try {
            let endpoint = "locations/1"
            let location = await api.send(endpoint)

            this.setState({location : location.data})

            console.log(this.state.location)
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
                    <Review value={5} />;
                </div>
            </div>
        )
    }
}

export default Location