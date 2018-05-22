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
            let endpoint = "reviews/1"
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
                    Review 1
                </h4>
            <p>
            Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
            <a href="#">View details »</a>
            </p>
            </div>
        )
    }
}

export default Review