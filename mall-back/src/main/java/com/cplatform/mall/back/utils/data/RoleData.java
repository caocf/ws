package com.cplatform.mall.back.utils.data;

public class RoleData {

	private String module;

	private String unitType;

	private String description;

	private String opStr;

	private String operate;

	public RoleData() {
	}

	public RoleData(String module, String unitType, String description, String opStr, String operate) {
		this.module = module;
		this.unitType = unitType;
		this.description = description;
		this.opStr = opStr;
		this.operate = operate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpStr() {
		return opStr;
	}

	public void setOpStr(String opStr) {
		this.opStr = opStr;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	// -----bus method ---------------
}
