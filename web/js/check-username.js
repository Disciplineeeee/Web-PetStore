
var elUsername = document.getElementById('username');
    elMsg                   = document.getElementById('isExistInfo');
var xhr = '';
function checkUsername(username) {
    if(username === null || username.length === 0 || username === '') {
        elMsg.textContent = '用户名不能为空';
    }
    else if(username.length <= 5) {
        elMsg.textContent = '用户名长度必须大于5';
    }
    else{
        sendRequest(username);
    }
}
elUsername.addEventListener('blur',function(){
    var username = elUsername.value.trim();
    checkUsername(username);
})

function sendRequest(username) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open('GET','http://localhost:8080/PetStore_Web_exploded/usernameExist?username=' + username);
    xhr.send();
    console.log(xhr.readyState);
}
function process() {
    if(xhr.readyState === 4) {
        if(xhr.status === 200) {
            var responseInfo = xhr.responseText.trim();
            if(responseInfo === 'E'){
                elMsg.textContent = '用户名已存在';
            }
            if(responseInfo === 'N'){
                elMsg.textContent = '用户名可用';
            }
        }
    }
}