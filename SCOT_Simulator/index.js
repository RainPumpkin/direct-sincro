import express from 'express'
const app = express()
import webSiteRouter from './scot-web-site.js'
import apiRouter from './scot-routes.js'
const PORT = 4000

app.set('view engine', 'hbs')
app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(express.json()) // => Parses HTTP request body and populates req.body
app.use(express.static('public'))
app.use('/scot', apiRouter)
app.use('/debugger', webSiteRouter)

/* error debugging */
app.use((err, req, resp, _next) => {
  resp
      .status(err.status || 500)
      .json({
          message: err.message
      }) 
})

app.listen(PORT, err => {
    if (err) {
      return console.log("ERROR", err);
    }
    console.log(`Listening on port ${PORT}`)
})

export default app 