import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import StarRatingComponent from 'react-star-rating-component';
import * as api from '../../api'
import { Carousel } from 'react-responsive-carousel';

class Show extends Component {

  constructor(props) {
    super(props);
    this.state = {
      review: {},
      category:{}
    };
  }


  componentWillMount() {
    this.initilize()
  }

initilize = async () => {
    try {
        let endpoint = "nwt2_ms_review-service-client/reviews/"+this.props.match.params.id;
        let review = await api.send(endpoint)

     
        this.setState({review : review.data})
        console.log(this.state.review)

        let endpoint1 = "nwt2_ms_review-service-client/types/"+this.state.review.reviewTypeId;
        let category = await api.send(endpoint1)

     
        this.setState({category : category.data})
        console.log(this.state.category)
    } catch (err) {
        console.log(err)
    }
} 

  

  delete(id){
    console.log(id);
    axios.delete('http://localhost:8084/nwt2_ms_review-service-client/reviews/'+id+'?access_token='+localStorage.getItem('token'))
      .then((result) => {
        console.log(result);
        alert("Poruka: Deleted!");
        this.props.history.push("/")
      });
  }

  render() {
    return (
        <div className="container"> 
        <br></br>     
        <div className="row">
          <div className="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
          <div class="card">
  <div class="card-header">
    Review preview
  </div>
  <div class="card-body">
    <h5 class="card-title">Review type</h5>
    <p class="card-text">{this.state.category.name}</p>
    <h5 class="card-title">Comment</h5>
    <p class="card-text">{this.state.review.comment}</p>
    <h5 class="card-title">Grade</h5>
    <StarRatingComponent 
                name="rate1" 
                starCount={5}
                value={this.state.review.grade}
                />
            <br/>
            <Carousel className="carousel">
                <div>
                    <img src="https://www.visitberlin.de/system/files/styles/visitberlin_teaser_single_visitberlin_mobile_1x/private/image/_SCH6057_c_Scholvien_PSR_SC_STD_V2_DL_PPT_0.jpg?h=32462309&itok=Xi0CMgn5" />
                </div>
                <div>
                    <img src="https://www.berlin-welcomecard.de/sites/default/files/styles/stage_desktop_20x/public/berlin-welcomecard_hero_2880_.jpg?itok=J4euvI5S&timestamp=1517230620" />
                </div>
            </Carousel>
                <br></br>
    <Link to="/" ><button className="btn btn-primary"> Back</button></Link>
    <button onClick={this.delete.bind(this, this.state.review.id)} className="btn btn-danger"  >Delete</button>

  </div>
</div>     
           
        </div>
      </div>
      </div>

    );
  }
}

export default Show;