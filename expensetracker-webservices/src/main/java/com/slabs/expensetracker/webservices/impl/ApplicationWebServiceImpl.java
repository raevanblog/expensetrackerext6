package com.slabs.expensetracker.webservices.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slabs.expensetracker.common.constants.Constants;
import com.slabs.expensetracker.common.database.entity.Message;
import com.slabs.expensetracker.common.database.entity.UserInfo;
import com.slabs.expensetracker.common.exception.ExpenseTrackerException;
import com.slabs.expensetracker.common.services.AdminService;
import com.slabs.expensetracker.common.services.ApplicationService;
import com.slabs.expensetracker.common.services.EmailService;
import com.slabs.expensetracker.common.services.MessageService;
import com.slabs.expensetracker.common.services.UserService;
import com.slabs.expensetracker.common.webservice.response.Operation;
import com.slabs.expensetracker.common.webservice.response.Response;
import com.slabs.expensetracker.common.webservices.ApplicationWebService;
import com.slabs.expensetracker.util.Base64Encoder;
import com.slabs.expensetracker.util.JSONUtil;
import com.slabs.expensetracker.webservices.core.MessageConstants;
import com.slabs.expensetracker.webservices.core.WebConstants;
import com.slabs.expensetracker.webservices.response.ResponseGenerator;

/**
 * {@link ApplicationWebServiceImpl} - Webservice to retrieve application data.
 * 
 * @author Shyam Natarajan
 *
 */
@RestController
@RequestMapping(value = "api")
public class ApplicationWebServiceImpl implements ApplicationWebService {

	@Autowired
	private ApplicationService service;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private MessageService messageService;

	/**
	 * This method will return a list of expense type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/expensetype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getExpenseType() throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getExpenseType(), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of income type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/incometype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getIncomeType() throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getIncomeType(), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of income type in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/currencytype", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getCurrencyType() throws ExpenseTrackerException {
		try {
			return ResponseGenerator.getSuccessResponse(service.getCurrency(), Operation.SELECT);
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * This method will return a list of item names for which expenses have been
	 * recorded, in the
	 * {@link com.slabs.expense.tracker.webservice.response.Response}
	 * 
	 * @return {@link com.slabs.expense.tracker.webservice.response.Response}
	 * @throws WebServiceException
	 *             throws {@link WebServiceException}
	 */
	@RequestMapping(value = "application/items", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	@Override
	public Response getItemNames(@RequestParam(name = "type") String type, @RequestParam(name = "username", defaultValue = "") String username) throws ExpenseTrackerException {
		try {
			if ("items".equals(type)) {
				return ResponseGenerator.getSuccessResponse(service.getExpenseNames(), Operation.SELECT);
			} else if ("trackitems".equals(type)) {
				return ResponseGenerator.getSuccessResponse(service.getItemNameForTracking(username), Operation.SELECT);
			}
			return ResponseGenerator.getExceptionResponse(HttpStatus.BAD_REQUEST, "Invalid dictionary type");
		} catch (Exception e) {
			throw new ExpenseTrackerException(e);
		}
	}

	@RequestMapping(value = "application/login", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" })
	public Response doLogin(HttpServletRequest request, HttpServletResponse response) throws ExpenseTrackerException {
		try {
			Map<String, String> parameters = JSONUtil.getMapFromInputStream(request.getInputStream());
			String[] credentials = Base64Encoder.decode(parameters.get("credential"), ":");
			List<UserInfo> users = userService.selectUser(credentials[0], Boolean.TRUE, Boolean.TRUE);
			if (users != null && !users.isEmpty()) {
				UserInfo info = users.get(0);
				if (info.getPassword().equals(credentials[1])) {
					if (info.getIsLocked().equals(Constants.N) && info.getIsVerified().equals(Constants.Y)) {
						HttpSession session = request.getSession(true);
						info.setPassword("");
						session.setAttribute(WebConstants.LOGGED_IN_USER, info);
						session.setMaxInactiveInterval(600);
						return ResponseGenerator.getSuccessResponse(info, MessageConstants.LOGIN_SUCCESS);
					} else if (info.getIsLocked().equals(Constants.Y)) {
						return ResponseGenerator.getExceptionResponse(HttpStatus.FORBIDDEN, MessageConstants.ACCOUNT_LOCKED);
					} else if (info.getIsVerified().equals(Constants.N)) {
						return ResponseGenerator.getExceptionResponse(HttpStatus.FORBIDDEN, MessageConstants.ACCOUNT_NOT_VERIFIED);
					}
				} else {
					return ResponseGenerator.getExceptionResponse(HttpStatus.UNAUTHORIZED, MessageConstants.CHECK_PWD);
				}

			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.UNAUTHORIZED, MessageConstants.CHECK_USRNME_PWD);
			}
		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
		return ResponseGenerator.getExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, MessageConstants.SERVICE_UNAVAILABLE);
	}

	@RequestMapping(value = "application/logout", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" })
	public Response doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(Boolean.FALSE);
		if (session != null) {
			session.removeAttribute(WebConstants.LOGGED_IN_USER);
			session.invalidate();
		}
		return ResponseGenerator.getSuccessResponse(MessageConstants.LOGD_OFF);
	}

	@RequestMapping(value = "application/session", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	public Response getSession(HttpServletRequest request, HttpServletResponse response) throws ExpenseTrackerException {
		try {
			HttpSession session = request.getSession(Boolean.FALSE);
			if (session != null) {
				UserInfo user = (UserInfo) session.getAttribute(WebConstants.LOGGED_IN_USER);
				List<UserInfo> list = userService.selectUser(user.getUsername(), Boolean.TRUE, Boolean.FALSE);
				if (list != null && !list.isEmpty()) {
					user = list.get(0);
					session.removeAttribute(WebConstants.LOGGED_IN_USER);
					session.setAttribute(WebConstants.LOGGED_IN_USER, user);
				}
				return ResponseGenerator.getSuccessResponse(user, MessageConstants.SESSION_ACTIVE);
			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.UNAUTHORIZED, MessageConstants.INVALID_SESSION);
			}
		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	@RequestMapping(value = "application/checkAvailability", method = { RequestMethod.GET }, produces = { "application/json", "application/xml" })
	public Response checkAvailability(@RequestParam(name = "type") String type, @RequestParam(name = "value") String value) throws ExpenseTrackerException {
		try {
			Boolean isAvailable = userService.checkAvailability(type, value, Boolean.FALSE);
			if (isAvailable) {
				return ResponseGenerator.getSuccessResponse(isAvailable, MessageConstants.AVAILABLE);
			} else {
				if ("username".equals(type)) {
					return ResponseGenerator.getSuccessResponse(isAvailable, MessageConstants.USERNAME_NOT_AVAILABLE);
				} else if ("email".equals(type)) {
					return ResponseGenerator.getSuccessResponse(isAvailable, MessageConstants.EMAIL_ALREADY_REGISTERED);
				} else {
					throw new ExpenseTrackerException("Invalid Type : " + type);
				}
			}
		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	@RequestMapping(value = "application/user/create", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public Response register(@RequestBody UserInfo user) throws ExpenseTrackerException {
		try {
			Integer isCreated = userService.createUser(user);
			if (isCreated > 0) {
				emailService.sendActivationEmail(user);
				return ResponseGenerator.getSuccessResponse(MessageConstants.USER_REGISTERED);
			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, MessageConstants.USER_NOT_REGISTERED);
			}

		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	@RequestMapping(value = "application/user/activate", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" })
	public Response activateUser(HttpServletRequest request, HttpServletResponse response) throws ExpenseTrackerException {
		try {
			Map<String, String> map = JSONUtil.getMapFromInputStream(request.getInputStream());
			String activationKey = map.get("activationkey");
			String username = map.get("username");

			List<UserInfo> info = userService.selectUser(username, Boolean.FALSE, Boolean.FALSE);

			if (info != null && !info.isEmpty()) {
				UserInfo user = info.get(0);
				if (Constants.N.equals(user.getIsVerified())) {
					if (user.getActivationKey().equals(activationKey)) {
						if (adminService.activateUser(username, Constants.Y)) {
							emailService.sendRegSuccessMail(user);
							messageService.sendWelcomeMessage(user);
							return ResponseGenerator.getSuccessResponse(MessageConstants.ACTIVATION_SUCCESSFUL);
						} else {
							return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.ACTIVATION_FAILED);
						}
					} else {
						return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.ACTIVATION_FAILED_INVALID_KEY);
					}
				} else {
					return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.USER_ACTIVATED_ALREADY);
				}
			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.USER_NOT_FOUND);
			}

		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	@RequestMapping(value = "application/user/email/activate", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" })
	public Response sendActivationMail(HttpServletRequest request, HttpServletResponse response) throws ExpenseTrackerException {
		try {
			Map<String, String> map = JSONUtil.getMapFromInputStream(request.getInputStream());

			String username = map.get("username");
			List<UserInfo> info = userService.selectUser(username, Boolean.FALSE, Boolean.FALSE);

			if (info != null && !info.isEmpty()) {
				UserInfo user = info.get(0);
				if (Constants.N.equals(user.getIsVerified())) {
					emailService.sendActivationEmail(user);
					return ResponseGenerator.getSuccessResponse(MessageConstants.ACTIVATION_MAILED);
				} else {
					return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.USER_ACTIVATED_ALREADY);
				}
			} else {
				return ResponseGenerator.getExceptionResponse(HttpStatus.OK, MessageConstants.USER_NOT_FOUND);
			}

		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

	@RequestMapping(value = "application/query", method = { RequestMethod.POST }, produces = { "application/json", "application/xml" })
	public Response sendMail(@RequestBody Message message) throws ExpenseTrackerException {
		try {
			messageService.createQuery(message);
			return ResponseGenerator.getSuccessResponse(MessageConstants.MAILED);
		} catch (Exception e) {
			throw new ExpenseTrackerException(MessageConstants.EXCEPTION, e);
		}
	}

}
