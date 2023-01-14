document.querySelector("#register-btn").onclick = function(e) {
    var id = document.querySelector("#username").value;
    var password = document.querySelector("#password").value;
    
    fetch('http://localhost:8080/registAccount', {
      method: 'POST',
      headers: {
        'Content-Type': 'Application/json'
      },
      body: JSON.stringify({
        "id":id,
        "password":password})
    })
    .then((response) => response.json())
    .then((obj) => {
    if (obj.status == "failure") {        
    	alert(obj.message);
      return;
    } 
		alert(obj.message);
  })
    .catch((err) => {
      console.log(err);});
  }