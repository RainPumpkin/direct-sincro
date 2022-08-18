import { createContext, useState } from 'react'

export const UserContext = createContext()
export const UserProvider = UserContext.Provider

export const UserContainer = (props) => {

    const [context, setContext] = useState()
    
    return(<UserProvider value={[context, setContext]}>{props.children}</UserProvider>)
}
