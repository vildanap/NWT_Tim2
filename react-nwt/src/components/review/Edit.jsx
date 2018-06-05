import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import StarRatingComponent from 'react-star-rating-component';
import Select from 'react-select';
import SimpleReactValidator from 'simple-react-validator'

import Loading from '../common/Loading'

import * as api from '../../api'
import * as auth from '../../auth'

class Edit extends Component {

  constructor() {
    super()

    this.state = {
      loading: true,
      id: null,
      review: [],
      types: [] 
    }

    this.validator = new SimpleReactValidator()
  }

  componentWillMount() {
    auth.redirectIfNotAuthenticated()
  }

  componentDidMount() {
    this.initilize(this.props.match.params.id)
    setTimeout(() => this.setState({ loading: false }), 1200)
  }

  initilize = async (id) => {
      try {
          // set the id
          this.setState({ id : id })

          let endpoint = "nwt2_ms_review-service-client/reviews/" + id;
          let review = await api.send(endpoint)
      
          this.setState({review : review.data})

          let endpoint1 = "nwt2_ms_review-service-client/types/all";
          let types = await api.send(endpoint1)
      
          this.setState({types : types.data})

          //slike
          let endpoint2 = "nwt2_ms_like-service-client/reviewphotos/review/" + id + "/urls";
          let photos = await api.send(endpoint2)

          this.setState({photos : photos.data})
      } catch (err) {
          alert("Ups something went wrong")
      }
  } 

  handleChange = (selectedOption) => {
    this.setState({ selectedOption });
  }

  onChange = (e) => {
    const state = this.state.review
    state[e.target.name] = e.target.value
    this.setState(state)
    this.forceUpdate()
    console.log(this.state.review)
  }

  onSubmit = async (e) => {  
    try{
      e.preventDefault()
    
      // trigger the validation
      if(!this.validator.allValid()) {
        this.validator.showMessages()
        this.forceUpdate()

        return
      }
      
      const { comment, grade, number_of_likes, number_of_dislikes, reviewTypeId, userId, cityId} = this.state.review

      let endpoint = "nwt2_ms_review-service-client/reviews/" + this.state.id
      let response = await api.send(
          endpoint, 
          { 
              comment: comment, 
              grade: grade, 
              numberOfLikes: number_of_likes, 
              numberOfDislikes: number_of_dislikes, 
              reviewTypeId: reviewTypeId, 
              userId: userId, 
              cityId: cityId
        }, "PUT")
    
      if(response.status == "200" ||response.status == "201"){ 
        alert("Successfully updated the review the review!")
        // redirect to the location
        window.location = '/location/' + this.state.id
      }
    }
    catch (err) {
      alert("We are sorry, something went wrong :(")
    }
  }

  onStarClick(nextValue, prevValue, name) {
    const state = this.state.review
    state['grade'] = nextValue
    this.setState(state)
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
                <div className="container">      
                    <div className="row">
                      <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
              <h3 className="panel-title">
                EDIT REVIEW
              </h3>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <label htmlFor="isbn">Comment:</label>
                  <textarea className="form-control" name="comment" value={this.state.review.comment} onChange={this.onChange} placeholder="Comment"></textarea>
                  <div className="text-danger">
                    { this.validator.message('comment', this.state.review.comment, 'required') }
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="isbn">Grade:</label><br/>
                  <StarRatingComponent 
                  name="rate1" 
                  starCount={5}
                  value={this.state.review.grade}
                  onStarClick={this.onStarClick.bind(this)}
                  />
                <div className="text-danger">
                    {this.validator.message('grade', this.state.review.grade, 'required')}
                  </div>
                </div>

                <button type="submit" className="btn btn-primary float-right">Submit</button>
                    </form>
                  </div>
                </div>
              </div>
            )
        }
      </div>
    );
  }
}

export default Edit;