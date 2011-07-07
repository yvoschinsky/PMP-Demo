/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.controller;

import com.pricetector.pmpdemo.dao.RetailerDao;
import com.pricetector.pmpdemo.dao.TrackerDao;
import com.pricetector.pmpdemo.model.Retailer;
import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import com.pricetector.pmpdemo.retailer.PriceManager;
import com.pricetector.pmpdemo.service.TrackerService;
import com.pricetector.pmpdemo.service.UserService;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dbukhanets
 */
@Controller
public class DefaultController {

	/*    @Resource(name="userDao")
	private UserDao userDao;*/
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "retailerDao")
	private RetailerDao retailerDao;
	@Resource(name = "trackerDao")
	private TrackerDao trackerDao;
	@Resource(name = "trackerService")
	private TrackerService trackerService;
	@Autowired
	protected HttpServletRequest httpServletRequest;
//	@Autowired
//	protected HttpServletResponse httpServletResponse;
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	protected PriceManager priceManager;

	@RequestMapping("index.htm")
	public ModelAndView indexHandler() {
		/*		User user = new User();
		user.setEmail("asd@asd.asd")
		.setFirstName("firstName")
		.setLastName("LastName")
		.setMiddleName("middleName");
		user = userDao.save(user);
		
		Retailer retailer = new Retailer();
		retailer.setName("retailer");
		retailer = retailerDao.save(retailer);
		
		Tracker tracker = new Tracker();
		tracker.setSku("productid")
		.setAffordablePrice(99.99)
		.setRetailer(retailer)
		.setUser(user);
		tracker = trackerDao.save(tracker);*/

		return new ModelAndView("index");
	}

	@RequestMapping("save.htm")
	public ModelAndView saveHandler(
		HttpServletResponse httpServletResponse,
		@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
		@RequestParam(value = "user", required = false, defaultValue = "1") Long userId,
		@RequestParam(value = "retailer", required = false, defaultValue = "0") Long retailerId,
		@RequestParam(value = "product", required = false, defaultValue = "0") String productId,
		@RequestParam(value = "period", required = false, defaultValue = "1") Long period,
		@RequestParam(value = "delete", required = false, defaultValue = "") String delete,
		@RequestParam(value = "price", required = false, defaultValue = "0") Double price) throws IOException {

		ModelAndView mav = new ModelAndView("save");

		User user = new User();
		Retailer retailer = new Retailer();
		user = userService.getLoggedInUser();
		if (null == user) {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		if ("delete".equals(delete)) {
			trackerDao.remove(id);
			id = 0L;
		}
		if (retailerId != null && retailerId > 0) {
			retailer = retailerDao.get(retailerId);
		}

		/**
		 * data were posted and should be saved
		 */
		Tracker tracker = new Tracker();
		if (id != null && id > 0L) {
			try {
				tracker = trackerDao.get(id);
			} catch (ObjectRetrievalFailureException ex) {
				id = 0L;
			}
		}

		if (period < 1) {
			period = 1L;
		}

		if ("POST".equals(httpServletRequest.getMethod())) {


			retailer = retailerDao.get(retailerId);

			tracker.setSku(productId).setAffordablePrice(price).setRetailer(retailer).setPeriod(period).setUser(user);
			tracker = trackerDao.save(tracker);
		} else if (0L == id) {
			tracker.setSku(productId).setRetailer(retailer).setPeriod(period).setUser(user);
		}

		mav.addObject(tracker);

		return mav;
	}

	@RequestMapping("active.htm")
	public ModelAndView activeHandler(
		HttpServletResponse httpServletResponse,
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) throws IOException {
		User user = new User();
		user = userService.getLoggedInUser();
		if (null == user) {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		ModelAndView mav = new ModelAndView("audit");

		PagedListHolder trackerList = new PagedListHolder(trackerDao.getActiveTrackers(user));

		trackerList.setPage(page);
		mav.addObject("trackerList", trackerList);
		mav.addObject("title", "Active Trackers");

		return mav;
	}

	@RequestMapping("simulate.htm")
	public ModelAndView simulateHandler(
		HttpServletResponse httpServletResponse,
		@RequestParam(value = "sku", required = false, defaultValue = "") String sku,
		@RequestParam(value = "price", required = false, defaultValue = "") Double price) throws IOException {
		User user = new User();
		user = userService.getLoggedInUser();
		if (null == user) {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		if (!"".equals(sku) && 0L < price) {
			Collection<Tracker> userCollection = userService.getFulfilledUsers(sku, price);
			for (Tracker tracker : userCollection) {
				if (null != tracker.getEmail()) {
					priceManager.finishOrder(tracker, price, sku);
				}
			}
		}

		ModelAndView mav = new ModelAndView("simulate");

		PagedListHolder skuList = new PagedListHolder(trackerService.getTrackedSkuCollection());

		mav.addObject("title", "Active Trackers");
		mav.addObject("skuList", skuList);

		return mav;
	}

	@RequestMapping("audit.htm")
	public ModelAndView auditHandler(
		HttpServletResponse httpServletResponse,
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) throws IOException {
		User user = new User();
		user = userService.getLoggedInUser();
		if (null == user) {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		ModelAndView mav = new ModelAndView("audit");

		PagedListHolder trackerList = new PagedListHolder(trackerDao.getAllTrackers(user));

		trackerList.setPage(page);
		mav.addObject("trackerList", trackerList);
		mav.addObject("title", "All Trackers");

		return mav;
	}

	@RequestMapping(value = "/price.htm", method = RequestMethod.GET)
	public ModelAndView getPrice(@RequestParam("sku") String sku) {
		ModelAndView mav = new ModelAndView("jsonView");
		if (sku == null) {
			logger.error("sku is empty!");
			mav.addObject("status", 404);
		} else {
			mav.addObject("status", 200);
			mav.addObject("data", priceManager.getProductInfo(sku));

		}

		logger.info("got sku:" + sku);

		return mav;
	}

	@RequestMapping(value = "json.htm")
	public ModelAndView json() {
		ModelAndView mav = new ModelAndView("jsonView");

		mav.addObject("user", userService.getLoggedInUser());

		return mav;
	}

	@RequestMapping(value = "/placeOrder.htm", method = RequestMethod.GET)
	public ModelAndView placeOrder(@RequestParam("email") String email,
		@RequestParam("sku") String sku,
		@RequestParam("period") Long period,
		@RequestParam("price") Double price) {
		ModelAndView mav = new ModelAndView("jsonView");

		if (email == null || sku == null || price == null) {
			logger.error("sku is empty!");
			return new ModelAndView("error");
		}

		logger.info("got sku:" + sku);
		logger.info("got price:" + price);
		logger.info("got email:" + email);

		/**
		 * save tracker to db
		 */
		Tracker tracker = new Tracker();
		tracker.setAffordablePrice(price).setSku(sku).setRetailer(retailerDao.get(2L)).setEmail(email).setPeriod(period).setDateCreated(new Date()).setUser(userService.getLoggedInUser());
		tracker = trackerDao.save(tracker);
		tracker.getUser().setTrackerCollection(null);
		tracker.getRetailer().setTrackerCollection(null);

		mav.addObject("status", 200);

		mav.addObject("infoMap", priceManager.placeOrder(tracker, price, sku));

		mav.addObject(tracker);

		return mav;
	}

	@RequestMapping(value = "/sentcoupon.htm", method = RequestMethod.GET)
	public ModelAndView sentCoupon(@RequestParam("email") String email,
		@RequestParam("retailerId") Long retailerId,
		@RequestParam("discount") String discount) {
		ModelAndView mav = new ModelAndView("jsonView");

		if (email == null || retailerId == null || discount == null) {
			logger.error("param is empty!");
			return new ModelAndView("error");
		}

		logger.info("got retailerId:" + retailerId);
		logger.info("got discount:" + discount);
		logger.info("got email:" + email);

		/**
		 * save tracker to db
		 */
		Retailer retailer = new Retailer();
		if (retailerId != null && retailerId > 0) {
			retailer = retailerDao.get(retailerId);
		}
		
		mav.addObject("status", 200);
		mav.addObject("infoMap", priceManager.sentCoupon(email, retailer, discount));

		return mav;
	}
}
