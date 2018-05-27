import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Edit extends Component {

  constructor(props) {
    super(props);
    this.state = {
      country: {}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8082/countries/'+this.props.match.params.id+"?access_token=183f777d-80a3-43c4-bc6c-7f71bf1197cf")
      .then(res => {
        this.setState({ country: res.data });
        console.log(this.state.country);
      });
  }

  onChange = (e) => {
    const state = this.state.country
    state[e.target.name] = e.target.value;
    this.setState({country:state});
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { name, address, city, postalCode, phone } = this.state.country;

    axios.put('http://localhost:8082/countries/'+this.props.match.params.id, { name, address, city, postalCode, phone })
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id)
      });
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