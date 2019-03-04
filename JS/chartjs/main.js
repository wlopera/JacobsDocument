var ctx = document.getElementById("myChart").getContext('2d');
// var myChart = new Chart(ctx, {
//     type: 'bar',
//     data: {
//         labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
//         datasets: [{
//             label: '# of Votes',
//             data: [12, 19, 3, 5, 2, 3],
//             backgroundColor: [
//                 'rgba(255, 99, 132, 0.2)',
//                 'rgba(54, 162, 235, 0.2)',
//                 'rgba(255, 206, 86, 0.2)',
//                 'rgba(75, 192, 192, 0.2)',
//                 'rgba(153, 102, 255, 0.2)',
//                 'rgba(255, 159, 64, 0.2)'
//             ],
//             borderColor: [
//                 'rgba(255,99,132,1)',
//                 'rgba(54, 162, 235, 1)',
//                 'rgba(255, 206, 86, 1)',
//                 'rgba(75, 192, 192, 1)',
//                 'rgba(153, 102, 255, 1)',
//                 'rgba(255, 159, 64, 1)'
//             ],
//             borderWidth: 1
//         }]
//     },
//     options: {
//         scales: {
//             yAxes: [{
//                 ticks: {
//                     beginAtZero:true
//                 }
//             }]
//         }
//     }
// });

var myChart = new Chart(ctx, {
    type: 'bubble',
    data: {
      datasets: [
        {
          label: 'John',
          data: [
            {
              x: 3,
              y: 7,
              r: 10
            }
          ],
          backgroundColor:"#ff6384",
          hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'Paul',
            data: [
              {
                x: 6,
                y: 2,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'George',
            data: [
              {
                x: 2,
                y: 6,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'Ringo',
            data: [
              {
                x: 5,
                y: 3,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'John',
            data: [
              {
                x: 2,
                y: 1,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'George',
            data: [
              {
                x: 1,
                y: 3,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'Ringo',
            data: [
              {
                x: 1,
                y: 1,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        },
        {
          label: 'George',
            data: [
              {
                x: 1,
                y: 2,
                r: 10
              }
            ],
            backgroundColor:"#ff6384",
            hoverBackgroundColor: "#ff6384"
        }
        ]
    }
});
