

document.querySelector("#hkt-login").onclick = function(e) {
  var id = document.querySelector("#inputEmail").value;
  var password = document.querySelector("#inputPassword").value;


  fetch(`http://localhost:8080/login`, {
    method: 'POST',
    headers: {
      'Content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${id}&password=${password}`
  })
  .then((response) => response.json())
  .then((obj) => {
  if (obj.status == "admin") {
    alert("어드민 계정 로그인!");    
    console.log(obj);
    location.href = "admin.html";
  } else {
    alert("일반 계정 로그인!");
    console.log(obj);
    location.href = "indevidual.html";
  }
})
  .catch((err) => {
    console.log(err);});
}