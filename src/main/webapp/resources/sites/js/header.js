/*const toggler = document.querySelector('.navbar-toggler');
const collapse = document.querySelector('#navbarNav');
toggler.addEventListener('click', () => {
    collapse.classList.toggle('active');
});*/


const toggleBtns = document.querySelector('.navbar-toggler');
  const hiddenBoxs = document.querySelector('.navbarNav');
  toggleBtns.addEventListener('click', function() {
    if (hiddenBoxs.style.display === 'none' || hiddenBoxs.style.display === '') {
      hiddenBoxs.style.display = 'block'; // Show box
    } else {
      hiddenBoxs.style.display = 'none';  // Hide box
    }
  });
  
  
const toggleButton = document.getElementById('service-toggle');
const dropdownContent = document.getElementById('dropdown-content');
toggleButton.addEventListener('click', function() {
  dropdownContent.style.display = dropdownContent.style.display === 'block' ? 'none' : 'block';
});
  
  
document.getElementById('service-toggle').addEventListener('click', function() {
    fetch('/api/getCategory')
        .then(response => response.json())  
        .then(data => {
            const dropdownContent = document.getElementById('dropdown-content');
            dropdownContent.innerHTML = '';  

            const columnsPerRow = 2; // Set how many links per row
            let row;

            data.forEach((category, index) => {
                if (index % columnsPerRow === 0) {
                    row = document.createElement('div');
                    row.classList.add('row');
                    dropdownContent.appendChild(row);
                }

                const col = document.createElement('div');
                col.classList.add('col');
                col.innerHTML = `<a class="nav-link service" href="/${category.url}">${category.name}</a>`;
                row.appendChild(col);
            });
        })
        .catch(error => {
            console.error('Error fetching categories:', error);
        });
});


const toggleBtn = document.querySelector('.search');
  const hiddenBox = document.querySelector('.search-input-data');
  toggleBtn.addEventListener('click', function() {
    if (hiddenBox.style.display === 'none' || hiddenBox.style.display === '') {
      hiddenBox.style.display = 'block'; // Show box
    } else {
      hiddenBox.style.display = 'none';  // Hide box
    }
  });