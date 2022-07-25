import axios from 'axios';
import { Component } from 'react';
import { withRouter } from 'react-router';

import Navbar from '../component/NavBar';
import "./Quote.scss";
import { dailyListQuotesUrl } from '../util/Constants';
import QuoteList from '../component/QuoteList';

export default withRouter(class QuotePage extends Component {
	constructor(props) {
        super(props);
        this.state = {
            quotes: []
        };
    }
    async componentDidMount() {
		// Fetch quotes here
        await this.getQuotes();
    }

    async getQuotes() {
        const response = await axios.get(dailyListQuotesUrl);
        console.log(response);
        if (response) {
            this.setState({
                quotes: response.data || []
            })
        }
    }

    render () {
		// Render the quotes page, similar to Dashboard
        return (
            <div className='quote-page'>
                <Navbar />
                <div className='quote-page-content'>
                    <div className='title'>
                        Quotes 
                    </div>
                    <QuoteList quotes={ this.state.quotes } />
                </div>
            </div>
        )
    }
})