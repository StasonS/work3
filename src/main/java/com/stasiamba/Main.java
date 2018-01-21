package com.stasiamba;

import com.stasiamba.dao.GenericDAO;
import com.stasiamba.dao.hibernate.ModelDAO;
import com.stasiamba.model.Developer;

public class Main {

    public static void main(String[] args) {

//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Developer developer = new Developer("Ivan", "Ivanov", new BigDecimal(3000));
//        Skill skill1 = new Skill("Java");
//        Skill skill2 = new Skill("C++");
//
////        session.save(skill1);
////        session.save(skill2);
//
//        Set<Skill> skills = new HashSet<Skill>();
//        skills.add(session.get(Skill.class, 11L));
//        skills.add(session.get(Skill.class, 11L));
//        developer.setSkills(skills);
//
////        session.save(developer);
//
//        System.out.println(session.get(Developer.class, 12L));
//
////        System.out.println(new DeveloperDAOImpl().read(12L));
//
//
//
//        transaction.commit();
//        session.close();

//        GenericDAO developerDAO = new DeveloperDAOImpl();
//        GenericDAO skillDAO = new SkillDAOImpl();
//
//        Skill skill = (Skill) skillDAO.read(10L);
//        Set<Skill> skills = new HashSet<Skill>();
//        skills.add(skill);
//
//        List developers = developerDAO.getAll();
//
//        Developer d = (Developer) developers.get(1);
//        d.setSkills(skills);
//
//        developerDAO.update(d);


//        GenericDAO skillDao = new ModelDAO<Skill>(Skill.class);
//        System.out.println(skillDao.getAll());

//        Skill skill1 = new Skill("Pascal");
//        Developer dev1 = new Developer("Den", "Denisov", new BigDecimal(4500));
//        GenericDAO skillDao = new ModelDAO<Skill>(Skill.class);
//        Set<Developer> developers = new HashSet<Developer>();
//        developers.add(dev1);
//        skill1.setDevelopers(developers);

//        skillDao.create(skill1);

        GenericDAO devDAO = new ModelDAO<Developer>(Developer.class);
//        GenericDAO skillDAO = new ModelDAO<Skill>(Skill.class);
//        GenericDAO projectDAO = new ModelDAO<Project>(Project.class);
//        GenericDAO customerDAO = new ModelDAO<Customer>(Customer.class);
//        GenericDAO companyDAO = new ModelDAO<Company>(Company.class);

        System.out.println(devDAO.getAll());

//        Company c1 = new Company("Google");
//        Project p1 = (Project) projectDAO.read(1L);
//        Set<Project> projects = new HashSet<Project>();
//        projects.add(p1);
//        c1.setProjects(projects);
//
//        companyDAO.create(c1);


        System.exit(0);

    }
}
