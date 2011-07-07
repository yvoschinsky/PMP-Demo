/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.retailer.bbyopen;

import com.mattwilliamsnyc.service.remix.Element;
import com.mattwilliamsnyc.service.remix.ProductResponse;
import com.mattwilliamsnyc.service.remix.Remix;
import com.mattwilliamsnyc.service.remix.RemixException;
import com.pricetector.pmpdemo.retailer.ProductManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dkozyrev
 */
@Repository("productManager")
public class ProductManagerImpl implements ProductManager {

	@Override
	public Map getProduct(String sku) {
		Map<String, Object> info = new HashMap();

		Remix remix = new Remix("zamadt2k3dgnzgsxc6vj29yj");

		String image = "";
		float price = 0;
		try {
			ProductResponse product = remix.getProduct(sku);

			for (Element item : product.getDocumentRoot().getChildren()) {
				info.put(item.getName(), item.getValue());
			}

//			 image = product.product().getImage();
//			 price = product.product().getRegularPrice();

		} catch (RemixException ex) {
			Logger.getLogger(ProductManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		info.put("image", image);
		info.put("price", price);

		return info;

	}
}
