-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 04-Jun-2019 às 22:48
-- Versão do servidor: 10.1.37-MariaDB
-- versão do PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gypsy`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_fechar` (`comanda` INT)  UPDATE tbl_pedido pedido INNER JOIN tbl_itens_pedido itens ON pedido.Num_Pedido = itens.Num_Pedido 
SET pedido.`Status` = 0 WHERE pedido.Num_Comanda = comanda AND pedido.`Status` = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_itens` (IN `prod` INT, IN `comanda` INT, IN `adic` INT, IN `locali` VARCHAR(50))  BEGIN
	DECLARE test BOOLEAN;
	SELECT EXISTS (SELECT * FROM tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND itens.Adicional = adic AND pedido.`Status` = 1) INTO test;
    IF NOT test THEN
    INSERT INTO tbl_itens_pedido (Cod_Prod,Num_Comanda,Adicional,Localidade,Qtde,`Data`) VALUES (prod,comanda,adic,locali,1,current_date());
    ELSE
    UPDATE tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    SET itens.Qtde = itens.Qtde + 1 
	WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND itens.Adicional = adic AND pedido.`Status` = 1;
    END IF;
    UPDATE tbl_relatorio_vendas SET Qtde_Vendido = Qtde_Vendido + 1 WHERE `Data` = current_date();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_itens_app` (`prod` INT, `comanda` INT, `adic` INT, `locali` VARCHAR(50), `valor` DOUBLE)  BEGIN
	DECLARE test BOOLEAN;
	SELECT EXISTS (SELECT * FROM tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND itens.Adicional = adic AND pedido.`Status` = 1) INTO test;
    IF NOT test THEN
    INSERT INTO tbl_itens_pedido (Cod_Prod,Num_Comanda,Adicional,Localidade,Qtde,`Data`) VALUES (prod,comanda,adic,locali,1,current_date());
    ELSE
    UPDATE tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    SET itens.Qtde = itens.Qtde + 1 
	WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND itens.Adicional = adic AND pedido.`Status` = 1;
    END IF;
    UPDATE tbl_relatorio_vendas SET Faturamento = Faturamento + valor, Qtde_Vendido = Qtde_Vendido + 1 WHERE `Data` = current_date();
	UPDATE tbl_pedido SET Valor_Total = Valor_Total + valor WHERE Num_Mesa = mesa AND `Status` = 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_itens_exc` (`prod` INT, `comanda` INT)  BEGIN
	DECLARE qtd INT;
    DECLARE uni DOUBLE;
	SELECT itens.Qtde FROM tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND pedido.`Status` = 1 INTO qtd;
    IF qtd > 1 THEN
    UPDATE tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    SET itens.Qtde = itens.Qtde - 1 
	WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND pedido.`Status` = 1;
    ELSE
    DELETE itens.* FROM tbl_itens_pedido itens 
	INNER JOIN tbl_pedido pedido ON itens.Num_Pedido = pedido.Num_Pedido 
    WHERE itens.Cod_Prod = prod AND itens.Num_Comanda = comanda AND pedido.`Status` = 1;
    END IF;
    SELECT Valor FROM tbl_produto WHERE Cod_Prod = prod INTO uni;
    UPDATE tbl_pedido SET Valor_Total = Valor_Total - uni 
	WHERE Num_Comanda = comanda AND `Status` = 1;
    UPDATE tbl_relatorio_vendas SET Qtde_Vendido = Qtde_Vendido - 1, Faturamento = Faturamento - uni 
	WHERE `Data` = current_date();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pedido` (`locali` VARCHAR(50), `comanda` INT, `vtotal` DOUBLE, `observ` VARCHAR(200))  BEGIN
	DECLARE test BOOLEAN;
	SELECT EXISTS (SELECT * FROM tbl_pedido WHERE Num_Comanda = comanda AND `Status` = 1) INTO test;
    IF NOT test THEN
    INSERT INTO tbl_pedido (Localidade,Num_Comanda,`Data`,Valor_Total,`Status`,Observacao) VALUES (locali,comanda,current_date(),vtotal,1,observ);
	SELECT 'Refresh' AS Att;
    ELSE
    UPDATE tbl_pedido SET Valor_Total = Valor_Total + vtotal, Observacao = observ WHERE Num_Comanda = comanda AND `Status` = 1;
	SELECT 'Atualizada' AS Att;
    END IF;
    UPDATE tbl_relatorio_vendas SET Faturamento = Faturamento + vtotal WHERE `Data` = current_date();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pegar_itens` (IN `comanda` INT)  SELECT prod.Cod_Prod, prod.Nome, prod.Valor, itens.Qtde FROM vw_produtos prod INNER JOIN vw_itens_pedido itens ON prod.Cod_Prod = itens.Cod_Prod
INNER JOIN vw_pedido pedido ON pedido.Num_Pedido = itens.Num_Pedido
WHERE itens.Num_Comanda = comanda AND pedido.Status = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pegar_itens_adic` (IN `comanda` INT)  SELECT itens.Qtde, prod.Cod_Prod, prod.Nome, adic.Adic_Nome, prod.Valor, adic.Adic_Valor FROM vw_produtos prod INNER JOIN vw_itens_pedido itens ON prod.Cod_Prod = itens.Cod_Prod
INNER JOIN vw_pedido pedido ON pedido.Num_Pedido = itens.Num_Pedido INNER JOIN vw_adicionais adic ON adic.Cod_Adic = itens.Adicional
WHERE itens.Num_Comanda = comanda AND pedido.Status = 1 ORDER BY adic.Adic_Nome$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_adicionais`
--

CREATE TABLE `tbl_adicionais` (
  `Cod_Adic` int(11) NOT NULL,
  `Adic_Nome` varchar(50) NOT NULL,
  `Produto` varchar(50) NOT NULL,
  `Adic_Valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_adicionais`
--

INSERT INTO `tbl_adicionais` (`Cod_Adic`, `Adic_Nome`, `Produto`, `Adic_Valor`) VALUES
(1, 'Sem Adicionais', '', 0),
(2, 'Bacon', 'Batata', 2),
(3, 'Cheddar', 'Batata', 2),
(4, 'Xablinho', 'Xablau', 50);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_itens_pedido`
--

CREATE TABLE `tbl_itens_pedido` (
  `Cod_Prod` int(11) NOT NULL,
  `Num_Pedido` int(11) NOT NULL,
  `Num_Comanda` int(11) NOT NULL,
  `Adicional` int(11) NOT NULL,
  `Localidade` varchar(50) NOT NULL,
  `Qtde` int(11) NOT NULL,
  `Data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_itens_pedido`
--

INSERT INTO `tbl_itens_pedido` (`Cod_Prod`, `Num_Pedido`, `Num_Comanda`, `Adicional`, `Localidade`, `Qtde`, `Data`) VALUES
(1, 1, 1, 1, 'Área Externa', 7, '2019-05-20'),
(1, 5, 3, 1, 'Balcão', 3, '2019-06-04'),
(2, 1, 1, 2, 'Área Externa', 2, '2019-05-20'),
(2, 1, 1, 3, 'Área Externa', 2, '2019-05-20'),
(2, 5, 3, 2, 'Localidade', 1, '2019-06-04'),
(2, 5, 3, 3, 'Localidade', 1, '2019-06-04'),
(5, 6, 5, 1, 'Localidade', 82, '2019-06-04');

--
-- Acionadores `tbl_itens_pedido`
--
DELIMITER $$
CREATE TRIGGER `tgr_att_itens` BEFORE INSERT ON `tbl_itens_pedido` FOR EACH ROW BEGIN
    SET NEW.Num_Pedido = (SELECT Num_Pedido FROM tbl_pedido WHERE Num_Comanda = NEW.Num_Comanda AND `Status` = 1);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_login`
--

CREATE TABLE `tbl_login` (
  `Cod_Log` int(11) NOT NULL,
  `User` varchar(50) NOT NULL,
  `Pass` varchar(20) NOT NULL,
  `Access` smallint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_login`
--

INSERT INTO `tbl_login` (`Cod_Log`, `User`, `Pass`, `Access`) VALUES
(1, 'admin', '123', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_pedido`
--

CREATE TABLE `tbl_pedido` (
  `Num_Pedido` int(11) NOT NULL,
  `Localidade` varchar(50) NOT NULL,
  `Num_Comanda` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Valor_Total` double NOT NULL,
  `Valor_Pago` double NOT NULL DEFAULT '0',
  `Status` smallint(1) NOT NULL,
  `Observacao` varchar(200) NOT NULL,
  `FK_Codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_pedido`
--

INSERT INTO `tbl_pedido` (`Num_Pedido`, `Localidade`, `Num_Comanda`, `Data`, `Valor_Total`, `Valor_Pago`, `Status`, `Observacao`, `FK_Codigo`) VALUES
(1, 'Área Externa', 1, '2019-05-20', 140, 0, 1, 'Sem Observações', 5),
(5, 'Balcão', 3, '2019-06-04', 0, 0, 1, 'Sem Observações', 6),
(6, 'Localidade', 5, '2019-06-04', 0, 0, 0, 'Sem Observações', 6);

--
-- Acionadores `tbl_pedido`
--
DELIMITER $$
CREATE TRIGGER `tgr_gera_relatorio` BEFORE INSERT ON `tbl_pedido` FOR EACH ROW BEGIN
	DECLARE test BOOLEAN;
	SELECT EXISTS (SELECT * FROM tbl_relatorio_vendas WHERE `Data` = current_date()) INTO test;
	IF NOT test THEN
    INSERT INTO tbl_relatorio_vendas (`Data`,Faturamento,Qtde_Vendido) VALUES (current_date(),0,0);
	END IF;
    SET NEW.FK_Codigo = (SELECT Codigo FROM tbl_relatorio_vendas WHERE `Data` = current_date());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_produto`
--

CREATE TABLE `tbl_produto` (
  `Cod_Prod` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `Valor` double NOT NULL,
  `Adicionais` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_produto`
--

INSERT INTO `tbl_produto` (`Cod_Prod`, `Nome`, `Tipo`, `Valor`, `Adicionais`) VALUES
(1, 'Hamburguer', 'Lanches', 25, 0),
(2, 'Batata', 'Porcoes', 8, 1),
(3, 'Xablau', 'Lanches', 45, 1),
(4, 'Cerveja', 'Bebidas', 234, 0),
(5, 'Teste', 'Porcoes', 123, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_relatorio_vendas`
--

CREATE TABLE `tbl_relatorio_vendas` (
  `Codigo` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Faturamento` double NOT NULL,
  `Qtde_Vendido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_relatorio_vendas`
--

INSERT INTO `tbl_relatorio_vendas` (`Codigo`, `Data`, `Faturamento`, `Qtde_Vendido`) VALUES
(5, '2019-05-20', 140, 8),
(6, '2019-06-04', 10256, 90);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_adicionais`
-- (See below for the actual view)
--
CREATE TABLE `vw_adicionais` (
`Cod_Adic` int(11)
,`Adic_Nome` varchar(50)
,`Produto` varchar(50)
,`Adic_Valor` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_itens_pedido`
-- (See below for the actual view)
--
CREATE TABLE `vw_itens_pedido` (
`Cod_Prod` int(11)
,`Num_Pedido` int(11)
,`Num_Comanda` int(11)
,`Adicional` int(11)
,`Localidade` varchar(50)
,`Qtde` int(11)
,`Data` date
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_login`
-- (See below for the actual view)
--
CREATE TABLE `vw_login` (
`Cod_Log` int(11)
,`User` varchar(50)
,`Pass` varchar(20)
,`Access` smallint(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_pedido`
-- (See below for the actual view)
--
CREATE TABLE `vw_pedido` (
`Num_Pedido` int(11)
,`Localidade` varchar(50)
,`Num_Comanda` int(11)
,`Data` date
,`Valor_Total` double
,`Valor_Pago` double
,`Status` smallint(1)
,`Observacao` varchar(200)
,`FK_Codigo` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_produtos`
-- (See below for the actual view)
--
CREATE TABLE `vw_produtos` (
`Cod_Prod` int(11)
,`Nome` varchar(50)
,`Tipo` varchar(50)
,`Valor` double
,`Adicionais` tinyint(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_relatorio_vendas`
-- (See below for the actual view)
--
CREATE TABLE `vw_relatorio_vendas` (
`Codigo` int(11)
,`Data` date
,`Faturamento` double
,`Qtde_Vendido` int(11)
);

-- --------------------------------------------------------

--
-- Structure for view `vw_adicionais`
--
DROP TABLE IF EXISTS `vw_adicionais`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_adicionais`  AS  select `tbl_adicionais`.`Cod_Adic` AS `Cod_Adic`,`tbl_adicionais`.`Adic_Nome` AS `Adic_Nome`,`tbl_adicionais`.`Produto` AS `Produto`,`tbl_adicionais`.`Adic_Valor` AS `Adic_Valor` from `tbl_adicionais` ;

-- --------------------------------------------------------

--
-- Structure for view `vw_itens_pedido`
--
DROP TABLE IF EXISTS `vw_itens_pedido`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_itens_pedido`  AS  select `tbl_itens_pedido`.`Cod_Prod` AS `Cod_Prod`,`tbl_itens_pedido`.`Num_Pedido` AS `Num_Pedido`,`tbl_itens_pedido`.`Num_Comanda` AS `Num_Comanda`,`tbl_itens_pedido`.`Adicional` AS `Adicional`,`tbl_itens_pedido`.`Localidade` AS `Localidade`,`tbl_itens_pedido`.`Qtde` AS `Qtde`,`tbl_itens_pedido`.`Data` AS `Data` from `tbl_itens_pedido` ;

-- --------------------------------------------------------

--
-- Structure for view `vw_login`
--
DROP TABLE IF EXISTS `vw_login`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_login`  AS  select `tbl_login`.`Cod_Log` AS `Cod_Log`,`tbl_login`.`User` AS `User`,`tbl_login`.`Pass` AS `Pass`,`tbl_login`.`Access` AS `Access` from `tbl_login` ;

-- --------------------------------------------------------

--
-- Structure for view `vw_pedido`
--
DROP TABLE IF EXISTS `vw_pedido`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_pedido`  AS  select `tbl_pedido`.`Num_Pedido` AS `Num_Pedido`,`tbl_pedido`.`Localidade` AS `Localidade`,`tbl_pedido`.`Num_Comanda` AS `Num_Comanda`,`tbl_pedido`.`Data` AS `Data`,`tbl_pedido`.`Valor_Total` AS `Valor_Total`,`tbl_pedido`.`Valor_Pago` AS `Valor_Pago`,`tbl_pedido`.`Status` AS `Status`,`tbl_pedido`.`Observacao` AS `Observacao`,`tbl_pedido`.`FK_Codigo` AS `FK_Codigo` from `tbl_pedido` ;

-- --------------------------------------------------------

--
-- Structure for view `vw_produtos`
--
DROP TABLE IF EXISTS `vw_produtos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_produtos`  AS  select `tbl_produto`.`Cod_Prod` AS `Cod_Prod`,`tbl_produto`.`Nome` AS `Nome`,`tbl_produto`.`Tipo` AS `Tipo`,`tbl_produto`.`Valor` AS `Valor`,`tbl_produto`.`Adicionais` AS `Adicionais` from `tbl_produto` ;

-- --------------------------------------------------------

--
-- Structure for view `vw_relatorio_vendas`
--
DROP TABLE IF EXISTS `vw_relatorio_vendas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_relatorio_vendas`  AS  select `tbl_relatorio_vendas`.`Codigo` AS `Codigo`,`tbl_relatorio_vendas`.`Data` AS `Data`,`tbl_relatorio_vendas`.`Faturamento` AS `Faturamento`,`tbl_relatorio_vendas`.`Qtde_Vendido` AS `Qtde_Vendido` from `tbl_relatorio_vendas` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_adicionais`
--
ALTER TABLE `tbl_adicionais`
  ADD PRIMARY KEY (`Cod_Adic`),
  ADD KEY `idx_produto_adic` (`Produto`);

--
-- Indexes for table `tbl_itens_pedido`
--
ALTER TABLE `tbl_itens_pedido`
  ADD PRIMARY KEY (`Cod_Prod`,`Num_Pedido`,`Num_Comanda`,`Adicional`),
  ADD KEY `FK_Num_Pedido` (`Num_Pedido`),
  ADD KEY `idx_local_itens` (`Localidade`),
  ADD KEY `idx_comanda_itens` (`Num_Comanda`);

--
-- Indexes for table `tbl_login`
--
ALTER TABLE `tbl_login`
  ADD PRIMARY KEY (`Cod_Log`);

--
-- Indexes for table `tbl_pedido`
--
ALTER TABLE `tbl_pedido`
  ADD PRIMARY KEY (`Num_Pedido`),
  ADD KEY `FK_Codigo` (`FK_Codigo`),
  ADD KEY `idx_localidade` (`Localidade`),
  ADD KEY `idx_num_comanda` (`Num_Comanda`);

--
-- Indexes for table `tbl_produto`
--
ALTER TABLE `tbl_produto`
  ADD PRIMARY KEY (`Cod_Prod`),
  ADD KEY `idx_produto_nome` (`Nome`);

--
-- Indexes for table `tbl_relatorio_vendas`
--
ALTER TABLE `tbl_relatorio_vendas`
  ADD PRIMARY KEY (`Codigo`),
  ADD UNIQUE KEY `Data` (`Data`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_adicionais`
--
ALTER TABLE `tbl_adicionais`
  MODIFY `Cod_Adic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_login`
--
ALTER TABLE `tbl_login`
  MODIFY `Cod_Log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_pedido`
--
ALTER TABLE `tbl_pedido`
  MODIFY `Num_Pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_produto`
--
ALTER TABLE `tbl_produto`
  MODIFY `Cod_Prod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_relatorio_vendas`
--
ALTER TABLE `tbl_relatorio_vendas`
  MODIFY `Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tbl_itens_pedido`
--
ALTER TABLE `tbl_itens_pedido`
  ADD CONSTRAINT `FK_Cod_Prod` FOREIGN KEY (`Cod_Prod`) REFERENCES `tbl_produto` (`Cod_Prod`),
  ADD CONSTRAINT `FK_Num_Pedido` FOREIGN KEY (`Num_Pedido`) REFERENCES `tbl_pedido` (`Num_Pedido`);

--
-- Limitadores para a tabela `tbl_pedido`
--
ALTER TABLE `tbl_pedido`
  ADD CONSTRAINT `FK_Codigo` FOREIGN KEY (`FK_Codigo`) REFERENCES `tbl_relatorio_vendas` (`Codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
