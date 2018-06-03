import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import * as api from '../../api'
import StarRatingComponent from 'react-star-rating-component';
import Select from 'react-select';


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
      city_id: 1,
      types: [] ,
      selectedOption: '',
    
    };
  }

  componentWillMount() {
    this.initilize()
  }
  initilize = async () => {
    try {
        let endpoint = "nwt2_ms_review-service-client/types/all";
        let types = await api.send(endpoint)

     
        this.setState({types : types.data})

        console.log(this.state.types)
    } catch (err) {
        console.log(err)
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
    e.preventDefault();
   
    const { comment, grade, number_of_likes, number_of_dislikes, review_type_id,selectedOption, user_id, city_id} = this.state;
try{
       
    let endpoint = "nwt2_ms_review-service-client/reviews/create"
    let response = await api.send(endpoint,{ comment: comment, grade: grade, numberOfLikes: number_of_likes, numberOfDislikes: number_of_dislikes, reviewTypeId: selectedOption.value, userId: localStorage.getItem('uid'), cityId: city_id} , "POST")
    
    console.log(response)
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
}
catch (err) {
  console.log(err)
}
  }

  onStarClick(nextValue, prevValue, name) {
    this.setState({grade: nextValue});
  }

  render() {
    const { comment, grade, number_of_likes, number_of_dislikes, review_type_id, user_id, city_id,selectedOption,types} = this.state;
    return (
      <div className="container">      
                  <div className="row">
                    <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
            <h3 className="panel-title">
              ADD REVIEW
            </h3>
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Comment:</label>
                <textarea className="form-control" name="comment" value={comment} onChange={this.onChange} placeholder="Comment"> </textarea>
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