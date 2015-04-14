package com.newfurniturey.mvc.app.models;

import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.InvalidDatabaseException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class Show extends Model {
	protected String _tableName = "shows";
	protected String _primaryKey = "id";
	private int _id;
	private String _name;
	private String _imageUrl;
	private double _rating;
	private String _description;
	
	public class ShowBuilder {
		private Connection conn = null;
		private int id = -1;
		private String name = "";
		private String imageUrl = "";
		private double rating = 0.0;
		private String description = "";
		public ShowBuilder(Connection connection) { conn = connection; }
		public ShowBuilder id(int data) { id = data; return this; }
		public ShowBuilder name(String data) { name = data; return this; }
		public ShowBuilder imageUrl(String data) { imageUrl = data; return this; }
		public ShowBuilder rating(double data) { rating = data; return this; }
		public ShowBuilder description(String data) { description = data; return this; }
		public Model build() { return new Show(this); }
	}
	
	public Show(Connection conn) {
		super(conn);
	}
	
	public Show(ShowBuilder builder) {
		this._connection = builder.conn;
		this._id = builder.id;
		this._name = builder.name;
		this._imageUrl = builder.imageUrl;
		this._rating = builder.rating;
		this._description = builder.description;
	}
    
    public boolean save() {
		try {
			PreparedStatement statement = this._connection.prepareStatement("INSERT INTO shows (name, rating, description, image_url) VALUES (?, ?, ?, ?);");
            statement.setString(1, this._name);
            statement.setDouble(2, this._rating);
            statement.setString(3, this._description);
            statement.setString(4, this._imageUrl);
			statement.executeUpdate();
            return true;
		} catch (SQLException e) {
			System.err.println("Failed to create table: " + this._tableName);
		}
        return false;
    }
	
	public String getTableName() {
		return this._tableName;
	}
	
	public String getPrimaryKey() {
		return this._primaryKey;
	}
	
	protected Model _newFromResultSet(ResultSet item) throws SQLException {
		return new ShowBuilder(this._connection)
			.id(item.getInt("id"))
			.name(item.getString("name"))
			.imageUrl(item.getString("image_url"))
			.rating(item.getDouble("rating"))
			.description(item.getString("description"))
			.build();
	}
	
	public int getId() {
		return this._id;
	}
	
	public String getName() {
		return this._name;
	}
	
	public void setName(String name) {
		this._name = name;
		notifyObservers(this._name);
	}
	
	public String getImageUrl() {
		return this._imageUrl;
	}
	
	public void setImageUrl(String url) {
		this._imageUrl = url;
		notifyObservers(this._imageUrl);
	}
	
	public ImageIcon getImage() {
		if (this._imageUrl == null) return null;
		
		try {
			return new ImageIcon(new URL(this._imageUrl));
		} catch (MalformedURLException e) {
			System.err.println("image error: " + e.getMessage());
			return null;
		}
	}
	
	public double getRating() {
		return this._rating;
	}
	
	public void setRating(double rating) {
		this._rating = rating;
		notifyObservers(this._rating);
	}
	
	public String getDescription() {
		return this._description;
	}
	
	public void setDescription(String desc) {
		this._description = desc;
		notifyObservers(this._description);
	}
	
	public void bootstrap() throws InvalidDatabaseException {
		if (this._tableExists(this._tableName)) {
			// table already exists, nothing to do =]
			return;
		}
		
		try {
			Statement statement = this._connection.createStatement();
			statement.executeUpdate("CREATE TABLE shows (id integer PRIMARY KEY, name string, image_url string, rating real, description text, added_on timestamp DEFAULT CURRENT_TIMESTAMP);");
			this.addSampleData();
		} catch (SQLException e) {
			System.err.println("Failed to create table: " + this._tableName);
		}
	}
	
	private void addSampleData() {
		try {
			Statement statement = this._connection.createStatement();
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Bleach', 5.0, 'Bleach follows the story of Ichigo Kurosaki. When Ichigo meets Rukia he finds his life is changed forever.', 'http://upload.wikimedia.org/wikipedia/en/thumb/5/5c/Bleach_01_-_The_Substitute.jpg/220px-Bleach_01_-_The_Substitute.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Fate/stay night', 3.8, 'There is a war going on between masters and servants in order to attain the Holy Grail. Each master can call up one servant each, and their task is to eradicate the other servants, either by defeating them or killing their master. When there is only one master or servant left, he or she is granted the Holy Grail, and any wish they desire will come true.', 'http://upload.wikimedia.org/wikipedia/en/d/d4/Fate-stay_night.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('FLCL', 4.2, 'A 12-year old boy named Naota one day meets a strange woman, riding a Vespa and wielding a big guitar. As soon as she appears, mysterious things start happening.', 'http://upload.wikimedia.org/wikipedia/en/thumb/f/fe/FLCL_image.jpg/230px-FLCL_image.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Fullmetal Alchemist: Brotherhood', 4.5, 'Brothers Edward and Alphonse Elric continue their search for the Philsopher''s Stone, hoping to restore their bodies, which were lost when they attempted to use their alchemy skills to resurrect their deceased mother.', 'http://upload.wikimedia.org/wikipedia/en/thumb/9/9c/Fullmetal_Alchemist_Brotherhood.jpg/230px-Fullmetal_Alchemist_Brotherhood.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Hellsing', 4.1, 'A British taskforce, lead by the daughter of the vampire hunter Prof. Van Helsing, battles the supernatural with the aid of two vampires.', 'http://upload.wikimedia.org/wikipedia/en/thumb/8/81/Hellsing_manga.jpg/230px-Hellsing_manga.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('One Piece', 4.5, 'Follows the adventures of Monkey D. Luffy and his friends in order to find the greatest treasure ever left by the legendary Pirate, Gol D Roger. The famous mystery treasure named \"One Piece\".', 'http://upload.wikimedia.org/wikipedia/en/thumb/9/90/One_Piece%2C_Volume_61_Cover_%28Japanese%29.jpg/220px-One_Piece%2C_Volume_61_Cover_%28Japanese%29.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Vandread', 4.0, 'The battle of the sexes may seem bad on Earth, but in a space colony far, far away, things are even worse. Men and women haven''t seen each other for decades, so they don''t just argue in the future – they go straight for each other''s throats.', 'http://upload.wikimedia.org/wikipedia/en/thumb/5/59/Vandread_girls.jpg/230px-Vandread_girls.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Vampire Hunter D', 3.6, 'In a far-future time ruled by the supernatural, a young girl requests the help of a vampire hunter to kill the vampire who has bitten her and thus prevent her from becoming a vampire herself.', 'http://upload.wikimedia.org/wikipedia/en/thumb/4/4e/Vampire_Hunter_D_Volume_1_Cover.jpg/230px-Vampire_Hunter_D_Volume_1_Cover.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Vampire Hunter D: Bloodlust', 3.9, 'When a girl is abducted by a vampire, a legendary bounty hunter is hired to bring her back.', 'http://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Vampire-hunter-d-poster.jpg/220px-Vampire-hunter-d-poster.jpg');");
			statement.executeUpdate("INSERT INTO shows (name, rating, description, image_url) VALUES ('Yakitate!! Ja-pan', 5.0, 'Azuma Kazuma is a young bakery worker with \"palms of sun\" and creative ideas with a dream to make Japanese bread, \"Ja-pan\", which will be better than any other bread in Japan.', 'http://upload.wikimedia.org/wikipedia/en/thumb/4/4c/Yakitate%21%21_Japan_volume_1.jpg/230px-Yakitate%21%21_Japan_volume_1.jpg');");
		} catch (SQLException e) {
			System.err.println("Failed to add sample data: " + e.getMessage());
		}
	}
}
