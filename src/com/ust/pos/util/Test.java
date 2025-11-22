package com.ust.pos.util;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.ust.pos.bean.CartBean;
import com.ust.pos.bean.CredentialsBean;
import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.dao.AdministratorDAO;
import com.ust.pos.dao.CustomerDAO;

public class Test {
    public static Connection con = getCon();
    public static PreparedStatement ps;
    public static ResultSet rs;

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "pass@word1");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Authentication authService = new AuthenticationImpl();

    public static void main(String[] args) {
        JFrame frame = new JFrame("POS System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Create and add panels to the card panel
        cardPanel.add(createLoginPanel(cardLayout, cardPanel), "Login");
        cardPanel.add(createAdminMenuPanel(cardLayout, cardPanel), "AdminMenu");
        cardPanel.add(createCustomerMenuPanel(cardLayout, cardPanel), "CustomerMenu");

        // Add card panel to the frame and show the login screen
        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private static JPanel createLoginPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("User ID:");
        JTextField userTextField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(userLabel);
        loginPanel.add(userTextField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String userId = userTextField.getText();
            String password = new String(passField.getPassword());
            CredentialsBean creds = new CredentialsBean();
            creds.setUserID(userId);
            creds.setPassword(password);

            boolean isValid = authService.authenticate(creds);

            if (isValid) {
                String userType = null;
                try {
                    String query = "SELECT UserType FROM usercredentials WHERE UserId = ?";
                    ps = con.prepareStatement(query);
                    ps.setString(1, userId);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        userType = rs.getString("UserType");
                    }
                } catch (SQLException sql) {
                    JOptionPane.showMessageDialog(null, "Database error: " + sql.getMessage());
                }

                if (userType != null) {
                    authService.changeLoginStatus(creds, 1);

                    if ("administrator".equalsIgnoreCase(userType)) {
                        cardLayout.show(cardPanel, "AdminMenu");
                    } else if ("user".equalsIgnoreCase(userType)) {
                        cardLayout.show(cardPanel, "CustomerMenu");
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown user type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials! Try again.");
            }
        });

        return loginPanel;
    }

    /* ---------------- ADMIN MENU ---------------- */
    private static JPanel createAdminMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(9, 1));

        JButton addStoreButton = new JButton("Add Store");
        JButton updateStoreButton = new JButton("Update Store");
        JButton removeStoreButton = new JButton("Remove Store");
        JButton viewStoreButton = new JButton("View Store");
        JButton viewAllStoresButton = new JButton("View All Stores");
        JButton addFoodButton = new JButton("Add Food");
        JButton updateFoodButton = new JButton("Update Food");
        JButton removeFoodButton = new JButton("Remove Food");
        JButton viewFoodButton = new JButton("View Food");
        JButton exitButton = new JButton("Exit");

        adminPanel.add(addStoreButton);
        adminPanel.add(updateStoreButton);
        adminPanel.add(removeStoreButton);
        adminPanel.add(viewStoreButton);
        adminPanel.add(viewAllStoresButton);
        adminPanel.add(addFoodButton);
        adminPanel.add(updateFoodButton);
        adminPanel.add(removeFoodButton);
        adminPanel.add(viewFoodButton);
        adminPanel.add(exitButton);

        addStoreButton.addActionListener(e -> addStore());
        updateStoreButton.addActionListener(e -> updateStore());
        removeStoreButton.addActionListener(e -> removeStore());
        viewStoreButton.addActionListener(e -> viewStore());
        viewAllStoresButton.addActionListener(e -> viewAllStores());
        addFoodButton.addActionListener(e -> addFood());
        updateFoodButton.addActionListener(e -> updateFood());
        removeFoodButton.addActionListener(e -> removeFood());
        viewFoodButton.addActionListener(e -> viewFood());

        exitButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        return adminPanel;
    }

    private static void addStore() {
        String storeId = JOptionPane.showInputDialog("Enter Store ID:");
        String name = JOptionPane.showInputDialog("Enter Store Name:");
        String street = JOptionPane.showInputDialog("Enter Street:");
        String city = JOptionPane.showInputDialog("Enter City:");
        String pincode = JOptionPane.showInputDialog("Enter Pincode:");
        String mobileNo = JOptionPane.showInputDialog("Enter Mobile No:");
        String state = JOptionPane.showInputDialog("Enter State:");

        StoreBean sb = new StoreBean();
        sb.setStoreId(storeId);
        sb.setName(name);
        sb.setStreet(street);
        sb.setCity(city);
        sb.setPincode(pincode);
        sb.setMobileNo(mobileNo);
        sb.setState(state);

        AdministratorDAO admindao = new AdministratorDAO();
        String result = admindao.addStore(sb);
        JOptionPane.showMessageDialog(null, result);
    }

    private static void updateStore() {
        String storeId = JOptionPane.showInputDialog("Enter Store ID to modify:");
        AdministratorDAO admindao = new AdministratorDAO();
        StoreBean existingStore = admindao.viewStore(storeId);
        if (existingStore == null) {
            JOptionPane.showMessageDialog(null, "Store not found.");
            return;
        }

        String name = JOptionPane.showInputDialog("Enter New Store Name (leave blank to skip):");
        name = name.isEmpty() ? existingStore.getName() : name;
        String street = JOptionPane.showInputDialog("Enter New Store Street (leave blank to skip):");
        street = street.isEmpty() ? existingStore.getStreet() : street;

        String mobileNo = JOptionPane.showInputDialog("Enter New Store Mobile No (leave blank to skip):");
        mobileNo = mobileNo.isEmpty() ? existingStore.getMobileNo() : mobileNo;

        String city = JOptionPane.showInputDialog("Enter New Store City (leave blank to skip):");
        city = city.isEmpty() ? existingStore.getCity() : city;

        String state = JOptionPane.showInputDialog("Enter New Store State (leave blank to skip):");
        state = state.isEmpty() ? existingStore.getState() : state;

        String pincode = JOptionPane.showInputDialog("Enter New Store Pincode (leave blank to skip):");
        pincode = pincode.isEmpty() ? existingStore.getPincode() : pincode;

        StoreBean updatedStore = new StoreBean();
        updatedStore.setStoreId(storeId);
        updatedStore.setName(name);
        updatedStore.setStreet(street);
        updatedStore.setMobileNo(mobileNo);
        updatedStore.setCity(city);
        updatedStore.setState(state);
        updatedStore.setPincode(pincode);

        boolean modified = admindao.modifyStore(updatedStore);
        JOptionPane.showMessageDialog(null, "Store modified: " + modified);
    }

    private static void removeStore() {
        String storeId = JOptionPane.showInputDialog("Enter Store ID to remove:");
        AdministratorDAO admindao = new AdministratorDAO();
        int result = admindao.removeStore(storeId);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Store removed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No matching store found.");
        }
    }

    private static void viewStore() {
        String storeId = JOptionPane.showInputDialog("Enter Store ID to view:");
        AdministratorDAO admindao = new AdministratorDAO();
        StoreBean store = admindao.viewStore(storeId);
        if (store != null) {
            JOptionPane.showMessageDialog(null,
                    "Store ID: " + store.getStoreId() + "\n" +
                            "Name: " + store.getName() + "\n" +
                            "Street: " + store.getStreet() + "\n" +
                            "City: " + store.getCity() + "\n" +
                            "Pincode: " + store.getPincode() + "\n" +
                            "Mobile No: " + store.getMobileNo() + "\n" +
                            "State: " + store.getState());
        } else {
            JOptionPane.showMessageDialog(null, "Store not found.");
        }
    }

    private static void viewAllStores() {
        AdministratorDAO admindao = new AdministratorDAO();
        ArrayList<StoreBean> allStores = admindao.viewAllStore();
        StringBuilder storesList = new StringBuilder();
        for (StoreBean store : allStores) {
            storesList.append("Store ID: ").append(store.getStoreId()).append("\n")
                    .append("Name: ").append(store.getName()).append("\n")
                    .append("Street: ").append(store.getStreet()).append("\n")
                    .append("City: ").append(store.getCity()).append("\n")
                    .append("Pincode: ").append(store.getPincode()).append("\n")
                    .append("Mobile No: ").append(store.getMobileNo()).append("\n")
                    .append("State: ").append(store.getState()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, storesList.toString());
    }

    private static void addFood() {
        FoodBean fb = new FoodBean();
        fb.setFoodId(JOptionPane.showInputDialog("Enter Food ID:"));
        fb.setStoreid(JOptionPane.showInputDialog("Enter Store ID:"));
        fb.setName(JOptionPane.showInputDialog("Enter Food Name:"));
        fb.setType(JOptionPane.showInputDialog("Enter Food Type:"));
        fb.setFoodSize(JOptionPane.showInputDialog("Enter Food Size:"));
        fb.setQuantity(Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:")));
        fb.setPrice(Double.parseDouble(JOptionPane.showInputDialog("Enter Price:")));

        AdministratorDAO admindao = new AdministratorDAO();
        String result = admindao.addFood(fb);
        JOptionPane.showMessageDialog(null, result);
    }

    private static void updateFood() {
        String foodId = JOptionPane.showInputDialog("Enter Food ID to modify:");
        AdministratorDAO admindao = new AdministratorDAO();
        FoodBean existingFood = admindao.viewFood(foodId);
        if (existingFood == null) {
            JOptionPane.showMessageDialog(null, "Food not found.");
            return;
        }

        String name = JOptionPane.showInputDialog("Enter New Food Name (leave blank to skip):");
        name = name.isEmpty() ? existingFood.getName() : name;

        String type = JOptionPane.showInputDialog("Enter New Food Type (leave blank to skip):");
        type = type.isEmpty() ? existingFood.getType() : type;

        String foodSize = JOptionPane.showInputDialog("Enter New Food Size (leave blank to skip):");
        foodSize = foodSize.isEmpty() ? existingFood.getFoodSize() : foodSize;

        String quantityStr = JOptionPane.showInputDialog("Enter New Quantity (leave blank to skip):");
        int quantity = existingFood.getQuantity();  // Default to existing quantity
        if (!quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr);  // Convert input to integer
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Keeping existing quantity.");
            }
        }

        String priceStr = JOptionPane.showInputDialog("Enter New Price (leave blank to skip):");
        double price = existingFood.getPrice();  // Default to existing price
        if (!priceStr.isEmpty()) {
            try {
                price = Double.parseDouble(priceStr);  // Convert input to double
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price. Keeping existing price.");
            }
        }

        // Create updated FoodBean
        FoodBean updatedFood = new FoodBean();
        updatedFood.setFoodId(foodId);
        updatedFood.setName(name);
        updatedFood.setType(type);
        updatedFood.setFoodSize(foodSize);
        updatedFood.setQuantity(quantity);
        updatedFood.setPrice(price);

        // Call the DAO method to update the food in the database
        boolean modified = admindao.modifyFood(updatedFood);
        JOptionPane.showMessageDialog(null, "Food modified: " + modified);
    }

    private static void removeFood() {
        String foodId = JOptionPane.showInputDialog("Enter Food ID to delete:");
        String storeId = JOptionPane.showInputDialog("Enter Store ID:");
        AdministratorDAO admindao = new AdministratorDAO();
        boolean success = admindao.removeFood(storeId, foodId);
        if (success) {
            JOptionPane.showMessageDialog(null, "Food removed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Food not found.");
        }
    }

    private static void viewFood() {
        String foodId = JOptionPane.showInputDialog("Enter Food ID to view:");
        AdministratorDAO admindao = new AdministratorDAO();
        FoodBean food = admindao.viewFood(foodId);
        if (food != null) {
            JOptionPane.showMessageDialog(null,
                    "Food ID: " + food.getFoodId() + "\n" +
                            "Food Name: " + food.getName() + "\n" +
                            "Food Type: " + food.getType() + "\n" +
                            "Food Size: " + food.getFoodSize() + "\n" +
                            "Quantity: " + food.getQuantity() + "\n" +
                            "Price: " + food.getPrice());
        } else {
            JOptionPane.showMessageDialog(null, "Food not found.");
        }
    }

    /* ---------------- CUSTOMER MENU ---------------- */
    private static JPanel createCustomerMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new GridLayout(7, 1));

        JButton addToCartButton = new JButton("Add to Cart");
        JButton updateCartButton = new JButton("Update Cart");
        JButton viewCartButton = new JButton("View Cart");
        JButton confirmOrderButton = new JButton("Confirm Order");
        JButton cancelOrderButton = new JButton("Cancel Order");
        JButton viewStoresByCityButton = new JButton("View Stores by City");
        JButton exitButton = new JButton("Exit");

        customerPanel.add(addToCartButton);
        customerPanel.add(updateCartButton);
        customerPanel.add(viewCartButton);
        customerPanel.add(confirmOrderButton);
        customerPanel.add(cancelOrderButton);
        customerPanel.add(viewStoresByCityButton);
        customerPanel.add(exitButton);

        addToCartButton.addActionListener(e -> addToCart());
        updateCartButton.addActionListener(e -> updateCart());
        viewCartButton.addActionListener(e -> viewCart());
        viewStoresByCityButton.addActionListener(e -> viewStoresByCity());
        confirmOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Order confirmed!"));
        cancelOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Order canceled!"));
        exitButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        return customerPanel;
    }

    private static void addToCart() {
        CartBean cart = new CartBean();
        cart.setCartId(Integer.parseInt(JOptionPane.showInputDialog("Enter Cart ID:")));
        cart.setType(JOptionPane.showInputDialog("Enter Food Type:"));
        cart.setFoodId(JOptionPane.showInputDialog("Enter Food ID:"));
        cart.setUserId(JOptionPane.showInputDialog("Enter User ID:"));
        cart.setCost(Double.parseDouble(JOptionPane.showInputDialog("Enter Food Cost:")));
        cart.setQuantity(Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:")));

        String dateString = JOptionPane.showInputDialog("Enter Order Date (YYYY-MM-DD):");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = sdf.parse(dateString);
            java.sql.Date orderDate = new java.sql.Date(utilDate.getTime());
            cart.setOrderDate(orderDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format! Please use YYYY-MM-DD.");
        }

        CustomerDAO customerdao = new CustomerDAO();
        String result = customerdao.addToCart(cart);
        JOptionPane.showMessageDialog(null, result);
    }

    private static void updateCart() {
        String cartId = JOptionPane.showInputDialog("Enter Cart ID to modify:");
        CustomerDAO customerdao = new CustomerDAO();
        CartBean existingCart = customerdao.viewCartbyid(cartId);
        if (existingCart == null) {
            JOptionPane.showMessageDialog(null, "Cart not found.");
            return;
        }

        String foodId = JOptionPane.showInputDialog("Enter New Food ID (leave blank to skip):");
        foodId = foodId.isEmpty() ? existingCart.getFoodId() : foodId;

        String type = JOptionPane.showInputDialog("Enter New Food Type (leave blank to skip):");
        type = type.isEmpty() ? existingCart.getType() : type;

        String quantityStr = JOptionPane.showInputDialog("Enter New Quantity (leave blank to skip):");
        int quantity = existingCart.getQuantity();  // Default to existing quantity
        if (!quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr);  // Convert input to integer
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Keeping existing quantity.");
            }
        }

        String costStr = JOptionPane.showInputDialog("Enter New Cost (leave blank to skip):");
        double cost = existingCart.getCost();  // Default to existing cost
        if (!costStr.isEmpty()) {
            try {
                cost = Double.parseDouble(costStr);  // Convert input to double
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid cost. Keeping existing cost.");
            }
        }

        String orderDateStr = JOptionPane.showInputDialog("Enter New Order Date (YYYY-MM-DD) (leave blank to skip):");
        java.sql.Date orderDate = existingCart.getOrderDate();  // Default to existing order date
        if (!orderDateStr.isEmpty()) {
            try {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDateStr);
                orderDate = new java.sql.Date(utilDate.getTime());  // Convert to SQL date
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format. Keeping existing order date.");
            }
        }

        // Create updated CartBean with the new values
        CartBean updatedCart = new CartBean();
        updatedCart.setCartId(Integer.parseInt(cartId));  // Ensure CartId is the same as the original
        updatedCart.setFoodId(foodId);
        updatedCart.setType(type);
        updatedCart.setQuantity(quantity);
        updatedCart.setCost(cost);
        updatedCart.setOrderDate(orderDate);

        // Call the DAO method to update the cart in the database
        boolean modified = customerdao.modifyCart(updatedCart);

        // Show success/failure message
        JOptionPane.showMessageDialog(null, "Cart modified: " + modified);
    }

    private static void viewCart() {
        String userId = JOptionPane.showInputDialog("Enter User ID to view cart:");
        CustomerDAO customerdao = new CustomerDAO();
        ArrayList<CartBean> cartItems = customerdao.viewCart(userId);

        if (cartItems != null && !cartItems.isEmpty()) {
            StringBuilder cartDetails = new StringBuilder();
            for (CartBean cart : cartItems) {
                cartDetails.append("Cart ID: ").append(cart.getCartId()).append("\n")
                        .append("Food ID: ").append(cart.getFoodId()).append("\n")
                        .append("Food Type: ").append(cart.getType()).append("\n")
                        .append("Quantity: ").append(cart.getQuantity()).append("\n")
                        .append("Cost: ").append(cart.getCost()).append("\n")
                        .append("Order Date: ").append(cart.getOrderDate()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, cartDetails.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No cart items found for this user.");
        }
    }

    private static void viewStoresByCity() {
        String city = JOptionPane.showInputDialog("Enter City to view stores:");
        CustomerDAO customerdao = new CustomerDAO();
        ArrayList<StoreBean> stores = customerdao.viewStore(city);

        if (stores != null && !stores.isEmpty()) {
            StringBuilder storeDetails = new StringBuilder();
            for (StoreBean store : stores) {
                storeDetails.append("Store ID: ").append(store.getStoreId()).append("\n")
                        .append("Name: ").append(store.getName()).append("\n")
                        .append("Street: ").append(store.getStreet()).append("\n")
                        .append("City: ").append(store.getCity()).append("\n")
                        .append("Pincode: ").append(store.getPincode()).append("\n")
                        .append("Mobile No: ").append(store.getMobileNo()).append("\n")
                        .append("State: ").append(store.getState()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, storeDetails.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No stores found in the specified city.");
        }
    }
}
