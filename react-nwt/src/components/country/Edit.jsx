import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import * as api from '../../api'


class Edit extends Component {

  constructor(props) {
    super(props);
    this.state = {
      country: {}
    };
  }

  componentWillMount() {
    this.initilize()
}

initilize = async () => {
    try {
        let endpoint = "nwt2_ms_location-service-client/countries/"+this.props.match.params.id;
        let location = await api.send(endpoint)

        
        this.setState({country : location.data})

        console.log(this.state.location)
        console.log(this.state.reviews)
    } catch (err) {
        console.log(err)
    }
} 



  onChange = (e) => {
    const state = this.state.country
    state[e.target.name] = e.target.value;
    this.setState({country:state});
  }

  onSubmit = async (e) => {
    e.preventDefault();
    const { name } = this.state.country;  

    let endpoint = "nwt2_ms_location-service-client/countries/"+this.props.match.params.id;
    let response = await api.send(endpoint, { name }, "PUT")
    
    if(response.status == "200" ||response.status == "201"){ alert("Poruka: Updated!");}
  }


  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              EDIT Country
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.state.country.id}`}><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Home</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" value={this.state.country.name} onChange={this.onChange} placeholder="Name" />
              </div>
              
              <button type="submit" class="btn btn-default">Update</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;