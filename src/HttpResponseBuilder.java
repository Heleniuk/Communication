import java.util.HashMap;
import java.util.Map;

class HttpResponseBuilder {
    private static final String HTTP_VERSION = "HTTP/1.1";

    private String status;
    private Map<String,String> headers = new HashMap<>();
    private byte[] content;
    private StringBuilder message = new StringBuilder();
    private byte[] fullResponse;

    public String getStatus() { return status; }

    public Map<String, String> getHeaders() { return headers; }

    public byte[] getContent() { return content; }

    public StringBuilder getMessage() { return message; }

    public HttpResponseBuilder status(String status) {
        this.status = status;
        return this;
    }

    public byte[] getFullResponse() {
        return fullResponse;
    }

    public HttpResponseBuilder content(byte[] content) {
        this.content = content;
        return this;
    }

    public HttpResponse build() {

        //building response
        this.message.append(HTTP_VERSION + " ");
        this.message.append(status + " ");
        this.message.append(HTTP_VERSION + "\r\n");

        //adding headers
        for(String s: headers.keySet()) {
            message.append(s + ": ");
            message.append(headers.get(s) + "\r\n");
        }

        //one more empty line before content
        this.message.append("\r\n");

        //adding content
        byte[] message = this.message.toString().getBytes();
        fullResponse = new byte[message.length + content.length];
        System.arraycopy(message,0,fullResponse,0,message.length);
        System.arraycopy(content,0,fullResponse,message.length,content.length);

        return new HttpResponse(this);

    }

}
