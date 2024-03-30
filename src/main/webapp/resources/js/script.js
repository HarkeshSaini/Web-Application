 function validateForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;

            if (username === "" || password === "") {
                document.getElementById("errorMessage").innerText = "Username and password are required.";
                return false;
            }

            // You can add more complex validation logic here if needed

            return true;
        }