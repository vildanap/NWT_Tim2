import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      name: '',
      error: ''     
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();
   
    const { name} = this.state;

    axios.post('http://localhost:8082/countries/new', { name })
    .then(function (response) {
      console.log("Response");
        console.log(response);
        if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
      })
      .catch(function (error) {
        console.log("Error");
        console.log(error);

      if(error.response.data.errorMessage == null)
        {alert("Poruka: " + error.response.data.fieldErrors[0].message);}      
        else{alert("Poruka: " + error.response.data.errorMessage);}
      });      
  }

  render() {
    const { name} = this.state;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              ADD COUNTRY
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="isbn">Name:</label>
                <input type="text" class="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
              </div>
              
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;