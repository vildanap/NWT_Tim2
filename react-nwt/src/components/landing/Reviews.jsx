import React, { Component } from 'react'

import Review from '../Review'

class Reviews extends Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    {
                        this.props.recent.map((item, key) => 
                            <Review value={item} />
                        )
                    }
                </div>
            </div>
        )
    }
}

export default Reviews