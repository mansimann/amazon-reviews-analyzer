import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Word;
import java.io.IOException;
import java.util.HashMap;

public class Main {
  /**
   * This main method is the entry point of the executable program.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) throws Exception {

  }

  /**
   * Parses the input for adjectives and counts the mentions.
   *
   * @param input the product review submitted by customer
   * @return a HashMap containing adjectives and mention count
   * @throws NetworkException  if unable to make API request to TextRazor
   * @throws AnalysisException if unable to analyze input query
   */
  public static HashMap<String, Integer> getAdjectives(String input)
      throws NetworkException, AnalysisException {
    // Create a TextRazor instance with API
    TextRazor client = new TextRazor(System.getenv("TEXT_RAZOR_API_KEY"));
    // Add an extractor to the request
    client.addExtractor("words");
    // Make a TextRazor request to analyze I/O
    AnalyzedText response = client.analyze(input);
    // Associate the number of mentions with adjectives
    HashMap<String, Integer> map = new HashMap<>();
    for (Word word : response.getResponse().getWords()) {
      // Check the tag is JJ, which signifies adjectives in the Penn Treebank Project
      if (word.getPartOfSpeech().contentEquals("JJ")) {
        if (map.containsKey(word.getToken())) {
          // Increase the frequency of the adjective by 1
          Integer temp = map.get(word.getToken()) + 1;
          map.put(word.getToken(), temp);
        } else {
          // Add the new adjective to the HashMap
          map.put(word.getToken(), 1);
        }
      }
    }
    return map;
  }

  /**
   * Assesses the input for sentiment score and magnitude.
   *
   * @param input the product review submitted by customer
   * @return an array containing the sentiment score and magnitude
   * @throws IOException if unable to create LanguageServiceClient using credentials
   */
  public static double[] getSentiment(String input) throws IOException {
    // Construct a Document instance that contains the input
    Document doc = Document.newBuilder().setContent(input).setType(Type.PLAIN_TEXT).build();
    // Detect the sentiment of the doc, which comprises the input
    Sentiment sentiment = LanguageServiceClient.create()
        .analyzeSentiment(doc)
        .getDocumentSentiment();
    // Return an array containing the sentiment score and sentiment magnitude
    double[] values = {sentiment.getScore(), sentiment.getMagnitude()};
    return values;
  }

  /**
   * Outputs an appropriate response.
   * @param input the product review submitted by customer
   * @return a String output response
   * @throws IOException if unable to create LanguageServiceClient using credentials
   */
  public static String getResponse(String input) throws IOException {
    double score = getSentiment(input)[0];
    if (score < -0.6) {
      return "We are sorry to hear that.";
    } else if (score < 0.6) {
      return "Thank you for your review!";
    } else {
      return "We are glad you loved our product!";
    }
  }
}




