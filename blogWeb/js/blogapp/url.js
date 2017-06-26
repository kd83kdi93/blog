var locationURL = '/blog'

function sendAJAX(method, url, data, successCall) {
	$.ajax({
		method: method,
		type: 'json',
		url: locationURL + url,
		data: data,
		success: successCall,
		error: function() {
			alert('请求发生了错误');
		}
	});
}

function sendFileAJAX(url, fromData, successCall) {
	$.ajax({
		url: locationURL + url,
		type: 'POST',
		data: fromData,
		processData: false, // 不处理数据
		contentType: false, // 不设置内容类型
		success: successCall,
		error: function() {
			alert('请求发生了错误');
		}
	});
}