import { Component } from 'react';
import Navbar from '../component/NavBar';
import { withRouter } from 'react-router';
import TraderList from '../component/TraderList';

import "./Dashboard.scss";

export default withRouter(class Dashboard extends Component {
    constructor(props) {
        super(props);
    }

    onTraderDelete(id) {
        //delete trader 
        console.log("Trader " + id + " is deleted.");
    }

    render () {
        return (
            <div className="dashboard">
                <Navbar /> 
                <div className='title'>
                    Dashboard
                </div>
                <TraderList onTraderDeleteClick={this.onTraderDelete} />
            </div>
        );
    }
});