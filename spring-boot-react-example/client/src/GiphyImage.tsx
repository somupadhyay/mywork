import * as React from 'react';

interface IGiphyImageProps {
    name: string;
}

class GiphyImage extends React.Component<IGiphyImageProps, any> {
    constructor(props: IGiphyImageProps){
        super(props);

        this.state = {
            giphyUrl: '',
            isLoading: false
        }
    }

    public componentDidMount() {
        const giphyApi = '//api.giphy.com/v1/gifs/search?api_key=dc6zaTOxFJmzC&limit=1&q=';

        fetch(giphyApi+this.props.name)
        .then(response => response.json())
        .then(response => {
            if (response.data.length > 0) {
                this.setState({giphyUrl: response.data[0].images.original.url});
            } else {
                this.setState({giphyUrl: 'http://media.giphy.com/media/YaOxRsmrv9IeA/giphy.gif'});
            }
            this.setState({isLoading: false});
        })
    }

   public render() {
        const {giphyUrl, isLoading} = this.state;
        if (isLoading) {
            return <p>Loading...</p>;
        }
        return (
            <img src={giphyUrl} alt={this.props.name} width="200" />
        );
    }
}

export default GiphyImage;