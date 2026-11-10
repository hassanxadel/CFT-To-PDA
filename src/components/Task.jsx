import { Component } from "react";

class Task extends Component{
    constructor(){
        super()
        this.state={name:"React"}
    }
    // state={name:"React"}
    render(){
        console.log(this.props);
        return <div>Task of {this.state.name} , {this.props.test}</div>
    }   
}
export default Task;