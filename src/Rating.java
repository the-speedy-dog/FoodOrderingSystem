
import java.util.Queue;

public class Rating { 
    private double rating;
    private int ratingTotal;
    private Queue<Integer> ratings;

    public Rating() {
        this.rating = 0;
        this.ratingTotal = 0;
        this.ratings = new Queue<>();
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratings.size();
    }

    public void rate(int rating) {
        if (ratings.size() == 10) {
            ratingTotal -= ratings.poll();
        }
        ratings.add(rating);
        ratingTotal += rating;

        rating = (double)ratingTotal / ratings.size();
    }
}
