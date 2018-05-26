import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import * as api from '../../api'
import StarRatingComponent from 'react-star-rating-component';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      comment: '',
      grade: '',
      number_of_likes: 0,
      number_of_dislikes: 0,
      review_type_id: 1,
      user_id: 1,
      city_id: 1     
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = async (e) => {
    e.preventDefault();
   
    const { comment, grade, number_of_likes, number_of_dislikes, review_type_id, user_id, city_id} = this.state;

    let endpoint = "nwt2_ms_review-service-client/reviews/create"
    let response = await api.send(endpoint,{ comment: comment, grade: grade, numberOfLikes: number_of_likes, numberOfDislikes: number_of_dislikes, reviewTypeId: review_type_id, userId: user_id, cityId: city_id} , "POST")
    console.log(response)
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
  }

  onStarClick(nextValue, prevValue, name) {
    this.setState({grade: nextValue});
  }

  render() {
    const { comment, grade, number_of_likes, number_of_dislikes, review_type_id, user_id, city_id} = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              ADD REVIEW
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Comment:</label>
                <input type="text" className="form-control" name="comment" value={comment} onChange={this.onChange} placeholder="Comment" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">Grade:</label><br/>
                <StarRatingComponent 
                name="rate1" 
                starCount={5}
                value={grade}
                onStarClick={this.onStarClick.bind(this)}
                />
              </div>
              <button type="submit" className="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;