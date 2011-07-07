/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.retailer.bbyopen;

import com.pricetector.pmpdemo.model.Retailer;
import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.retailer.PriceManager;
import com.pricetector.pmpdemo.retailer.ProductManager;
import java.util.ArrayList;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dkozyrev
 */
@Repository("priceManager")
public class PriceManagerImpl implements PriceManager {

	private static Log logger = LogFactory.getLog(PriceManagerImpl.class.getName());
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	
	@Autowired
	private ProductManager productManager;

	@Value("${email.from}")
    private String emailFrom;
		
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Override
	public Map getProductInfo(String sku) {

		Map<String, Object> info = productManager.getProduct(sku);

		logger.info(info);

		return info;
	}

	private void sendConfirmationEmail(final String userEmail, final Map model,
		final String template, final String subject) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(userEmail);
				message.setSubject(subject);
				logger.info("emailFrom: " + emailFrom);
				message.setFrom(emailFrom);

				String text = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, template, model);

				logger.info(text);

				message.setText(text, true);
			}
		};

		this.mailSender.send(preparator);
	}

	@Override
	public List<String> placeOrder(Tracker tracker, Double orderPrice, String sku) {

		List<String> info = new ArrayList<String>();
		// Do the registration logic...

		Map productInfo = getProductInfo(sku);

		String tpltName = "velocityTemplates/order-confirmation.vm";
		String subject = "PMP Acknowledge";

		Map model = new HashMap();

		model.put("user", tracker.getEmail());
		model.put("orderprice", orderPrice);
		model.put("sku", productInfo.get("sku"));
		model.put("mid", productInfo.get("modelNumber"));
		model.put("cprice", productInfo.get("salePrice"));
		model.put("name", productInfo.get("name"));
		model.put("description", productInfo.get("shortDescription"));
		model.put("period", tracker.getPeriod());

		logger.info("send email: " + tpltName);
		sendConfirmationEmail(tracker.getEmail(), model, tpltName, subject);
		logger.info("send email done");

		return info;
	}
	
	@Override
	public List finishOrder(Tracker tracker, Double price, String sku) {

		List<String> info = new ArrayList<String>();
		// Do the registration logic...

		Map productInfo = getProductInfo(sku);


		String tpltName = "velocityTemplates/order-done.vm";
		String subject = "Your Price Alert (" + tracker.getRetailer().getName() + ", " + sku + ") ";

		Map model = new HashMap();

		model.put("user", tracker.getEmail());
		model.put("orderPrice", price);
		model.put("sku", productInfo.get("sku"));

		logger.info("send email: " + tpltName);
		sendConfirmationEmail(tracker.getEmail(), model, tpltName, subject);
		logger.info("send email done");

		return info;
	}

	@Override
	public List sentCoupon(String email, Retailer retailer, String discount) {

		List<String> info = new ArrayList<String>();
		// Do the registration logic...

		String tpltName = "velocityTemplates/discount.vm";
		String subject = retailer.getName()+" discount coupon";

		Map model = new HashMap();

		model.put("user", email);
		model.put("discount", discount);

		logger.info("send email: " + tpltName);
		sendConfirmationEmail(email, model, tpltName, subject);
		logger.info("send email done");

		return info;
	}	
	
}
