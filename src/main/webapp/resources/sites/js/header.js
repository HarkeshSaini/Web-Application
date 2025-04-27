const toggler = document.querySelector('.navbar-toggler');
const collapse = document.querySelector('#navbarNav');
toggler.addEventListener('click', () => {
    collapse.classList.toggle('active');
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
