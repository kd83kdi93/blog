package mapper;

import java.util.List;

import domain.User;
import dto.Family;

public interface UserMapper {
	void add(User u);
	User get(User u);
	void setActivited(User u);
	void setPassword(User u);
	User getByName(String name);
	String getNameById(int id);
	List<Family> getFamily();
	User getById(int id);
}
