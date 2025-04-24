 
 const toggler = document.querySelector('.navbar-toggler');
  const collapse = document.querySelector('#navbarNav');

  toggler.addEventListener('click', () => {
    collapse.classList.toggle('active');
  });