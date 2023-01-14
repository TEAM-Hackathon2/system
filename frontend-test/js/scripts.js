window.addEventListener('DOMContentLoaded', event => {


    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
const ctx1 = document.getElementById('myChart1');
const mydata = [3, 2, 1, 1, 2, 2, 0, 0];
const mydataHalf = [20, 18, 20, 19, 20, 20];

mixedChart = {
  type: 'bar',
  labels: ['월요일', '화요일', '수요일', '목요일', '금요일'],
  datasets : [
    {
      label: '결석률',
      data : mydata,
      backgroundColor: 'rgba(256, 0, 0, 0.1)'
    },
    {
      label: '출석률',
      data: mydataHalf,
      backgroundColor: 'transparent',
      borderColor: 'skyblue',
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

  // 파이형 차트
  const myPiedata = [1, 2, 1, 1, 2];
    const myPiedataHalf = [20, 18, 20, 19, 20];  
    ctx2 = document.getElementById("myChart2");
  
  myPieData = {
  type: 'pie',
  labels: ['출석', '결석', '지각', '조퇴', '병가'],
  datasets : [
    {
      label: ['출석', '결석', '지각', '조퇴', '병가'],
      data : myPiedata,
      backgroundColor: ['rgba(256, 60, 40, 0.1)', 'rgba(256, 255, 10, 0.3)', 'rgba(70, 100, 100, 0.1)', 'rgba(256, 0, 100, 0.5)', 'rgba(256, 156, 100, 0.4)'],
    }],
  }

    // 가운데 구멍이 없는 파이형 차트
  myPieChart = new Chart(ctx2, {
    type: 'pie',
    data: myPieData,
    options: {}
});