import * as React from 'react';
import GiphyImage from './GiphyImage'

interface IBeer {
  id: string;
  name: string;
}

interface IBeerListState {
  beers: IBeer[];
  isLoading: boolean;
}

class BeerList extends React.Component<{}, IBeerListState> {
  constructor(props: any) {
    super(props);

    this.state = {
      beers: [],
      isLoading: false
    }
  }
  public componentDidMount() {
    this.setState({ isLoading: true });

    fetch('http://localhost:8080/good-beers')
      .then(response => response.json())
      .then(data => this.setState({ beers: data, isLoading: false }));
  }
  public render() {
    const { beers, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
    return (
      <div>
        <h1>Good Beers</h1>
        {beers.map((beer: IBeer) =>
          <div key={beer.id}>
            {beer.name}<br/>
            <GiphyImage name={beer.name}/>
          </div>
        )}
      </div>
    );
  }
}

export default BeerList;
