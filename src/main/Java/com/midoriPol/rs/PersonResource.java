package com.midoriPol.rs;
import com.midoriPol.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
public class PersonResource {
    private EntityManagerFactory emf;

    public PersonResource() {

        emf = Persistence.createEntityManagerFactory
                ("my-persistence-unit");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();

        return Response.ok().build();
    }
}