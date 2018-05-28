/* Import */
import axios from 'axios'

/* Initialize the API configuration */
import apiconfig from './config';

/**
 * @desc
 * The base functionalitie of this method is to send a request (GET initally, but also POST, PUT and DELETE)
 * We provide the parameters as shown below
 * The method returns a Promise create by axios
 * 
 * @param {string} endpoint 
 *  the endpoint that we are targeting aat
 * @param {array} data
 *  data payload that we send, body 
 * @param {string} method 
 *  request method that we use 
 * @param {array} headers 
 *  setting up headers of the call (e.g. Authorization heades if needed)
 */
export const send = (endpoint, data = {}, method = "GET", headers = {}) => {
  // set authorizaiton token if avialable
  if(localStorage.getItem('token') !== null) {
    headers = {
      Authorization : "Bearer " + localStorage.getItem('token'),
      ...headers
    }
  }

  // Configure the request options
  let options = {
    method,
    headers,
    data,
    url: apiconfig.url + endpoint,
  }

  
  // Return axios promise
  return axios(options);
}