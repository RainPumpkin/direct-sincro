import { Fragment, useContext, useState } from "react"
import { Navigate, useParams } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Delegar = () => {
    const [user] = useContext(UserContext)
    const {matricula} = useParams()
    const [redirect, setRedir] = useState(null)
    console.warn(user.nif)
    const onSubmitHandler = (event) => {
        event.preventDefault()
        let b = event.target.nif.value
        if(user.nif === event.target.nif.value) {
            alert("Não se pode delegar a si mesmo")
            return
        }else
            request(`/api/subscritores/${user.nif}/veiculos/${matricula}/delegacoes`, { method: "POST", headers: {'Content-Type': 'application/json'}, body: b}, setRedir(<Navigate to={`/veiculo/${matricula}`}/>))
    }
    return (
        <Fragment>
            {redirect}
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="nif">Nif</label>
                    <input type="text" name="nif" className="form-control" id="nif" pattern="[0-9]{9}" placeholder="Enter nif"/>
                </div>
                <p></p>
                <p></p>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </Fragment>
    )
}

