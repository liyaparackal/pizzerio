package com.ust.pos.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.ust.pos.bean.FoodBean;
import com.ust.pos.bean.StoreBean;
import com.ust.pos.dao.AdministratorDAO;

public class Test {
//public static final String StoreBeanarray=null;
	public static ArrayList<StoreBean> StoreBeanArray = new ArrayList<StoreBean>();
	public static ArrayList<FoodBean> FoodBeanArray = new ArrayList<FoodBean>();

	public static void main(String[] args) {
		AdministratorDAO admindao = new AdministratorDAO();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter login id");
		String id=sc.nextLine();
		System.out.println("enter password");
		String pd=sc.nextLine();
		
		StoreBean sb1 = new StoreBean();
		sb1.setStoreId("101");
		sb1.setName("a");
		sb1.setStreet("jn11");
		sb1.setCity("ang");
		sb1.setPincode("1234");
		sb1.setMobileNo("156349839");
		sb1.setState("kerala");
		Test.StoreBeanArray.add(sb1);// its already added in admindao
		StoreBean sb2 = new StoreBean();
		sb2.setStoreId("102");
		sb2.setName("a");
		sb2.setStreet("jn11");
		sb2.setCity("ang");
		sb2.setPincode("1234");
		sb2.setMobileNo("156349839");
		sb2.setState("kerala");
		Test.StoreBeanArray.add(sb2);
	if(id.equals("admin1")&& pd.equals("000"))	
		{while(true) {
		System.out.println("enter the options");
		System.out.println("MENU: ");
		System.out.println("AD-001:ADD STORE");
				System.out.println("AD-002:UPDATE STORE");
				System.out.println("AD-003:DELETE STORE");
				System.out.println("AD-004:VIEW STORE BY ID");
				System.out.println( "AD-005:VIEW ALL STORES ");
				System.out.println( "EX:VIEW ALL STORES ");
		
		Scanner scc = new Scanner(System.in);
		String option = scc.nextLine();
		switch (option) {
		case "AD-001":
			
				StoreBean sb = new StoreBean();
				System.out.println("enter id");
				sb.setStoreId(sc.nextLine());
				System.out.println("enter name");
				sb.setName(sc.nextLine());
				System.out.println("enter street");
				sb.setStreet(sc.nextLine());
				System.out.println("enter city");
				sb.setCity(sc.nextLine());
				System.out.println("enter pincode");
				sb.setPincode(sc.nextLine());
				System.out.println("enter mobile no.");
				sb.setMobileNo(sc.nextLine());
				System.out.println("enter state.");
				sb.setState(sc.nextLine());
				
				System.out.println(admindao.addStore(sb));
				break;
			

		case "AD-002":
			StoreBean updatedStore = new StoreBean();
			System.out.println("Enter Store ID to modify:");
			updatedStore.setStoreId(sc.nextLine());
			System.out.println("Enter New Store Name:");
			updatedStore.setName(sc.nextLine());
			System.out.println("Enter New Street:");
			updatedStore.setStreet(sc.nextLine());
			System.out.println("Enter New City:");
			updatedStore.setCity(sc.nextLine());
			System.out.println("Enter New Pincode:");
			updatedStore.setPincode(sc.nextLine());
			System.out.println("Enter New Mobile No:");
			updatedStore.setMobileNo(sc.nextLine());
			System.out.println("Enter New State:");
			updatedStore.setState(sc.nextLine());

			boolean modified = admindao.modifyStore(updatedStore);
			System.out.println("Modify Store Result: " + modified);
			System.out.println(StoreBeanArray);
			break;

		case "AD-003":
			 System.out.println("Enter number of stores to remove:");
			    int count = Integer.parseInt(sc.nextLine());
			    ArrayList<String> storeIdsToRemove = new ArrayList<>();

			    for (int i = 0; i < count; i++) {
			        System.out.println("Enter Store ID " + (i + 1) + ":");
			        storeIdsToRemove.add(sc.nextLine());
			    }
			    int result = admindao.removeStore(storeIdsToRemove);
			    if (result > 0) {
			        System.out.println(" store(s) removed successfully.");
			    } else {
			        System.out.println("No matching store found.");
			    }
			    break;

		case "AD-004":
			System.out.println("enter store id to view ");
			String viewId=sc.nextLine();
			StoreBean store=admindao.viewStore(viewId);
			if(store !=null)
			{
				System.out.println("storeid is "+store.getStoreId());
				System.out.println("store name is "+store.getName());
				System.out.println("store city is "+store.getCity());
				System.out.println("store mobile number is "+store.getMobileNo());
				System.out.println("store pincode is "+store.getPincode());
				System.out.println("store street is "+store.getStreet());
				System.out.println("store state is "+store.getState());
			}
			else
			{
				System.out.println("store not found");
			}
			break;
		case "AD-006":
				FoodBean fb = new FoodBean();
				System.out.println("enter food id");
				fb.setFoodId(sc.nextLine());
				System.out.println("enter store id");
				fb.setStoreid(sc.nextLine());
				System.out.println("enter name");
				fb.setName(sc.nextLine());
				System.out.println("enter food size");
				fb.setFoodSize(sc.nextLine());
				System.out.println("enter quantity");
				fb.setQuantity(sc.nextInt());
				System.out.println("enter price");
				fb.setPrice(sc.nextInt());
				
			System.out.println(admindao.addFood(fb));
			

			break;
			
		case "AD-007":
			FoodBean f = new FoodBean();
			System.out.println("Enter Food ID to modify:");
			f.setFoodId(sc.nextLine());
			System.out.println("Enter Food name to modify:");
			f.setName(sc.nextLine());
			System.out.println("Enter Food Size to modify:");
			f.setFoodSize(sc.nextLine());
			System.out.println("Enter Food ID to modify:");
			f.setQuantity(sc.nextInt());
			System.out.println("Enter Food ID to modify:");
			f.setPrice(sc.nextDouble());
			boolean mod = admindao.modifyFood(f);
			System.out.println("Modify Store Result: " + mod);
			System.out.println(StoreBeanArray);
			break;
		
		case "AD-008":
			System.out.println(FoodBeanArray);
			System.out.println("enter food id to delete ");
			String delf=sc.nextLine();
			System.out.println("enter food id to delete ");
			String dels=sc.nextLine();
			boolean res = admindao.removeFood(dels,delf);
		    if (res) {
		        System.out.println( " store(s) removed successfully.");
		    } else {
		        System.out.println("No matching store found.");
		    }
		    break;

			
		case "AD-005":
			ArrayList<StoreBean> allStores = admindao.viewAllStore();
		    if (allStores.isEmpty()) {
		        System.out.println("No stores available.");
		    } else {
		        System.out.println("All Stores:");
		        for (StoreBean stor : allStores) {
		            System.out.println("Store ID: " + stor.getStoreId());
		            System.out.println("Name: " + stor.getName());
		            System.out.println("Street: " + stor.getStreet());
		            System.out.println("City: " + stor.getCity());
		            System.out.println("Pincode: " + stor.getPincode());
		            System.out.println("Mobile No: " + stor.getMobileNo());
		            System.out.println("State: " + stor.getState());
		            System.out.println("-----------------------------");
		        }
		    }
		    break;

		case "EX":	
			System.out.println("Exiting...");
			System.exit(0);
			
		}
	}
		}
	else
	{
		System.out.println("invalid credentials");
	}

		
		//Iterator itr = Test.StoreBeanArray.iterator();
//	while(itr.hasNext()) {
//		System.out.println(itr.next());
//	}
		
		
		// TODO Auto-generated method stub

	}

}
