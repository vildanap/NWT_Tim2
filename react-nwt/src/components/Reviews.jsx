import React, { Component } from 'react'

import Loading from '../components/common/Loading'
import Review from '../components/Review'
import { Link } from 'react-router-dom'

import * as api from '../api'

class Reviews extends Component {
    constructor() {
        super()

        this.state = {
            loading: true,
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
            let endpoint = "nwt2_ms_review-service-client/reviews/user/" + localStorage.getItem('uid');
            let reviews = await api.send(endpoint)

            this.setState({reviews : reviews.data})
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
                                    <h2>My reviews</h2>
                                </div>
                                <img src="https://i.pinimg.com/originals/de/fc/39/defc395955a5dd93a78b4d648ab2c05a.jpg" />
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

export default Reviews