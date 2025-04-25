$(document).ready(function(){
  // Open sidebar
  $(".open-btn").click(function(){
    $(".sidebar").css("width","250px");
    $(".content").css("margin-left","250px");
  });

  // Close sidebar
  $(".close-btn").click(function(){
    $(".sidebar").css("width","0");
    $(".content").css("margin-left","0");
  });
});
/*=========================================*/
 const input = document.getElementById('titleUrl');
    input.addEventListener('input', function () {
      let value = input.value;
      value = value.replace(/[^a-zA-Z0-9]/g, '-');
      value = value.replace(/-+/g, '-');
      value = value.replace(/^-|-$/g, '-');
      input.value = value;
    });
    
    
/*==========================================*/

document.getElementById('category_delete').addEventListener('click', async function () {
	const responseBox = document.getElementById('successResponse');
	const id = document.querySelector('input[name="id"]').value.trim();
	if (!id ) {
      responseBox.innerText = 'fields are required.';
      responseBox.style.color = 'red';
      setTimeout(() => {
        responseBox.innerText = '';
      }, 5000);
      return;
    }
    
    var settings = {
	  "url": "/api/deleteCategory/"+id,
	  "method": "DELETE",
	  "timeout": 0,
	  "headers": {
	    "Content-Type": "application/json"
	  },
	  "data": " ",
	};
	$.ajax(settings).done(function (response) {
	  responseBox.innerText = JSON.stringify("Successfully category permanently delete", null, 2);
	  responseBox.style.color = 'green';
	});
	
    setTimeout(() => {
      responseBox.innerText = '';
    }, 5000);
  });

  document.getElementById('category_sub').addEventListener('click', async function () {
	const responseBox = document.getElementById('successResponse');
	const name = document.querySelector('input[name="name"]').value.trim();
	const url = document.querySelector('input[name="url"]').value.trim();
	if (!name || !url) {
      responseBox.innerText = 'Both fields are required.';
      responseBox.style.color = 'red';
      setTimeout(() => {
        responseBox.innerText = '';
      }, 5000);
      return;
    }
    const data = {
      name: name,
      url: url
    };

    
    var settings = {
	  "url": "/api/addCategory",
	  "method": "POST",
	  "timeout": 0,
	  "headers": {
	    "Content-Type": "application/json"
	  },
	  "data": JSON.stringify(data),
	};
	$.ajax(settings).done(function (response) {
	  console.log(response);
	  responseBox.innerText = JSON.stringify(response, null, 2);
	  responseBox.style.color = 'green';
	});
	
    setTimeout(() => {
      responseBox.innerText = '';
    }, 5000);
  });
  
  
  /*=================================================*/
   