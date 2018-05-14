import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom'

// Main css
import './index.css';

// Components & Containers
import Landing from './containers/Landing'

// Service worker
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
  <BrowserRouter>
    <div>
        <Route exact path="/" component={Landing} /> 
    </div>
  </BrowserRouter>, 
	document.getElementById('root')
);

registerServiceWorker()