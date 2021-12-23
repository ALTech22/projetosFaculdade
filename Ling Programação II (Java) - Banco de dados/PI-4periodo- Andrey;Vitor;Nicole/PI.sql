-- ====================================================================================================================================
-- ========= INICIO DO SCRIPT DE GERAÇÃO DE BANCO DE DADOS DO PI ======================================================================
-- ====================================================================================================================================
-- ========= CRIAÇÃO E ABERTURA DO BANCO DE DADOS DO PI ===============================================================================
-- ====================================================================================================================================
-- ======================================== ANDREY HENRIQUE LOPES ZEFERINO ============================================================
-- ======================================== VITOR ARAUJO LINS =========================================================================
-- ======================================== NICOLE GUERREIRO DINIZ ====================================================================
-- ============================================== 10/24/2021 ==========================================================================
-- ====================================================================================================================================

DROP DATABASE IF EXISTS Instituto_Politecnico_Do_Norte;
CREATE DATABASE IF NOT EXISTS Instituto_Politecnico_Do_Norte;
USE Instituto_Politecnico_Do_Norte;

-- ================== CRIAÇÃO DAS TABELAS =============================================================================================

CREATE TABLE IF NOT EXISTS Escola(
	idEscola INTEGER,
    diretor VARCHAR(40) UNIQUE NOT NULL,
    nomeEscola VARCHAR(45) UNIQUE NOT NULL,
    PRIMARY KEY (idEscola)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Departamento(
	idDepartamento INTEGER,
    nomeDepartamento VARCHAR(45) UNIQUE NOT NULL,
    professorChefeDepartamento INTEGER UNIQUE,
    idEscola INTEGER,
    disciplinaEspecial VARCHAR(45) NOT NULL UNIQUE,
	PRIMARY KEY (idDepartamento)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Professor(
	idProfessor INTEGER,
    idDepartamento INTEGER NOT NULL,
    idDisciplina INTEGER NOT NULL,
    estaSobContratoDeInvestigacao BOOLEAN,
    nome VARCHAR(60) NOT NULL,
    CPF VARCHAR(11) UNIQUE NOT NULL,
	PRIMARY KEY (idProfessor)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Estudante(
	idEstudante INTEGER,
    idTurma INTEGER,
    idDepartamentoDisciplinaEspecial INTEGER NOT NULL,
    idProfessorTutor INTEGER NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    dataNasc DATE NOT NULL,
	PRIMARY KEY (idEstudante)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Disciplina(
	idDisciplina INTEGER,
    nomeDisciplina VARCHAR(45) NOT NULL,
    idDepartamento INTEGER NOT NULL,
	PRIMARY KEY (idDisciplina)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Estudante_Possue_Disciplina(
	Disciplina_idDisciplina INTEGER NOT NULL,
    Estudante_idEstudante INTEGER NOT NULL
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Turma(
	idTurma INTEGER,
    idDisciplina INTEGER,
	PRIMARY KEY (idTurma)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS Aula(
	idAula INTEGER,
    sala VARCHAR(45) NOT NULL,
    horario DATETIME NOT NULL UNIQUE,
    idTurma INTEGER NOT NULL,
	PRIMARY KEY (idAula)
) ENGINE InnoDB;

-- ====CRIAÇÃO DAS FOREIGN KEYS===
ALTER TABLE Departamento ADD CONSTRAINT fk_Departamento_Escola FOREIGN KEY (idEscola) REFERENCES Escola(idEscola),
	ADD CONSTRAINT fk_Departamento_Professor FOREIGN KEY (professorChefeDepartamento) REFERENCES Professor (idProfessor);
    
ALTER TABLE Professor ADD CONSTRAINT fk_Professor_Departamento FOREIGN KEY (idDepartamento) REFERENCES Departamento (idDepartamento),
	ADD CONSTRAINT fk_Professor_Disciplina FOREIGN KEY (idDisciplina) REFERENCES Disciplina (idDisciplina);
    
ALTER TABLE Disciplina ADD CONSTRAINT fk_Disciplina_Departamento FOREIGN KEY (idDepartamento) REFERENCES Departamento (idDepartamento);

ALTER TABLE Estudante ADD CONSTRAINT fk_Estudante_Turma FOREIGN KEY (idTurma) REFERENCES Turma (idTurma),
	ADD CONSTRAINT fk_Estudante_Departamento FOREIGN KEY (idDepartamentoDisciplinaEspecial) REFERENCES Departamento (idDepartamento),
    ADD CONSTRAINT fk_Estudante_Professor FOREIGN KEY (idProfessorTutor) REFERENCES Professor (idProfessor);

ALTER TABLE Estudante_Possue_Disciplina ADD CONSTRAINT fk_Estudante_Disciplina FOREIGN KEY (Estudante_idEstudante) REFERENCES Estudante (idEstudante),
	ADD CONSTRAINT fk_Disciplina_Estudante FOREIGN KEY (Disciplina_idDisciplina) REFERENCES Disciplina (idDisciplina);
    
ALTER TABLE Turma ADD CONSTRAINT fk_Turma_Disciplina FOREIGN KEY (idDisciplina) REFERENCES Disciplina (idDisciplina);

ALTER TABLE Aula ADD CONSTRAINT fk_Aula_Turma FOREIGN KEY (idTurma) REFERENCES Turma(idTurma);

-- ====CRIAÇÃO DE INDEX====
CREATE INDEX NOME_ALUNO ON Estudante (nome);
CREATE INDEX NOME_DISCIPLIA ON Disciplina (nomeDisciplina);
CREATE INDEX NOME_PROFESSOR ON Professor (nome);
CREATE INDEX NOME_DEPARTAMENTO ON Departamento (nomeDepartamento);

-- ====INSERÇÃO DE ALGUNS VALORES CONSTANTES====
INSERT INTO Escola VALUES (0, 'Edgar', 'Escola superior de informática'), (1, 'Barbara', 'Escola superior de ciências biológicas'), (2, 'Rodolfo', 'Escola superior de geografia');

INSERT INTO Departamento VALUES (0, 'Ciência da Computação', null, 0, 'Tópicos especiais em Ciência da Computação'), (1, 'Analise e desenvolvimento de sistemas', null, 0, 'Tópicos especiais em programação'); 
INSERT INTO Departamento VALUES(2, 'Biologia', null, 1, 'Tópicos especiais em Biologia'), (3, 'Botânica', null, 1, 'Tópicos especiais em Botânica');
INSERT INTO Departamento VALUES(4, 'Geografia', null, 2, 'Tópicos especiais em Geografia'), (5, 'Geopolítica', null, 2, 'Tópicos especiais em geopolitica');

INSERT INTO Disciplina VALUES (0, 'Linguagem de programação I', 0), (1, 'Engenharia de Software', 1), (2, 'Genética', 2), (3, 'Anatomia de plantas', 3), (4, 'Climatologia', 4), (5, 'Antropologia', 5);

INSERT INTO Professor VALUES(0, 0, 0, false, 'Camille', '11111111111'), (1, 1, 1, false, 'Marta', '22222222222'), 
	(2,2,2, false, 'Roberto', '33333333333'), (3,3,3, false, 'Marcelo', '44444444444'), (4,4,4, false, 'Julia', '55555555555'), (5,5,5, false, 'Laura', '66666666666');
    
UPDATE Departamento SET professorChefeDepartamento = 0 WHERE idDepartamento = 0;
UPDATE Departamento SET professorChefeDepartamento = 1 WHERE idDepartamento = 1;
UPDATE Departamento SET professorChefeDepartamento = 2 WHERE idDepartamento = 2;
UPDATE Departamento SET professorChefeDepartamento = 3 WHERE idDepartamento = 3;
UPDATE Departamento SET professorChefeDepartamento = 4 WHERE idDepartamento = 4;
UPDATE Departamento SET professorChefeDepartamento = 5 WHERE idDepartamento = 5;

INSERT INTO Turma VALUES (0, 0), (1,1), (2,2), (3,3), (4,4), (5,5);