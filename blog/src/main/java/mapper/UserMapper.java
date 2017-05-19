package mapper;

import domain.User;

public interface UserMapper {
	void add(User u);
	User get(User u);
	void setActivited(User u);
	void setPassword(User u);
	
}
