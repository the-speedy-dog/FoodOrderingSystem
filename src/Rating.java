import java.util.LinkedList;
import java.util.Queue;

public class Rating { 
    private double rating;
    private int ratingTotal;
    private Queue<Integer> ratings;

    public Rating() {
        rating = 0;
        ratingTotal = 0;
        ratings = new LinkedList<>();
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

        rating = (double)ratingTotal / ratings.size();
    }

    public String toString() {
        return String.format("★ %.1f", getRating()); 
    }
}
