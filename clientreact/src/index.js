import React from 'react';
import ReactDOM from 'react-dom';
// import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

import {Provider} from 'react-redux';
import store from './services/store';

ReactDOM.render(<Provider store={store}><App /></Provider>, document.getElementById('root'));
// const root = ReactDOM.createRoot(document.querySelector('#root'));
// root.render(<Provider store={store}><App /></Provider>);

serviceWorker.unregister();
