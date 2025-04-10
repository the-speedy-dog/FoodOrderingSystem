
public class Rating { 
    private double rating;
    private int ratingCount;

    public Rating(double rating, int ratingCount) {
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public Rating() {
        this.rating = 0;
        this.ratingCount = 0;
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void rate(double rating) {
        this.rating = ((this.rating * ratingCount) + rating) / ++ratingCount;
    }
}
