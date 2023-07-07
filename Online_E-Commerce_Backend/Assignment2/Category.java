import java.util.LinkedList;

public class Category{

    private LinkedList <Item> itemList;
    private String categoryName;
    private int categoryId;
    private double itemId;

    public LinkedList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(LinkedList<Item> itemList) {
        this.itemList = itemList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getItemId() {
        return itemId;
    }

    public void setItemId(double itemId) {
        this.itemId = itemId;
    }

    public Category(String categoryName, int categoryId){
        this.itemList = new LinkedList<Item>();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.itemId = 0.1;
    }
}