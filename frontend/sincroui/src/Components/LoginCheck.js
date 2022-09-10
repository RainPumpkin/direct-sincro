import { Fragment, useContext } from "react"
import { UserContext } from "./UserContext"
import { subscribeToPushNotification } from "../Services/HandleSubscription"
import { unsubscribeFromPushNotification } from "../Services/HandleSubscription";

export const LoginVerifier = (props) => {

  const [user] = useContext(UserContext)

  let content = props.children
  if(user !== undefined && user.loading){
    content = <h2>Waiting</h2>
  }
  else if(!user || !user.logged){
    content = window.location.assign("/login")
  }

  let subscribe = (user) => {
    if(user!=null && user.subscritor){
      subscribeToPushNotification(user.nif)
  } else if(user!=null && !user.subscritor) {
      unsubscribeFromPushNotification(user.nif)
  }
  }
  
  if (user != null && user.logged) subscribe(user)

  return(
    <Fragment>
        {content}
    </Fragment>
  )
}