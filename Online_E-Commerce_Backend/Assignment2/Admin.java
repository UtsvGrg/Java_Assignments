// import java.util.Collections;
import java.util.Scanner;

public class Admin {
    Scanner scan = new Scanner(System.in);
    Scanner scanS = new Scanner(System.in);
    private String adminName;
    private String adminPass;
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
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

    public String getTempDetails() {
        return tempDetails;
    }

    public void setTempDetails(String tempDetails) {
        this.tempDetails = tempDetails;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public double getDealID() {
        return dealID;
    }

    public void setDealID(double dealID) {
        this.dealID = dealID;
    }

    private int tempInt;
    private String tempString;
    private double tempDouble;
    private String tempDetails;
    private int check = 0;
    private double dealID = 100.1;

    public Admin(String adminName, String adminPass){
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public void menu(Application application){
        System.out.println("Please choose any one of the following actions");
        System.out.println("1) Add category");
        System.out.println("2) Delete category");
        System.out.println("3) Add Product");
        System.out.println("4) Delete Product");
        System.out.println("5) Set Discount on Product");
        System.out.println("6) Add giveaway deal");
        System.out.println("7) Back");
        tempInt = scan.nextInt();
        if (tempInt==1){
            this.addCategory(application);
            this.menu(application);
        }
        if (tempInt==2){
            this.deleteCategory(application);
            this.menu(application);
        }
        if (tempInt==3){
            this.addProduct(application);
            this.menu(application);
        }
        if (tempInt==4){
            this.deleteProduct(application);
            this.menu(application);
        }
        if (tempInt==5){
            this.setDiscount(application);
            this.menu(application);
        }
        if (tempInt==6){
            this.giveawayDeal(application);
            this.menu(application);
        }
        if (tempInt==7){
            application.menu();
        }
    }

    private void addCategory(Application application){
        System.out.println("Enter the category id: "); // Check if already exists,then try again
        tempInt = scan.nextInt();
        for(Category category: application.categoryList){
            if(tempInt==category.getCategoryId()){
                System.out.println("This ID is already in use.");
                this.menu(application);
            }
        }
        System.out.println("Enter the name of the category: ");
        tempString = scanS.nextLine();
        Category category = new Category(tempString, tempInt);
        application.categoryList.add(category);
        System.out.println("Enter the product name for the item in respective category: ");
        tempString = scanS.nextLine();
        tempDouble = tempInt;
        tempDouble += category.getItemId();
        category.setItemId(category.getItemId()+0.1);
        System.out.println("Enter the product details(Put spaces to distinguish): ");
        tempDetails = scanS.nextLine();
        System.out.println("Enter the product price: ");
        tempInt = scan.nextInt();
        System.out.println("Enter the number of product you need to add");
        int tempIntt = scan.nextInt();
        Item item = new Item(tempString, tempDouble, tempInt,tempIntt);
        item.setItemDetails(tempDetails);
        category.getItemList().add(item);
        System.out.println("Category Added Successfully");
    }

    private void deleteCategory(Application application){
        System.out.println("Enter the name of the category you wish to delete: ");
        tempString = scanS.nextLine();
        System.out.println("Enter the category id of that product: ");
        tempInt = scan.nextInt();
        for(Category category: application.categoryList){
            if (category.getCategoryId() == tempInt){
                application.categoryList.remove(category);
                System.out.println("Category Removed Successfully, redirecting to admin-menu\n");
                return;
            }
        }
        System.out.println("The Category Id you have entered does not exist. Try Again");
    }

    private void addProduct(Application application){ // Make function for this
        System.out.println("To add a product, first enter the category id associated: ");
        tempInt = scan.nextInt();
        for(Category category: application.categoryList){
            if (category.getCategoryId() == tempInt){
                System.out.println("Enter the product name for the item in respective category: ");
                tempString = scanS.nextLine();
                tempDouble = tempInt;
                tempDouble += category.getItemId();
                category.setItemId(category.getItemId()+0.1);
                System.out.println("Enter the product details(Put spaces to distinguish): ");
                tempDetails = scanS.nextLine();
                System.out.println("Enter the product price: ");
                tempInt = scan.nextInt();
                System.out.println("Enter the number of product you need to add");
                int tempIntt = scan.nextInt();
                Item item = new Item(tempString, tempDouble, tempInt,tempIntt);
                item.setItemDetails(tempDetails);
                category.getItemList().add(item);
                System.out.println("Product Added Successfully");
                return;
            }
        }
        System.out.println("The category does not exist, redirecting to add category page");
        this.addCategory(application);
    }

    private void deleteProduct(Application application){
        System.out.println("Enter the name of the category of the product");
        tempString = scanS.nextLine();
        for(Category category: application.categoryList){
            if (tempString.equals(category.getCategoryName())){
                System.out.println("Enter the product id: ");
                tempDouble = scan.nextDouble();
                for(Item item: category.getItemList()){
                    if(item.getItemId() == tempDouble){
                        category.getItemList().remove(item);
                        System.out.println("Item Removed");
                        category.setItemId(category.getItemId()-0.1);
                        check = 1;
                        break;
                    }
                }
                if(check==0){
                    System.out.println("Product ID does not exist");
                    break;
                }
                return;
            }
            if (category.getItemList().size()==0){
                System.out.println("The category has no more product inside");
                System.out.println("If you wish to delete the category press 0");
                System.out.println("If you want to add a product press 1");
                tempInt = scan.nextInt();
                if(tempInt==0){
                    application.categoryList.remove(category);
                    System.out.println("Category deleted");
                }
                else{ // Make function for this
                    System.out.println("Enter the product name for the item in respective category: ");
                    tempString = scanS.nextLine();
                    tempDouble = tempInt;
                    tempDouble += category.getItemId();
                    category.setItemId(category.getItemId());
                    System.out.println("Enter the product details(Put spaces to distinguish): ");
                    tempDetails = scanS.nextLine();
                    System.out.println("Enter the product price: ");
                    tempInt = scan.nextInt();
                    System.out.println("Enter the number of product you need to add");
                    int tempIntt = scan.nextInt();
                    Item item = new Item(tempString, tempDouble, tempInt,tempIntt);
                    item.setItemDetails(tempDetails);
                    category.getItemList().add(item);
                    System.out.println("Product added successfully");
                }
                return;
            }
        }
        System.out.println("Category not found, returning to main menu");
        this.menu(application);
    }

    private void setDiscount(Application application){
        check = 0;
        System.out.println("Enter in the product id, for which discount needs for");
        tempDouble = scan.nextDouble();
        tempInt = (int)tempDouble;
        for(Category category: application.categoryList){
            if (category.getCategoryId() == tempInt){
                for(Item item: category.getItemList()){
                    if(item.getItemId() == tempDouble){
                        System.out.println("Enter Discount for Elite");
                        tempInt = scan.nextInt();
                        item.getDiscount().set(3,tempInt);
                        System.out.println("Enter Discount for Prime");
                        tempInt = scan.nextInt();
                        item.getDiscount().set(2,tempInt);
                        System.out.println("Enter Discount for Normal");
                        tempInt = scan.nextInt();
                        item.getDiscount().set(1,tempInt);
                        check = 1;
                    }
                }
            }
        }
        if(check==0) {
            System.out.println("The product id is not valid");
        }
        else{
            System.out.println("Discount added successfully");
        }
    }

    private void giveawayDeal(Application application){
        double prod1ID;
        double prod2ID;
        Item item1 = null;
        Item item2 = null;
        int deal_price;
        this.check = 0;
        System.out.println("Enter the product id of the first product: ");
        prod1ID = scan.nextDouble();
        System.out.println("Enter the product id of the second product: ");
        prod2ID = scan.nextDouble();
        for(Category category: application.categoryList){
            for(Item item: category.getItemList()){
                if(prod1ID==item.getItemId()){
                    item1 = item;
                    this.check++;
                }
                if(prod2ID == item.getItemId()){
                    item2 = item;
                    this.check++;
                }
            }
        }
        if(check==2){
            System.out.println("Enter the combined price of the deal for normal:");
            deal_price = scan.nextInt();
            Item deal = new Item(item1.getItemName()+"  & "+item2.getItemName(),dealID,deal_price,Math.min(item1.getAvailableNumber(),item2.getAvailableNumber()));
            deal.setDealNormal(deal_price);
            System.out.println("Enter the combined price of the deal for prime:");
            deal_price = scan.nextInt();
            deal.setDealPrime(deal_price);
            System.out.println("Enter the combined price of the deal for elite:");
            deal_price = scan.nextInt();
            deal.setDealELite(deal_price);
            application.dealsList.add(deal);
            System.out.println("Deal Added Successfully");
            // this.menu(application);
        }
        else{
            System.out.println("Product Id not found, try again");
            // this.menu(application);
        }
    }
}

