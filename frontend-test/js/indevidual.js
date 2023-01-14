const id = document.querySelector('#hkt-id')

fetch(`http://localhost:8080/dashboard`)
  .then((response) => response.json())
  .then((obj) => {
    console.log(obj);
    if (obj.data == null) {
        location.href = "404.html";
        return;
    } else {
        id.innerHTML = obj.data + "님의 정보입니다.";
    }    
})
  .catch((err) => {
    console.log(err);});