package com.stasiamba;

import com.stasiamba.dao.GenericDAO;
import com.stasiamba.dao.hibernate.ModelDAO;
import com.stasiamba.model.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        GenericDAO devDAO = new ModelDAO<Developer>(Developer.class);
        GenericDAO skillDAO = new ModelDAO<Skill>(Skill.class);
        GenericDAO projectDAO = new ModelDAO<Project>(Project.class);
        GenericDAO customerDAO = new ModelDAO<Customer>(Customer.class);
        GenericDAO companyDAO = new ModelDAO<Company>(Company.class);



        Developer d1 = new Developer(
                "Ivan", "Ivanov", new BigDecimal(3000));
        devDAO.create(d1);

        Skill s1 = new Skill("Java");
        skillDAO.create(s1);

        Project p1 = new Project("Relax");
        projectDAO.create(p1);

        Customer cu1 = new Customer("Zakazchik");
        customerDAO.create(cu1);

        Company co1 = new Company("Google");
        companyDAO.create(co1);

        Developer d = (Developer) devDAO.read(1L);
        Skill s = (Skill) skillDAO.read(1L);
        Project p = (Project) projectDAO.read(1L);
        Customer cu = (Customer) customerDAO.read(1L);
        Company co = (Company) companyDAO.read(1L);

        Set<Developer> developers = new HashSet<Developer>();
        Set<Skill> skills = new HashSet<Skill>();
        Set<Project> projects = new HashSet<Project>();
        Set<Customer> customers = new HashSet<Customer>();
        Set<Company> companies = new HashSet<Company>();

        skills.add(s);
        d.setSkills(skills);
        devDAO.update(d);

        developers.add(d);
        customers.add(cu);
        companies.add(co);

        p.setCompanies(companies);
        p.setCustomers(customers);
        p.setDevelopers(developers);

        projectDAO.update(p);


        System.exit(0);

    }
}
