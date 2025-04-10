public class Driver extends User {
    private Rating rating;

    public Driver(String name) {
        super(name);
        this.rating = new Rating();
    }

    public double getRating() {
        return rating.getRating();
    }

    // SHOULD NOT BE ACCESSIBLE BY DRIVER
    public void addRating(int rating) {
        this.rating.rate(rating % 5); // Keeps it from 0-5 stars, idk
    }
}
