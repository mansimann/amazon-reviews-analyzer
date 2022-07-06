import com.textrazor.AnalysisException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Description: This is a JUnit Test.
 *
 * Scenario: This is the customer review: "This is a bad laptop, and it's expensive ugh"
 * Result: Test passes when sentiment score = -0.8 and sentiment magnitude = 0.8 and...
 * hashmap contains the following pairs: ("adjectives", 3), ("expensive", 1) and ...
 * output response is ""We are sorry to hear that."
 */
public class reviewTest {

  @Test
  public void testReview() throws IOException, AnalysisException {

    // Create a sample customer review
    String review = "This is a bad bad bad laptop and it's expensive ugh";

    // Ensure that the hashmap containing adjective mention count is correct
    HashMap<String, Integer> actualMap = new HashMap<>();
    actualMap.put("bad", 3);
    actualMap.put("expensive", 1);
    assertEquals(Main.getAdjectives(review), actualMap);

    // Ensure that the sentiment score and magnitude are correct
    assertEquals(Main.getSentiment(review)[0], -0.8, 0.01);
    assertEquals(Main.getSentiment(review)[1], 0.8, 0.01);

    // Ensure that the output response in appropriate
    assertEquals(Main.getResponse(review), "We are sorry to hear that.");

  }

}
