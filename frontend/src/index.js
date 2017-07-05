import React from 'react';
import ReactDOM from 'react-dom';
import App from './js/components/App/App';
import { Router, Route, IndexRoute, browserHistory } from 'react-router';



ReactDOM.render(
	<Router history={browserHistory}>
		<Route path='/' component={App}>
		</Route>
	</Router>,
	document.getElementById('root')
);
