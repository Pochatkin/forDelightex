import React, { Component } from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Navbar from 'react-bootstrap/lib/Navbar';
import Button from 'react-bootstrap/lib/Button';
import ButtonToolbar from 'react-bootstrap/lib/ButtonToolbar';
import Well from 'react-bootstrap/lib/Well';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import FormControl from 'react-bootstrap/lib/FormControl';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import './bootstrap.css';



class App extends Component {
    constructor() {
        super();
        this.state = {
            allRectangle : null,
            counterSky : null,
            check : false,
            number : 0
        }
        this.generateRectangleAndCounterSky = this.generateRectangleAndCounterSky.bind(this);
        this.updateCanvasForDrawSkyCounter = this.updateCanvasForDrawSkyCounter.bind(this);
        this.handleChange = this.handleChange.bind(this);
    };

    generateRectangleAndCounterSky(isInt) {
        let self = this;
        let url;
        if (this.state.number > 0) {
            if (isInt) {
                url = "http://localhost:8090/intPoints";
            } else {
                url = "http://localhost:8090/realPoints";
            } 
            fetch(url + '/generateRectangle', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    number: this.state.number,
                    maxRangeX: 1100,
                    maxRangeY: 500
                })
            }).then(function(response) {
                if(response.status === 200) {
                    response.json().then(function(data) {
                        self.setState({
                            allRectangle: data 
                        });
                        fetch(url + '/getCounterSky', {
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
                                    }, function() {self.updateCanvasForDrawRectangle()});
                                })
                            }
                        });
                    })
                }
            });
        }

        
    }

    

    drawRectangle(props) {
        const {ctx, x1, x2, height} = props;


        ctx.strokeStyle = "#000000";
        ctx.beginPath();
        ctx.lineWidth = "1";
        ctx.moveTo(x1, 500);
        ctx.lineTo(x1, 500 - height);
        ctx.lineTo(x2, 500 - height);
        ctx.lineTo(x2, 500);
        ctx.stroke();
    }

    drawSky(props) {
        const {ctx, x1, x2, nextXleft, height} = props;

        if (x1 <= x2) {

            ctx.lineTo(x1, 500 - height);
            ctx.lineTo(x2, 500 - height);
            if (nextXleft !== x2) {
                ctx.lineTo(x2, 500);
                ctx.lineTo(nextXleft, 500);
            }
        }
    }

    updateCanvasForDrawRectangle() {
        const ctx = document.getElementById("canvas").getContext("2d");
        ctx.clearRect(0, 0, 1100, 500);
        for (let rectangleIndex in this.state.allRectangle) {
            let rectangle = this.state.allRectangle[rectangleIndex];
            this.drawRectangle({ctx, x1: rectangle.leftPoint, x2: rectangle.rightPoint, height: rectangle.height, color: "#000000"});
        }
        
        this.setState({
            check: true 
        });
    }

    updateCanvasForDrawSkyCounter() {
        let self = this;
        const ctx = document.getElementById("canvas").getContext("2d");
        let keys = Object.keys(self.state.counterSky);
        keys.sort(function(a, b) {return self.state.counterSky[a].leftPoint - self.state.counterSky[b].leftPoint});
        ctx.beginPath();
        ctx.strokeStyle = "#0000FF";
        ctx.lineWidth = "1";
        ctx.moveTo(0, 500);
        ctx.lineTo(self.state.counterSky[keys[0]].leftPoint, 500);
        for (let i = 0; i < keys.length; i++) {
            let skyElement = this.state.counterSky[keys[i]];
            if (i + 1 != keys.length && skyElement.rightPoint > this.state.counterSky[keys[i + 1]].leftPoint) {
                if (skyElement.height <= this.state.counterSky[keys[i + 1]].height) {
                    continue;
                } else {
                    i++;
                }
            } 
            if (skyElement.leftPoint !== skyElement.rightPoint) {
                this.drawSky({ctx, x1: skyElement.leftPoint, x2: skyElement.rightPoint, nextXleft: (i + 1 === keys.length) ? 1100 : this.state.counterSky[keys[i+1]].leftPoint, height: skyElement.height});
            }
        }
        ctx.stroke();
        this.setState({
            check: false 
        });
    }

    handleChange(e) {
        this.setState({
            number : e.target.value 
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
                    <form>
                        <FormGroup controlId="numberRectangle">
                            <FormControl type="text" value={this.state.number} placeholder="Enter number of rectangle" onChange={this.handleChange}/>
                            <HelpBlock>Number must be positive integer</HelpBlock>

                        </FormGroup>
                    </form>
                    <div>
                        <ButtonToolbar>
                            <Button onClick={() => this.generateRectangleAndCounterSky(true)}>
                                {"Integer points"}
                            </Button>
                            <Button onClick={() => this.generateRectangleAndCounterSky(false)}>
                                {"Real points"}
                            </Button>
                        </ButtonToolbar>

                        <ButtonToolbar>
                            <Button bsStyle='success' onClick={this.updateCanvasForDrawSkyCounter} disabled={!this.state.check}>
                                {"Draw sky counter"}
                            </Button>
                        </ButtonToolbar>
                    </div>                
                </Grid>    
            </div>
        ); 
    }
}

export default App;