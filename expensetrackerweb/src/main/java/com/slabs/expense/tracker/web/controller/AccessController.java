package com.slabs.expense.tracker.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.slabs.expense.tracker.web.WebConstants;

@Controller
@RequestMapping("request")
public class AccessController {

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE, UserService.class);
			Map<String, String> parameters = JSONUtil.getMapFromInputStream(request.getInputStream());
			String[] credentials = Base64Encoder.decode(parameters.get("credential"), ":");
			UserInfo info = service.select(credentials[0]);
			if (info != null) {
				if (info.getPassword().equals(credentials[1])) {
					HttpSession session = request.getSession(true);
					info.setPassword("");
					session.setAttribute(WebConstants.LOGGED_IN_USER, info);
					session.setMaxInactiveInterval(600);
					output.put(WebConstants.SUCCESS, true);
					output.put(WebConstants.MESSAGE, "Login Successful");
					output.put(WebConstants.USER, info);
				} else {
					output.put(WebConstants.SUCCESS, false);
					output.put(WebConstants.MESSAGE, "* Please check your password");
					output.put(WebConstants.USER, null);
				}

			} else {
				output.put(WebConstants.SUCCESS, false);
				output.put(WebConstants.MESSAGE, "* Please check your username/password");
				output.put(WebConstants.USER, null);
			}
			return new ModelAndView("json", output);
		} catch (Exception e) {
			output.put(WebConstants.SUCCESS, false);
			output.put(WebConstants.MESSAGE, "* Exception occurred, please contact customer support");
			output.put(WebConstants.USER, null);
			return new ModelAndView(WebConstants.JSON, output);
		}
	}

	@RequestMapping(value = "logout", method = { RequestMethod.POST })
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebConstants.LOGGED_IN_USER);
			session.invalidate();
		}
		output.put(WebConstants.SUCCESS, true);
		output.put(WebConstants.MESSAGE, "Logged Off");
		return new ModelAndView(WebConstants.JSON, output);
	}

	@RequestMapping(value = "session", method = { RequestMethod.GET })
	public ModelAndView getSession(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				UserInfo user = (UserInfo) session.getAttribute(WebConstants.LOGGED_IN_USER);
				output.put(WebConstants.SUCCESS, true);
				output.put(WebConstants.MESSAGE, "Session Active");
				output.put(WebConstants.USER, user);
			} else {
				output.put(WebConstants.SUCCESS, false);
				output.put(WebConstants.MESSAGE, "Session Invalid");
				output.put(WebConstants.USER, null);

			}
			return new ModelAndView("json", output);
		} catch (Exception e) {
			output.put(WebConstants.SUCCESS, false);
			output.put(WebConstants.MESSAGE, "* Exception occurred, please contact customer support");
			output.put(WebConstants.USER, null);
			return new ModelAndView("json", output);
		}
	}

}
