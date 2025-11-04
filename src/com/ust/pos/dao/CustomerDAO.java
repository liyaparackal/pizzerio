package com.ust.pos.dao;

import java.util.ArrayList;

import com.ust.pos.bean.CartBean;
import com.ust.pos.bean.OrderBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.service.Customer;

public class CustomerDAO implements Customer{

	@Override
	public int addToCart(CartBean cartBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean modifyCart(CartBean cartBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String confirmOrder(OrderBean orderBean, ArrayList<CartBean> cartbean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelOrder(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StoreBean> viewStore(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CartBean> viewCart(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderBean> viewOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
