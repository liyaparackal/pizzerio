package com.ust.pos.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.service.Administrator;
import com.ust.pos.util.Test;

public class AdministratorDAO implements Administrator {

	@Override
	public String addStore(StoreBean storebean) {
		// TODO Auto-generated method stub
		String res = (Test.StoreBeanArray.add(storebean)) ? "SUCCESS" : "FAIL";
		return res;
	}

	@Override
	public boolean modifyStore(StoreBean storebean) {
		// TODO Auto-generated method stub
		Iterator<StoreBean> itr = Test.StoreBeanArray.iterator();
		while (itr.hasNext()) {
			if (itr.next().getStoreId().equals(storebean.getStoreId())) {
				itr.remove();
				Test.StoreBeanArray.add(storebean);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public int removeStore(ArrayList<String> storeId) {

		int removedCount = 0;
		Iterator<StoreBean> itr = Test.StoreBeanArray.iterator();

		while (itr.hasNext()) {
			StoreBean store = itr.next();
			if (storeId.contains(store.getStoreId())) {
				itr.remove();
				removedCount++;
			}
		}

		return removedCount;

		// Iterator<String> itr=storeId.iterator();
		// while(itr.hasNext())
		// {
		// Iterator<StoreBean> itr1=Test.StoreBeanArray.iterator();
		// while(itr1.hasNext())
		// {
		// if(itr1.next().getStoreId().equals(itr.next()))
		// {
		// itr1.remove();
		// return 1;}
		// }
		// }
		//
		// return 0;
	}

	@Override

	public StoreBean viewStore(String storeId) {
		for (StoreBean store : Test.StoreBeanArray) {
			if (store.getStoreId().equals(storeId)) {
				return store;
			}
		}

		return null;
	}

	@Override
	public ArrayList<StoreBean> viewAllStore() {
		// TODO Auto-generated method stub
		return Test.StoreBeanArray;
	}

	@Override
	public String addFood(FoodBean foodbean) {
		List<String> sd = Test.StoreBeanArray.stream().map(x -> x.getStoreId()).toList();
		for (String id : sd) {
			if (id.equals(foodbean.getStoreid())) {
				Test.FoodBeanArray.add(foodbean);
				return "success";
			}
		}
		return "store not found";
	}

	@Override
	public boolean modifyFood(FoodBean foodbean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFood(String storeid, String foodid) {
		Iterator<FoodBean> itr = Test.FoodBeanArray.iterator();
		FoodBean fb;
		while (itr.hasNext()) {
//			System.out.println(itr.next());
//			if (itr.next().getFoodId().equals(foodid) && itr.next().getStoreid().equals(storeid)) {
//				itr.remove();
			fb=(FoodBean)itr.next();
			if(fb.getFoodId().equals(foodid)&& fb.getStoreid().equals(storeid))
			{
				Test.FoodBeanArray.remove(fb);
				return true;
			}
		}
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
