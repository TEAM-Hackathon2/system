const loginId = document.querySelector("#loginId");
let recordedId;

fetch('http://localhost:8080/indiv')
  .then((response) => response.json())
  .then((obj) => {
    if (obj.status === "success") {
        console.log(obj.data.name);
        loginId.innerHTML = obj.data.name;
        recordedId = obj.data.id;
        let common = 0;
        let late = 0;
        let sick = 0;
        let absent = 0;
        let already = 0;
        for (var a in obj.data.attendance) {  
          for(var b in obj.data.attendance[a]) {
            let innerData = obj.data.attendance[a][b];
            if (!innerData.absent) {
              common++
              if (innerData.inTime != 0) {
                let tmpin = (""+ innerData.inTime).split(".");

                document.querySelector(".ipsildata").innerHTML = tmpin[0] + ":" + tmpin[1];
                if (innerData.inTime >= 9.5) {
                  late++;
                }
              }
              if (innerData.outTime !== 0) {
                let tmpout = (""+ innerData.outTime).split(".");

                document.querySelector(".teisildata").innerHTML = tmpout[0] + ":" + tmpout[1];
                if(innerData.outTime <= 18) {
                  already++;
                }
              } 
            } else {
              absent++;
            }            
          }
        }

        
        document.querySelector(".bokguidata").innerHTML
        document.querySelector(".oichuldata").innerHTML
        document.querySelector(".teisildata").innerHTML

        document.querySelector("#chulsuk").innerHTML = common;
        document.querySelector("#jigak").innerHTML = late;
        document.querySelector("#joetei").innerHTML = already;
        document.querySelector("#oeichul").innerHTML = 0;
        document.querySelector("#gyulsuk").innerHTML = absent;

  } else {
    location.href = "404.html";
    return;
  }    
})
  .catch((err) => {
    console.log(err);});


    $(function(){
      $('.gakpersent').each(function(i){
          var skill = $(this).find('.skill');//this=.container 안에 있는 .skill의 값을 읽어서 skill이라는 값에 넣어라
          var percent = skill.text(); //skill에 들어있는 값을 percent라는 변수에 넣어라
    
          skill.delay(i*500).animate({'width':percent});//skill을 애니메이트하겠다. percent에 들어있는 값만큼
      });
    });

document.querySelector("#inTime").onclick = (e) => {
  let today = new Date();   

  let hours = today.getHours(); // 시
  let minutes = today.getMinutes();  // 분
  let time = hours + "." + minutes;
  console.log(time);

  document.querySelector(".ipsildata").innerHTML = hours + " : " + minutes;

  fetch('http://localhost:8080/instage', {
    method: 'PUT',
    headers: {
      'Content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${recordedId}&time=${time}`
  })
  .then((response) => response.json())
  .then((obj) => {
  if (obj.status == "failure") {        
    alert(obj.message);
    return;
  } 
})
  .catch((err) => {
    console.log(err);});
}

document.querySelector("#outTime").onclick = (e) => {
  let today = new Date();   

  let hours = today.getHours(); // 시
  let minutes = today.getMinutes();  // 분
  let time = hours + "." + minutes;
  console.log(time);

  document.querySelector(".teisildata").innerHTML = hours + " : " + minutes;

  fetch('http://localhost:8080/outstage', {
    method: 'PUT',
    headers: {
      'Content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${recordedId}&time=${time}`
  })
    .then((response) => response.json())
    .then((obj) => {
    if (obj.status == "failure") {        
      return;
    } 
  })
    .catch((err) => {
      console.log(err);});
}


document.querySelector('#del').onclick = (e) => {

  fetch('http://localhost:8080/delmember', {
    method: 'DELETE',
    headers: {
      'Content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${recordedId}`
  })
    .then((response) => response.json())
    .then((obj) => {
    if (obj.status == "failure") {        
      return;
    } 
  })
    .catch((err) => {
      console.log(err);});

  fetch('http://localhost:8080/deleteAccount', {
    method: 'DELETE',
    headers: {
      'Content-Type': 'Application/json'
    },
    body: JSON.stringify({
      "id":recordedId,
      "password":"1"})
  })
    .then((response) => response.json())
    .then((obj) => {
      if (obj.status == "failure") {
        return;
      }
    })
    .catch((err) => {
      console.log(err);
    });    



  Swal.fire({
    icon: 'success',
    title: '삭제되었습니다!',
  }).then(() => {
    location.href = "./login.html";
  });



};