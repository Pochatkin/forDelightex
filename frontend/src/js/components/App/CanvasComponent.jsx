import React, { Component } from 'react';

function drawRectangle(props) {
    const {ctx, x1, x2, height} = props;
    ctx.moveTo(x1, 0);
    ctx.lineTo(x1, height);
    ctx.lineTo(x2, height);
    ctx.lineTo(x2, 0);
    ctx.stroke();
}

class CanvasComponent extends Component {
	constructor(props) {
		super(props);
		this.state = {
			allRectangle : this.props.allRectangle,
			counterSky : this.props.counterSky
		}
	}
	componentDidMount() {
        this.updateCanvas();
    }
    componentDidUpdate() {
        this.updateCanvas();
    }
    updateCanvas() {
        const ctx = this.refs.canvas.getContext('2d');
        for (let rectangle in this.state.allRectangle) {
        	drawRectangle({ctx, x1: rectangle.leftPoint, x2: rectangle.rightPoint, height: rectangle.height});
        }
    }
    render() {
         return (
             <canvas ref="canvas" width={1100} height={500}/>
         );
    }
}

export default CanvasComponent;