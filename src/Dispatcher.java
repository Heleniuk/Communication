import java.io.IOException;

class Dispatcher {
    public HttpResponse dispatch(String request) {

        DispatchingContext dispatchingContext = null;

        //we choose dispatching strategy
        if(request.contains("/calc?")) {
            //dynamic content
            dispatchingContext = new DispatchingContext(new ArithmeticDispatchingStrategy());
        } else if(request.contains("/images/")) {
            //serving image via html
            dispatchingContext = new DispatchingContext(new ImageDispatchingStrategy());
        } else if(request.contains("/html/") || request.contains("/css/")) {
            //serving html along with css
            dispatchingContext = new DispatchingContext(new HtmlDispatchingStrategy());
        } else {
            //serving file attachment
            dispatchingContext = new DispatchingContext(new AttachmentDispatchingStrategy());
        }

        //serving HTTP exceptions
        try {
            return dispatchingContext.executeDispatchingStrategy(request);
        } catch (IOException e) {
            dispatchingContext = new DispatchingContext(new NotFoundDispatchingStrategy());
        } catch (IllegalArgumentException | ArithmeticException e) {
            dispatchingContext = new DispatchingContext(new BadRequestDispatchingStrategy());
        }

        //in case we can't find the html file containing HTTP exception
        finally {
            try {
                return dispatchingContext.executeDispatchingStrategy(request);
            } catch (IOException e) {
                System.err.println("Looks like the html file containing the response to an exceptional request hasn't been found");
                e.printStackTrace();
            }
        }

    return null;

    }
}
