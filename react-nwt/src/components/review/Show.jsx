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
      category:{},
      photos:[]
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

        //slike
        let endpoint2 = "nwt2_ms_like-service-client/reviewphotos/review/"+this.state.review.id+"/urls";
        let photos = await api.send(endpoint2)

     
        this.setState({photos : photos.data})
        console.log(this.state.photos)
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
      
    let images = this.state.photos.map(image => {
        return <img key={image} src={image} alt="" className="img-responsive" />
     });
    return (
        <div className="container"> 
        <br></br>     
        <div className="row">
          <div className="col-md-8 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">
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
    <div className="col-md-8 offset-md-3 col-sm-8 offset-sm-2 col-xs-12">

            <h5 class="card-title">Photos</h5>        
            <Carousel className="carousel" >
            {images}
            </Carousel>
            </div>
                <br></br>
    <Link to={`/location/${this.state.review.cityId}`} ><button className="btn btn-primary"> Back</button></Link>
    {this.state.review.id == localStorage.getItem('ui')?<button onClick={this.delete.bind(this, this.state.review.id)} className="btn btn-danger"  >Delete</button> : ''}
    

  </div>
</div>     
           
        </div>
      </div>
      </div>

    );
  }
}

export default Show;