import React, { Component } from 'react';
import CanvasComponent from './CanvasComponent';
import Grid from 'react-bootstrap/lib/Grid';
import Nav from 'react-bootstrap/lib/Nav';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem  from 'react-bootstrap/lib/NavItem';
import FormControl from 'react-bootstrap/lib/FormControl';
import Button from 'react-bootstrap/lib/Button';
import ButtonToolbar from 'react-bootstrap/lib/ButtonToolbar';
import { Link, browserHistory } from 'react-router';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import Well from 'react-bootstrap/lib/Well';
import './bootstrap.css';


class App extends Component {
    constructor() {
        super();
        this.state = {
            allRectangle : null,
            counterSky : null
            
        }
        this.generateIntRectangleAndCounterSky = this.generateIntRectangleAndCounterSky.bind(this);
        this.generateRealReactangleAndCounterSky = this.generateRealReactangleAndCounterSky.bind(this);
    };

    generateIntRectangleAndCounterSky() {
        let self = this;
        fetch('http://localhost:8090/intPoints/generateRectangle', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                number: 5,
                maxRangeX: 1100,
                maxRangeY: 500
            })
        }).then(function(response) {
            if(response.status == 200) {
                response.json().then(function(data) {
                    self.setState({
                        allRectangle: data 
                    });
                })
            }
        });

        fetch('http://localhost:8090/intPoints/getCounterSky', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(function(response) {
            if(response.status == 200) {
                response.json().then(function(data) {
                    self.setState({
                        counterSky: data 
                    });
                })
            }
        })

    }

    generateRealReactangleAndCounterSky() {
        fetch('http://localhost:8090/realPoints/', {
            method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
            body: JSON.stringify({
                code: "code"})
        })
    }

    render() {
        return (
            <div>
                <Navbar>
                    <Navbar.Header>
                        <Navbar.Brand>
                          <a>Application for Delightex Internship</a>
                        </Navbar.Brand>
                        <Navbar.Toggle />
                    </Navbar.Header>
                    <Navbar.Collapse>
                    </Navbar.Collapse>
                </Navbar>
                <Grid>
                    <Well>
                        <CanvasComponent allRectangle = {this.state.allRectangle} counterSky = {this.state.counterSky}/>
                    </Well>
                    <ButtonToolbar>
                        <Button bsStyle='success' onClick={this.generateIntRectangleAndCounterSky}>
                            {"Integer points"}
                        </Button>
                        <Button bsStyle='success' onClick={this.generateRealReactangleAndCounterSky}>
                            {"Real points"}
                        </Button>
                    </ButtonToolbar>
                </Grid>    
            </div>
        ); 
    }
}

export default App;