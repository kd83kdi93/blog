function index(data,successFun) {
	sendAJAX('get','/blogapi/index',data,successFun);
}

function getByPage(data,successFun) {
	sendAJAX('get','/blogapi/getByPage',data,successFun);
}


function getBlog(data,successFun) {
	sendAJAX('get','/blogapi/getBlog',data,successFun);
}

function getBlogUser(data,successFun) {
	sendAJAX('get','/blogapi/getBlogUser',data,successFun);
}

function changeBlogUserInfo(fromData,successFun) {
	sendFileAJAX('/blogapi/changeBlogUserInfo',fromData,successFun);
}
