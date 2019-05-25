DROP DATABASE IF EXISTS ecommerce;

CREATE DATABASE ecommerce CHARACTER SET UTF8 COLLATE utf8_bin;

USE ecommerce;

CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `endereco` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `enderecos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint(20) NOT NULL,
  `cep` varchar(8) COLLATE utf8_bin NOT NULL,
  `logradouro` varchar(120) COLLATE utf8_bin NOT NULL,
  `numero` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `bairro` varchar(120) COLLATE utf8_bin NOT NULL,
  `complemento` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `estado_id` int(11) NOT NULL,
  `municipio` varchar(120) COLLATE utf8_bin NOT NULL,
  `endereco_principal` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `enderecos_clientes_FK` (`cliente_id`),
  CONSTRAINT `enderecos_clientes_FK` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin

CREATE TABLE `vendedores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `departamento` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `perc_comissao` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria_id` bigint(20) NOT NULL,
  `descricao` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `quantidade` int(11) DEFAULT 0,
  `preco` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `produtos_categorias_FK` (`categoria_id`),
  CONSTRAINT `produtos_categorias_FK` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vendedor_id` bigint(20) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `data_pedido` date DEFAULT NULL,
  `situacao` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pedidos_vendedores_FK` (`vendedor_id`),
  KEY `pedidos_clientes_FK` (`cliente_id`),
  CONSTRAINT `pedidos_clientes_FK` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `pedidos_vendedores_FK` FOREIGN KEY (`vendedor_id`) REFERENCES `vendedores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `itens_pedidos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor_item` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `itens_pedidos_pedidos_FK` (`pedido_id`),
  KEY `itens_pedidos_produtos_FK` (`produto_id`),
  CONSTRAINT `itens_pedidos_pedidos_FK` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
  CONSTRAINT `itens_pedidos_produtos_FK` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

