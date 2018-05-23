import React, { Component } from 'react'

import Location from './Location'

class Locations extends Component {
    constructor() {
        super()
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
                        <Location city={cities[0]}/>
                        <Location city={cities[1]}/>
                        <Location city={cities[2]}/>

                        <Location city={cities[0]}/>
                        <Location city={cities[1]}/>
                        <Location city={cities[2]}/>
                    </div>
                </div>
            </section>
        )
    }
}

export default Locations