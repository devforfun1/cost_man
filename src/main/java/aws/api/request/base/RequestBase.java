package aws.api.request.base;

import handler.response.base.ResponseHandlerBase;

/**
 * Each API request (in this package) shall have a ResponseHandler
 * @param <ResponseHandler>
 */
public abstract class RequestBase<ResponseHandler extends ResponseHandlerBase> {

    protected ResponseHandler handler;


}
