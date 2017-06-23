function getWho(workFun) {
	who(function(res) {
		if(res.success == 1) {
			v.userName = res.data.name;
			v.userId = res.data.id;
			workFun(res);
		} else {
			window.location = 'login.html';
		}
	})
}