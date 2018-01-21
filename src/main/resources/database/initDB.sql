CREATE TABLE IF NOT EXISTS developers(
  developer_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL ,
  last_name VARCHAR(50) NOT NULL ,
  salary DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS skills(
  skill_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  skill_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS projects(
  project_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  project_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS companies(
  company_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  company_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers(
  customer_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  customer_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS dev_skill (
  developer_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,

  FOREIGN KEY (developer_id) REFERENCES developers(developer_id),
  FOREIGN KEY (skill_id) REFERENCES skills(skill_id),

  UNIQUE (developer_id, skill_id)
);

CREATE TABLE IF NOT EXISTS project_developer (
  project_id BIGINT NOT NULL,
  developer_id BIGINT NOT NULL,

  FOREIGN KEY (project_id) REFERENCES projects(project_id),
  FOREIGN KEY (developer_id) REFERENCES developers(developer_id),

  UNIQUE (project_id, developer_id)
);

CREATE TABLE IF NOT EXISTS company_projects (
  company_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,

  FOREIGN KEY (company_id) REFERENCES companies(company_id),
  FOREIGN KEY (project_id) REFERENCES projects(project_id),

  UNIQUE (company_id, project_id)
);

CREATE TABLE IF NOT EXISTS customer_project (
  customer_id BIGINT NOT NULL,
  project_id  BIGINT NOT NULL,

  FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
  FOREIGN KEY (project_id) REFERENCES projects (project_id),

  UNIQUE (customer_id, project_id)
);