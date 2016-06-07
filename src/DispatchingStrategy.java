import java.io.IOException;

public interface DispatchingStrategy {
    HttpResponse respond(String request) throws IOException;
}
