import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileRequestParser {
    private static  final String VALID_REQUEST_REGEX = "GET\\s[/\\w]+[.]\\w+\\sHTTP/1.1";

    String parse(String httpRequest) throws IllegalArgumentException{

        //request format validation
        Pattern pattern = Pattern.compile(VALID_REQUEST_REGEX);
        Matcher matcher = pattern.matcher(httpRequest);
        if(matcher.matches()) {

            //we cut file path from the rest of the request
            String[] cutFileNameFromRequest = httpRequest.split("[\\s]");
            String fileName = cutFileNameFromRequest[1].replaceAll("/","\\\\");
            return fileName;

        } else {
            //if request format is invalid
            throw new IllegalArgumentException();
        }
    }

}
