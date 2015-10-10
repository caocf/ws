package com.cplatform.mall.back.websys.web;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.dao.UserDao;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.websys.dao.SysAnnouncementDao;
import com.cplatform.mall.back.websys.entity.SysAnnouncement;
import com.cplatform.mall.back.websys.entity.SysFileImg;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.mall.back.websys.service.SysAnnouncementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.Map;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-5 上午11:15
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping(value = "/websys/announcement")
public class SysAnnouncementController {

    @Autowired
    SysAnnouncementService sysAnnouncementService;

    @Autowired
    SysAnnouncementDao sysAnnouncementDao;

    @Autowired
    UserDao userDao;
    
    @Autowired
    private LogUtils logUtils;

    @RequestMapping("")
    public String findLogistics(SysAnnouncement sysAnnouncement,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                Model model) throws SQLException {

        Page<Map<String, String>> pageData = this.sysAnnouncementService.getAnnouncementPage(sysAnnouncement, page, Page.DEFAULT_PAGESIZE);
        model.addAttribute("pageData", pageData);


        model.addAttribute("destMap", SysAnnouncement.DEST_MAP);

        model.addAttribute("statusMap", SysAnnouncement.STATUS_MAP);

        model.addAttribute("queryObject", sysAnnouncement);

        return "/websys/announcement/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        SysAnnouncement sysAnnouncement = new SysAnnouncement();
        model.addAttribute("sysAnnouncement", sysAnnouncement);
        model.addAttribute("destMap", SysAnnouncement.DEST_MAP);
        return "/websys/announcement/form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addPost(@ModelAttribute("sysAnnouncement") SysAnnouncement sysAnnouncement, BindingResult result,MultipartFile uploadfileAdvise,MultipartFile uploadfileAdviseExtend) throws Exception {
        if (StringUtils.isBlank(sysAnnouncement.getDescription()) && sysAnnouncement.getPubDest().equals("1")){
            result.rejectValue("description", "server", "公告内容不能为空");
            return JsonRespWrapper.error(result.getFieldErrors());
        }
        if("1".equals(sysAnnouncement.getPubDest())){
    		sysAnnouncement.setTitle(sysAnnouncement.getTitle().substring(0, sysAnnouncement.getTitle().length()-2));
    	}
        if("3".equals(sysAnnouncement.getPubDest())){
        	sysAnnouncement.setTitle(sysAnnouncement.getTitle().substring(1));
        }
        logUtils.logAdd("添加公告内容","添加成功");
        sysAnnouncement.setEndTime(sysAnnouncement.getEndTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
        this.sysAnnouncementService.save(sysAnnouncement,uploadfileAdvise,uploadfileAdviseExtend);
        return JsonRespWrapper.success("操作成功", "/websys/announcement");
       
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        SysAnnouncement sysAnnouncement = sysAnnouncementDao.findOne(id);
        model.addAttribute("sysAnnouncement", sysAnnouncement);
        model.addAttribute("destMap", SysAnnouncement.DEST_MAP);
        return "/websys/announcement/edit";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editPost(@ModelAttribute("sysAnnouncement") SysAnnouncement sysAnnouncement, BindingResult result,MultipartFile uploadfileAdvise,MultipartFile uploadfileAdviseExtend) throws Exception {
    	if(null == sysAnnouncement.getPubDest()){
    		SysAnnouncement s = sysAnnouncementDao.findOne(sysAnnouncement.getId());
    		sysAnnouncement.setPubDest(s.getPubDest());
    	}
    	if (StringUtils.isBlank(sysAnnouncement.getDescription()) && sysAnnouncement.getPubDest().equals("1")){
            result.rejectValue("description", "server", "公告内容不能为空");
            return JsonRespWrapper.error(result.getFieldErrors());
        }
        if("1".equals(sysAnnouncement.getPubDest())){
    		sysAnnouncement.setTitle(sysAnnouncement.getTitle().split(",")[0]);
    	}
        if("3".equals(sysAnnouncement.getPubDest())){
        	sysAnnouncement.setTitle(sysAnnouncement.getTitle().split(",")[1]);
        }
        sysAnnouncement.setEndTime(sysAnnouncement.getEndTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
        this.sysAnnouncementService.save(sysAnnouncement,uploadfileAdvise,uploadfileAdviseExtend);
        logUtils.logAdd("编辑公告内容，","修改成功,公告编号：", +sysAnnouncement.getId());
        return JsonRespWrapper.success("操作成功", "/websys/announcement");
    }
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, Model model) {
        SysAnnouncement sysAnnouncement = sysAnnouncementDao.findOne(id);
        model.addAttribute("sysAnnouncement", sysAnnouncement);
        model.addAttribute("destMap", SysAnnouncement.DEST_MAP);
        model.addAttribute("statusMap", SysAnnouncement.STATUS_MAP);

        if (sysAnnouncement.getPubUser() != null) {
            model.addAttribute("pubUser", userDao.findOne(sysAnnouncement.getPubUser()));
        }

        if (sysAnnouncement.getAuditUser() != null) {
            model.addAttribute("auditUser", userDao.findOne(sysAnnouncement.getAuditUser()));
        }
        
        SysFileImg advise  = sysAnnouncementService.findImg(sysAnnouncement.getId(), BsFileService.ADVISE_PIC_KEY);
		if(null != advise){
			model.addAttribute("advisePath", advise.getFileWebPath());
		}
		 SysFileImg adviseExtend   = sysAnnouncementService.findImg(sysAnnouncement.getId(), BsFileService.ADVISE_EXTEND_PIC_KEY);
		if(null != adviseExtend){
			model.addAttribute("adviseExtendPath", adviseExtend.getFileWebPath());
		}
        
        model.addAttribute("tp", "view");
        return "/websys/announcement/view";
    }

    @RequestMapping(value = "/audit", method = RequestMethod.GET)
    public String audit(Long id, Model model) {
        SysAnnouncement sysAnnouncement = sysAnnouncementDao.findOne(id);
        model.addAttribute("sysAnnouncement", sysAnnouncement);
        model.addAttribute("destMap", SysAnnouncement.DEST_MAP);
        model.addAttribute("statusMap", SysAnnouncement.STATUS_MAP);

        if (sysAnnouncement.getPubUser() != null) {
            model.addAttribute("pubUser", userDao.findOne(sysAnnouncement.getPubUser()));
        }

        if (sysAnnouncement.getAuditUser() != null) {
            model.addAttribute("auditUser", userDao.findOne(sysAnnouncement.getAuditUser()));
        }
        SysFileImg advise  = sysAnnouncementService.findImg(sysAnnouncement.getId(), BsFileService.ADVISE_PIC_KEY);
		if(null != advise){
			model.addAttribute("advisePath", advise.getFileWebPath());
		}
		 SysFileImg adviseExtend   = sysAnnouncementService.findImg(sysAnnouncement.getId(), BsFileService.ADVISE_EXTEND_PIC_KEY);
		if(null != adviseExtend){
			model.addAttribute("adviseExtendPath", adviseExtend.getFileWebPath());
		}

        model.addAttribute("tp", "audit");
        return "/websys/announcement/view";
    }

    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public Object auditPost(Long id, int auditResult) {
        this.sysAnnouncementService.audit(id, auditResult == 1);
        return JsonRespWrapper.success("操作成功", "/websys/announcement");
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(@RequestParam(value = "id") Long id) {
        this.sysAnnouncementService.delete(id);
        logUtils.logAdd("删除公告内容，","删除成功,公告编号：", +id);
        return JsonRespWrapper.success("操作成功", "/websys/announcement");
    }


}
