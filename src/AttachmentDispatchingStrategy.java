import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class AttachmentDispatchingStrategy implements DispatchingStrategy {

    @Override
    public HttpResponse respond(String request) throws IOException{

        //parsing the request
        FileRequestParser fileRequestParser = new FileRequestParser();
        String result = fileRequestParser.parse(request);

        //searching for requested file
        File resultFile = new File(Server.getContentRoot() + result);
        byte[] resultFileBytes = Files.readAllBytes(resultFile.toPath());
        String mime = Files.probeContentType(resultFile.toPath());

        //building the response
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        httpResponseBuilder.status("200 OK");
        httpResponseBuilder.getHeaders().put("Content-Length", resultFileBytes.length + "");
        httpResponseBuilder.getHeaders().put("Accept-ranges", "bytes");
        httpResponseBuilder.getHeaders().put("Content-type", mime);
        httpResponseBuilder.getHeaders().put(
                "Content-Disposition", "attachment; filename=\"" + resultFile + "\""
            );
        httpResponseBuilder.content(resultFileBytes);
        httpResponseBuilder.build();

        return new HttpResponse(httpResponseBuilder);

    }

}
