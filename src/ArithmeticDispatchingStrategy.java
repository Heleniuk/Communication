import java.util.Map;

class ArithmeticDispatchingStrategy implements DispatchingStrategy {

    @Override
    public HttpResponse respond(String request) throws ArithmeticException{

        //parsing the request
        ArithmeticRequestParser arithmeticRequestParser = new ArithmeticRequestParser();
        Map<String, String> parameters = arithmeticRequestParser.parse(request);

        //passing the parameters to the ArithmeticProcessor
        ArithmeticProcessor arithmeticProcessor = new ArithmeticProcessor();
        String result = arithmeticProcessor.processCalculation(
                parameters.get("operation"),
                Integer.parseInt(parameters.get("first")),
                Integer.parseInt(parameters.get("second"))
        );

        //building the response
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        httpResponseBuilder.status("200 OK");
        httpResponseBuilder.getHeaders().put("Content-Length", result.length() + "");
        httpResponseBuilder.content(result.getBytes());
        httpResponseBuilder.build();

        return new HttpResponse(httpResponseBuilder);

    }

}
