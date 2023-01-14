document.querySelector("#login-btn").onclick = function(e) {
    var id = document.querySelector("#username").value;
    var password = document.querySelector("#password").value;
  
  
    fetch('http://localhost:8080/login2', {
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
      alert("어드민 계정 로그인!");    
      console.log(obj);
      location.href = "admin.html";
    } else {
      if (obj.status == "success") {
      console.log(obj);
      location.href = "indevidual.html";
      } else {
        alert(obj.message);
      }
    }
  })
    .catch((err) => {
      console.log(err);});
  }