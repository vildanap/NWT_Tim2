import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Show extends Component {

  constructor(props) {
    super(props);
    this.state = {
      country: {}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8082/countries/'+this.props.match.params.id)
      .then(res => {
        this.setState({ country: res.data });
        console.log(this.state.country);
      });
  }

  delete(id){
    console.log(id);
    axios.delete('http://localhost:8082/countries/'+id)
      .then((result) => {
        alert("Poruka: Deleted!");
        this.props.history.push("/")
      });
  }

  render() {
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
            Country Details
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Home</Link></h4>
            <dl>
              <dt>Name:</dt>
              <dd>{this.state.country.name}</dd>
              
            </dl>
            <Link to={`/country/edit/${this.state.country.id}`} className="btn btn-success">Edit</Link>&nbsp;
            <button onClick={this.delete.bind(this, this.state.country.id)} className="btn btn-danger">Delete</button>
          </div>
        </div>
      </div>
    );
  }
}

export default Show;