const monthName = document.getElementById('month-name');
const yearDisplay = document.getElementById('year');
const daysContainer = document.getElementById('calendar-days');
const prevBtn = document.getElementById('prev-month');
const nextBtn = document.getElementById('next-month');
const infoPanel = document.getElementById('info-panel');

const monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"];

let current = new Date();
let currentMonth = current.getMonth();
let currentYear = current.getFullYear();

const monthDetails = {
    January: ["Republic Day - 26th Jan", "Company Off - 15th Jan"],
    February: ["No official holidays"],
    March: ["Holi - 25th Mar", "Company Leave - 31st Mar"],
    April: ["Ambedkar Jayanti - 14th Apr"],
    May: ["Labor Day - 1st May"],
    June: ["No official holidays"],
    July: ["No official holidays"],
    August: ["Independence Day - 15th Aug", "Raksha Bandhan - 19th Aug"],
    September: ["Ganesh Chaturthi - 7th Sept"],
    October: ["Gandhi Jayanti - 2nd Oct", "Diwali - 31st Oct"],
    November: ["Diwali Continues - 1st Nov", "Children's Day - 14th Nov"],
    December: ["Christmas - 25th Dec"]
};

function renderCalendar(year, month) {
    const today = new Date();
    monthName.textContent = monthNames[month];
    yearDisplay.textContent = year;

    const firstDay = new Date(year, month, 1).getDay();
    const totalDays = new Date(year, month + 1, 0).getDate();

    let daysHTML = '';
    for (let i = 0; i < firstDay; i++) {
        daysHTML += '<div></div>';
    }

    for (let day = 1; day <= totalDays; day++) {
        const isToday = (day === today.getDate() && month === today.getMonth() && year === today.getFullYear());
        daysHTML += `<div class="${isToday ? 'today' : ''}">${day}</div>`;
    }

    daysContainer.innerHTML = daysHTML;

    updateInfoPanel(monthNames[month]);
}

function updateInfoPanel(monthNameText) {
    let holidays = monthDetails[monthNameText] || ["No info available"];
    infoPanel.innerHTML = `<h3>${monthNameText} Holidays & Info</h3><ul>${holidays.map(day => `<li>${day}</li>`).join('')}</ul>`;
}

prevBtn.addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    renderCalendar(currentYear, currentMonth);
});

nextBtn.addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    renderCalendar(currentYear, currentMonth);
});

renderCalendar(currentYear, currentMonth);


 
