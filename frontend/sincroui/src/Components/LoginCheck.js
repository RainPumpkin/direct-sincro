import { Fragment, useContext } from "react"
import { UserContext } from "./UserContext"
import { Navigate } from 'react-router-dom';

export const LoginVerifier = (props) => {

  const [user] = useContext(UserContext)

  let content = props.children
  if(!user || !user.logged) content = <Navigate to="/login"/>
  //if(!user.subscritor) content = <Navigate to="/NaoSubscritor">

  return(
    <Fragment>
      {content}
    </Fragment>
  )
}