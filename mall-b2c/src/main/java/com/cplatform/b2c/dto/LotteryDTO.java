package com.cplatform.b2c.dto;

import java.util.List;

import com.cplatform.b2c.model.LotteryBean;

// "FLAG":0,"MSG":"查询成功","TOTAL_ROW":8}
public class LotteryDTO {

	private List<LotteryBean> DATA;

	private Integer FLAG;

	private String MSG;

	private int TOTAL_ROW;

	public List<LotteryBean> getDATA() {
		return DATA;
	}

	public void setDATA(List<LotteryBean> dATA) {
		DATA = dATA;
	}

	public Integer getFLAG() {
		return FLAG;
	}

	public void setFLAG(Integer fLAG) {
		FLAG = fLAG;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public int getTOTAL_ROW() {
		return TOTAL_ROW;
	}

	public void setTOTAL_ROW(int tOTALROW) {
		TOTAL_ROW = tOTALROW;
	}

}
