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
            counterSky : null,
            check : false
            
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
            if(response.status === 200) {
                response.json().then(function(data) {
                    self.setState({
                        allRectangle: data 
                    });
                    fetch('http://localhost:8090/intPoints/getCounterSky', {
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json',
                        }
                    }).then(function(response) {
                        if(response.status === 200) {
                            response.json().then(function(data) {
                                self.setState({
                                    counterSky: data 
                                }, function() {self.updateCanvas()});
                            })
                        }
                    });
                })
            }
        });

        
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

    drawRectangle(props) {
        const {ctx, x1, x2, height} = props;


        ctx.strokeStyle = "#000000";
        ctx.beginPath();
        ctx.moveTo(x1, 500);
        ctx.lineTo(x1, 500 - height);
        ctx.lineTo(x2, 500 - height);
        ctx.lineTo(x2, 500);
        ctx.stroke();
    }

    drawSky(props) {
        const {ctx, x1, x2, nextXleft, height} = props;

        
        ctx.beginPath();
        ctx.strokeStyle = "#0000FF";
        ctx.lineWidth = "3";
        ctx.lineTo(x1, 500 - height);
        ctx.lineTo(x2, 500 - height);
        if (nextXleft !== x2) {
            ctx.lineTo(x2, 500);
            ctx.lineTo(nextXleft, 500);
        }
        ctx.stroke();
    }

    updateCanvas() {
        let self = this;
        const ctx = document.getElementById("canvas").getContext("2d");
        ctx.clearRect(0, 0, 1100, 500);
        for (let rectangleIndex in this.state.allRectangle) {
            let rectangle = this.state.allRectangle[rectangleIndex];
            this.drawRectangle({ctx, x1: rectangle.leftPoint, x2: rectangle.rightPoint, height: rectangle.height, color: "#000000"});
        }
        let keys = Object.keys(self.state.counterSky);
        keys.sort(function(a, b) {return self.state.counterSky[a].leftPoint - self.state.counterSky[b].leftPoint});       
        ctx.beginPath();
        ctx.strokeStyle = "#0000FF";
        ctx.lineWidth = "3";
        ctx.moveTo(0, 500);
        ctx.lineTo(self.state.counterSky[keys[0]].leftPoint, 500);
        ctx.stroke();
        for (let i = 0; i < keys.length; i++) {
            let skyElement = this.state.counterSky[keys[i]];
            console.log(skyElement);

            this.drawSky({ctx, x1: skyElement.leftPoint, x2: skyElement.rightPoint, nextXleft: (i + 1 === keys.length) ? 1100 : this.state.counterSky[keys[i+1]].leftPoint, height: skyElement.height});
        }
        this.setState({
            check: !this.state.check 
        });
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
                        <canvas id="canvas" width={1100} height={500}/>    
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