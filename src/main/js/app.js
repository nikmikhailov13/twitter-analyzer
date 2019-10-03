'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {contacts: []};
        this.handleClick = this.handleClick.bind(this)
        this.input_topic = React.createRef();
        this.input_limit = React.createRef();
    }

    render() {
        return (
            <div>
                <center><img src="https://img.icons8.com/bubbles/2x/twitter.png" alt="logo" width="130"
                             height="130"/></center>
                <center><h1>Twitter Sentiment Analysis</h1></center>
                <center>
                    <div>
                        <label> <input type="text" placeholder="Topic" ref={this.input_topic}/></label><a> </a>
                        <label> <input type="text" placeholder="Count" ref={this.input_limit}/></label><a> </a>
                        <button onClick={this.handleClick}>Get Tweets</button>
                    </div>
                </center>

                <Contacts contacts={this.state.contacts}/>
            </div>

        )
    }

    handleClick() {
        fetch('http://localhost:8080/tweets/' + this.input_topic.current.value + '/?limit=' + this.input_limit.current.value)
            .then(res => res.json())
            .then((data) => {
                this.setState({contacts: data})
            })
            .catch(console.log)
    }
}

const Contacts = ({contacts}) => {
    return (
        <div>
            {contacts.map((contact) => (
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">{contact.author}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Tweet score: {contact.score}</h6>
                        <p class="text">{contact.text}</p>
                    </div>
                </div>
            ))}
        </div>
    )
};
// end::app[]


// tag::render[]
ReactDOM.render(
    <App/>
    ,
    document.getElementById('react')
)
// end::render[]

