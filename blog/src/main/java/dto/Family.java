package dto;

import java.io.Serializable;

public class Family implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2292248398585391418L;
	private int id;
	private String name;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
