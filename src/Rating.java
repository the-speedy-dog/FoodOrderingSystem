
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

    public void rate(int newRating) {
        newRating = Math.clamp(newRating, 1, 5); // Limits from 1-5 Stars 
        if (ratings.size() == 10) {
            ratingTotal -= ratings.poll();
        }
        ratings.add(newRating);
        ratingTotal += newRating;

        this.rating = (double)ratingTotal / ratings.size();
    }
}
