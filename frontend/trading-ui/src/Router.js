import { Component } from 'react';
// Imports we need for routing, provided in react-router-dom library
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

// We have to import all components to be able to use them.
import Dashboard from './page/Dashboard';

// Initialization of Router Component
// Every React component extends Component from "react" library
export default class Router extends Component {

    render() {
        return (
            <BrowserRouter>
                <Routes>
                    <Route exact path="/">
                        <Navigate to="/dashboard" />
                    </Route>
                    <Route exact path="/dashboard">
                        <Dashboard />
                    </Route>
               </Routes>
            </BrowserRouter>
        )
    }
}