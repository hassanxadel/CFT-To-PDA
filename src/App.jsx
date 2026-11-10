// import { Fragment } from "react";

import Task from "./components/Task";
import User from "./components/User";
import Users from "./components/Users";

function App() {
  var name="React Js"
  // "background-color:red ; color:white"
  var styleObj={backgroundColor:"red" , color:"white"}
  return (
    <>
   <Users></Users>
    <Task test="hi"></Task>
    {/* <label htmlFor="inp">Name:</label>
    <input id="inp"></input>
      <div style= {{backgroundColor:"red" , color:"white"}}>hello ya {name.toLocaleUpperCase()}</div>
      <div>hii {6*8/2+1}</div> */}
      </>
  );
}

export default App;
