import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

import * as api from '../../api'
import * as auth from '../../auth'
class Index extends Component {

  constructor() {
    super();
    this.state = {
      locations: []
    };
  }

  componentWillMount() {
    auth.redirectIfNotAuthenticated()
    this.initilize()
  }

initilize = async () => {
    try {
        let endpoint = "nwt2_ms_location-service-client/locations/all";
        let locations = await api.send(endpoint)
     
        this.setState({locations : locations.data})
    } catch (err) {
        console.log(err)
    }
}


delete(id){
    
    console.log(id);
    axios.delete('http://localhost:8084/nwt2_ms_location-service-client/locations/'+id+'?access_token='+localStorage.getItem('token'))
      .then((result) => {
        console.log(result);
        alert("Poruka: Deleted!");
      });
  }
  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              LOCATIONS LIST
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="location/create"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Location</Link></h4>
            
            <table class="table">
  <thead class="thead-light">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Description</th>
      <th scope="col">Latitude</th>
      <th scope="col">Longitude</th>
      <th scope="col">Operation</th>

    </tr>
  </thead>
  <tbody>
  {this.state.locations.map(c =>
  
                  <tr>
                    <th scope="row">{c.id}</th>

                    <td><Link to={`/location/${c.id}`}>{c.name}</Link></td>
                    <td>{c.description}</td>
                    <td>{c.latitude}</td>
                    <td>{c.longitude}</td>
                    <td><button onClick={this.delete.bind(this, c.id)} className="btn btn-danger">Delete</button>
                    <Link to={`/location/edit/${c.id}`} className="btn btn-success">Edit</Link>
                    </td>

                  </tr>
                )}
      </tbody>
      </table>

          </div>
        </div>
        </div>


        



    );
  }
}

export default Index;