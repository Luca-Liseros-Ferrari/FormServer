package com.midoriPol.business;
import com.midoriPol.dao.BaseDao;
import com.midoriPol.dao.PersonDao;
import com.midoriPol.model.Person;
import com.midoriPol.model.PersonList;

public class PersonManager {
    private PersonDao personDao;

    public PersonManager() {
        BaseDao.initFactory("my-persistence-unit");
        personDao = new PersonDao();
    }

    public void createPerson(Person person) {
        PersonList personList = personDao.getById(PersonList.class, 1L);
        if (personList == null) {
            personList = new PersonList();
        }
        personList.getPersons().add(person);
        personDao.save(personList);
    }

    public PersonList getPersonList() {
        return personDao.getById(PersonList.class, 1L);
    }
}
