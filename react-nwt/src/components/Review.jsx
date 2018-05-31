import React, { Component } from 'react'
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import StarRatingComponent from 'react-star-rating-component';
import Modal from 'react-responsive-modal';


import * as api from '../api'

class Review extends Component {

    state = {
        open: false,
        review: [],
        numberOfLikes: 0,
        numberOfDislikes: 0
    };

    constructor() {
        super()
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        this.setState({review: this.props.value, numberOfLikes: this.props.value.numberOfLikes, numberOfDislikes: this.props.value.numberOfDislikes})
    } 

    
    like = async () => {
        let endpoint = "nwt2_ms_review-service-client/reviews/"+this.props.value.id+"/like";
        let response = await api.send(endpoint, {}, "PUT")
        this.setState({ numberOfLikes: this.state.numberOfLikes+1 });
        console.log("LIKED")
    }

    dislike = async () => {
        let endpoint = "nwt2_ms_review-service-client/reviews/"+this.props.value.id+"/dislike";
        let response = await api.send(endpoint, {}, "PUT")
        this.setState({ numberOfDislikes: this.state.numberOfDislikes+1 });

        console.log("DISLIKED")
    }

    onOpenModal = () => {
        this.setState({ open: true });
      };
     
      onCloseModal = () => {
        this.setState({ open: false });
      };
    
    render() {
        const { open, review, numberOfLikes, numberOfDislikes } = this.state;
        return (
            <div className="review-column">
            <br/>
            <div className="row border review-wrapper">
            <div className="col-sm-6">
            <br/>
            <div className="row">
            <div className="col-sm-6 review-username">
            aminapuce
            </div>
            <div className="col-sm-6 review-username">
            <StarRatingComponent 
                name="rate1" 
                starCount={5}
                value={review.grade}
                />
            </div>
            </div>
            <br/>
            <div className="row">
            <i class="fa fa-pencil-square-o icon" onClick={this.onOpenModal}></i>
            <p className="review-comment trim-text">
            &nbsp; {review.comment}
            </p>
            </div>
            <div className="row down">
            <div className="col-sm-8">
            <p class="review-likes">{numberOfLikes} people liked this</p>
            <p class="review-dislikes">{numberOfDislikes} people disliked this</p>
            </div>
            <div className="col-sm-2">
                <i class="fa fa-thumbs-o-up icon-like" onClick={this.like.bind(this)}></i>
            <i class="fa fa-thumbs-o-down icon-dislike" onClick={this.dislike.bind(this)}></i>
            </div>
            </div>
            </div>
            
            <div className="col-sm-6">
            <br/>
            <Carousel className="carousel">
                <div>
                    <img src="https://www.visitberlin.de/system/files/styles/visitberlin_teaser_single_visitberlin_mobile_1x/private/image/_SCH6057_c_Scholvien_PSR_SC_STD_V2_DL_PPT_0.jpg?h=32462309&itok=Xi0CMgn5" />
                </div>
                <div>
                    <img src="https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880_.jpg?itok=J4euvI5S&timestamp=1517230620" />
                </div>
            </Carousel>
            </div>

            <Modal open={open} onClose={this.onCloseModal} center>
            <h5>aminapuce</h5>{review.comment}
            </Modal>
            
            </div>
            </div>
        )
    }
}

export default Review
