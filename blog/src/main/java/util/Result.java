package util;

public class Result {
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

}
