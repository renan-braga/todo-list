# Todo List Application

## Overview

This application is a basic todo list management system where a user can add, edit, delete and view their tasks. The project is developed in Java, using the Servlet API, JSP for views, MySQL for the database, and a touch of encryption and validation for user data.

## Installation

### Prerequisites

- Java 8 or higher
- Apache Tomcat or any similar servlet container
- MySQL Server
- Maven (for dependency management)

### Instructions

1. Clone the repository.
2. Navigate to the root directory of the project.
3. Run the SQL script provided in the repository to set up the database and seed it with initial data.
4. Edit the `DatabaseConnection` class in `br.com.ifsp.todolist.database` package with your MySQL credentials.
5. Compile the project with Maven using `mvn clean install`.
6. Deploy the generated WAR file in your servlet container.

## Usage

After successful deployment, the application is ready to use. You can access the login page at `{your server address}/login.jsp`.

## Features

- **User Registration**: A new user can register using the `usuario-cadastro.jsp` page, providing a login, name, email, and password.
- **User Login**: Existing users can login using the `login.jsp` page.
- **Task Management**: After logging in, a user will be redirected to the `principal.jsp` page where they can see their tasks and perform operations like adding, editing, or deleting tasks.

The following views are available for the task operations:
- Add Task: `tarefa-adicao.jsp`
- Edit Task: `tarefa-edicao.jsp`
- Delete Task: `tarefa-excluir.jsp`

## Models

The application uses two main entities:
- `Usuario`: Represents a user with properties such as login, name, email, and password.
- `Tarefa`: Represents a task with properties such as title, description, creation date, completion date, status, and the user it belongs to.

## Utilities

- `PasswordEncryptor`: A utility class to encrypt passwords using SHA-256 algorithm before storing them in the database.
- `Validator`: A utility class to validate user inputs like email, password, name, and login.

---

Please adjust and expand the content as needed, for example, by providing more details about your application's features, or specific installation steps for your environment.
