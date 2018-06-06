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

class Create extends Component {

  constructor() {
    super()

    this.state = {
      loading: true,
      comment: '',
      grade: '',
      number_of_likes: 0,
      number_of_dislikes: 0,
      review_type_id: 1,
      user_id: 1,
      city_id: 1,
      types: [] ,
      selectedOption: '',
    }

    this.validator = new SimpleReactValidator()
  }

  componentWillMount() {
    auth.redirectIfNotAuthenticated()
    this.initilize()
  }

  componentDidMount() {
      setTimeout(() => this.setState({ loading: false }), 1200)
  }

  initilize = async () => {
    try {
        let endpoint = "nwt2_ms_review-service-client/types/all";
        let types = await api.send(endpoint)

        this.setState({types : types.data})
    } catch (err) {
        alert('Whoops! Seems like we could not load all the data properly :(')
    }
  } 

  handleChange = (selectedOption) => {
    this.setState({ selectedOption });
    console.log(`Selected: ${selectedOption.value}`);
  }       
  
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = async (e) => {  
    try{
      e.preventDefault()
    
      // trigger the validation
      if(!this.validator.allValid()) {
        this.validator.showMessages();
        this.forceUpdate()

        return
      }
      
      const { comment, grade, number_of_likes, number_of_dislikes, review_type_id,selectedOption, user_id, city_id} = this.state

      let endpoint = "nwt2_ms_review-service-client/reviews/create"
      let response = await api.send(endpoint,{ comment: comment, grade: grade, numberOfLikes: number_of_likes, numberOfDislikes: number_of_dislikes, reviewTypeId: selectedOption.value, userId: localStorage.getItem('uid'), cityId: this.props.location.state.cityId} , "POST")
    
      if(response.status == "200" ||response.status == "201"){ 
        alert("Successfully created the review!")
        // redirect to the location
        window.location = 'location/' + this.state.city_id
      }
    }
    catch (err) {
      alert("We are sorry, something went wrong :(")
    }
  }

  onStarClick(nextValue, prevValue, name) {
    this.setState({grade: nextValue});
  }

  render() {
    const { comment, grade, number_of_likes, number_of_dislikes, review_type_id, user_id, city_id,selectedOption,types} = this.state;
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
                ADD REVIEW
              </h3>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <label htmlFor="isbn">Comment:</label>
                  <textarea className="form-control" name="comment" value={comment} onChange={this.onChange} placeholder="Comment"> </textarea>

                  <div className="text-danger">
                    {this.validator.message('comment', comment, 'required|min:5')}
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="isbn">Grade:</label><br/>
                  <StarRatingComponent 
                  name="rate1" 
                  starCount={5}
                  value={grade}
                  onStarClick={this.onStarClick.bind(this)}
                  />

                  <div className="text-danger">
                    {this.validator.message('grade', grade, 'required')}
                  </div>
                </div>
                <div className="form-group">
                  <label htmlFor="isbn">Review type:</label><br/>
                <Select
                  name="form-field-name"
                  value={selectedOption}
                  onChange={this.handleChange}
                  options = {this.state.types.map((c) => {
                      return {
                          value: c.id,
                          label: c.name
                      };}
                  )}
                />
                <div className="text-danger">
                  {this.validator.message('Review type', selectedOption, 'required')}
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

export default Create;