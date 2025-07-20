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


// Technology Dropdown
const toggleTechnology = document.getElementById('service-toggle');
const dropdownTechnology = document.getElementById('dropdown-content');
toggleTechnology.addEventListener('click', function() {
	dropdownTechnology.style.display = dropdownTechnology.style.display === 'block' ? 'none' : 'block';
});

// Links Dropdown
const toggleLinks = document.getElementById('service-toggle-links');
const dropdownLinks = document.querySelector('.dropdown-content-links');
toggleLinks.addEventListener('click', function() {
	dropdownLinks.style.display = dropdownLinks.style.display === 'block' ? 'none' : 'block';
});

// Service Dropdown
const toggleService = document.getElementById('service-toggle-service');
const dropdownService = document.querySelector('.dropdown-content-service');
toggleService.addEventListener('click', function() {
	dropdownService.style.display = dropdownService.style.display === 'block' ? 'none' : 'block';
});

// About Us Dropdown
const toggleAboutUs = document.getElementById('service-toggle-about-us');
const dropdownAboutUs = document.querySelector('.dropdown-content-about-us');
toggleAboutUs.addEventListener('click', function() {
	dropdownAboutUs.style.display = dropdownAboutUs.style.display === 'block' ? 'none' : 'block';
});


document.getElementById('service-toggle').addEventListener('click', function() {
	fetch('/api/getCategory')
		.then(response => response.json())
		.then(data => {
			const dropdownContent = document.getElementById('dropdown-content');
			dropdownContent.innerHTML = '';

			const columnsPerRow = 1; // Set how many links per row
			let row;

			data.forEach((category, index) => {
				if (index % columnsPerRow === 0) {
					row = document.createElement('div');
					row.classList.add('row');
					dropdownContent.appendChild(row);
				}

				const link_item = document.createElement('div');
				link_item.classList.add('link_item');
				link_item.innerHTML = `<a class="nav-link service" href="/${category.url}">${category.name}</a>`;
				row.appendChild(link_item);
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