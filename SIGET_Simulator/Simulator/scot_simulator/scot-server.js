import express from 'express'
const app = express()
import router from './scot-web-site'
const PORT = 4000

app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(express.json()) // => Parses HTTP request body and populates req.body
app.use('/scot', router)

app.listen(PORT, err => {
    if (err) {
      return console.log("ERROR", err);
    }
    console.log(`Listening on port ${PORT}`)
})

export default app