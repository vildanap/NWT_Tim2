import React, { Component } from 'react'

import * as api from '../api'

class Review extends Component {
    constructor() {
        super()

        this.state = {
            review: []
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        try {
            let endpoint = "nwt2_ms_review-service-client/reviews/1"
            let review = await api.send(endpoint)

            this.setState({review : review.data})

            console.log(this.state.review)
        } catch (err) {
            console.log(err)
        }
    } 

    render() {
        return (
            
            <div>
                 <h4>
                Review {this.props.value.id}
                     </h4>
                 <p>
                 {this.props.value.comment}
                 </p>
                 <p>
                 <a href="#">View details »</a>
                 </p>
            </div>
        )
    }
}

export default Review