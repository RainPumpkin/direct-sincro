
import { readFile } from "fs"
import sendEvents from "./manage-requests.js";
import app from "./siget-server.js"

var importEvents = function() {
  readFile('./Data/contraordenações.json','utf-8',function(err, jsonData){
    if (err) throw err;  
    const data = JSON.parse(jsonData)
    sendEvents(data)
  }); 
}(); //self execution