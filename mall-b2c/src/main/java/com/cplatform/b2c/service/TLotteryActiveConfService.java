package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TLotteryActiveConf;
import com.cplatform.b2c.model.TLotteryPrize;
import com.cplatform.b2c.repository.TLotteryActiveConfDao;

@Service
@Transactional
public class TLotteryActiveConfService {

	@Autowired
	TLotteryActiveConfDao tLotteryActiveConfDao;

	public List<TLotteryActiveConf> getActiveConfList(Integer activeId) {
		return tLotteryActiveConfDao.findActiveConfByActiveId(activeId);
	}

	public List<TLotteryPrize> getPrizeList(Integer activeId) {
		return tLotteryActiveConfDao.findPrizeByActiveId(activeId);
	}

	public List<Map<String, Object>> getMessage(int activeId) throws SQLException {
		return tLotteryActiveConfDao.findMessage(activeId);
	}
}
