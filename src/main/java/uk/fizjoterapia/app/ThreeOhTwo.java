package uk.fizjoterapia.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/r")
public class ThreeOhTwo {

    @GET
    @Path("/0")
    public Response info() throws URISyntaxException {
        return Response.temporaryRedirect(new URI("https://info.fizjoterapia.uk")).build();
    }

    @GET
    @Path("/1")
    public Response formNo1() throws URISyntaxException {
        return Response.temporaryRedirect(new URI("https://app.fizjoterapia.uk/forms/pre-appointment.html")).build();
    }

}
