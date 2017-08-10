package com.example.aabdelnazeer.foodrecipeproject.CallBacks;

public abstract class SuccessFailureCallBack<T> {
	private boolean active=true;
	public abstract void  onSuccess(T data);
	public abstract void onFail(Object... data);

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
