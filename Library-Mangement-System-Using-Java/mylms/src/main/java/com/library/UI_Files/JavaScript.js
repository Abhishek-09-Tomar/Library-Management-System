let userDetails = {};

// Toggle Signup and Login Forms
function toggleForm(formType) {
    if (formType === 'signup') {
        document.getElementById('signupForm').classList.add('show');
        document.getElementById('loginForm').classList.remove('show');
    } else {
        document.getElementById('loginForm').classList.add('show');
        document.getElementById('signupForm').classList.remove('show');
    }
}

// Close the Forms
function closeForm() {
    document.getElementById('signupForm').classList.remove('show');
    document.getElementById('loginForm').classList.remove('show');
}

// Sign-Up Logic
document.getElementById('signup').onsubmit = function (e) {
    e.preventDefault();
    const userID = document.getElementById('userID').value;
    const username = document.getElementById('signupUsername').value;
    const contact = document.getElementById('contact').value;
    const password = document.getElementById('signupPassword').value;
    userDetails[username] = { password, userID, contact };
    alert("Sign-up successful!");
    closeForm();
};

// Login Logic
document.getElementById('login').onsubmit = function (e) {
    e.preventDefault();
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    if (userDetails[username] && userDetails[username].password === password) {
        alert("Login successful!");
        window.location.href = "dashboard.html"; // Redirect to Dashboard
    } else {
        alert("Invalid credentials!");
    }
};
