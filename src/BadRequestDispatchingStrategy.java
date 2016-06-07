import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class BadRequestDispatchingStrategy implements DispatchingStrategy {
    private static final String NOT_FOUND_PATH = "\\html\\BadRequest.html";

    @Override
    public HttpResponse respond(String request) throws IOException {

        //searching for the file BadRequest.html to render
        File resultFile = new File(Server.getContentRoot() + NOT_FOUND_PATH);
        byte[] resultFileBytes = Files.readAllBytes(resultFile.toPath());
        String mime = Files.probeContentType(resultFile.toPath());

        //building the response
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        httpResponseBuilder.status("400 Bad Request");
        httpResponseBuilder.getHeaders().put("Content-Length", resultFileBytes.length + "");
        httpResponseBuilder.getHeaders().put("Content-type", mime);
        httpResponseBuilder.content(resultFileBytes);
        httpResponseBuilder.build();

        return new HttpResponse(httpResponseBuilder);

    }
}
