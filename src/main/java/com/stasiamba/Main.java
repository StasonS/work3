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

        Set<Developer> developers = new HashSet<Developer>();
        Set<Skill> skills = new HashSet<Skill>();
        Set<Project> projects = new HashSet<Project>();
        Set<Customer> customers = new HashSet<Customer>();
        Set<Company> companies = new HashSet<Company>();

//        Project project = (Project) projectDAO.read(2L);
//        Customer customer = new Customer("Jora");
//        customers.add(customer);
//        project.setCustomers(customers);

//        projectDAO.update(project);


        System.out.println(devDAO.read(2L));



//        System.out.println(projectDAO.getAll());

        System.exit(0);

    }
}
