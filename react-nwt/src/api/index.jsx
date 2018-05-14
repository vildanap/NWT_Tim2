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
export const sendRequest = (endpoint, data = {}, method = "GET", headers = {}) => {
  // Configure the request options
  let options = {
    method,
    headers,
    data,
    url: apiconfig.url + endpoint
  }

  // Return axios promise
  return fetch((options.url), {
    method: options.method,
    headers: options.headers,
    data: options.data
  })
}