import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import SimpleReactValidator from 'simple-react-validator'

import * as api from '../../api'

class Create extends Component {

  constructor() {
    super()

    this.state = {
      name: '',
      error: ''     
    }

    this.validator = new SimpleReactValidator()
  }

  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = async (e) => {
    e.preventDefault();

    if(!this.validator.allValid()) {
      this.validator.showMessages();
      this.forceUpdate()

      return
    }
   
    const { name } = this.state;

    let endpoint = "nwt2_ms_location-service-client/countries/new";
    let response = await api.send(endpoint, { name }, "POST")
    
    if(response.status == "200" ||response.status == "201") { 
      alert("Poruka: Created!")
    }
  }

  render() {
    const { name} = this.state;
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-sm-12">
            <div className="panel panel-default">
              <div className="panel-heading">
                <h3 className="panel-title">
                  ADD COUNTRY
                </h3>
              </div>
              <div className="panel-body">
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <label htmlFor="isbn">Name:</label>
                    <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
                    <div className="text-danger">
                      {this.validator.message('name', name, 'required|alpha|min:5')}
                    </div>
                  </div>
                  
                  <button type="submit" className="btn btn-primary float-right">Submit</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;