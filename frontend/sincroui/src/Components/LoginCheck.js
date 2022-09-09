import { Fragment, useContext } from "react"
import { UserContext } from "./UserContext"
import { Navigate } from 'react-router-dom';

export const LoginVerifier = (props) => {

  const [user] = useContext(UserContext)

  let content = props.children
  if(user !== undefined && user.loading){
    content = <h2>Waiting</h2>
  }
  else if(!user || !user.logged){
    console.log("not logged")
    console.log(user)
    content = <Navigate to="/login"/>
  } else if(!user.subscritor) content = <Navigate to="/naosubscritor"/>

  return(
    <Fragment>
      {content}
    </Fragment>
  )
}