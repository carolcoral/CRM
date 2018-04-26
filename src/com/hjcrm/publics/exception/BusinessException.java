package com.hjcrm.publics.exception;

public class BusinessException extends Exception {
	protected Throwable e;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable e) {
		this.e = e;
	}

	public Throwable getE() {
		return e;
	}

	public void setE(Throwable e) {
		this.e = e;
	}

	/**
	 * 获得堆栈内容
	 * 
	 * @return
	 */
	public String getStackContent() {
		StringBuffer sb = new StringBuffer();
		if (e != null) {
			StackTraceElement[] stacks = e.getStackTrace();
			sb.append(e);
			sb.append("\r\n");
			sb.append("异常堆栈：");
			sb.append("\r\n");
			for (int i = 0; i < stacks.length; i++) {
				sb.append(stacks[i].toString());
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
}
