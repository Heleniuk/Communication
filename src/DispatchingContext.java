import java.io.IOException;

class DispatchingContext {

    private DispatchingStrategy dispatchingStrategy;

    DispatchingContext(DispatchingStrategy dispatchingStrategy) { this.dispatchingStrategy = dispatchingStrategy; }

    public HttpResponse executeDispatchingStrategy(String request) throws IOException {
        return dispatchingStrategy.respond(request);
    }

}
