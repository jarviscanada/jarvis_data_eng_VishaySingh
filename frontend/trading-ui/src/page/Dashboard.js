import { Component } from 'react';
import { withRouter } from 'react-router';
import { Input, DatePicker, Modal, Button, Form } from 'antd';
//import axios from 'axios';

//import { createTraderUrl, deleteTraderUrl, tradersUrl } from '../util/constants';
import Navbar from '../component/NavBar';
import TraderList from '../component/TraderList';

import 'antd/dist/antd.min.css';
import "./Dashboard.scss";

export default withRouter(class Dashboard extends Component {
    constructor(props) {
        super(props);
		// Bind methods to this component in constructor so they can set the state of the component
        this.showModal = this.showModal.bind(this);
        this.handleOk = this.handleOk.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.onInputChange = this.onInputChange.bind(this);
        this.onTraderDelete = this.onTraderDelete.bind(this);
        this.state = {
            isModalVisible: false,
            traders: []
        }
    }

    async componentDidMount() {
       // fetch traders 
       await this.getTraders();
    }

    async getTraders() {
				// Set traders to state of the component
				// You should mock traders here for now
        this.setState({
            traders: []
        })
    }


		// Method that sets if the modal for adding traders is visible or not
    showModal() {
        this.setState({
            isModalVisible: true
        });
    };

    
    async handleOk() {
        // Here we would send a request to the backend to create a new trader
        // After creating a new trader, refresh traders list
        await this.getTraders();
        // Close the modal & unset all fields
        this.setState({
            isModalVisible: false,
            firstName: null,
            lastName: null,
            dob: null,
            country: null,
            email: null
        });
    };
    
    handleCancel() {
		// On cancel just close the modal
        this.setState({
            isModalVisible: false,
            firstName: null,
            lastName: null,
            dob: null,
            country: null,
            email: null
        });		
    };

    onInputChange(field, value) {
        this.setState({
            [field]: value
        });
    }

    async onTraderDelete(id) {
        console.log("Trader " + id + " is deleted.");
        // send a request to backend to delete the trader with specific id
        
        // refresh traders list
        await this.getTraders();
    }


    render () {
        return (
            <div className="dashboard">
                <Navbar />
                <div className="dashboard-content">
                    <div className="title">
                        Dashboard
                        <div className="add-trader-button">
                            <Button onClick={this.showModal.bind(this)}>Add New Trader</Button>
                            <Modal title="Add New Trader"  okText="Submit" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel}>
                                <Form
                                    layout="vertical"
                                >
                                    <div className="add-trader-form">
                                        <div className="add-trader-field">
                                            <Form.Item label="First Name">
                                                <Input allowClear={false} placeholder="John" onChange={(event) => this.onInputChange("firstName", event.target.value)} />
                                            </Form.Item>
                                        </div>
                                        <div className="add-trader-field">
                                            <Form.Item label="Last Name">
                                                <Input allowClear={false} placeholder="Doe" onChange={(event) => this.onInputChange("lastName", event.target.value)} />
                                            </Form.Item>
                                        </div>
                                        <div className="add-trader-field">
                                            <Form.Item label="Email">
                                                <Input allowClear={false} placeholder="JohnDoe@email.com" onChange={(event) => this.onInputChange("email", event.target.value)} />
                                            </Form.Item>
                                        </div>
                                        <div className="add-trader-field">
                                            <Form.Item label="Country">
                                                <Input allowClear={false} placeholder="Canada" onChange={(event) => this.onInputChange("country", event.target.value)} />
                                            </Form.Item>
                                        </div>
                                        <div className="add-trader-field">
                                            <Form.Item label="Date of Birth">
                                                <DatePicker style={{width:"100%"}} placeholder="" onChange={(date, dateString) => this.onInputChange("dob", date.format("yyyy-MM-DD"))} />
                                            </Form.Item>
                                        </div>
                                    </div>
                                </Form>
                            </Modal>
                        </div>
                    </div>
                    <TraderList onTraderDeleteClick={ this.onTraderDelete } traders={ this.state.traders } />
                </div>
            </div>
        );
    }
});