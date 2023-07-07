import java.util.*;

public class Application {
    Scanner scan = new Scanner(System.in);
    Scanner scanS = new Scanner(System.in);

    LinkedList <Customer> customerList;
    LinkedList <Category> categoryList;
    LinkedList <Item> dealsList;
    Admin admin;
    private int tempInt;
    private String tempString;

    public Application(){
        categoryList = new LinkedList<>();
        dealsList = new LinkedList<>();
        admin = new Admin("Utsav Garg","2021108");
        customerList = new LinkedList<>();
    }

    public void menu(){
        System.out.println("Welcome to FlipZone");
        System.out.println("1) Enter as Admin"); // Check valid credentials in this function
        System.out.println("2) Explore the Product Catalog");
        System.out.println("3) Show Available Deals");
        System.out.println("4) Enter as Customer");
        System.out.println("5) Exit the Application");
        tempInt = scan.nextInt();
        if (tempInt==1){
            System.out.println("Entering as admin");
            System.out.println("Please enter username: ");
            tempString = scanS.nextLine();
            if(tempString.equals(admin.getAdminName())){
                System.out.println("Welcome Utsav, enter your password");
                tempString = scanS.nextLine();
                if(tempString.equals(admin.getAdminPass())){
                    System.out.println("Access granted");
                    admin.menu(this);
                }
            }
            else{
                System.out.println("Username not found in database, Try again");
                this.menu();
                return;
            }

        }
        if (tempInt==2){
            if(this.categoryList.size()==0){
                System.out.println("No available products, login as admin to add");
                System.out.println("_______________________________________");
                this.menu();
            }
            System.out.println("Following are the available product category: ");
            for(Category category: this.categoryList ){
                System.out.println("For the "+category.getCategoryName()+" we have the following products: ");
                for(Item item : category.getItemList()){
                    System.out.println(item.getItemName()+" - "+item.getItemId());
                }
            }
            System.out.println("_______________________________________");
            this.menu();
        }
        if (tempInt==3){
            if(this.dealsList.size()==0){
                System.out.println("Dear User, there are no deals for now!!");
                System.out.println("_______________________________________");
                this.menu();
            }
            else{
                for(Item item: this.dealsList){
                    System.out.println(item.getItemName()+" at just "+item.getItemPrice());
                    this.menu();
                }
            }
        }
        if (tempInt==4){
            this.enterCustomer();
        }

        if (tempInt==5){
            System.out.println("Exiting the application");
            System.exit(0);
        }
        else{
            System.out.println("Invalid Input");
            System.out.println("Redirecting to home page");
            System.out.println("_______________________________________");
            this.menu();
        }
    }

    public static void main(String[] args) {
        Application FlipZone = new Application();
        FlipZone.menu();
    }


    public void enterCustomer(){
        System.out.println("Welcome!");
        System.out.println("1) Sign Up");
        System.out.println("2) Log in");
        System.out.println("3) Back");
        tempInt = scan.nextInt();
        if (tempInt==1){
            System.out.println("Please enter your name: ");
            tempString = scanS.nextLine();
            Customer customer = new Customer(tempString);
            customer.signUp(this);  
            this.enterCustomer();
        }  
        if (tempInt==2){
            System.out.println("Please enter your name: ");
            tempString = scanS.nextLine();
            for(Customer customer: this.customerList){
                if(tempString.equals(customer.getCustomerName())){
                    System.out.println("Welcome "+customer.getCustomerName());
                    customer.login(this);
                    break;
                }
            }
            System.out.println("The username is not found in the database.");
            this.enterCustomer();
        }
        if (tempInt==3){
            System.out.println("Redirecting to Home Page");
            this.menu();
        }
    }
}