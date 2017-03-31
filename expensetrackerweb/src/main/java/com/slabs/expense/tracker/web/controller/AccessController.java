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

import com.slabs.expense.tracker.common.constants.Constants;
import com.slabs.expense.tracker.common.database.entity.Message;
import com.slabs.expense.tracker.common.database.entity.UserInfo;
import com.slabs.expense.tracker.common.services.AdminService;
import com.slabs.expense.tracker.common.services.EmailService;
import com.slabs.expense.tracker.common.services.MessageService;
import com.slabs.expense.tracker.common.services.Services;
import com.slabs.expense.tracker.common.services.UserService;
import com.slabs.expense.tracker.core.ServiceFactory;
import com.slabs.expense.tracker.core.services.EmailServiceImpl;
import com.slabs.expense.tracker.core.services.MessageServiceImpl;
import com.slabs.expense.tracker.core.services.UserServiceImpl;
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
			List<UserInfo> users = service.selectUser(credentials[0], Boolean.TRUE, Boolean.TRUE);
			if (users != null && !users.isEmpty()) {
				UserInfo info = users.get(0);
				if (info.getPassword().equals(credentials[1])) {
					if (info.getIsLocked().equals(Constants.N)
							&& info.getIsVerified().equals(Constants.Y)) {
						HttpSession session = request.getSession(true);
						info.setPassword("");
						session.setAttribute(WebConstants.LOGGED_IN_USER, info);
						session.setMaxInactiveInterval(600);
						output.put(WebConstants.SUCCESS, Boolean.TRUE);
						output.put(WebConstants.MESSAGE, MessageConstants.LOGIN_SUCCESS);
						output.put(WebConstants.USER, info);
					} else if (info.getIsLocked().equals(Constants.Y)) {
						output.put(WebConstants.SUCCESS, Boolean.FALSE);
						output.put(WebConstants.MESSAGE, MessageConstants.ACCOUNT_LOCKED);
						output.put(WebConstants.USER, null);
					} else if (info.getIsVerified().equals(Constants.N)) {
						output.put(WebConstants.SUCCESS, Boolean.FALSE);
						output.put(WebConstants.MESSAGE, MessageConstants.ACCOUNT_NOT_VERIFIED);
						output.put(WebConstants.USER, null);
					}
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
				UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
						UserService.class);
				UserInfo user = (UserInfo) session.getAttribute(WebConstants.LOGGED_IN_USER);
				List<UserInfo> list = service.selectUser(user.getUsername(), Boolean.TRUE,
						Boolean.FALSE);
				if (list != null && !list.isEmpty()) {
					user = list.get(0);
					session.removeAttribute(WebConstants.LOGGED_IN_USER);
					session.setAttribute(WebConstants.LOGGED_IN_USER, user);
				}
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

	@RequestMapping(value = "checkAvailability", method = { RequestMethod.GET })
	public ModelAndView checkAvailability(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserServiceImpl.class);

			String type = request.getParameter("type");
			String value = request.getParameter("value");

			Boolean availability = service.checkAvailability(type, value, Boolean.FALSE);

			output.put(WebConstants.SUCCESS, Boolean.TRUE);		
			output.put(WebConstants.IS_AVAILABLE, availability);
			
			if (availability) {
				output.put(WebConstants.MESSAGE, MessageConstants.AVAILABLE);
			} else {
				if ("username".equals(type)) {
					output.put(WebConstants.MESSAGE, MessageConstants.USERNAME_NOT_AVAILABLE);
				} else if ("email".equals(type)) {
					output.put(WebConstants.MESSAGE, MessageConstants.EMAIL_ALREADY_REGISTERED);
				}
			}
		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
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
			EmailServiceImpl emailService = ServiceFactory.getInstance()
					.getService(Services.EMAIL_SERVICE, EmailServiceImpl.class);
			UserInfo user = JSONUtil.getObjectFromJSON(request.getInputStream(), UserInfo.class);
			Integer isCreated = service.createUser(user);
			output.put(WebConstants.SUCCESS, Boolean.TRUE);
			if (isCreated > 0) {
				emailService.sendActivationEmail(user);
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

	@RequestMapping(value = "user/activate", method = { RequestMethod.POST })
	public ModelAndView activateUser(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserServiceImpl.class);
			EmailService emailService = ServiceFactory.getInstance()
					.getService(Services.EMAIL_SERVICE, EmailService.class);

			AdminService adminService = ServiceFactory.getInstance()
					.getService(Services.ADMIN_SERVICE, AdminService.class);

			MessageService messagingService = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageService.class);

			Map<String, String> map = JSONUtil.getMapFromInputStream(request.getInputStream());
			String activationKey = map.get("activationkey");
			String username = map.get("username");

			List<UserInfo> info = service.selectUser(username, Boolean.FALSE, Boolean.FALSE);

			if (info != null && !info.isEmpty()) {
				UserInfo user = info.get(0);
				if (Constants.N.equals(user.getIsVerified())) {
					if (user.getActivationKey().equals(activationKey)) {
						if (adminService.activateUser(username, Constants.Y)) {
							output.put(WebConstants.SUCCESS, Boolean.TRUE);
							output.put(WebConstants.MESSAGE,
									MessageConstants.ACTIVATION_SUCCESSFUL);
							emailService.sendRegSuccessMail(user);
							messagingService.sendWelcomeMessage(user);
						} else {
							output.put(WebConstants.SUCCESS, Boolean.FALSE);
							output.put(WebConstants.MESSAGE, MessageConstants.ACTIVATION_FAILED);
						}
					} else {
						output.put(WebConstants.SUCCESS, Boolean.FALSE);
						output.put(WebConstants.MESSAGE,
								MessageConstants.ACTIVATION_FAILED_INVALID_KEY);
					}
				} else {
					output.put(WebConstants.SUCCESS, Boolean.FALSE);
					output.put(WebConstants.MESSAGE, MessageConstants.USER_ACTIVATED_ALREADY);
				}
			} else {
				output.put(WebConstants.SUCCESS, Boolean.FALSE);
				output.put(WebConstants.MESSAGE, MessageConstants.USER_NOT_FOUND);
			}

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			return new ModelAndView(WebConstants.JSON, output);
		}
		return new ModelAndView(WebConstants.JSON, output);
	}

	@RequestMapping(value = "user/email/activate", method = { RequestMethod.POST })
	public ModelAndView sendActivationMail(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			UserService service = ServiceFactory.getInstance().getService(Services.USER_SERVICE,
					UserService.class);
			EmailService emailService = ServiceFactory.getInstance()
					.getService(Services.EMAIL_SERVICE, EmailService.class);

			Map<String, String> map = JSONUtil.getMapFromInputStream(request.getInputStream());

			String username = map.get("username");

			List<UserInfo> info = service.selectUser(username, Boolean.FALSE, Boolean.FALSE);

			if (info != null && !info.isEmpty()) {
				UserInfo user = info.get(0);
				if (Constants.N.equals(user.getIsVerified())) {
					emailService.sendActivationEmail(user);
					output.put(WebConstants.SUCCESS, Boolean.TRUE);
					output.put(WebConstants.MESSAGE, MessageConstants.ACTIVATION_MAILED);
				} else {
					output.put(WebConstants.SUCCESS, Boolean.FALSE);
					output.put(WebConstants.MESSAGE, MessageConstants.USER_ACTIVATED_ALREADY);
				}
			} else {
				output.put(WebConstants.SUCCESS, Boolean.FALSE);
				output.put(WebConstants.MESSAGE, MessageConstants.USER_NOT_FOUND);
			}

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			return new ModelAndView(WebConstants.JSON, output);
		}
		return new ModelAndView(WebConstants.JSON, output);
	}

	@RequestMapping(value = "query", method = { RequestMethod.POST })
	public ModelAndView sendMail(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> output = new HashMap<String, Object>();
		try {
			MessageServiceImpl messagingService = ServiceFactory.getInstance()
					.getService(Services.MESSAGING_SERVICE, MessageServiceImpl.class);

			Message message = JSONUtil.getObjectFromJSON(request.getInputStream(), Message.class);
			messagingService.createQuery(message);

			output.put(WebConstants.SUCCESS, Boolean.TRUE);
			output.put(WebConstants.MESSAGE, MessageConstants.MAILED);

		} catch (Exception e) {
			L.error("Exception occurred, {}", e);
			output.put(WebConstants.SUCCESS, Boolean.FALSE);
			output.put(WebConstants.MESSAGE, MessageConstants.EXCEPTION);
			return new ModelAndView(WebConstants.JSON, output);
		}
		return new ModelAndView(WebConstants.JSON, output);
	}

}
