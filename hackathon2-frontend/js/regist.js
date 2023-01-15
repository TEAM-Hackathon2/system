let recordedId = "";

document.querySelector("#register-btn").onclick = function(e) {
    var id = document.querySelector("#username").value;

    if (id === "" || id.length === 0) {
      return;
    }


    var password = document.querySelector("#password").value;
    recordedId = id;
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
    popUpAlert();
  })
    .catch((err) => {
      console.log(err);});
  }


  async function popUpAlert() {
    const { value: formValues } =  await Swal.fire({
    title: '회원정보등록',
    html:
      '<input id="name" class="swal2-input" placeholder="Name">' +
      '<input id="age" class="swal2-input" placeholder="Age">' +
      '<input id="gender" class="swal2-input" placeholder="Gender">' +
      '<input id="address" class="swal2-input" placeholder="Address">' +
      '<input id="tel" class="swal2-input" placeholder="Tel">',
    focusConfirm: false,
    allowOutsideClick: false,
    preConfirm: () => {
      return [
        document.getElementById('name').value,
        document.getElementById('age').value,
        document.getElementById('gender').value,
        document.getElementById('address').value,
        document.getElementById('tel').value
      ]
    }
  })
  if (formValues) {
    // Swal.fire(JSON.stringify(formValues))
    Swal.fire("입력되었습니다.");
    console.log(formValues);
    fetch('http://localhost:8080/addMember', {
      method: 'POST',
      headers: {
        'Content-Type': 'Application/json'
      },
      body: JSON.stringify({
        "name": formValues[0],
        "id": recordedId,
        "age": formValues[1],
        "gender" : formValues[2],
        "address" : formValues[3],
        "tel" : formValues[4]
      })
    })
    .then((response) => response.json())
    .then((obj) => {})
    .catch((err) => {console.log(err);});
  }
}; 
