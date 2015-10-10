package com.cplatform.mall.back.comment.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.comment.entity.ItemComment;
import com.cplatform.mall.back.comment.entity.ItemCommentReply;
import com.cplatform.mall.back.comment.service.CommentService;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;

@Controller
@RequestMapping("/comment/item")
public class ItemCommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	private LogUtils logUtils;

	@RequestMapping(value = { "list", "/", "" })
	public String itemCommentList(ItemComment itemComment, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model)
	        throws SQLException {
		if (itemComment == null) {
			itemComment = new ItemComment();
			// 默认查询评论类型的商品评论
			itemComment.setType(1);
		}

		Page<ItemComment> commentPage = commentService.findComment(itemComment, page);
		model.addAttribute("commentPage", commentPage);
		model.addAttribute("typeMap", ItemComment.commentTypeMap);
		return "/comment/comment-item-list";
	}

	@RequestMapping(value = "auditPage", method = RequestMethod.GET)
	public String auditPage(@RequestParam(value = "id") Long id, Model model) {
		ItemComment vo = commentService.findItemCommentById(id);
		model.addAttribute("itemComment", vo);
		return "/comment/audit";
	}

	/**
	 * 批量审核
	 * @param id	id数组
	 * @param status
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "auditAllPage", method = RequestMethod.POST)
	@ResponseBody
	public Object auditAllPage(long[] id,Integer status,HttpServletRequest request, Model model) {
	
	try {
		for(long perid:id){
		ItemComment itemComment = commentService.findItemCommentById(perid);
		itemComment.setStatus(status);
		commentService.addOrUpdateItemComment(itemComment);
		
	}
	} catch (Exception e) {
	   logUtils.logAdd("批量审核", "审核失败，编号"+id);
	}
		logUtils.logAdd("批量审核", "审核成功，编号"+id);
		return JsonRespWrapper.success("操作成功！", "/comment/item/list");
	}
	
	@RequestMapping(value = "audit", method = RequestMethod.POST)
	@ResponseBody
	public Object auditComment(@RequestParam(value = "id") Long id, @RequestParam(value = "status") Long status, HttpSession session, Model model) {
		ItemComment vo = commentService.findItemCommentById(id);
		vo.setStatus(status.intValue());
		vo.setAuditTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
		SessionUser sessionUser = (SessionUser) session.getAttribute(SessionUser.SESSION_USER_KEY);
		vo.setAuditUser(sessionUser.getId());
		commentService.addOrUpdateItemComment(vo);
		return JsonRespWrapper.success("操作成功！", "/comment/item/list");
	}

	@RequestMapping(value = "replyPage", method = RequestMethod.GET)
	public String replyPage(@RequestParam(value = "id") Long id, Model model) {
		ItemComment itemComment = commentService.findItemCommentById(id);

		model.addAttribute("itemComment", itemComment);
		ItemCommentReply reply = this.commentService.findItemCommentReplyByCommentId(id);
		model.addAttribute("reply", reply);
		// model.addAttribute("id", id);
		return "/comment/reply";
	}

	@RequestMapping(value = "reply", method = RequestMethod.POST)
	@ResponseBody
	public Object replyAction(ItemCommentReply vo) {
		try {
			vo.setUpdateTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			ItemCommentReply oldvo = this.commentService.findItemCommentReplyByCommentId(vo.getCommentId());
			if (oldvo == null) {
				this.commentService.addOrUpdateItemCommentReply(vo);
			} else {
				oldvo.setContent(vo.getContent());
				oldvo.setNickName(vo.getNickName());
				this.commentService.addOrUpdateItemCommentReply(oldvo);
			}
		} catch (Exception e) {
			   logUtils.logAdd("批量审核", "审核失败，编号"+vo.getId());
		}
		   logUtils.logAdd("批量审核", "审核成功，编号"+vo.getId());
		return JsonRespWrapper.success("操作成功！", "/comment/item/list");
	}
}
