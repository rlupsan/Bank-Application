INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('1', 'Roxana Lupsan', '297120155', 'CJ1774', '202 N. Brancusi St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('2', 'Danielle Michigan', '292060641', 'NY9012', '111 N. New York St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('3', 'Selena-Marie Gomez', '293010590', 'HW9888', '400 N. Hollywood St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('4', 'Radu Miguel', '197121331', 'SB1991', '40 N. Plopilor St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('5', 'Mark Lupsan', '168121788', 'CJ7871', '202 N. Brancusi St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('6', 'Diana NorthVille', '279080742', 'AM7611', '88 N. Andrei Muresanu St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('7', 'Julia Boston', '295090933', 'MN2121', '304 N. Nuferilor St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('8', 'Claire-Ann Bay', '297040422', 'HI7865', '209 N. Hilton St.');
INSERT INTO `bank`.`client` (`id`, `name`, `pnc`, `idCard`, `address`) VALUES ('9', 'Blaire Chuck', '290050511', 'GG4141', '66 N. Gossip Girl St.');


INSERT INTO `bank`.`account` (`id`, `type`, `amountMoney`, `dateCreation`, `clientId`) VALUES ('1', 'CURRENT', '417', '2019-03-30', '1');
INSERT INTO `bank`.`account` (`id`, `type`, `amountMoney`, `dateCreation`, `clientId`) VALUES ('2', 'CURRENT', '210', '2019-03-30', '6');
INSERT INTO `bank`.`account` (`id`, `type`, `amountMoney`, `dateCreation`, `clientId`) VALUES ('3', 'CURRENT', '310', '2019-03-30', '4');
INSERT INTO `bank`.`account` (`id`, `type`, `amountMoney`, `dateCreation`, `clientId`) VALUES ('4', 'DEPOSIT', '500', '2019-03-30', '5');
INSERT INTO `bank`.`account` (`id`, `type`, `amountMoney`, `dateCreation`, `clientId`) VALUES ('5', 'DEPOSIT', '180', '2019-03-31', '8');



INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('roxie97', 'salut', 'ADMIN');
INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('oana13', 'joja', 'USER');
INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('radu13', 'joja', 'USER');
INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('ovi_ovi', 'Salut21', 'ADMIN');
INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('test', 'test1L', 'USER');
INSERT INTO `bank`.`employee` (`username`, `password`, `role`) VALUES ('maria5', 'Marie00', 'USER');

INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-30', '1', '2', '8', 'transfer1');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-30', '1', '2', '5', 'transfer2');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-30', '1', '2', '7', 'mancare');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-30', '1', '2', '71', 'test');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-30', '2', '1', '321', 'apa si curent');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-31', '1', '1', '500', 'for bills');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-31', '2', '1', '200', 'holiday');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-31', '2', '1', '200', 'pet');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-03-31', '1', '2', '10', 'popcorn film');
INSERT INTO `bank`.`transaction` (`dateTransaction`, `fromAccount`, `toAccount`, `amountMoneyTransferred`, `description`) VALUES ('2019-04-01', '1', '3', '20', 'party suc');

