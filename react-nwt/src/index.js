import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom'

// Main css
import './index.css';

// Components & Containers
// commone
import Menu from './components/common/Menu'
import Footer from './components/common/Footer'

// authenticaiton
import Login from './containers/Login'
import Registration from './containers/Registration'

// public
import Landing from './containers/Landing'
import Location from './containers/Location'
import Review from './components/Review'

//protected
import ShowReview from './components/review/Show'
import CreateReview from './components/review/Create'
import EditReview from './components/review/Edit'
import ShowCountry from './components/country/Show'
import CreateCountry from './components/country/Create'
import EditCountry from './components/country/Edit'
import LocationCreate from './components/location/Create'
import LocationsAll from './components/location/Index'
import LocationEdit from './components/location/Edit'


// Service worker
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
  <BrowserRouter>
    <div>
        <Menu />
        <Route exact path="/" component={Landing} /> 
  
        <Route path="/location/:id" component={Location} />
        <Route exact path="/country/create" component={CreateCountry} />
        <Route exact path="/country/show/:id" component={ShowCountry} />
        <Route path='/country/edit/:id' component={EditCountry} />
        <Route exact path="/review/create" component={CreateReview} />
        <Route exact path="/review/show/:id" component={ShowReview} />
        <Route path='/review/edit/:id' component={EditReview} />
        <Route exact path="/location/create" component={LocationCreate} />
        <Route exact path="/location" component={LocationsAll} />
        <Route path='/location/edit/:id' component={LocationEdit} />

        <Route path="/registration" component={Registration} />
        <Route path="/login" component={Login} />

        <Footer />
    </div>
  </BrowserRouter>, 
	document.getElementById('root')
);

registerServiceWorker()