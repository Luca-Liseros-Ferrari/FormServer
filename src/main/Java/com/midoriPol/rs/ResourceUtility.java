package com.midoriPol.rs;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResourceUtility {
    public static Response ok (Object body) {
        return Response
                .status(Response.Status.OK)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response created (Object body) {
        return Response
                .status(Response.Status.CREATED)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response bad (Object body) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response notFound (Object body) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response internalServerError (Object body) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}