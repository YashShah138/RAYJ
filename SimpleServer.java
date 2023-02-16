import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// public class = public template
// instance = copy of template/class
public class SimpleServer {
  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    // object.method(createcontext) new --> handler
    // !!!! API NAME: convertCurrency
    server.createContext("/convertCurrency", new ConvertCurrencyHandler());
    server.setExecutor(null); // creates a default executor
    // servers ready to listen:
    server.start();
  }

  static class ConvertCurrencyHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
      // Get the request method
      String requestMethod = exchange.getRequestMethod();
      if (requestMethod.equalsIgnoreCase("POST")) {
        // Get the request body as an input stream
        InputStream requestBody = exchange.getRequestBody();
        String requestBodyString = new String(requestBody.readAllBytes());

        // Parse the request body to get the two values
        String[] requestBodyValues = requestBodyString.split(",");
        Double dollarAmount = Double.parseDouble(requestBodyValues[0].trim());
      //  sdjkfhksdhfksd = function
        String countryCurrency = requestBodyValues[1].trim();

        // Convert the currency and prepare the response
        Double convertedCurrency = convertCurrency(dollarAmount, countryCurrency);
        String responseBody = "Converted currency: " + convertedCurrency;

        // Send the response back to the client
        exchange.sendResponseHeaders(200, responseBody.getBytes().length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBody.getBytes());
        outputStream.flush();
        outputStream.close();
      } else {
        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
      }
    }
  }

  private static Double convertCurrency(Double dollarAmount, String countryCurrency) {
    // Perform currency conversion here
    // Set the exchange rate for the country currency
    Double exchangeRate;
    switch (countryCurrency) {
        case "EUR":
            exchangeRate = 0.82;
            break;
        case "GBP":
            exchangeRate = 0.72;
            break;
        case "JPY":
            exchangeRate = 105.72;
            break;
        default:
            exchangeRate = 1.0;
            break;
    }
    // Convert the currency and return the result
    return dollarAmount * exchangeRate;
    // Return the converted currency value
  }
}
