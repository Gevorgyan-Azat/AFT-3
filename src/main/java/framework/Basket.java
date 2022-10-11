package framework;

public class Basket {

    private int totalPrice;
    private int quantity = 0;
    private String deleteProdName;

    public String getDeleteProdName() {
        return deleteProdName;
    }

    public void setDeleteProdName(String deleteProdName) {
        this.deleteProdName = deleteProdName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
