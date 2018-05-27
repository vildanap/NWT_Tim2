import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import * as api from '../../api'

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

  onSubmit = async (e) => {
    e.preventDefault();
   
    const { name} = this.state;

    let endpoint = "nwt2_ms_location-service-client/countries/new?access_token=183f777d-80a3-43c4-bc6c-7f71bf1197cf"
    let response = await api.send(endpoint, { name }, "POST")
    
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
  }

  render() {
    const { name} = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              ADD COUNTRY
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
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