$(document).ready(function() {
	who(function(res) {
		if(res.success == 1) {
			v.userName = res.data.name;
		} else {
			window.location = 'login.html';
		}
	})
})