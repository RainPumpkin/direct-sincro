export const Notificacao = (props) => {
    console.log("notificacao")
    const not = props.elem
    const emitida = new Date(not.emitida).toLocaleString()
    const visualizada = () => {
        if(not.visualizada) return "Sim"
        return "Não"
    }
    return(
        <div className="card border border-dark">
            <div className="card-body">
                <h3 className="card-title">{`Emissao Notificacao [${emitida}]`}</h3>
                <p className="card-text">Mensagem:&emsp;{not.mensagem}&emsp;Visualizada:&emsp;{visualizada()}&emsp;Tipo:&emsp;{not.tipo}</p>
            </div>
        </div>
    )
}