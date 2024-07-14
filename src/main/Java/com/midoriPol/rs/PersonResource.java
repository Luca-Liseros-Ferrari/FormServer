package com.midoriPol.rs;

import com.midoriPol.business.PersonManager;
import com.midoriPol.model.Person;
import com.midoriPol.model.PersonList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
public class PersonResource {
    private PersonManager personManager;

    public PersonResource() {
        personManager = new PersonManager();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        personManager.createPerson(person);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonList getPersonList() {
        return personManager.getPersonList();
    }
}
