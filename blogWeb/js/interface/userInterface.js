
function who(successFun) {
	sendAJAX('get','/user/who',null,successFun);
}

function logout(successFun) {
	sendAJAX('get','/user/logout',null,successFun);
}

function resetPassword(data,successFun) {
	sendAJAX('post','/user/resetpassword',data,successFun);
}

function register(data,successFun) {
	sendAJAX('post', '/user/register', data, successFun);
}

function login(data,successFun) {
	sendAJAX('post', '/user/login', data, successFun);
}

function CONsole(data,successFun) {
	sendAJAX('post', '/console', data, successFun);
}
