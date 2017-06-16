package util;

import java.io.Serializable;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4864197110143736535L;
	private boolean success = false;
	private Object data = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void setStateAndData(boolean state, Object data) {
		this.success = state;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", data=" + data + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
