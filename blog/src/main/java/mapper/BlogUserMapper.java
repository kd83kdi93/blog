package mapper;

import java.util.List;

import domain.BlogUser;
import dto.Family;

public interface BlogUserMapper {
	void add(BlogUser blogUser);
	BlogUser getByUserId(int userId);
	void update(BlogUser blogUser);
}
