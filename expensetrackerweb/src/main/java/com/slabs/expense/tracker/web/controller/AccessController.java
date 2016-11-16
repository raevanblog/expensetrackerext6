package com.slabs.expense.tracker.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.slabs.expense.tracker.common.db.entity.UserInfo;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.services.Services;
import com.slabs.expense.tracker.core.services.UserService;
import com.slabs.expense.tracker.util.Base64Encoder;
import com.slabs.expense.tracker.util.JSONUtil;
import com.slabs.expense.tracker.web.MessageConstants;
import com.slabs.expense.tracker.web.WebConstants;

@Controller
@RequestMapping("request")
public class AccessController {
	
	private static final Logger L = LoggerFactory.getLogger(AccessController.class);

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			Map<String, String> parameters = JSONUtil
					.getMapFromInputStream(request.getInputStream());
			String[] credentials = Base64Encoder.decode(parameters.get("credential"), ":");
			List<UserInfo> users = service.select(credentials[0], Boolean.TRUE);
			if (users != null && !users.isEmpty()) {
				UserInfo info = users.get(0);
				if (info.getPassword().equals(credentials[1])) {
					HttpSession session = request.getSession(true);
					info.setPassword("");
					session.setAttribute(WebConstants.LOGGED_IN_USER, info);
					session.setMaxInactiveInterval(600);
					output.put(WebConstants.SUCCESS, Boolean.TRUE);
					output.put(WebConstants.MESSAGE, MessageConstants.LOGIN_SUCCESS);
					output.put(WebConstants.USER, info);
				} else {
					output.put(WebConstants.SUCCESS, Boolean.FALSE);
					output.put(WebConstants.MESSAGE, MessageConstants.CHECK_PWD);
					output.put(WebConstants.USER, null);
				}

			} else {
				output.put(WebConstants.SUCCESS, Boolean.FALSE);
				output.put(WebConstants.MESSAGE, MessageConstants.CHECK_USRNME_PWD);
				output.put(WebConstants.USER, null);
			}
			return new ModelAndView(WebConstants.JSON, output);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			output.put(WebConstants.USER, null);
			return new ModelAndView(WebConstants.JSON, output);
		}
	}

	@RequestMapping(value = "logout", method = { RequestMethod.POST })
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			session.removeAttribute(WebConstants.LOGGED_IN_USER);
			session.invalidate();
		}
		output.put(WebConstants.SUCCESS, Boolean.TRUE);
		output.put(WebConstants.MESSAGE, MessageConstants.LOGD_OFF);
		return new ModelAndView(WebConstants.JSON, output);
	}

	@RequestMapping(value = "session", method = { RequestMethod.GET })
	public ModelAndView getSession(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession(Boolean.FALSE);
			if (session != null) {
				UserInfo user = (UserInfo) session.getAttribute(WebConstants.LOGGED_IN_USER);
				output.put(WebConstants.SUCCESS, Boolean.TRUE);
				output.put(WebConstants.MESSAGE, MessageConstants.SESSION_ACTIVE);
				output.put(WebConstants.USER, user);
			} else {
				output.put(WebConstants.SUCCESS, Boolean.FALSE);
				output.put(WebConstants.MESSAGE, MessageConstants.INVALID_SESSION);
				output.put(WebConstants.USER, null);

			}
			return new ModelAndView("json", output);
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			output.put(WebConstants.USER, null);
			return new ModelAndView("json", output);
		}
	}

	@RequestMapping(value = "username", method = { RequestMethod.GET })
	public ModelAndView isUserNameAvailable(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);

			String username = request.getParameter("checkAvailable");
			Boolean availability = service.isUserNameAvailable(username);

			output.put(WebConstants.SUCCESS, Boolean.TRUE);
			output.put(MessageConstants.AVAILABLE, availability);
			if (availability) {
				output.put(WebConstants.MESSAGE, MessageConstants.AVAILABLE);
			} else {
				output.put(WebConstants.MESSAGE, MessageConstants.NOT_AVAILABLE);
			}

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.SERVER_ERROR);
			return new ModelAndView(WebConstants.JSON, output);
		}
		return new ModelAndView(WebConstants.JSON, output);
	}

	@RequestMapping(value = "user/create", method = { RequestMethod.POST })
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			UserInfo user = JSONUtil.getObjectFromJSON(request.getInputStream(), UserInfo.class);
			Integer isCreated = service.create(user);
			output.put(WebConstants.SUCCESS, Boolean.TRUE);
			if (isCreated > 0) {
				output.put(WebConstants.IS_USER_REGISTERED, Boolean.TRUE);
				output.put(WebConstants.MESSAGE, MessageConstants.USER_REGISTERED);
			} else {
				output.put(WebConstants.IS_USER_REGISTERED, Boolean.FALSE);
				output.put(WebConstants.MESSAGE, MessageConstants.USER_NOT_REGISTERED);
			}

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			return new ModelAndView(WebConstants.JSON, output);
		}
		return new ModelAndView(WebConstants.JSON, output);
	}

}
