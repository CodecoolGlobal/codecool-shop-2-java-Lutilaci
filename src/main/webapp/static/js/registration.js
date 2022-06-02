let registrationButton = document.getElementById("registration");
let headerTxt = document.querySelector(".headerText");
let modal = document.getElementById("myModal");
let modalBody = document.querySelector(".modal-body");

export function registrationAddEventListener() {
    let headerText = "Registration";
    registrationButton.addEventListener("click", () => {

        modal.style.display = "block";
        modalBody.innerHTML = `<form action="/action_page.php">
                                 
                                  <div class="container">
                                    <p>Please fill in this form to create an account.</p>
                                    <hr>
                                
                                    <label for="email"><b>Email</b></label><br>
                                    <input type="text" placeholder="Enter Email" name="email" id="email" required><br>
                                
                                    <label for="psw"><b>Password</b></label><br>
                                    <input type="password" placeholder="Enter Password" name="psw" id="psw" required><br>
                                
                                    <label for="psw-repeat"><b>Repeat Password</b></label><br>
                                    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required><br>
                                    
                                    <h3>Address</h3>
                                    <label for="zip-code"><b>Zip code</b></label><br>
                                    <input type="number" placeholder="Enter Zip code" name="zip" id="zip" required><br>
                                    
                                    <label for="city"><b>City</b></label><br>
                                    <input type="text" placeholder="Enter you city" name="city" id="city" required><br>
                                    
                                    <label for="street"><b>Street and house number</b></label><br>
                                    <input type="text" placeholder="Enter street and house number" name="street" id="street" required><br>
                                    <hr>
                                    <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
                                
                                    <button type="submit" class="registerbtn">Register</button>
                                  </div>
                                  
                                  <div class="container signin">
                                    <p>Already have an account? <a href="#">Sign in</a>.</p>
                                  </div>
                                </form>`
    })
    headerTxt.innerHTML = headerText;

}