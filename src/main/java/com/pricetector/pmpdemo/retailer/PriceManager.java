/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.retailer;

import com.pricetector.pmpdemo.model.Retailer;
import com.pricetector.pmpdemo.model.Tracker;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dkozyrev
 */
public interface PriceManager {

	public List placeOrder(Tracker tracker, Double price, String sku);

	public List finishOrder(Tracker tracker, Double price, String sku);

	public Map getProductInfo(String sku);

	public List sentCoupon(String email, Retailer retailer, String discount);
}
