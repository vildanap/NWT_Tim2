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

// public
import Landing from './containers/Landing'
import Location from './components/Location'
import Review from './components/Review'

//protected
import Show from './components/country/Show'
import Create from './components/country/Create'
import Edit from './components/country/Edit'
// Service worker
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
  <BrowserRouter>
    <div>
        <Menu />
        <Route exact path="/" component={Landing} /> 
        <Route exact path="/location" component={Location} />
        <Route exact path="/create" component={Create} />
        <Route exact path="/show/:id" component={Show} />
        <Route path='/edit/:id' component={Edit} />
        
        <Route path="/login" component={Login} />

        <Footer />
    </div>
  </BrowserRouter>, 
	document.getElementById('root')
);

registerServiceWorker()