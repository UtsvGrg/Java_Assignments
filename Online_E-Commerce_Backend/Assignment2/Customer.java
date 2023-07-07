import java.util.*;

public class Customer{
    Scanner scan = new Scanner(System.in);
    Scanner scanS = new Scanner(System.in);

    private String customerName;
    private int customerPrivileges;
    private String customerPass; //copy
    private int customerWallet; // copy
    private LinkedList<Item> customerCart; // copy
    private int productDiscount;
    private int deliveryRate;
    private boolean surpriseOffer;
    private int maxCouponGenerate;
    private int tempInt;
    private String tempString;
    private double tempDouble;

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public Scanner getScanS() {
        return scanS;
    }

    public void setScanS(Scanner scanS) {
        this.scanS = scanS;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerPrivileges() {
        return customerPrivileges;
    }

    public void setCustomerPrivileges(int customerPrivileges) {
        this.customerPrivileges = customerPrivileges;
    }

    public String getCustomerPass() {
        return customerPass;
    }

    public void setCustomerPass(String customerPass) {
        this.customerPass = customerPass;
    }

    public int getCustomerWallet() {
        return customerWallet;
    }

    public void setCustomerWallet(int customerWallet) {
        this.customerWallet = customerWallet;
    }

    public LinkedList<Item> getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(LinkedList<Item> customerCart) {
        this.customerCart = customerCart;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    public int getDeliveryRate() {
        return deliveryRate;
    }

    public void setDeliveryRate(int deliveryRate) {
        this.deliveryRate = deliveryRate;
    }

    public boolean isSurpriseOffer() {
        return surpriseOffer;
    }

    public void setSurpriseOffer(boolean surpriseOffer) {
        this.surpriseOffer = surpriseOffer;
    }

    public int getMaxCouponGenerate() {
        return maxCouponGenerate;
    }

    public void setMaxCouponGenerate(int maxCouponGenerate) {
        this.maxCouponGenerate = maxCouponGenerate;
    }

    public int getTempInt() {
        return tempInt;
    }

    public void setTempInt(int tempInt) {
        this.tempInt = tempInt;
    }

    public String getTempString() {
        return tempString;
    }

    public void setTempString(String tempString) {
        this.tempString = tempString;
    }

    public double getTempDouble() {
        return tempDouble;
    }

    public void setTempDouble(double tempDouble) {
        this.tempDouble = tempDouble;
    }

    public LinkedList<Integer> getCustomerCoupons() {
        return customerCoupons;
    }

    public void setCustomerCoupons(LinkedList<Integer> customerCoupons) {
        this.customerCoupons = customerCoupons;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private LinkedList <Integer> customerCoupons; //copy
    private String deliveryDate;

    public Customer(String customerName){
        this.customerName = customerName;
        this.customerCart = new LinkedList<>();
        this.customerPrivileges = 1;
        this.productDiscount = 0;
        this.deliveryRate = 5;
        this.surpriseOffer = false;
        this.maxCouponGenerate = 0;
        this.customerCoupons = new LinkedList<>();
        this.deliveryDate = "7-10 days";
        this.customerWallet = 1000;
    }

    public void signUp(Application application){ // will create object in main, and register using signUp
        System.out.println("Enter password: ");
        tempString = scanS.nextLine();
        this.customerPass = tempString;
        System.out.println("Registration is completed, redirecting to home page");
        application.customerList.add(this);
    }

    public void login(Application application){
        System.out.println("Enter password: ");
        tempString = scanS.nextLine();
        if(tempString.equals(this.customerPass)){
            System.out.println("Found Matching, login-in");
            this.customerMenu(application);
        }
        else{
            System.out.println("Incorrect Password, Try Again");
            application.enterCustomer();
        }
    }

    public void customerMenu(Application application){
        System.out.println("Welcome "+this.customerName);
        System.out.println("1)  Browse products");
        System.out.println("2)  Browse deals");
        System.out.println("3)  Add a product to cart");
        System.out.println("4)  Add products in deal to cart"); 
        System.out.println("5)  View coupons"); //here
        System.out.println("6)  Check account balance");
        System.out.println("7)  View cart");
        System.out.println("8)  Empty cart");
        System.out.println("9)  Checkout cart"); // here 
        System.out.println("10) Upgrade customer status");
        System.out.println("11) Add amount to wallet");
        System.out.println("12) Back");
        tempInt = scan.nextInt();
        if(tempInt==1){
            exploreCatalog(tempInt, application);
        }

        if(tempInt==2){
            exploreCatalog(tempInt, application);
        }

        if(tempInt==3){
            System.out.println("Enter the product-id you wish to add: ");
            tempDouble = scan.nextDouble();
            System.out.println("Type the quantity of the product you wish to order: ");
            tempInt = scan.nextInt();
            this.addCart(application,tempDouble,tempInt);
            this.customerMenu(application);

        }
        if(tempInt==4){
            System.out.println("Enter the deal-id you wish to add: ");
            tempDouble = scan.nextDouble();
            System.out.println("Type the quantity of the product you wish to order: ");
            tempInt = scan.nextInt();
            for(Item item: application.dealsList){
                if(item.getAvailableNumber()>tempInt) {
                    item.setItemNumber(tempInt);
                    this.customerCart.add(item);
                    item.setAvailableNumber(item.getAvailableNumber()-tempInt);
                    System.out.println("Deal Added Successfully");
                }
                else{
                    System.out.println("The number of quantity demanded is greater than the available quantity, try again");
                }
                this.customerMenu(application);
            }
            System.out.println("The product id not found");
            this.customerMenu(application);
        }
        if (tempInt==5){
            if(this.customerCoupons.size()!=0) {
                System.out.println("Following are the available coupons");
                for (Integer integer : this.customerCoupons) {
                    System.out.println(integer + "%");
                }
            }
            else{
                System.out.println("No coupons present at current stage, buy products to get coupons");
                this.customerMenu(application);
            }
        }
        if(tempInt == 6){
            System.out.println("Your account balance is "+this.customerWallet);
            this.customerMenu(application);
        }
        if(tempInt ==7){
            if(this.customerCart.size()==0){
                System.out.println("The Cart is Empty!");
            }
            System.out.println("The following items are in the cart");
            for(Item item: this.customerCart){
                if((int)item.getItemId()==100){
                    System.out.println(item.getItemName()+"deal available at price "+item.getItemPrice());
                }
                else{
                    System.out.println(item.getItemName()+"product available at price"+item.getItemPrice());
                }
            }
            this.customerMenu(application);
        }
        if(tempInt == 8){
            this.customerCart.clear();
            System.out.println("Cart has been emptied");
            this.customerMenu(application);
        }
        if (tempInt == 9){
            System.out.println("Checking out");
            payPortal.paymentMenu(this, application);
        }
        if(tempInt == 10){
            this.upgradeStatus(application);
        }
        if(tempInt == 11){
            System.out.println("Add the amount you want to add to the wallet");
            tempInt = scan.nextInt();
            this.customerWallet += tempInt;
            System.out.println("Amount added successfully");
            this.customerMenu(application);
        }
        if(tempInt == 12){
            System.out.println("Returning back to main-menu");
            application.enterCustomer();
        }
    }

    private void upgradeStatus(Application application){
        payPortal.paymentMenu(this, application);
        application.menu();

    }
    private void exploreCatalog(int x, Application application){ // add interface for this for Application and Customer
        if(x==1){
            System.out.println("Following are the available product category: ");
            for(Category category: application.categoryList ){
                System.out.println("For the "+category.getCategoryName()+" we have the following products: ");
                for(Item item : category.getItemList()){
                    System.out.println(item.getItemName()+" - "+item.getItemId());
                }
            }
        }
        else{
            System.out.println("Following are the available deals:"); // If zero then also, add from interface
            for(Item item: application.dealsList){
                System.out.println(item.getItemName()+" - "+item.getItemId());
            }
        }
        System.out.println("_______________________________________");
        this.customerMenu(application);
    }
    

    private void addCart(Application application, double tempDouble, int tempInt){
        for(Category category: application.categoryList){
            for(Item item: category.getItemList()){
                if(item.getItemId()==tempDouble){
                    if(item.getAvailableNumber()>tempInt) {
                        item.setItemNumber(tempInt);
                        this.customerCart.add(item);
                        item.setAvailableNumber(item.getAvailableNumber()-tempInt);
                        System.out.println("Product added successfully");
                    }
                    else{
                        System.out.println("The number of quantity demanded is greater than the available quantity, try again");
                    }
                    return;
                }
            }
        }
        System.out.println("The product ID not found");
    }
}