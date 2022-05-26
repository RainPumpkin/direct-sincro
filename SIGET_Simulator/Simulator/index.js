import fetch from 'node-fetch';
import { readFile } from "fs";

const URL = 'https://hookb.in/lJjW18gG2OiJ7ZEJWRgB'

readFile('../Data/eventos.json','utf-8',function(err, jsonData){
    if (err) throw err;    
    prepareRequests(JSON.parse(jsonData))
});

async function prepareRequests(jsonData) {
  const responses = await Promise.all(
    jsonData.map(async evento => {
      await fetch(URL, {
        method: 'post',
        body: JSON.stringify(evento),
        headers: {'Content-Type': 'application/json'}
      });
    })
  );
  console.log(responses)
}