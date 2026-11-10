const User=(props)=>{
    console.log(props);
    let {id,name,age}= props
    return <div style={{backgroundColor:"violet" , margin:"10px"}}>
         <div>id: {id}</div>
        <div>Name: {name}</div>
        <div>Age: {age}</div>
    </div>
}
export default User;