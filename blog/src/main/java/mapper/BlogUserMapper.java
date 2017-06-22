package mapper;

import domain.BlogUser;

public interface BlogUserMapper {
	void add(BlogUser blogUser);
	BlogUser getByUserId(int userId);
	
}
