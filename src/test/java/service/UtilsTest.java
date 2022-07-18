package service;

import com.textrazor.AnalysisException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Description: This is a JUnit Test for the main.Utils class.
 */
public class UtilsTest {

  @Test
  public void getReview_Pass_ValidURL() throws IOException {
    String URL = "https://www.amazon.com/Apple-MLWK3AM-A-AirPods-Pro/product-reviews/" +
        "B09JQMJHXY/ref=cm_cr_dp_d_show_all_btm?ie=UTF8&reviewerType=all_reviews";
    String reviews = Utils.getReviews(URL);
    assertNotNull(reviews);
  }

  @Test
  public void getSentiment_Pass_ValidReview() throws IOException {
    String review = "I love this product! Its amazing";
    assertEquals(Utils.getSentiment(review)[0], 0.9, 0.01);
    assertEquals(Utils.getSentiment(review)[1], 1.9, 0.01);
  }

  @Test
  public void getEntities_Pass_ValidReview() throws IOException, AnalysisException {
    String review = "Samsung and Apple laptops are too expensive ugh";
    Set<String> actual = new HashSet<>();
    actual.add("Samsung");
    actual.add("Apple Inc.");
    assertEquals(Utils.getEntities(review), actual);
  }

}
