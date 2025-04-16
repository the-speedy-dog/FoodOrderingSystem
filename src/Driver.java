public class Driver extends User {
    private Rating rating;

    public Driver(String name) {
        super(name);
        this.rating = new Rating();
    }

    public double getRating() {
        return rating.getRating();
    }

    public void addRating(int rating) {
        this.rating.rate(rating);
    }
}
