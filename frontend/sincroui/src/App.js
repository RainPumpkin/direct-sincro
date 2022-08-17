//import logo from './logo.svg';
//import './App.css';
import { UserContainer } from "./Components/UserContext"

import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Login } from "./Pages/LoginPage"
import { Home } from "./Pages/HomePage"
import { LoginVerifier } from "./Components/LoginCheck"


/**
 * Para definir rotas para paginas diferentes, ver BrowserRouter do react-router-dom
 * Vai ser necessário criarmos um contexto global de autenticação provavelmente
 * os componentes que definirmos pomos numa pasta components
 */

 const App = () => {
  return(
    <UserContainer>
      <BrowserRouter> 
        <div style={{margin: "auto", width: "90%"}}>
          <Routes>
            <Route path="/login" element={<Login />}/>
            <Route path="/" element={<LoginVerifier><Home /></LoginVerifier>}/>
          </Routes>
        </div>
      </BrowserRouter>
    </UserContainer>
  )
}
//rotas
//<Route path="PATH" element={<LoginVerifier><OurPage /></LoginVerifier>}/>
export default App;
/*
o que vinha por default para o caso de ser preciso
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
                  className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  )
}*/