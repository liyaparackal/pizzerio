package com.ust.pos.dao;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.service.Administrator;
import com.ust.pos.util.Test;

public class AdministratorDAO implements Administrator {


	public static Connection con = getCon();

	public static PreparedStatement ps;

	public static ResultSet rs;

	public static Connection getCon() {

 try {

 Class.forName("com.mysql.cj.jdbc.Driver");

 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","pass@word1");	

 }

 catch(ClassNotFoundException cnf) {

	 cnf.printStackTrace();

 }

 catch(SQLException sql) {

 System.out.println(sql);

 }

 return con;

}

	
	
	@Override
//	public String addStore(StoreBean storebean) {
//		// TODO Auto-generated method stub
//		String res = (Test.StoreBeanArray.add(storebean)) ? "SUCCESSFULLY ADDED THE STORE" : "FAIL";
//		return res;
//	}
	public String addStore(StoreBean eb) {

		 int i=0;

		 try {

		 ps = con.prepareStatement("insert into PizzaStore values(?,?,?,?,?,?,?)");

		 ps.setString(1, eb.getStoreId());

		 ps.setString(2, eb.getName());

		 ps.setString(5, eb.getCity());
		 ps.setString(4, eb.getMobileNo());
		 ps.setString(7, eb.getPincode());
		 ps.setString(3, eb.getStreet());
		 ps.setString(6, eb.getState());
		 
		 i=ps.executeUpdate();

		 }

		 catch (SQLException sql) {

		 System.out.println(sql);

		 }

		 if(i>0) {return "success";}
		 else {return "fail";}
			}
	public boolean modifyStore(StoreBean eb) {

		 int i=0;

		 try {

		 ps = con.prepareStatement("update PizzaStore set Name=?,Street=?,MobileNo=?,City=?,State=?,Pincode=? where StoreId=?");
		 ps.setString(7, eb.getStoreId());
		 ps.setString(1, eb.getName());
		 ps.setString(2, eb.getStreet());
		 ps.setString(4, eb.getCity());
		 ps.setString(6, eb.getPincode());
		 ps.setString(3, eb.getMobileNo());
		 ps.setString(5, eb.getState()); 

		 i=ps.executeUpdate();

		 }

		 catch (SQLException sql) {

		 System.out.println(sql);

		 }

		 if (i>0) {return true;}
		 else {return false;}

			}		
//	public boolean modifyStore(StoreBean updatedStore) {
//	    for (StoreBean store : Test.StoreBeanArray) {
//	        if (store.getStoreId().equals(updatedStore.getStoreId())) {
//
//	            if (updatedStore.getName() != null) {
//	                store.setName(updatedStore.getName());
//	            }
//	            if (updatedStore.getStreet() != null) {
//	                store.setStreet(updatedStore.getStreet());
//	            }
//	            if (updatedStore.getCity() != null) {
//	                store.setCity(updatedStore.getCity());
//	            }
//	            if (updatedStore.getPincode() != null) {
//	                store.setPincode(updatedStore.getPincode());
//	            }
//	            if (updatedStore.getMobileNo() != null) {
//	                store.setMobileNo(updatedStore.getMobileNo());
//	            }
//	            if (updatedStore.getState() != null) {
//	                store.setState(updatedStore.getState());
//	            }
//
//	            return true; // Successfully updated
//	        }
//	    }
//	    return false; // Store not found
//	}
//	public boolean modifyStore(StoreBean storebean) {
//		// TODO Auto-generated method stub
//		Iterator<StoreBean> itr = Test.StoreBeanArray.iterator();
//		while (itr.hasNext()) {
//			if (itr.next().getStoreId().equals(storebean.getStoreId())) {
//				itr.remove();
//				Test.StoreBeanArray.add(storebean);
//				return true;
//			} else {
//				return false;
//			}
//		}
//		return false;
//	}
	public int removeStore(String storeid) {
//THIS IS SUPPOSED TO INPUT STRING OF IDS SO CHANGE IT
		 int i=0;

		 try {

		 ps = con.prepareStatement("delete from PizzaStore where Storeid=?");
		 
		 
		 ps.setString(1,storeid);
		//only pass id not whole 

		 i=ps.executeUpdate();

		 }

		 catch (SQLException sql) {

		 System.out.println(sql);

		 }

		 return i;

			}	
	
	//@Override
//	public int removeStore(ArrayList<String> storeId) {
//
//		int removedCount = 0;
//		Iterator<StoreBean> itr = Test.StoreBeanArray.iterator();
//
//		while (itr.hasNext()) {
//			StoreBean store = itr.next();
//			if (storeId.contains(store.getStoreId())) {
//				itr.remove();
//				removedCount++;
//			}
//		}
//
//		return removedCount;

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
	// }

	public StoreBean viewStore(String storeid) {	
		StoreBean e=new StoreBean();
	try
	{
		 ps = con.prepareStatement("select * from PizzaStore where StoreId=?");	
		 ps.setString(1, storeid);
		 rs=ps.executeQuery();
		 //for this we need to pull ip into resultset
		 //then traverse it using next 
		 if(rs.next())
		 {
			 e.setStoreId(rs.getString(1));
			 e.setName(rs.getString(2));
			 e.setStreet(rs.getString(3));
			 e.setMobileNo(rs.getString(4));
			 e.setCity(rs.getString(5));
			 e.setState(rs.getString(6));
			 e.setPincode(rs.getString(7));
		 }
		 //e is a temp variable where we store results fetched frm DB just to display
	}
	catch (SQLException sql) {

		 System.out.println(sql);

		 }
	return e;
	}
	
	
	//@Override

//	public StoreBean viewStore(String storeId) {
//		for (StoreBean store : Test.StoreBeanArray) {
//			if (store.getStoreId().equals(storeId)) {
//				return store;
//			}
//		}
//
//		return null;
//	}

	@Override
	public ArrayList<StoreBean> viewAllStore() {
		// TODO Auto-generated method stub
		ArrayList<StoreBean> all=new ArrayList<StoreBean>();

		try

			{

		 ps=con.prepareStatement("select * from PizzaStore");

		rs=ps.executeQuery();

		while(rs.next())

		{
		
			StoreBean eb=new StoreBean();

			eb.setStoreId(rs.getString(1));
			 eb.setName(rs.getString(2));
			 eb.setStreet(rs.getString(3));
			 eb.setCity(rs.getString(4));
			 eb.setPincode(rs.getString(5));
			 eb.setMobileNo(rs.getString(6));
			 eb.setState(rs.getString(7));
			 			
		all.add(eb);}}		
		catch(SQLException sql)

		{

		System.out.println(sql);

		}

		return all;

		}

	public String addFood(FoodBean foodbean) {
	//NOTE-ITS NOT CONNECTING STORE AND FOODID	
		
	    String query = "INSERT INTO Food (FoodId, Name, Type, FoodSize, Quantity, Price,StoreId) VALUES (?, ?, ?, ?, ?, ?,?)";
	    
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","pass@word1");
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setString(1, foodbean.getFoodId());
	        stmt.setString(2, foodbean.getName());
	        stmt.setString(3, foodbean.getType());
	        stmt.setString(4, foodbean.getFoodSize());
	        stmt.setInt(5, foodbean.getQuantity());
	        stmt.setDouble(6, foodbean.getPrice());
	        stmt.setString(7, foodbean.getStoreid());
	        
	        
	        int rowsInserted = stmt.executeUpdate();
	        if( rowsInserted > 0)
	        	{return "success";}
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return "store not found";
	}
	
//	@Override
//	public String addFood(FoodBean foodbean) {
//		List<String> sd = Test.StoreBeanArray.stream().map(x -> x.getStoreId()).toList();
//		for (String id : sd) {
//			if (id.equals(foodbean.getStoreid())) {
//				Test.FoodBeanArray.add(foodbean);
//				return "success";
//			}
//		}
//		return "store not found";
//	}

	
	
	@Override
	public boolean modifyFood(FoodBean eb) {
		int i=0;

		 try {

		 ps = con.prepareStatement("update Food set Name=?,Type=?,FoodSize=?,Quantity=?,Price=? where FoodId=?");
		 ps.setString(6, eb.getFoodId());
		 ps.setString(1, eb.getName());
		 ps.setString(2, eb.getType());
		 ps.setInt(4, eb.getQuantity());
		 ps.setString(3, eb.getFoodSize());
		 ps.setDouble(5, eb.getPrice()); 
		 i=ps.executeUpdate();

		 }

		 catch (SQLException sql) {

		 System.out.println(sql);

		 }

		 if (i>0) {return true;}
		 else {return false;}
	}

	@Override
	public boolean removeFood(String storeid, String foodid) {
	    int i = 0;

	    try {
	        // Step 1: Delete cart items referencing the food item (if any)
	        String deleteCartQuery = "DELETE FROM Cart WHERE FoodId = ?";
	        ps = con.prepareStatement(deleteCartQuery);
	        ps.setString(1, foodid);  // Delete all cart items referencing this food
	        ps.executeUpdate();  // Execute deletion of dependent cart items

	        // Step 2: Now delete the food item from the Food table
	        String deleteFoodQuery = "DELETE FROM Food WHERE StoreId = ? AND FoodId = ?";
	        ps = con.prepareStatement(deleteFoodQuery);
	        ps.setString(1, storeid);  // Pass storeid
	        ps.setString(2, foodid);   // Pass foodid

	        i = ps.executeUpdate();  // Execute delete query for food

	    } catch (SQLException sql) {
	        System.out.println("SQL Error: " + sql.getMessage());
	    }

	    return i > 0;  // Return true if food was deleted, false otherwise
	}

	
	
	

	@Override
	public FoodBean viewFood(String foodId) {
	    FoodBean f = new FoodBean();
	    try {
	        ps = con.prepareStatement("SELECT * FROM Food WHERE FoodId = ?");
	        ps.setString(1, foodId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            f.setFoodId(rs.getString(1));
	            f.setName(rs.getString(2));
	            f.setType(rs.getString(3));
	            f.setFoodSize(rs.getString(4));
	            f.setQuantity(rs.getInt(5));
	            f.setPrice(rs.getDouble(6));
	        }
	    } catch (SQLException sql) {
	        System.out.println(sql);
	    }
	    return f;
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
