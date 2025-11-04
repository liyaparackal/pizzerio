package com.ust.pos.dao;

import java.util.ArrayList;

import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.service.Administrator;

public class AdministratorDAO implements Administrator{

	@Override
	public String addStore(StoreBean storebean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyStore(StoreBean storebean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeStore(ArrayList<String> storeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StoreBean viewStore(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StoreBean> viewAllStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addFood(FoodBean foodbean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyFood(FoodBean foodbean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFood(String storeId, String foodId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FoodBean viewFood(String foodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FoodBean> viewAllFood(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeOrderStatus(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
