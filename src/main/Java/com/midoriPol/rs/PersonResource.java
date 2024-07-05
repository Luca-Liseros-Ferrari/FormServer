package com.midoriPol.rs;

import com.midoriPol.model.Person;
import com.midoriPol.model.PersonList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
public class PersonResource {
    private EntityManagerFactory emf;

    public PersonResource() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Check if PersonList exists or create a new one
        PersonList personList = em.find(PersonList.class, 1L); // Assuming PersonList with ID 1

        if (personList == null) {
            personList = new PersonList();
        }

        // Add the new person to the list
        personList.getPersons().add(person);

        // Persist the updated PersonList
        em.persist(personList);

        em.getTransaction().commit();
        em.close();

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonList getPersonList() {
        EntityManager em = emf.createEntityManager();
        PersonList personList = em.find(PersonList.class, 1L); // Assuming PersonList with ID 1
        em.close();
        return personList;
    }
}
