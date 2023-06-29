CREATE DATABASE todo;
USE todo;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50),
    nome VARCHAR(50),
    email VARCHAR(50),
    senha VARCHAR(250)
);

ALTER TABLE usuario MODIFY COLUMN senha VARCHAR(250);

INSERT INTO usuario (login, nome, email, senha) VALUES (
                            'braga',
                            'Renan Braga',
                            'renan.braga@ifsp.com.br',
                            'braga'
                           );

CREATE TABLE tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50),
    descricao VARCHAR(50),
    data_criacao TIMESTAMP,
    data_conclusao TIMESTAMP,
    status VARCHAR(50),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE
);

INSERT INTO tarefa (titulo, descricao, data_criacao, status, user_id)
VALUES (
           'Tarefa exemplo 2',
           'Descricao Tarefa Exemplo 2',
           NOW(),
           'Aberto',
           1
       );