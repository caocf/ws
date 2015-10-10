package com.cplatform.mall.back.sys.service;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.sys.dao.SysSearchIdxDao;
import com.cplatform.mall.back.sys.entity.SysSearchIdx;
import com.cplatform.mall.back.sys.web.SysIndexUpdateController;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.LogUtils;

@Service
public class SysSearchIdxService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private SysSearchIdxDao sysSearchIdxDao;
	
	@Autowired
	LogUtils logUtils;
	
	private static final Logger log = Logger.getLogger(SysIndexUpdateController.class);

	// 查询索引全量更新列表信息
	public Page<SysSearchIdx> listIdx(SysSearchIdx searchIdx, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		sql.append("select t.*,t2.user_code usercode,t2.user_name username from T_SYS_SEARCH_IDX t,T_SYS_USER t2 where t.actor = t2.id order by t.id desc");
		return dbHelper.getPage(sql.toString(), SysSearchIdx.class, pageNo, pageSize);
	}

	// 新增索引全量更新信息
	@Transactional
	public void saveSysSearchIdx(SysSearchIdx searchIdx) {
		sysSearchIdxDao.save(searchIdx);
	}

	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysSearchIdx findById(Long id) {
		return sysSearchIdxDao.findOne(id);
	}
	public String searchIndexUpdate(HttpServletRequest request,URL url,String flag){
		String msg = "搜索索引"+flag+"更新成功！";
		java.io.BufferedReader in = null;
		try {
			String res = "";
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			// 设置连接超时时间（单位毫秒）
			conn.setConnectTimeout(1000 * 60 * 2);
			in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
			// 记录更新信息
			SysSearchIdx searchIdx = new SysSearchIdx();
			searchIdx.setType("search");
			searchIdx.setTypename("搜索索引"+flag+"更新");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			searchIdx.setUpdatetime(sdf.format(new Date()));
			// 获取当前的登录用户
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
			searchIdx.setActor(sessionUser.getId());
			saveSysSearchIdx(searchIdx);
			log.info("搜索索引"+flag+"更新成功：" + searchIdx.getTypename() + "--" + searchIdx.getUpdatetime() + "--" + sessionUser.getName() + "--返回数据:" + res);
		}
		catch (Exception e) {
			logUtils.logAdd("搜索索引"+flag+"更新失败", e.getMessage());
			msg = "搜索索引"+flag+"更新失败！";
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
				}
			}
		}
		return msg;
	}
}
