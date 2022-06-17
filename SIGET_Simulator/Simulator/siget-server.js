import express from 'express'
const app = express()
import router from './siget-routes.js'
const PORT = 3000

app.use('/siget', router)
app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(express.json()) // => Parses HTTP request body and populates req.body

app.listen(PORT, err => {
    if (err) {
      return console.log("ERROR", err);
    }
    console.log(`Listening on port ${PORT}`)
})

export default app