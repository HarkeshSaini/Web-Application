 

const dropdown = document.querySelector('.custom-dropdown');
const selected = dropdown.querySelector('.selected');
const optionsContainer = dropdown.querySelector('.options');
const optionsList = optionsContainer.querySelectorAll('li');

selected.addEventListener('click', () => {
  dropdown.classList.toggle('active');
});

optionsList.forEach(option => {
  option.addEventListener('click', () => {
    selected.textContent = option.textContent;
    selected.setAttribute('data-value', option.getAttribute('data-value'));
    dropdown.classList.remove('active');
  });
});

document.addEventListener('click', function (e) {
  if (!dropdown.contains(e.target)) {
    dropdown.classList.remove('active');
  }
});


/*phone view*/

 const toggler = document.querySelector('.navbar-toggler');
  const collapse = document.querySelector('#navbarNav');

  toggler.addEventListener('click', () => {
    collapse.classList.toggle('active');
  });