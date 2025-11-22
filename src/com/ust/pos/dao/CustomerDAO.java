package com.ust.pos.dao;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

import com.ust.pos.bean.CartBean;
import com.ust.pos.bean.OrderBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.service.Customer;

public class CustomerDAO implements Customer{

	public static Connection con = getCon();

	public static PreparedStatement ps;

	public static ResultSet rs;

	public static Connection getCon()
	
	{

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
	public String addToCart(CartBean cart) {
int i = 0;
        
        try {//ORDER IS WRONG CHANGE IT 
            // SQL query to insert a new cart item
            String query = "INSERT INTO Cart (CartId,UserId,FoodId,Type,Quantity,Cost,OrderDate) VALUES (?, ?, ?, ?, ?, ?,?)";
            
            // Prepare the statement
            PreparedStatement ps = con.prepareStatement(query);
            
            // Set the values from CartBean
            ps.setInt(1, cart.getCartId());
            ps.setString(4, cart.getType());        // type
            ps.setString(3, cart.getFoodId());      // foodId
            ps.setString(2, cart.getUserId());      // userId
            ps.setDouble(6, cart.getCost());        // cost
            ps.setInt(5, cart.getQuantity());       // quantity
            ps.setDate(7,cart.getOrderDate());   // orderDate

            // Execute the query and get the result
            i = ps.executeUpdate();
            
        } catch (SQLException sql) {
            // Print the exception if something goes wrong
            System.out.println("Error in addToCart: " + sql.getMessage());
        }

        // Return the result based on whether the insertion was successful or not
        if (i > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

	
	@Override
	public boolean modifyCart(CartBean cartBean) {
	    int i = 0;

	    try {
	        // SQL query to update the Cart table
	        String query = "UPDATE Cart SET FoodId = ?, Type = ?, Quantity = ?, Cost = ?, OrderDate = ? WHERE CartId = ?";
	        
	        // Prepare the SQL statement
	        ps = con.prepareStatement(query);

	        // Set the parameters from the CartBean object
	        ps.setString(1, cartBean.getFoodId());  // Set the FoodId
	        ps.setString(2, cartBean.getType());    // Set the Type (Veg/Non-Veg)
	        ps.setInt(3, cartBean.getQuantity());   // Set the Quantity
	        ps.setDouble(4, cartBean.getCost());    // Set the Cost
	        ps.setDate(5, cartBean.getOrderDate()); // Set the OrderDate
	        ps.setInt(6, cartBean.getCartId());     // Set the CartId to identify which cart to update

	        // Execute the update query
	        i = ps.executeUpdate();

	    } catch (SQLException sql) {
	        System.out.println("Error in modifyCart: " + sql);
	    }

	    // Return true if at least one row was updated, otherwise return false
	    return i > 0;
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

	//@Override
	//public ArrayList<StoreBean> viewStore(String city) {
		//}
	@Override
	public ArrayList<StoreBean> viewStore(String city) {
	    ArrayList<StoreBean> stores = new ArrayList<>();
	    String query = "SELECT * FROM PizzaStore WHERE City = ?";  // Select stores based on city

	    try {
	        // Prepare the SQL query
	        ps = con.prepareStatement(query);
	        ps.setString(1, city);  // Set the city parameter

	        rs = ps.executeQuery();

	        // Loop through the result set and create StoreBean objects
	        while (rs.next()) {
	            StoreBean store = new StoreBean();
	            store.setStoreId(rs.getString("StoreId"));
	            store.setName(rs.getString("Name"));
	            store.setStreet(rs.getString("Street"));
	            store.setCity(rs.getString("City"));
	            store.setPincode(rs.getString("Pincode"));
	            store.setMobileNo(rs.getString("MobileNo"));
	            store.setState(rs.getString("State"));

	            stores.add(store);  // Add each store to the list
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return stores;  // Return the list of stores
	}

	
/*
	@Override
	public ArrayList<CartBean> viewCart(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	public CartBean viewCartbyid(String cartId) {
	    CartBean cart = null;
	    String query = "SELECT * FROM cart WHERE CartId = ?";  // Adjust the table and column names as needed
	    
	    try {
	        // Prepare the query
	        ps = con.prepareStatement(query);
	        ps.setString(1, cartId);  // Set the CartId parameter
	        
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            // Create a CartBean and populate it with data from the ResultSet
	            cart = new CartBean();
	            cart.setCartId(rs.getInt("CartId"));
	            cart.setUserId(rs.getString("UserId"));
	            cart.setFoodId(rs.getString("FoodId"));
	            cart.setType(rs.getString("Type"));
	            cart.setQuantity(rs.getInt("Quantity"));
	            cart.setCost(rs.getDouble("Cost"));
	            cart.setOrderDate(rs.getDate("OrderDate"));  // Make sure the column is of type Date
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return cart;  // Returns null if no matching cart is found
	}
	@Override
	public ArrayList<OrderBean> viewOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CartBean> viewCart(String userId) {
	    ArrayList<CartBean> cartItems = new ArrayList<>();
	    String query = "SELECT * FROM Cart WHERE UserId = ?";  // Select cart items for the given userId

	    try {
	        // Prepare the SQL query
	        ps = con.prepareStatement(query);
	        ps.setString(1, userId);  // Set the userId parameter

	        rs = ps.executeQuery();

	        // Loop through the result set and create CartBean objects
	        while (rs.next()) {
	            CartBean cart = new CartBean();
	            cart.setCartId(rs.getInt("CartId"));
	            cart.setFoodId(rs.getString("FoodId"));
	            cart.setType(rs.getString("Type"));
	            cart.setUserId(rs.getString("UserId"));
	            cart.setCost(rs.getDouble("Cost"));
	            cart.setQuantity(rs.getInt("Quantity"));
	            cart.setOrderDate(rs.getDate("OrderDate"));

	            cartItems.add(cart);  // Add each cart item to the list
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cartItems;  // Return the list of cart items
	}
	
}
