import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ArithmeticRequestParser {
    private final static String VALID_REQUEST_REGEX = "GET\\s/calc\\?operation=(ADD|SUBTRACT|MULTIPLY|DIVIDE)" +
                                                  "&first=-?\\d+&second=-?\\d+\\sHTTP/1.1";

    Map<String,String> parse(String httpRequest) throws IllegalArgumentException {
        Map<String, String> parameters = new HashMap<>();

        //request format validation
        Pattern pattern = Pattern.compile(VALID_REQUEST_REGEX);
        Matcher matcher = pattern.matcher(httpRequest);
        if (matcher.matches()) {

            //we cut parameters from the rest of the request
            String[] cutParamsFromHostName = httpRequest.split("[\\?|\\s]");

            //we split parameters from each other
            String[] splitParams = cutParamsFromHostName[2].split("&");

            //we split parameter names from values and put them into a map
            for (String s : splitParams) {
                String[] splitNameFromValue = s.split("=");
                for (int i = 0; i < splitNameFromValue.length - 1; i++) {
                    String name = splitNameFromValue[i];
                    String value = splitNameFromValue[i + 1];
                    parameters.put(name, value);
                }
            }
            return parameters;

        } else {
            //if request format is invalid
            throw new IllegalArgumentException();
        }
    }

}
