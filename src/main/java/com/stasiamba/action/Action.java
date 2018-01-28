package com.stasiamba.action;

import com.stasiamba.dao.GenericDAO;
import com.stasiamba.dao.hibernate.ModelDAO;
import com.stasiamba.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Action {

    static {
        System.out.println("Hello!");
    }

    private static boolean exit = false;
    private String action;
    private String modelName;
    private String parameterName;
    private GenericDAO activeDAO;
    private GenericDAO parameterDAO;

    public void act() throws IOException, IllegalAccessException, InstantiationException {

        activeDAO = modelChooser();

        while (!exit){

            actionChooser();

            if (action.equals("1")){
                create();

            } else if (action.equals("2")){
                read();

            } else if (action.equals("3")){
                update();

            } else if (action.equals("4")){
                delete();

            } else if (action.equals("5")){
                getAll();

            } else if (action.equals("6")){
                addParameter();

            } else if (action.equals("7")){
                changeActiveModel();

            } else if (action.equals("8")){
                exit = true;
                System.out.println("Have a good day");
            }

        }


    }

    private void changeActiveModel() throws IllegalAccessException, IOException, InstantiationException {
        act();
    }

    private void addParameter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Choose an ID of " + activeDAO.getType().getSimpleName() + ":");
        String modelID = reader.readLine();
        String s;

        System.out.println("Choose parameter you want to add");
        if (activeDAO.getType().getSimpleName().equals(Developer.class.getSimpleName()) ){
            Developer developer = (Developer) activeDAO.read(Long.parseLong(modelID));

            System.out.println("1 - Skill\n" +
                    "2 - Project\n" +
                    "Choose a number");
            s = reader.readLine();

            if (Objects.equals(s, "1")){
                System.out.print("Choose a number of skill: ");
                s = reader.readLine();
                Skill skill = new ModelDAO<Skill>(Skill.class).read(Long.parseLong(s));
                Set<Skill> set = new HashSet<>();
                set.add(skill);
                developer.setSkills(set);
                activeDAO.update(developer);

            } else if (Objects.equals(s, "2")){
                System.out.print("Choose a number of project: ");
                s = reader.readLine();
                Project project = new ModelDAO<Project>(Project.class).read(Long.parseLong(s));
                Set<Project> set = new HashSet<>();
                set.add(project);
                developer.setProjects(set);
                activeDAO.update(developer);

            } else {
                System.out.println("Wrong number");
            }

            actionChooser();

        } else if (activeDAO.getType().getSimpleName().equals(Skill.class.getSimpleName()) ){

            Skill skill = (Skill) activeDAO.read(Long.parseLong(modelID));

            System.out.println("1 - Developer\nChoose a number");
            s = reader.readLine();

            System.out.print("Choose a number of developer: ");
            s = reader.readLine();
            if (s == "1") {
                Developer developer = new ModelDAO<Developer>(Developer.class).read(Long.parseLong(s));
                Set<Developer> set = new HashSet<>();
                set.add(developer);
                skill.setDevelopers(set);
                activeDAO.update(skill);
            } else System.out.println("Wrong number");

            actionChooser();

        } else if (activeDAO.getType().getSimpleName().equals(Project.class.getSimpleName()) ){

            Project project = (Project) activeDAO.read(Long.parseLong(modelID));

            System.out.println("1 - Developer\n" +
                    "2 - Customer\n" +
                    "3 - Company\n" +
                    "Choose a number");
            s = reader.readLine();

            if (Objects.equals(s, "2")){
                Customer customer = new ModelDAO<Customer>(Customer.class).read(Long.parseLong(s));
                Set<Customer> set = new HashSet<>();
                set.add(customer);
                project.setCustomers(set);

            } else if (Objects.equals(s, "1")){
                Developer developer = new ModelDAO<Developer>(Developer.class).read(Long.parseLong(s));
                Set<Developer> set = new HashSet<>();
                set.add(developer);
                project.setDevelopers(set);

            } else if (Objects.equals(s, "3")){

                Company company = new ModelDAO<Company>(Company.class).read(Long.parseLong(s));
                Set<Company> set = new HashSet<>();
                set.add(company);
                project.setCompanies(set);

            } else System.out.println("Wrong number");

            activeDAO.update(project);
            actionChooser();

        } else if (activeDAO.getType().getSimpleName().equals(Customer.class.getSimpleName()) ){

            Customer customer = (Customer) activeDAO.read(Long.parseLong(modelID));

            System.out.println("1 - Project\nChoose a number");
            s = reader.readLine();

            System.out.print("Choose a number of project: ");
            s = reader.readLine();
            if (Objects.equals(s, "1")) {
                Project project = new ModelDAO<Project>(Project.class).read(Long.parseLong(s));
                Set<Project> set = new HashSet<>();
                set.add(project);
                customer.setProjects(set);
                activeDAO.update(customer);
            } else System.out.println("Wrong number");

            actionChooser();

        } else if (activeDAO.getType().getSimpleName().equals(Company.class.getSimpleName()) ){

            Company customer = (Company) activeDAO.read(Long.parseLong(modelID));

            System.out.println("1 - Project\nChoose a number");
            s = reader.readLine();

            System.out.print("Choose a number of project: ");
            s = reader.readLine();
            if (Objects.equals(s, "1")) {
                Project project = new ModelDAO<Project>(Project.class).read(Long.parseLong(s));
                Set<Project> set = new HashSet<>();
                set.add(project);
                customer.setProjects(set);
                activeDAO.update(customer);
            } else System.out.println("Wrong number");

            actionChooser();

        }
    }

    private void getAll() {
        System.out.println(activeDAO.getAll());
        actionChooser();
    }

    private void delete() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Select ID of model to delete: ");
        String s = reader.readLine();
        Long id = Long.parseLong(s);
        activeDAO.delete(activeDAO.read(id));
        actionChooser();

    }

    private void update() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Select ID of model to update: ");
        String s = reader.readLine();

        if (activeDAO.getType().getSimpleName().equals(Developer.class.getSimpleName()) ){

            Developer developer = (Developer) activeDAO.read(Long.parseLong(s));

            System.out.print("What do you want to update:\n" +
                    "1 - First name?\n" +
                    "2 - Last name?\n" +
                    "3 - Salary?\n" +
                    "Choose the number: ");
            s = reader.readLine();

            if (Objects.equals(s, "1")){
                System.out.print("Enter a new Name: ");
                s = reader.readLine();
                developer.setFirstName(s);
            } else if (Objects.equals(s, "2")){
                System.out.print("Enter a new Last Name: ");
                s = reader.readLine();
                developer.setLastName(s);
            } else if (Objects.equals(s, "3")){
                System.out.print("Enter a new Name: ");
                s = reader.readLine();
                BigDecimal b = BigDecimal.valueOf(Long.parseLong(s));
                developer.setSalary(b);
            } else {
                System.out.println("Wrong number!");
                update();
            }
            activeDAO.update(developer);

        } else if (activeDAO.getType().getSimpleName().equals(Skill.class.getSimpleName())){
            Skill skill = (Skill) activeDAO.read(Long.parseLong(s));
            System.out.print("Enter a new name of " + skill.getName() + ":");
            skill.setName(reader.readLine());
            activeDAO.update(skill);

        } else if (activeDAO.getType().getSimpleName().equals(Project.class.getSimpleName())){
            Project project = (Project) activeDAO.read(Long.parseLong(s));
            System.out.print("Enter a new name of " + project.getName() + ":");
            project.setName(reader.readLine());
            activeDAO.update(project);

        } else if (activeDAO.getType().getSimpleName().equals(Customer.class.getSimpleName())){
            Customer customer = (Customer) activeDAO.read(Long.parseLong(s));
            System.out.print("Enter a new name of " + customer.getName() + ":");
            customer.setName(reader.readLine());
            activeDAO.update(customer);

        } else if (activeDAO.getType().getSimpleName().equals(Company.class.getSimpleName())){
            Company company = (Company) activeDAO.read(Long.parseLong(s));
            System.out.print("Enter a new name of " + company.getName() + ":");
            company.setName(reader.readLine());
            activeDAO.update(company);

        }

        actionChooser();
    }

    private void read() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter ID of " + activeDAO.getType().getSimpleName() + ": ");
        String s = reader.readLine();
        Long id = Long.parseLong(s);

        System.out.println(activeDAO.read(id));
    }

    private void create() throws IllegalAccessException, InstantiationException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (activeDAO.getType().newInstance() instanceof Developer){
            Developer developer = (Developer) activeDAO.getType().newInstance();

            System.out.print("Enter developer's first name: ");
            String firstName = reader.readLine();

            System.out.print("Enter developer's last name: ");
            String lastName = reader.readLine();

            System.out.print("Enter developer's salary: ");
            String salary = reader.readLine();
            Double d = Double.parseDouble(salary);
            BigDecimal salaryBDec = BigDecimal.valueOf(d);

            developer.setFirstName(firstName);
            developer.setLastName(lastName);
            developer.setSalary(salaryBDec);

            activeDAO.create(developer);

            actionChooser();

        } else if (activeDAO.getType().newInstance() instanceof Skill){
            Skill skill = (Skill) activeDAO.getType().newInstance();

            System.out.print("Enter a skill name: ");
            String name = reader.readLine();
            skill.setName(name);

            activeDAO.create(skill);

        } else if (activeDAO.getType().newInstance() instanceof Project){
            Project project = (Project) activeDAO.getType().newInstance();

            System.out.print("Enter a project name: ");
            String name = reader.readLine();
            project.setName(name);

            activeDAO.create(project);

        } else if (activeDAO.getType().newInstance() instanceof Customer){
            Customer customer = (Customer) activeDAO.getType().newInstance();

            System.out.print("Enter a customer name: ");
            String name = reader.readLine();
            customer.setName(name);

            activeDAO.create(customer);

        } else if (activeDAO.getType().newInstance() instanceof Company){
            Company company = (Company) activeDAO.getType().newInstance();

            System.out.print("Enter a company name: ");
            String name = reader.readLine();
            company.setName(name);

            activeDAO.create(company);

        }
    }

    private GenericDAO modelChooser(){

        System.out.println("Choose the object you want to work with:\n" +
                "- Developer\n" +
                "- Skill\n" +
                "- Project\n" +
                "- Customer\n" +
                "- Company\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ModelDAO dao = null;

        try {
            switch (reader.readLine()){
                case "Developer":
                    dao = new ModelDAO<Developer>(Developer.class);
                    break;
                case "Skill":
                    dao = new ModelDAO<Skill>(Skill.class);
                    break;
                case "Project":
                    dao = new ModelDAO<Project>(Project.class);
                    break;
                case "Customer":
                    dao = new ModelDAO<Customer>(Customer.class);
                    break;
                case "Company":
                    dao = new ModelDAO<Company>(Company.class);
                    break;
//                default:
//                    System.out.println("Wrong. Enter a name again");
//                    modelChooser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dao;
    }

    private void actionChooser(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Choose the action number please:\n" +
                "1 - Create\n" +
                "2 - Read\n" +
                "3 - Update\n" +
                "4 - Delete\n" +
                "5 - Get all\n" +
                "6 - Add parameter\n" +
                "7 - Change active model\n" +
                "8 - Exit program\n");

        try {
            action = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
