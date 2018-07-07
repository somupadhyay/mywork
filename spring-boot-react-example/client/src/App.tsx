import * as React from 'react';
import './App.css';
import BeerList from './BeerList';

import logo from './logo.svg';


class App extends React.Component<{}, any> {
  
  public render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </div>
        
        <BeerList />
      </div>
    );
  }
}

export default App;
