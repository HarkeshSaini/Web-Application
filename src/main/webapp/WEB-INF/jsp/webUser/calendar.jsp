<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/sites/css/calendar.css">
    <title>User calendar</title>
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
                  <div class="left-info" id="info-panel">
				        <h3>Month Info</h3>
				        <p>Select a month to view holidays & leaves</p>
				    </div>
				    <div class="selecting-day">
				    	<p>This interactive calendar allows users to view any month and year easily.
							On selecting a month, it displays a detailed list of holidays and authorized leaves on the side.
							It helps employees and users plan their work and leaves effectively.
							The design is responsive, user-friendly, and enhances overall time management.</p>
				    </div>
				   </div>
                   <div class="col-md-6">
                    <div class="calendar">
				        <div class="month">
				            <button id="prev-month">«</button>
				            <span id="month-name"></span> <span id="year"></span>
				            <button id="next-month">»</button>
				        </div>
				        <div class="weekdays">
				            <div>Sun</div><div>Mon</div><div>Tue</div><div>Wed</div><div>Thu</div><div>Fri</div><div>Sat</div>
				        </div>
				        <div class="days" id="calendar-days"></div>
				    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
     <script src="/resources/sites/js/calendar.js"></script>
  </body>
</html>