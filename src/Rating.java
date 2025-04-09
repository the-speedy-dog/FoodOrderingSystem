public class Rating { 
    private double rating;
    private int ratingCount;

    public Rating(rating) {
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public Rating() {
        this.rating = 0;
    }

    public double getRating() {
        return rating;
    }

    public void rate(double rating) {
        this.rating = (this.rating * ratingCount) + rating) / ++ratingCount;
    }
}
