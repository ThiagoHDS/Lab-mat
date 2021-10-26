CREATE DATABASElabmat;
USE labmat;

CREATE TABLE IF NOT EXISTS `labmat`.`usuarios` (
  `nome` VARCHAR(45) NOT NULL,
  `matricula` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`matricula`));
  
  CREATE TABLE IF NOT EXISTS `labmat`.`objetos` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `dirManual` VARCHAR(45) NULL,
  `disponivel` TINYINT NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE IF NOT EXISTS `labmat`.`emprestimos` (
  `id` INT NOT NULL,
  `DataInicio` DATE NOT NULL,
  `DataFim` DATE NOT NULL,
  `idObjeto` INT NOT NULL,
  `idUsuario` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Emprestimo_Objeto_idx` (`idObjeto` ASC) VISIBLE,
  INDEX `fk_Emprestimo_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Emprestimo_Objeto`
    FOREIGN KEY (`idObjeto`)
    REFERENCES `labmat`.`objetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emprestimo_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `labmat`.`usuarios` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
    
CREATE TABLE IF NOT EXISTS `labmat`.`funcionarios` (
  `codigo` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`));
  
  CREATE TABLE IF NOT EXISTS `labmat`.`logins` (
  `username` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `idFuncionario` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `fk_Login_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `labmat`.`funcionarios` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
  
