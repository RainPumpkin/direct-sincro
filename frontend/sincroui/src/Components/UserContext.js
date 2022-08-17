import { createContext, useState } from 'react'

export const UserContext = createContext(null)
export const UserProvider = UserContext.Provider

export const UserContainer = (props) => {

    const [context, setContext] = useState({logged: false, nif: null})
    //TODO: adicionar nome
    return(<UserProvider value={[context, setContext]}>{props.children}</UserProvider>)
}
