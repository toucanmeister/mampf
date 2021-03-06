package mampf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Collection of methods that involve communicating
 * with the Database.
**/
public class DatabaseHandler {
    private Connection db;

    DatabaseHandler(String path) {
        connectToDb(path);
        createTables();
    }

    public void connectToDb(String path) {
        final String jdbcPath = "jdbc:sqlite:" + path;
        try {
            db = DriverManager.getConnection(jdbcPath);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            db.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Recipe> getAllRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        try {
            Statement statement = db.createStatement();
            ResultSet rsRecipe = statement.executeQuery("SELECT * FROM recipes");
            while(rsRecipe.next()) {
                int id = rsRecipe.getInt("id");
                String title = rsRecipe.getString("title");
                int minutes = rsRecipe.getInt("minutes");
                int page = rsRecipe.getInt("page");
                String rating = rsRecipe.getString("rating");

                statement = db.createStatement();
                ResultSet rsBook = statement.executeQuery("SELECT title FROM books WHERE id = " + rsRecipe.getInt("book_id"));
                rsBook.next();
                String bookTitle = rsBook.getString("title");
                recipeList.add(new Recipe(id, title, minutes, bookTitle, page, rating));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return recipeList;
    }


    public void createTables() {
        try {
            Statement statement = db.createStatement();
            statement.execute("PRAGMA foreign_keys = ON");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS recipes(\n"
                    + "	id INTEGER PRIMARY KEY,\n"
                    + "	title TEXT NOT NULL,\n"
                    + " minutes INTEGER NOT NULL,\n"
                    + " book_id INTEGER NOT NULL,\n"
                    + " page INTEGER NOT NULL,\n"
                    + " rating TEXT NOT NULL,\n"
                    + " FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE\n"
                    + " FOREIGN KEY(rating) REFERENCES rating(name) ON DELETE CASCADE\n"
                    + ");");
            statement= db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS preparations(\n"
                    + "	name TEXT PRIMARY KEY\n"
                    + ");");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ingredients(\n"
                    + "	name TEXT PRIMARY KEY \n"
                    + ");");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS rating(\n"
                    + "	name TEXT PRIMARY KEY \n"
                    + ");");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS books(\n"
                    + "	id INTEGER PRIMARY KEY,\n"
                    + "	title TEXT NOT NULL,\n"
                    + "	author TEXT NOT NULL\n"
                    + ");");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS recipe_prep(\n"
                    + "	recipe_id INTEGER NOT NULL,\n"
                    + " prepName TEXT NOT NULL,\n"
                    + " PRIMARY KEY(recipe_id, prepName),\n"
                    + "	FOREIGN KEY(recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,\n"
                    + "	FOREIGN KEY(prepName) REFERENCES preparations(name) ON DELETE CASCADE\n"
                    + ");");
            statement = db.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS recipe_ingredients(\n"
                    + "	recipe_id INTEGER NOT NULL,\n"
                    + " ingredientName TEXT NOT NULL,\n"
                    + " PRIMARY KEY(recipe_id, ingredientName),\n"
                    + " FOREIGN KEY(recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,\n"
                    + "	FOREIGN KEY(ingredientName) REFERENCES ingredients(name) ON DELETE CASCADE\n"
                    + ");");
            statement = db.createStatement();
            statement.execute("INSERT OR IGNORE INTO rating VALUES(\"Sehr Lecker\")");
            statement = db.createStatement();
            statement.execute("INSERT OR IGNORE INTO rating VALUES(\"Lecker\")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
