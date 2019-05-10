package com.jin.learn.exception;


public enum ExceptionCode {
	/**
	 * 公共code
	 */
	SUCCESS("SUCCESS", 1000, "success", "成功"),
	FAILED("FAILED", 1001, "failed", "失败"),
	LOGIN_ERROR("LOGIN_ERROR", 1002, "login error", "登陆异常"),
	SYSTEM_ERROR("SYSTEM_ERROR", 1003, "system error", "系统异常"),
	NETWORK_ERROR_404("NETWORK_ERROR",1004,"network not found","网络异常 404"),
	PARAM_ERROR("PARAM_ERROR",1005,"param error","传入参数异常"),
	METHOD_ERROR("METHOD_ERROR",1006,"method error","请求方法不支持"),


	TOKEN_FAILURE("TOKEN_FAILURE",240003,"token failure","token失效"),
	NO_PERMISSION("NO_PERMISSION",240004,"no permission","无操作权限"),
	TRY_AGAIN("TRY_AGAIN",240009,"try again","操作失败，请重试");


	private String code;
	private int errorCode;
	private String errorMsg;
	private String desc;

	private ExceptionCode(String code, int errorCode, String errorMsg, String desc) {
		this.code = code;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.desc = desc;
	}

	public static ExceptionCode getByCode(String code) {
		if (code != null && !"".equalsIgnoreCase(code)) {
			ExceptionCode[] errorCodes = values();
			ExceptionCode[] var2 = errorCodes;
			int var3 = errorCodes.length;

			for(int var4 = 0; var4 < var3; ++var4) {
				ExceptionCode acsErrorCode = var2[var4];
				if (acsErrorCode.getCode().equals(code)) {
					return acsErrorCode;
				}
			}

			return null;
		} else {
			return null;
		}
	}

	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}
}
