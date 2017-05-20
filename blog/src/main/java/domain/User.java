package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private int id;
	private String name;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String authcode;
	@JsonIgnore
	private int activited;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public int getActivited() {
		return activited;
	}

	public void setActivited(int activited) {
		this.activited = activited;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
