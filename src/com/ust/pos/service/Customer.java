package com.ust.pos.service;

import java.util.ArrayList;

import com.ust.pos.bean.CartBean;
import com.ust.pos.bean.OrderBean;
import com.ust.pos.bean.StoreBean;

public interface Customer {
public int addToCart(CartBean cartBean);
boolean modifyCart(CartBean cartBean);
String confirmOrder(OrderBean orderBean, ArrayList<CartBean> cartbean);
String cancelOrder(String orderId);
ArrayList<StoreBean> viewStore(String city);
ArrayList<CartBean> viewCart(String userid);
ArrayList <OrderBean> viewOrder();
}
