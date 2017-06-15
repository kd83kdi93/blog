package service;

import domain.User;

public interface UserService {
	User find(String name, String password);
	User findByName(String name);
	void activited(String name, String authCode);
	void register(String name, String password);
	boolean resetPassword(String name);
}
