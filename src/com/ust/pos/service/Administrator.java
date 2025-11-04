package com.ust.pos.service;

import java.util.ArrayList;

import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;

public interface Administrator {
public String addStore(StoreBean storebean);
boolean modifyStore(StoreBean storebean);
int removeStore(ArrayList<String> storeId) ;
StoreBean viewStore(String storeId);
ArrayList <StoreBean> viewAllStore();
String addFood(FoodBean foodbean);
boolean modifyFood(FoodBean foodbean);
boolean removeFood(String storeId, String foodId);
FoodBean viewFood(String foodId);
ArrayList<FoodBean> viewAllFood(String storeId);   
String changeOrderStatus(String orderId);
}
