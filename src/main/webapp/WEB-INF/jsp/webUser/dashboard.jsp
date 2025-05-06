<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  </head>
  <body>
    <div class="container-fluid py-5">
      <div class="row user-dash">
        <!-- Include Sidebar (you can adjust or remove this as needed) -->
        <jsp:include page="sideBar.jsp"></jsp:include>
        <div class="col-md-8">
          <div class="card dashboard-card border-0 shadow-sm">
            <div class="card-header bg-primary text-white">
              <p class="mb-0">User Dashboard <p>${message}</p></p>
            </div>
            <div class="container mt-5">
              <!-- User Base Graph, Rating, and Author Total -->
              <div class="row g-3">
                <!-- User Base Graph -->
                <div class="col-md-4">
                  <div class="card dashboard-section">
                    <div class="card-header">
                      <h5>User Base Graph</h5>
                    </div>
                    <div class="card-body">
                      <div class="chart-container">
                        <canvas id="userBaseGraph"></canvas>
                      </div>
                      <p>This approach improves the readability and clarity of your HTML structure, making it easier</p>
                    </div>
                  </div>
                </div>
                <!-- Rating -->
                <div class="col-md-4">
                  <div class="card dashboard-section c-1">
                    <div class="card-header">
                      <h5>Rating</h5>
                    </div>
                    <div class="card-body">
                      <div class="info-box">
                        <div class="info-title">Average Rating</div>
                        <div class="info-value">4.7</div>
                        <p>I have kept the styling structure of Bootstrap and used appropriate card headers and body sections.</p>
                      </div>
                    </div>
                  </div>
                  
                  <div class="card dashboard-section c-2">
                    <div class="card-header">
                      <h5>Rating</h5>
                    </div>
                    <div class="card-body">
                      <div class="info-box">
                        <div class="info-title">Average Rating</div>
                        <div class="info-value">4.7</div>
                        <p>The script for the user base graph remains intact, and it will still render the chart dynamically once</p>
                      </div>
                    </div>
                  </div>
                  
                </div>
                <!-- Author Total -->
                <div class="col-md-4">
                  <div class="card dashboard-section c-1">
                    <div class="card-header">
                      <h5>Author Total</h5>
                    </div>
                    <div class="card-body">
                      <div class="info-box">
                        <div class="info-title">Total Authors</div>
                        <div class="info-value">150</div>
                        <p>This approach improves the readability and clarity of your HTML structure, making it easier</p>
                      </div>
                    </div>
                  </div>
                  <div class="card dashboard-section c-2">
                    <div class="card-header">
                      <h5>Author Total</h5>
                    </div>
                    <div class="card-body">
                      <div class="info-box">
                        <div class="info-title">Total Authors</div>
                        <div class="info-value">150</div>
                        <p>maintain different categories. You can now expand each section independently,</p>
                      </div>
                    </div>
                  </div>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>