package DatabaseTests;

import org.junit.Before;
import org.junit.Test;
import quiz_web.Database.DbConnection;
import quiz_web.Database.RatingReviewDbManager;
import quiz_web.Database.RelationshipDbManager;
import quiz_web.Database.UserDbManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RatingReviewDbManagerTests {

    RatingReviewDbManager db;
    RelationshipDbManager relDb;
    DbConnection dbCon;
    Connection con;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        dbCon = new DbConnection();
        db = new RatingReviewDbManager(dbCon.getConnection(), true);
        dbCon.runSqlFile("testUsers.sql");
        String rateTable = "test_rate";
        Statement stmt = dbCon.getConnection().createStatement();
        String insertRateDataSql = "INSERT INTO " + rateTable + " (quiz_id, username, rating, review) VALUES " +
                "(1, 'user1', 5, 'Great quiz!'), " +
                "(1, 'user3', 5, 'Excellent quiz!'), " +
                "(1, 'user2', 4, 'Good quiz!'), " +
                "(2, 'user1', 3, 'Average quiz.'), " +
                "(2, 'user2', 2, 'Not so great.'), " +
                "(3, 'user1', 1, 'Terrible quiz.'), " +
                "(3, 'user3', 5, 'Loved it!'), " +
                "(3, 'user4', 4, 'Pretty good!'), " +
                "(4, 'user5', 3, 'It was okay.'), " +
                "(4, 'user6', 2, 'Could be better.'), " +
                "(4, 'user7', 1, 'Did not like it.'), " +
                "(4, 'user8', 4, 'Nice one.'), " +
                "(4, 'user9', 5, 'Perfect quiz!')";

        stmt.execute(insertRateDataSql);
    }


    @Test
    public void testAddRateAndReview() throws SQLException {
        for (int i = 2; i < 5; i++) {
            db.addRateAndReview("user" + i, i, i, "Nice quiz mate!");
        }

        HashMap<String, String> reviews = db.getReviewsByQuizId(1);
        assertEquals(3, reviews.size());
        assertEquals("Excellent quiz!", reviews.get("user3"));
    }

    @Test
    public void testGetReviewsByQuizId() throws SQLException {
        HashMap<String, String> reviews = db.getReviewsByQuizId(1);
        assertEquals(3, reviews.size());
        assertEquals("Great quiz!", reviews.get("user1"));
        assertEquals("Good quiz!", reviews.get("user2"));
    }

    @Test
    public void testDeleteAllRateReviewByQuizId() throws SQLException {
        db.deleteAllRateReview(null, 1);
        HashMap<String, String> reviews = db.getReviewsByQuizId(1);
        assertTrue(reviews.isEmpty());
    }

    @Test
    public void testDeleteAllRateReviewByUsername() throws SQLException {
        db.deleteAllRateReview("user1", -1);
        HashMap<String, String> reviews = db.getReviewsByQuizId(1);
        assertEquals(2, reviews.size());
        assertEquals("Good quiz!", reviews.get("user2"));
    }

    @Test
    public void testGetAverageRatingByQuizId() throws SQLException {
        double avgRating = db.getAverageRatingByQuizId(1);
        assertEquals(5, avgRating, 0.01);
    }

}
