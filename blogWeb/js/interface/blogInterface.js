function index(data,successFun) {
	sendAJAX('get','/blogapi/index',data,successFun);
}

function getByPage(data,successFun) {
	sendAJAX('get','/blogapi/getByPage',data,successFun);
}


function getBlog(data,successFun) {
	sendAJAX('get','/blogapi/getBlog',data,successFun);
}
