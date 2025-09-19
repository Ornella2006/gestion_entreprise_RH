document.addEventListener('DOMContentLoaded', function() {
    const container = document.querySelector('.container');
    const registerBtn = document.querySelector('.register-btn');
    const loginBtn = document.querySelector('.login-btn');
    
    if (registerBtn && loginBtn) {
        registerBtn.addEventListener('click', () => {
            container.classList.add('active');
        });
        
        loginBtn.addEventListener('click', () => {
            container.classList.remove('active');
        });
    }
    
    // Fermer les messages d'alerte
    document.querySelectorAll('.close').forEach(closeBtn => {
        closeBtn.addEventListener('click', function() {
            this.parentElement.style.display = 'none';
        });
    });
    
    // Fermer automatiquement les messages après 5 secondes
    setTimeout(() => {
        document.querySelectorAll('.error-bar, .success-bar').forEach(alert => {
            alert.style.display = 'none';
        });
    }, 5000);
    
    // Validation des formulaires
    const loginForm = document.querySelector('.login form');
    const registerForm = document.querySelector('.register form');
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            if (!this.checkValidity()) {
                e.preventDefault();
                highlightInvalidFields(this);
            }
        });
    }
    
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            const password = this.querySelector('input[name="password"]');
            const confirmPassword = this.querySelector('input[name="confirm_password"]');
            
            if (password && confirmPassword && password.value !== confirmPassword.value) {
                e.preventDefault();
                confirmPassword.setCustomValidity('Les mots de passe ne correspondent pas');
                highlightInvalidFields(this);
            } else if (confirmPassword) {
                confirmPassword.setCustomValidity('');
            }
            
            if (!this.checkValidity()) {
                e.preventDefault();
                highlightInvalidFields(this);
            }
        });
    }
    
    function highlightInvalidFields(form) {
        form.querySelectorAll('input, select').forEach(field => {
            if (!field.checkValidity()) {
                field.style.borderColor = 'var(--error-color)';
            }
        });
    }
});