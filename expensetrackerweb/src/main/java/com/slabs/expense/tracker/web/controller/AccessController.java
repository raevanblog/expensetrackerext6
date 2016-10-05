package com.slabs.expense.tracker.web.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("login")
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
					output.put("success", true);
					output.put("message", "Login Successful");
					output.put("user", info);
				} else {
					output.put("success", false);
					output.put("message", "* Please check your password");
					output.put("user", null);
				}

			} else {
				output.put("success", false);
				output.put("message", "* Please check your username/password");
				output.put("user", null);
			}
			return new ModelAndView("json", output);
		} catch (Exception e) {
			output.put("success", false);
			output.put("message", "* Exception occurred, please contact customer support");
			output.put("user", null);
			return new ModelAndView("json", output);
		}
	}

	@RequestMapping("session")
	public ModelAndView getSession(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				UserInfo user = (UserInfo) session.getAttribute(WebConstants.LOGGED_IN_USER);
				output.put("success", true);
				output.put("message", "Session Active");
				output.put("user", user);
			} else {
				output.put("success", false);
				output.put("message", "Session Invalid");
				output.put("user", null);

			}
			return new ModelAndView("json", output);
		} catch (Exception e) {
			output.put("success", false);
			output.put("message", "* Exception occurred, please contact customer support");
			output.put("user", null);
			return new ModelAndView("json", output);
		}
	}

}
