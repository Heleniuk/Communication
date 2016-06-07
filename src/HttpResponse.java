import java.util.HashMap;
import java.util.Map;

class HttpResponse {

    private String status;
    private Map<String,String> headers = new HashMap<>();
    private byte[] content;
    private StringBuilder message = new StringBuilder();
    private byte[] fullResponse;

    public byte[] getFullResponse() {
        return fullResponse;
    }

    HttpResponse(HttpResponseBuilder httpResponseBuilder) {
        this.status = httpResponseBuilder.getStatus();
        this.headers = httpResponseBuilder.getHeaders();
        this.content = httpResponseBuilder.getContent();
        this.message = httpResponseBuilder.getMessage();
        this.fullResponse = httpResponseBuilder.getFullResponse();
    }

}
