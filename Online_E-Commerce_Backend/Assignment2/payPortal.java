import java.util.*;
import java.lang.Math;

public class payPortal{
    static private int tempInt;
    static private String tempString;
    static Scanner scan = new Scanner(System.in);
    static Scanner scanS = new Scanner(System.in);
    static HashMap<Integer,String> map = new HashMap<>();
    static HashMap<String,Integer> map2 = new HashMap<>();

    private static void upgradeStatus(Customer customer, Application application){
        String customerStatus;
        customerStatus = map.get(customer.getCustomerPrivileges());
        System.out.println("The Customer Status is "+customerStatus);
        System.out.print("Enter the new Status: ");
        tempString = scanS.nextLine();
        tempInt = map2.get(tempString);
        if (tempInt>customer.getCustomerPrivileges()){
            if(tempInt==2){
                if (customer.getCustomerWallet() >= 200){
                    System.out.println("You have been promoted to PRIME membership");
                    primeCustomer primeCustomer1 = new primeCustomer(customer);
                    customer.setCustomerWallet(customer.getCustomerWallet()-200);
                    customer.setCustomerPrivileges(2);
                    customer.setProductDiscount(5);
                    customer.setDeliveryRate(2);
                    customer.setSurpriseOffer(false);
                    customer.setMaxCouponGenerate(2);
                    customer.setDeliveryDate("3-6 days");
                }
                else{
                    System.out.println("Sorry insufficient balance");
                    customer.customerMenu(application);
                }
            }
            if(tempInt==3){
                if (customer.getCustomerWallet() >= 300){
                    System.out.println("You have been promoted to ELITE membership");
                    eliteCustomer eliteCustomer1 = new eliteCustomer(customer);
                    customer.setCustomerWallet(customer.getCustomerWallet()-300);
                    customer.setCustomerPrivileges(3);
                    customer.setProductDiscount(10);
                    customer.setDeliveryRate(0);
                    customer.setSurpriseOffer(true);
                    customer.setMaxCouponGenerate(4);
                    customer.setDeliveryDate("1-2 days");
                }
                else{
                    System.out.println("Sorry insufficient balance");
                    customer.customerMenu(application);
                }
            }
        }
        else{
            System.out.println("You have are already a member of this or higher segment, Thank You!!");
            customer.customerMenu(application);
        }
    }

    private static void checkout(Customer customer, Application application){
        int totalCartPrice=0;
        double totalDiscount=0;
        Item maxItem = new Item("Temp",0.1,-1,1);
        for(Item item: customer.getCustomerCart()){
            totalCartPrice+=item.getItemBulkPrice();
            if(maxItem.getItemPrice()<item.getItemPrice()){
                maxItem = item;
            }
        }
        double deliveryCharges = 100 + customer.getDeliveryRate()*totalCartPrice*0.01;
        int couponDiscount = 0;
        if(customer.getCustomerCoupons().size()!=0){
            couponDiscount = Collections.max(customer.getCustomerCoupons());
            if(couponDiscount>maxItem.getDiscount().get(customer.getCustomerPrivileges())&& couponDiscount>customer.getProductDiscount()){
                totalDiscount += couponDiscount*maxItem.getItemBulkPrice()*0.01;
                customer.getCustomerCart().remove(maxItem);
                customer.getCustomerCoupons().remove(Collections.max(customer.getCustomerCoupons()));
                System.out.println("Coupon applied on "+maxItem.getItemName()+"of value "+couponDiscount+"%");
            }
        }
        int tempDiscount;
        for(Item item: customer.getCustomerCart()){
            if((int)item.getItemId()==100){
                System.out.println("Deal Products "+item.getItemName());
                if(customer.getCustomerPrivileges()==1){
                    totalCartPrice += item.getItemNumber()*item.getDealNormal();
                }
                if(customer.getCustomerPrivileges()==2){
                    totalCartPrice += item.getItemNumber()*item.getDealPrime();
                }
                if(customer.getCustomerPrivileges()==3){
                    totalCartPrice += item.getItemNumber()*item.getDealELite();
                }
                System.out.println("No coupons is valid on a deal");
                continue;
            }
            if(item.getDiscount().get(customer.getCustomerPrivileges())>customer.getProductDiscount()){
                tempDiscount = item.getDiscount().get(customer.getCustomerPrivileges());
            }
            else{
                tempDiscount = customer.getProductDiscount();
            }
            System.out.println("Item Name: "+item.getItemName());
            System.out.println("Discount given on the product: "+item.getItemBulkPrice()*tempDiscount*0.01);
            totalDiscount += item.getItemBulkPrice()*tempDiscount*0.01;
        }
        double totalCostPayable = totalCartPrice+deliveryCharges-totalDiscount;
        if (totalCostPayable>customer.getCustomerWallet()){
            customer.getCustomerCart().add(maxItem);
            if(customer.getCustomerCoupons().size()!=0) {
                customer.getCustomerCoupons().add(Collections.max(customer.getCustomerCoupons()));
            }
            System.out.println("\nBut still Insufficient Balance, sorry!");
            for(Item item: customer.getCustomerCart()){
                item.setAvailableNumber(item.getAvailableNumber()+item.getItemNumber());
            }
            System.out.println("Returning to main menu");
            customer.customerMenu(application);
        }

        System.out.println("Delivery Charges: "+deliveryCharges);
        System.out.println("Total Discount on the cart: "+totalDiscount);
        System.out.println("Total cost to be payed by you: "+(totalCartPrice+deliveryCharges-totalDiscount));
        System.out.println("Your order will be delivered within "+customer.getDeliveryDate());
        customer.setCustomerWallet((int) (customer.getCustomerWallet()-totalCartPrice+deliveryCharges-totalDiscount));
        if(customer.isSurpriseOffer() ==false){
            System.out.println("You are not eligible for surprise gift.");
        }
        if(customer.isSurpriseOffer() ==true){
            System.out.println("No surprise offer!, Better Luck Next Time");
        }
        if(totalCartPrice>5000) {
            for (int i = 0; i < customer.getMaxCouponGenerate(); i++) {
                int rand = (int) (Math.random() * 10) + 5;
                customer.getCustomerCoupons().add(rand);
                System.out.println("Your have been awarded with coupon of value "+rand+"%s");
            }
        }
        customer.getCustomerCart().clear();
    }

    public static void paymentMenu(Customer customer, Application application){
        map.put(1,"NORMAL");
        map.put(2,"PRIME");
        map.put(3,"ELITE");
        map2.put("NORMAL",1);
        map2.put("PRIME",2);
        map2.put("ELITE",3);

        System.out.println("Welcome to Payment Portal");
        System.out.println("1) Checkout Cart");
        System.out.println("2) Upgrade Status");
        tempInt = scan.nextInt();
        if(tempInt == 1){
            //Do Checking Out
            checkout(customer,application);
            customer.customerMenu(application);
        }
        if(tempInt == 2){
            upgradeStatus(customer, application);
            customer.customerMenu(application);
        }
    }
}
