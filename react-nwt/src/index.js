import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom'

// Main css
import './index.css';

// Components & Containers
import Landing from './containers/Landing'
import Location from './components/Location'
import Review from './components/Review'

// Service worker
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
  <BrowserRouter>
    <div>
        <Route exact path="/" component={Landing} /> 
        <Route exact path="/location" component={Location} />
    </div>
  </BrowserRouter>, 
	document.getElementById('root')
);

registerServiceWorker()