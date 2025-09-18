const container = document.querySelector('.container');
const registerBtn = document.querySelector('.register-btn');
const loginBtn = document.querySelector('.login-btn');
registerBtn.addEventListener('click', () => {
    container.classList.add('active');
    void container.offsetWidth;
});
loginBtn.addEventListener('click', () => {
    container.classList.remove('active');
    void container.offsetWidth;
});
document.querySelectorAll('.close').forEach(closeBtn => {
    closeBtn.addEventListener('click', function() {
        this.parentElement.style.display = 'none';
    });
});
