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
  
  
// Function to fetch categories and populate the dropdown
 document.getElementById('service-toggle').addEventListener('click', function() {
        fetch('/api/getCategory')
            .then(response => response.json())  
            .then(data => {
                const dropdownContent = document.getElementById('dropdown-content');
                dropdownContent.innerHTML = '';  
                data.forEach(category => {
                    const categoryElement = document.createElement('p');
                    categoryElement.innerHTML = `<a class="nav-link service" href="/category/${category.url}">${category.name}</a>`;
                    dropdownContent.appendChild(categoryElement);
                });
            })
            .catch(error => {
                console.error('Error fetching categories:', error);
            });
    });