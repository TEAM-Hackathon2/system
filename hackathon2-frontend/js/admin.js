let resultData;

fetch('http://localhost:8080/admin')
  .then((response) => response.json())
  .then((obj) => {
    if (obj.status === "success") {
      resultData = changeData(obj.onedaydata);
      let plz = changeData(obj.alldata);

      document.querySelector("#attendance").innerHTML = resultData.common;
      document.querySelector("#tardy").innerHTML = resultData.late;
      document.querySelector("#sick-absent").innerHTML = resultData.sick;
      document.querySelector("#absent").innerHTML = resultData.absent;

      drawCircleChart(resultData.common, resultData.absent, resultData.late,
        resultData.already, resultData.sick);
      
      drawMixChart(plz.weekend);

  } else {
    location.href = "404.html";
    return;
  }    
})
  .catch((err) => {
    console.log(err);});

function changeData(array) {
  let common = 0;
  let late = 0;
  let sick = 0;
  let absent = 0;
  let already = 0;
  let weekData = [[0,0],[0,0],[0,0],[0,0],[0,0]];

  let attendenceList = [];
  let lateList = [];
  let sickList = [];
  let absentList = [];

  var count = 0;
  for (let a of array) {
    for (let b in a.attendance.attendances) {      
      let c = a.attendance.attendances[b];
        if (c == null) {
          absent++;
          absentList.push(a.name);
        } else if (c.absent) {
          if (c.absentType === 0) {
            absent++;
            absentList.push(a.name);
          } else if ((c.absentType === 1)) {
            sick++;
            sickList.push(a.name);
          }
          weekData[count][1]++;
        } else {
          if (c.inTime >= 9.5) {
            late++;
            lateList.push(a.name);
          } else if (c.outTime <= 18) {
            already++;
            lateList.push(a.name);
          } else {
            common++;
            attendenceList.push(a.name);
          }     
          weekData[count][0]++;
        }
        count++;
        if (count === 5) {
          count = 0;
        }
        }
      }          
  return {
    "common" : common,
    "late" : late,
    "already" : already,
    "sick" : sick,
    "absent" : absent,
    "weekend" : weekData,
    "attendenceList" : attendenceList,
    "lateList" : lateList,
    "sickList" : sickList,
    "absentList" : absentList,
  }
}



function drawCircleChart(attendance, absent, late, already, sick) {
  // ????????? ??????
  ctx2 = document.getElementById("myChart2");

myPieData = {
type: 'pie',
labels: ['??????', '??????', '??????',  '??????'],
datasets : [
  {
    label: ['??????', '??????', '??????', '??????'],
    data : [attendance, absent, late, sick],
    backgroundColor: ['#0d6efd', '#dc3546', '#fec107', '#188754'],
  }],
}

    // ????????? ????????? ?????? ????????? ??????
myPieChart = new Chart(ctx2, {
  type: 'pie',
  data: myPieData,
  options: {}
});
}

function drawMixChart(weekendData) {
const ctx1 = document.getElementById('myChart1');
const mydata = [weekendData[0][1],weekendData[1][1],weekendData[2][1],
weekendData[3][1],weekendData[4][1]];
const mydataHalf = [weekendData[0][0],weekendData[1][0],weekendData[2][0],
weekendData[3][0],weekendData[4][0]];;

mixedChart = {
type: 'bar',
labels: ['?????????', '?????????', '?????????', '?????????', '?????????'],
datasets : [
  {
    label: '?????????',
    data : mydata,
    backgroundColor: '#dc3546'
  },
  {
    label: '?????????',
    data: mydataHalf,
    backgroundColor: 'transparent',
    borderColor: '#0d6efd',
    type: 'line'
  }
]
};
myChart = new Chart(ctx1, {
  type: 'bar',
  data: mixedChart,
  options: {
    legend: {
      display: true
    }
  }
});  
}

$().ready(function () {
$("#attendance-alert").click(function () {
    Swal.fire({
        title: '?????????!!!!',
        imageUrl: 'img/1.jpg',
        imageWidth: 400,
        imageHeight: 400,
        imageAlt: 'Custom image',
        width: 600,
        padding: '3em',
        backdrop: `
        rgba(0,0,123,0.4)
        url("")
        left top
        no-repeat`,
        text: resultData.attendenceList,
    });
});
});     

$().ready(function () {
$("#late-alert").click(function () {
    Swal.fire({
        title: '?????? ?????? ?????? ??????!!',
        imageUrl: 'img/2.jpg',
        imageWidth: 400,
        imageHeight: 400,
        imageAlt: 'Custom image',
        width: 600,
        padding: '3em',
        backdrop: `
        rgba(0,0,123,0.4)
        url("")
        left top
        no-repeat`,
        text: resultData.lateList,
    });
});
});   

$().ready(function () {
$("#sick-alert").click(function () {
    Swal.fire({
        title: '????????? ?????? ????????? ??????!',
        imageUrl: 'img/3.jpg',
        imageWidth: 400,
        imageHeight: 400,
        imageAlt: 'Custom image',
        width: 600,
        padding: '3em',
        backdrop: `
        rgba(0,0,123,0.4)
        url("")
        left top
        no-repeat`,
        text: resultData.sickList,
    });
});
});     

$().ready(function () {
$("#absent-alert").click(function () {
    Swal.fire({
        title: '??? ?????????? ?????? ??????????!',
        imageUrl: 'img/4.jpg',
        imageWidth: 400,
        imageHeight: 400,
        imageAlt: 'Custom image',
        width: 600,
        padding: '3em',
        backdrop: `
        rgba(0,0,123,0.4)
        url("")
        left top
        no-repeat`,
        text: resultData.absentList,
    });
});
});     