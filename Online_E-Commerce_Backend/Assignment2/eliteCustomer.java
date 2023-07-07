import java.util.LinkedList;

public class eliteCustomer extends Customer{
    private String customerName;
    private int customerPrivileges;
    private String customerPass; //copy
    private int customerWallet; // copy
    private LinkedList<Item> customerCart; // copy
    private int productDiscount;
    private int deliveryRate;
    private boolean surpriseOffer;
    private int maxCouponGenerate;

    eliteCustomer(Customer customer){
        super(customer.getCustomerName());
        this.customerPrivileges = 3;
        this.productDiscount = 10;
        this.deliveryRate = 0;
        this.surpriseOffer = true;
        this.maxCouponGenerate = 4;
        this.customerPass = customer.getCustomerPass();
        this.customerWallet = customer.getCustomerWallet();
        this.customerCart = customer.getCustomerCart();
        this.setCustomerCoupons(customer.getCustomerCoupons());
        this.setDeliveryDate("1-2 days");
    }
}
