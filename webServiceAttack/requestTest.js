var request = require('request');

request({
    url: 'https://graph-na02-useast1.api.smartthings.com/api/smartapps/installations/627b677f-f12f-4789-885d-829bfbfd83d8/locks/lock', //URL to hit
    //qs: {from: 'blog example', time: +new Date()}, //Query string data
    method: 'PUT',
    headers: { //We can define headers too
        'Authorization': 'Bearer c96e28dd-a8c8-4f59-901f-06f383ab1cf7',
        'Content-Type': 'application/json',
    }
    //Lets post the following key/values as form
    /*
    form: {
        'command':'lock'
    }
    */
}, function(error, response, body){
    if(error) {
        console.log(error);
    } else {
        console.log(response.statusCode, body);
    }
});