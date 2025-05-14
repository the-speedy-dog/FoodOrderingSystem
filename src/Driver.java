public class Driver extends User {
    private Rating rating;
    private Order currOrder;
    private boolean hasOrder;

    public Driver(String name) {
        super(name);
        this.rating = new Rating();
        this.currOrder = null;
        this.hasOrder = false;
    }

    public void pickupOrder() {
        this.hasOrder = true;
    }

    public void deliverOrder() {
        this.hasOrder = false;
        this.currOrder = null;
    }

    public boolean hasOrder() {
        return this.hasOrder;
    }

    public Order getOrder() {
        return this.currOrder;
    }

    public void setOrder(Order order) {
        this.currOrder = order;
    }

    public double getRating() {
        return rating.getRating();
    }

    public Rating getRatingObject() {
        return rating;
    }

}
