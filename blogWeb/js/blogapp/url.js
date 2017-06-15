var locationURL = '/blog'

function sendAJAX(method, url, data, successCall) {
	$.ajax({
		method:method,
		type:'json', 
		url: locationURL+url, 
		data: data, 
		success: successCall,
		error: function() {
			alert('请求发生了错误');
		}
	});
}
