import java.util.ArrayList;

public class Item{

    private String itemName;
    private double itemId;
    private int itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemId() {
        return itemId;
    }

    public void setItemId(double itemId) {
        this.itemId = itemId;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public ArrayList<Integer> getDiscount() {
        return discount;
    }

    public void setDiscount(ArrayList<Integer> discount) {
        this.discount = discount;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public int getItemBulkPrice() {
        return itemBulkPrice;
    }

    public void setItemBulkPrice(int itemBulkPrice) {
        this.itemBulkPrice = itemBulkPrice;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    public int getDealNormal() {
        return dealNormal;
    }

    public void setDealNormal(int dealNormal) {
        this.dealNormal = dealNormal;
    }

    public int getDealPrime() {
        return dealPrime;
    }

    public void setDealPrime(int dealPrime) {
        this.dealPrime = dealPrime;
    }

    public int getDealELite() {
        return dealELite;
    }

    public void setDealELite(int dealELite) {
        this.dealELite = dealELite;
    }

    private String itemDetails; // Check This
    private ArrayList <Integer> discount;
    private int itemNumber;
    private int itemBulkPrice;
    private int availableNumber;
    private int dealNormal;
    private int dealPrime;
    private int dealELite;

    public Item(String itemName, double itemId, int itemPrice, int availableNumber){
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        discount = new ArrayList<>();
        discount.add(0);
        discount.add(0);
        discount.add(0);
        discount.add(0);
        this.availableNumber = availableNumber;
    }

    public void setItemNumber(int itemNumber){
        this.itemNumber = itemNumber;
        this.itemBulkPrice = itemNumber*this.itemPrice;
    }
}