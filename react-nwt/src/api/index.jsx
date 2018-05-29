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
export const send = (endpoint, data = {}, method = "GET", headers = {}, formData = false) => {
  // set authorizaiton token if avialable
  if(localStorage.getItem('token') !== null) {
    headers = {
      'Authorization' : "Bearer " + localStorage.getItem('token'),
      ...headers
    }
  }

  console.log(headers)
  // Configure the request options
  let options = {
    method,
    headers,
    data,
    url: apiconfig.url + endpoint,
  }

  // for application/x-www-form-urlencoded
  if(formData) {
    var formBody = [];

    for (var property in options.data) {
      var encodedKey = encodeURIComponent(property);
      var encodedValue = encodeURIComponent(options.data[property]);
      formBody.push(encodedKey + "=" + encodedValue);
    }

    formBody = formBody.join("&");

    options.data = formBody

    return axios(options)
  }

  // Return axios promise
  return axios(options)
}