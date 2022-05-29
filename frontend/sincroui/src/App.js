import logo from './logo.svg';
import './App.css';

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
  );
}
/**
 * Para definir rotas para paginas diferentes, ver BrowserRouter do react-router-dom
 * Vai ser necessário criarmos um contexto global de autenticação provavelmente
 * os componentes que definirmos pomos numa pasta components
 */

export default App;
