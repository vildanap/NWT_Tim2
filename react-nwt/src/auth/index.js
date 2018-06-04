import React from 'react'

/**
 * We use this function to redirect to login page if the user is 
 * not authenticaed and this is the restricted area
 */
export function redirectIfNotAuthenticated() {
    if(localStorage.getItem('token') === null)
        window.location = '/login';
} 

/**
 * We use this function to redriect from the area that is hiden
 * to any authenticated user
 * e.g. Login page; Registation page
 */
export function redirectIfAuthenticated() {
    if(localStorage.getItem('token') !== null)
        window.location = '/';
} 

/**
 *  Check if an user is authetnicate
 */
export function isAuthenticated() {
    if(localStorage.getItem('token') != null)
        return true
    else 
        return false
}

/**
 * Logout function
 */
export function logout() {
    localStorage.removeItem('token')
    window.location = '/'
}


/**
 * My reviews function
 */
export function myreviews() {
    window.location = '/myreviews'
}