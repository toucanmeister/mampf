package mampf;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Recipe {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty title = new SimpleStringProperty("");
    private final SimpleIntegerProperty minutes = new SimpleIntegerProperty(0);
    private final SimpleStringProperty bookTitle = new SimpleStringProperty("");
    private final SimpleIntegerProperty page = new SimpleIntegerProperty(0);
    private final SimpleStringProperty rating = new SimpleStringProperty("");

    public Recipe() {
        this(0, "", 0, "", 0, "");
    }

    public Recipe(int id, String title, int minutes, String bookTitle, int page, String rating) {
        setId(id);
        setTitle(title);
        setMinutes(minutes);
        setBookTitle(bookTitle);
        setPage(page);
        setRating(rating);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getMinutes() {
        return minutes.get();
    }

    public SimpleIntegerProperty minutesProperty() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes.set(minutes);
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public SimpleStringProperty bookTitleProperty() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public int getPage() {
        return page.get();
    }

    public SimpleIntegerProperty pageProperty() {
        return page;
    }

    public void setPage(int page) {
        this.page.set(page);
    }

    public String getRating() {
        return rating.get();
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }
}
