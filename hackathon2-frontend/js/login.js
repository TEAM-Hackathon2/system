document.querySelector("#login-btn").onclick = function(e) {
    var id = document.querySelector("#username").value;
    var password = document.querySelector("#password").value;
  
    fetch('http://localhost:8080/login', {
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
    if (obj.status == "admin") {      
      location.href = "index.html";
    } else {
      if (obj.status == "success") {
      location.href = "indevidual.html";
      } else {
        Swal.fire({
          icon: 'error',
          title: obj.message,
        });
        return;
      }
    }
  })
    .catch((err) => {
      console.log(err);});
  }