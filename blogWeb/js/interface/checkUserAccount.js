function getWho(workFun) {
	who(function(res) {
		if(res.success == 1) {
			v.currentUser = res.data.name;
			v.currentUserId = res.data.id;
			workFun(res);
		} else {
			window.location = 'login.html';
		}
	})
}