import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import * as api from '../api'

import Select from 'react-select';
import 'react-select/dist/react-select.css';

class LocationCreate extends Component {

  constructor() {
    super();
    this.state = {
      name: '',
      description: '',
      photoUrl : '',
      longitude: '',
      latitude: '',
      country: '' ,
      selectedOption: '',
      countries: []     
    };
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

  componentWillMount() {
    this.initilize()
}

initilize = async () => {
    try {
        let endpoint = "nwt2_ms_location-service-client/countries/all"+"?access_token=8b79b001-4343-4d70-99a9-a6ecd7edfb2c"
        let countries = await api.send(endpoint)

     
        this.setState({countries : countries.data})

        console.log(this.state.countries)
    } catch (err) {
        console.log(err)
    }
} 

  onSubmit = async (e) => {
    e.preventDefault();
   
    const { name, description, photoUrl, longitude, latitude, country,selectedOption} = this.state;

    let endpoint = "nwt2_ms_location-service-client/locations/new?access_token=8b79b001-4343-4d70-99a9-a6ecd7edfb2c"
    let response = await api.send(endpoint, { name:name, description:description, photoUrl:photoUrl, longitude:longitude, latitude:latitude, country: { name: selectedOption.label, id: selectedOption.value} }, "POST")
    
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
  }

  render() {
    var options = this.state.countries;
    //var Answer = countries => 
    //<select>{this.state.countries.map(x => <option key={x.id} value={x.name}>{x.name}</option>)}</select>;
    const {name, description, photoUrl, longitude, latitude, country,selectedOption } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              ADD LOCATION
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">Description:</label>
                <input type="text" className="form-control" name="description" value={description} onChange={this.onChange} placeholder="Name" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">Photo URL:</label>
                <input type="text" className="form-control" name="photoUrl" value={photoUrl} onChange={this.onChange} placeholder="Name" />
              </div>

               <div className="form-group">
                <label htmlFor="isbn">Longitude:</label>
                <input type="text" className="form-control" name="longitude" value={longitude} onChange={this.onChange} placeholder="Name" />
              </div>

               <div className="form-group">
                <label htmlFor="isbn">Latitude:</label>
                <input type="text" className="form-control" name="latitude" value={latitude} onChange={this.onChange} placeholder="Name" />
              </div>
             
              <Select
        name="form-field-name"
        value={selectedOption}
        onChange={this.handleChange}
        options = {this.state.countries.map((c) => {
            return {
                value: c.id,
                label: c.name
            };}
        )}
      />


                 <button type="submit" className="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default LocationCreate;