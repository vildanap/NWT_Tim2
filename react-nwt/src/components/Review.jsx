import React, { Component } from 'react'
import { Carousel } from 'react-responsive-carousel'
import 'react-responsive-carousel/lib/styles/carousel.min.css'
import StarRatingComponent from 'react-star-rating-component'
import Modal from 'react-responsive-modal'

import * as api from '../api'

class Review extends Component {
    constructor() {
        super()

        this.state = {
            open: false,
            review: [],
            numberOfLikes: 0,
            numberOfDislikes: 0,
            hideReview: false,
            userId: null,
            username: ''
        }
    }

    componentWillMount() {
        this.initilize()
    }

    initilize = async () => {
        try {
            this.setState({
                review: this.props.value, 
                numberOfLikes: this.props.value.numberOfLikes, 
                numberOfDislikes: this.props.value.numberOfDislikes,
                userId: this.props.value.userId
            })

            let id = this.props.value.userId

            let endpoint = 'nwt2_ms_review-service-client/user/' + id
            let response = await api.send(endpoint)

            let username = response.data.username

            this.setState({ username: username })
        } catch(err) {
            alert("Whoops! Something went wrong while redaing this review. Please try again!")
        }
    } 
    
    like = async () => {
        try {
            // check if there is already a reaction
            if(localStorage.getItem('reaction-' + this.props.value.id) != null) {
                alert('You have already reacted to this review!')
                return
            }

            let endpoint = "nwt2_ms_review-service-client/reviews/"+this.props.value.id+"/like";
            let response = await api.send(endpoint, {}, "PUT")
            this.setState({ numberOfLikes: this.state.numberOfLikes+1 });

            localStorage.setItem('reaction-' + this.props.value.id, true)
        } catch(err) {
            alert("We are sorry, something went wrong.")
        }
    }

    dislike = async () => {
        try {
            if(localStorage.getItem('reaction-' + this.props.value.id) != null) {
                alert('You have already reacted to this review!')
                return
            }

            let endpoint = "nwt2_ms_review-service-client/reviews/"+this.props.value.id+"/dislike";
            let response = await api.send(endpoint, {}, "PUT")
            this.setState({ numberOfDislikes: this.state.numberOfDislikes+1 });

            localStorage.setItem('reaction-' + this.props.value.id, true)
        } catch(err) {
            console.log(err)
            alert("We are sorry, something went wrong.")
        }
    }

    deleteReview = async () => {
        let endpoint = "nwt2_ms_review-service-client/reviews/" + this.props.value.id;
        let response = await api.send(endpoint, {}, "DELETE")

        this.props.reinitilize()

        console.log("DELETED")
    }

    onOpenModal = () => {
        this.setState({ open: true });
    };
    
    onCloseModal = () => {
        this.setState({ open: false });
    };
    
    render() {
        const { username, open, review, numberOfLikes, numberOfDislikes, hideReview } = this.state;
        return (
            <div className="review-column" hidden={hideReview}>
            <br/>
            <div className="row border review-wrapper">
            <div className="col-sm-6">
            <br/>
            <div className="row">
            <div className="col-sm-6 review-username">
            {username}
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

            <div hidden={localStorage.getItem('uid')!=review.userId}>
            <i class="fa fa-trash icon-dislike" onClick={this.deleteReview.bind(this)}></i>
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
