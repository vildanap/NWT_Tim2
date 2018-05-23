import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom'

// Main css
import './index.css';

// Components & Containers
import Menu from './components/common/Menu'
import Footer from './components/common/Footer'

import Landing from './containers/Landing'
import Location from './components/Location'
import Review from './components/Review'

// Service worker
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
  <BrowserRouter>
    <div>
        <Menu />
        <Route exact path="/" component={Landing} /> 
        <Route exact path="/location" component={Location} />
        <Footer />
    </div>
  </BrowserRouter>, 
	document.getElementById('root')
);

registerServiceWorker()