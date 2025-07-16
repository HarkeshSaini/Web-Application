<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row user-dash">
        <jsp:include page="sideBar.jsp"></jsp:include>
        <div class="col-md-8">
          <div class="card dashboard-card border-0 shadow-sm">
            <div class="card-header bg-primary text-white">
               <p class="mb-0">User Account Information <p>${message}</p> 
            </div>
            <div class="card-body">
              <div class="row g-3">
                <div class="col-md-6">
                   <div class="chart-container">
		            <canvas id="userBaseGraph"></canvas>
		          </div>
				</div>
                <div class="col-md-6">
                   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <script>
      // Wait for the DOM content to be fully loaded before initializing the chart
      document.addEventListener("DOMContentLoaded", function() {
          // Chart.js for User Base Graph
          const ctx = document.getElementById('userBaseGraph').getContext('2d');
          
          new Chart(ctx, {
              type: 'line', // Change to 'bar' for bar chart
              data: {
                  labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'], // Labels for months
                  datasets: [{
                      label: 'User Base Growth',
                      data: [10, 25, 35, 40, 55, 60], // Example data (you can update with real data)
                      borderColor: 'rgba(75, 192, 192, 1)',
                      backgroundColor: 'rgba(75, 192, 192, 0.2)',
                      borderWidth: 2
                  }]
              },
              options: {
                  responsive: true,
                  scales: {
                      y: {
                          beginAtZero: true
                      }
                  }
              }
          });
      });
    </script>
  </body>
</html>