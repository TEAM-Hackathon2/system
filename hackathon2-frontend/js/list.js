const datatablesSimple = document.getElementById('datatablesSimple');
const tbody = document.querySelector("tbody");

fetch('http://localhost:8080/admin')
  .then((response) => response.json())
  .then((obj) => {
    let html = '';
    for (let m of obj.onedaydata) {
      let attendance = "";
      for (let at in m.attendance.attendances) {
        if(m.attendance.attendances[at].absent) {
          attendance = "결석";
        } else {
          attendance = "출석";
        }
      }      
      html += `<tr>
        <td>${m.no}</td>
        <td>${m.name}</td>
        <td>${m.age}</td>
        <td>${m.gender}</td>
        <td>${m.tel}</td>
        <td>${m.address}</td>
        <td>${attendance}</td>
        </tr>`;

    }    
    tbody.innerHTML = html;
    if (datatablesSimple) {
      new simpleDatatables.DataTable(datatablesSimple);
    }
    // ${m.attendance}
  })
  .catch((err) => {
    alert("서버 요청 오류!");
    console.log(err);
  });