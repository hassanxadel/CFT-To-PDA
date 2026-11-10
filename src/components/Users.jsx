import { useState } from "react";
import User from "./User";
import {v4 as uuid} from "uuid"
const Users = () => {
  const [usersArr] = useState([
    { id: uuid(), name: "Ahmed", age: 20 },
    { id: uuid(), name: "Omar", age: 22 },
    { id: uuid(), name: "Mariam", age: 25 },
    { id: uuid(), name: "Sara", age: 34 },
    { id: uuid(), name: "Gana", age: 12 },
    { id: uuid(), name: "Mohamed", age: 19 },
  ]);
  console.log(usersArr);
  return <div>
    {/* {usersArr.map((u)=>{ return <User id={u.id} name={u.name} age={u.age}></User>})} */}
    {/* {usersArr.map((u)=>{ return <User user={u} ></User>})} */}
    {usersArr.map((u)=><User key={uuid()} {...u} ></User>)}

  </div>;
};

export default Users;
