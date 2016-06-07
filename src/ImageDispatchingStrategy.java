import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

class ImageDispatchingStrategy implements DispatchingStrategy {

    @Override
    public HttpResponse respond(String request) throws IOException {

        //parsing the request
        FileRequestParser fileRequestParser = new FileRequestParser();
        String result = fileRequestParser.parse(request);

        //searching for requested file
        File resultFile = new File(Server.getContentRoot() + result);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] resultFileBytes = Files.readAllBytes(resultFile.toPath());
        String mime = Files.probeContentType(resultFile.toPath());
        String fileWrapping = "\r\n<html><img src=data:" + mime + ";base64,"
                    + encoder.encodeToString(resultFileBytes) + "></html>";

        //building the response
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        httpResponseBuilder.status("200 OK");
        httpResponseBuilder.getHeaders().put("Content-Length", fileWrapping.length() + "");
        httpResponseBuilder.content(fileWrapping.getBytes());
        httpResponseBuilder.content(resultFileBytes);
        httpResponseBuilder.build();

        return  new HttpResponse(httpResponseBuilder);

    }

}
