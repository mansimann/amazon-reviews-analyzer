package service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Sentiment;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Entity;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.google.cloud.language.v1.LanguageServiceClient.create;

/**
 * Name: Mansi Mann.
 * Description: This class contains the analysis utilities.
 */
public class Utils {
  /**
   * Gets Amazon reviews from a URL.
   *
   * @param url the URL address of a location's Google reviews
   * @return the Google reviews
   */
  public static String getReviews(String url) throws IOException {
    org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
    return doc.getElementsByClass("a-size-base review-text review-text-content").text();
  }

  /**
   * Extracts named entities from the input.
   *
   * @param input the Amazon reviews
   * @return a set containing all named entities extracted from input
   * @throws NetworkException  if unable to make API request to TextRazor
   * @throws AnalysisException if unable to analyze input query
   */
  public static Set<String> getEntities(String input) throws NetworkException, AnalysisException {
    // Create a TextRazor instance with API
    TextRazor client = new TextRazor(System.getenv("TEXT_RAZOR_API_KEY"));
    // Add an extractor to the request
    client.addExtractor("entities");
    // Make a TextRazor request to analyze I/O
    AnalyzedText response = client.analyze(input);
    // Extract entities and get the disambiguated ID for each entity
    Set<String> entitySet = new HashSet<>();
    for (Entity entity : response.getResponse().getEntities()) {
      entitySet.add(entity.getEntityId());
    }
    // Return a set containing all named entities extracted from input
    return entitySet;
  }

  /**
   * Assesses the input for sentiment score and magnitude.
   *
   * @param input the Amazon reviews
   * @return an array containing the sentiment score and magnitude
   * @throws IOException if unable to create LanguageServiceClient using credentials
   */
  public static double[] getSentiment(String input) throws IOException {
    // Construct a Document instance that contains the input
    Document doc = Document.newBuilder().setContent(input).setType(Document.Type.PLAIN_TEXT).build();
    // Detect the sentiment of the doc, which comprises the input
    Sentiment sentiment = create()
        .analyzeSentiment(doc)
        .getDocumentSentiment();
    // Return an array containing the sentiment score and sentiment magnitude
    return new double[]{sentiment.getScore(), sentiment.getMagnitude()};
  }
}


