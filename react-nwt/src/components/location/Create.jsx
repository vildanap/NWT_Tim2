import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import 'react-select/dist/react-select.css';

import * as api from '../../api'
import * as auth from '../../auth'

import Loading from '../common/Loading'

class Create extends Component {

  constructor() {
    super();
    this.state = {
      loading : true,
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

  componentDidMount() {
    setTimeout(() => this.setState({ loading: false }), 1200)
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
    auth.redirectIfNotAuthenticated()
    this.initilize()
  }

initilize = async () => {
    try {
        let endpoint = "nwt2_ms_location-service-client/countries/all";
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

    let endpoint = "nwt2_ms_location-service-client/locations/new";
    let response = await api.send(endpoint, { name:name, description:description, photoUrl:photoUrl, longitude:longitude, latitude:latitude, country: { name: selectedOption.label, id: selectedOption.value} }, "POST")
    
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Created!");}
  }

  render() {
    var options = this.state.countries;
    //var Answer = countries => 
    //<select>{this.state.countries.map(x => <option key={x.id} value={x.name}>{x.name}</option>)}</select>;
    const {name, description, photoUrl, longitude, latitude, country,selectedOption } = this.state;
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
                    ADD LOCATION
                  </h3>
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
            <br></br>
                      <button type="submit" className="btn btn-default">Submit</button>
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