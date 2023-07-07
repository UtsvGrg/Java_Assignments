import java.util.LinkedList;

public class primeCustomer extends Customer{
    private String customerName;
    private int customerPrivileges;
    private String customerPass; //copy
    private int customerWallet; // copy
    private LinkedList<Item> customerCart; // copy
    private int productDiscount;
    private int deliveryRate;
    private boolean surpriseOffer;
    private int maxCouponGenerate;

    primeCustomer(Customer customer){
        super(customer.getCustomerName());
        this.customerPrivileges = 2;
        this.productDiscount = 5;
        this.deliveryRate = 2;
        this.surpriseOffer = false;
        this.maxCouponGenerate = 2;
        this.customerPass = customer.getCustomerPass();
        this.customerWallet = customer.getCustomerWallet();
        this.customerCart = customer.getCustomerCart();
        this.setCustomerCoupons(customer.getCustomerCoupons());
        this.setDeliveryDate("3-6 days");

    }
}