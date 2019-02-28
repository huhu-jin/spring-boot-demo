package com.jin.learn.exception;


public enum ExceptionCode {
	/**
	 * 公共code
	 */
	SUCCESS("SUCCESS", 1000, "success", "成功"),
	FAILED("FAILED", 1001, "failed", "失败"),
	LOGIN_ERROR("LOGIN_ERROR", 1002, "login error", "登陆异常"),
	SYSTEM_ERROR("SYSTEM_ERROR", 1003, "system error", "流量过大系统开小差啦，请尝试重新发起"),
	NETWORK_ERROR("NETWORK_ERROR",1004,"network error","网络异常"),
	PARAM_ERROR("PARAM_ERROR",1005,"param error","传入参数异常"),
	DATA_NOT_FOUND("DATA_NOT_FOUND", 1006, "data not found", "数据未找到"),
	OPERATE_FREQUENTLY("OPERATE_FREQUENTLY", 1007, "operate frequently", "操作频繁"),
	DATA_SYNCHRONIZE_FAILED("DATA_SYNCHRONIZE_FAILED", 1008, "synchronize data failed", "数据同步异常"),

	/**
	 * code 240000-249999
	 */
	LOGIN_ERROR_REGISTER("LOGIN_ERROR_REGISTER",240000,"not register","该用户未注册"),
	LOGIN_ERROR_PASSWORD("LOGIN_ERROR_PASSWORD",240001,"password error","密码错误"),
	USER_ID_IS_NULL("USER_ID_IS_NULL",240002,"userId is null error","用户id为空"),
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
