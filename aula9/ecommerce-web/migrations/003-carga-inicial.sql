SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE itens_pedidos;
TRUNCATE TABLE produtos;
TRUNCATE TABLE categorias;
TRUNCATE TABLE pedidos;
TRUNCATE TABLE clientes;
TRUNCATE TABLE vendedores;

SET FOREIGN_KEY_CHECKS = 1;

DELETE FROM categorias;
ALTER TABLE categorias AUTO_INCREMENT = 1;

INSERT INTO categorias (descricao) VALUES ('Acessórios de Informática');
INSERT INTO categorias (descricao) VALUES ('Agro, Indústria e Comércio');
INSERT INTO categorias (descricao) VALUES ('Alimentos e Bebidas');
INSERT INTO categorias (descricao) VALUES ('Ar condicionado e Aquecedores');
INSERT INTO categorias (descricao) VALUES ('Artesanato');
INSERT INTO categorias (descricao) VALUES ('Artigos de Festas');
INSERT INTO categorias (descricao) VALUES ('Automotivo');
INSERT INTO categorias (descricao) VALUES ('Bebês');
INSERT INTO categorias (descricao) VALUES ('Beleza e Perfumaria');
INSERT INTO categorias (descricao) VALUES ('Brinquedos');
INSERT INTO categorias (descricao) VALUES ('Cama, Mesa e Banho');
INSERT INTO categorias (descricao) VALUES ('Casa e Construção');
INSERT INTO categorias (descricao) VALUES ('Celulares e Smartphones');
INSERT INTO categorias (descricao) VALUES ('Câmeras Digitais e Filmadoras');
INSERT INTO categorias (descricao) VALUES ('Decoração');
INSERT INTO categorias (descricao) VALUES ('Eletrodomésticos');
INSERT INTO categorias (descricao) VALUES ('Eletroportáteis');
INSERT INTO categorias (descricao) VALUES ('Enfeites de Natal');
INSERT INTO categorias (descricao) VALUES ('Esporte e Lazer');
INSERT INTO categorias (descricao) VALUES ('Filmes e Séries');
INSERT INTO categorias (descricao) VALUES ('Games');
INSERT INTO categorias (descricao) VALUES ('Gift Card');
INSERT INTO categorias (descricao) VALUES ('Informática');
INSERT INTO categorias (descricao) VALUES ('Instrumentos Musicais');
INSERT INTO categorias (descricao) VALUES ('Livros');
INSERT INTO categorias (descricao) VALUES ('Livros Importados');
INSERT INTO categorias (descricao) VALUES ('Malas, Mochilas e Acessórios');
INSERT INTO categorias (descricao) VALUES ('Moda');
INSERT INTO categorias (descricao) VALUES ('Móveis');
INSERT INTO categorias (descricao) VALUES ('Música');
INSERT INTO categorias (descricao) VALUES ('PC Gamer');
INSERT INTO categorias (descricao) VALUES ('Papelaria');
INSERT INTO categorias (descricao) VALUES ('Pet Shop');
INSERT INTO categorias (descricao) VALUES ('Páscoa');
INSERT INTO categorias (descricao) VALUES ('Relógios');
INSERT INTO categorias (descricao) VALUES ('Saúde');
INSERT INTO categorias (descricao) VALUES ('Sinalização e Segurança');
INSERT INTO categorias (descricao) VALUES ('Suplementos e Vitaminas');
INSERT INTO categorias (descricao) VALUES ('Telefonia Fixa');
INSERT INTO categorias (descricao) VALUES ('Tv e Home Theater');
INSERT INTO categorias (descricao) VALUES ('Utilidades Domésticas');
INSERT INTO categorias (descricao) VALUES ('Vestuário Esportivo');
INSERT INTO categorias (descricao) VALUES ('Áudio');



DELETE FROM produtos;
ALTER TABLE produtos AUTO_INCREMENT = 1;

INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (1, 'Mochila Para Notebook Dell Gaming 15,6" Preto E Vermelho', 500, 304);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (1, 'Filtro de privacidade 23.0 W9 p/ telas 23 polegadas wide - 3M', 500, 475.31);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (1, 'Fonte Carregador Para Lenovo Ideapad 310-10 20v 2.25a By66', 500, 59.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (2, 'Impressora Não Fiscal Térmica MP-4200 TH - Bematech', 500, 665);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (2, 'Betoneira Motor 2cv Monofásico 400 Litros Maqtron-M-400', 500, 2853.92);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (2, 'Contra Angulo Intra de Baixa Rotação T10 - Schuster', 500, 530.61);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (3, 'Biscoito Wafer Chocolate Natal 165g - Bauducco', 500, 0.73);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (3, 'Panettone Frutas 400g - Tommy', 500, 8.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (3, 'Barra Cereal Light Morango E Chocolate C/3 - Hersheys', 500, 3.33);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (4, 'Climatizador De Ar Britânia Bcl01f', 500, 279.92);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (4, 'Aquecedor De Ar E Desumidificador Elétrico Nilko Nk 565 Cinza', 500, 145);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (4, 'Placa Interface Lavadora Consul W10626365 Cwc10 Cwg11 Cwk11', 500, 129.14);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (5, 'Base De Corte 43x30cm Cm-a3 - Olfa', 500, 185.10);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (5, 'Pistola Elétrica de Cola Quente 30W Bivolt 43755/530 - Tramontina', 500, 50.72);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (5, 'Quadro Decorativo Star Wars - Ilustração', 500, 80);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (6, 'Chapéu Estilo Panamá', 500, 15);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (6, 'Fantasia Naruto Infantil Com Mascara e Sandália', 500, 157.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (6, 'Kit Iluminação Festa 3x1 Com Bola Maluca - Strobo - LASER Luz Para Festa', 500, 115);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (7, 'Calha De Chuva Spin 4p 2012... Tg Poli', 500, 96.46);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (7, 'Retrovisor Câmera Filmadora Espiã Veicular Full Hd Dvr Carro', 500, 110);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (7, 'Driver D220ti Trio Titânio - 90 Watts Rms', 500, 119.9);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (8, 'Banheira Splash Peixinhos Azul - Burigotto', 500, 389);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (8, 'KIT MAMADEIRAS MAM - 2 de 320ML + 2 de 160ml ROSA', 500, 272);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (8, 'Carrinho De Bebê Tessy Moisés Cinza 1045cz Galzerano 3 Rodas', 500, 1799);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (9, 'Kit Aparador de Cabelo e Pelos Deluxe Groom Pro - Wahl Clipper', 500, 219.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (9, 'Cera Depilatória Quente 10kg Depimiel', 500, 238.28);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (9, 'Kit Anasol Facial Viso Antirrugas Dia Protetor Solar FPS 50 + Antirrugas Noite', 500, 99.50);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (10, 'Brinquedo Pista Carrinho Wave Racers Mega Match Dtc', 500, 219.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (10, 'Casa Casinha Da Boneca Chelsea Irmã Da Barbie - Mattel', 500, 149.98);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (10, 'Mochila 1000 Blocos Monta Facil', 500, 139.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (11, 'Colcha Cobre Leito Casal King Belga Goiaba 03 peças', 500, 339.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (11, 'Cortina Blackout Em Tecido 6,00x2,80 Corta Luz Avela', 500, 154.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (11, 'Toalha de Mesa Impermeavel Retangular 160x220 cm - Angora Bege', 500, 111.89);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (12, 'Pia de Cozinha Dubai Full 200 cm Polida', 500, 1089.89);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (12, 'Solda MIG 250A Monofásica 220V MIG-250 SMARTER', 500, 2600);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (12, 'Capa de Piscina Lona Leve 6,5x3,5 Polietileno 200 Micras', 500, 353.89 );
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (13, 'Smartphone Motorola Moto G7 Play 32GB Dual Chip Android Pie - 9.0 Tela 5.7" 1.8 GHz Octa-Core 4G Câmera 13MP - Indigo', 500, 940);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (13, 'Smartphone Motorola Moto G7 Plus 64GB Dual Chip Android Pie - 9.0 Tela 6.3" 1.8 GHz Octa-Core 4G Câmera 16MP F1.7 + 5MP F1.9 (Dual Cam) - Indigo', 500, 1640);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (13, 'Smartphone Samsung Galaxy S10+ 128GB Dual Chip Android 9.0 Tela 6.4\” Octa-Core 4G Câmera Tripla Traseira 12MP + 12MP + 16MP - Preto', 500, 5499);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (14, 'Sony Alpha A6500 - Câmera Mirrorless Digital (corpo)', 500, 5659);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (14, 'Cartão De Memória Micro Sd Sandisk Ultra Classe 10 80mbs 64gb', 500, 54);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (14, 'Filmadora Canon Vixia Hf R800 Entr Mic R800+64gb+bolsa+tripé', 500, 1589);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (15, 'Papel De Parede Bobinex Coleção Renascer Paperssauro 6206 - Lymdecor', 500, 186);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (15, 'Cabideiro Armário Aéreo Quarto Lojas De Roupas Parede Closet', 500, 65);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (15, 'Vaso Robert Plant - Para Cactos E Sulentas Branco', 500, 49);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (16, 'Adega de Vinhos Philco 12 Garrafas PH12E', 500, 619.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (16, 'Fogão Consul 5 bocas cor Inox com mesa de vidro CFS5VAR', 500, 1439);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (16, 'Geladeira / Refrigerador Electrolux French Door DM84X 579 Litros - Inox', 500, 4899);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (17, 'Aspirador Po Philco Ph1100 Rapid Turbo 2 em 1 Vermelho - 1000W', 500, 119.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (17, 'Chaleira Elétrica Hamilton Beach Vidro Capacidade 1,7 L Jarra sem fio 1500W Real', 500, 189.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (17, 'Power Mixer Mondial Vermelho Premium com Copo Dosador de 750ml - 500W', 500, 134.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Árvore de Natal Dinamarca Verde 580 Galhos 1,80m', 500, 349.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Bola De Natal Azul Decorada Brilhante Com 5 Unidades', 500, 21.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Kit Cortador de Bolachas de por na Caneca Tema de Natal! Cutter EXCLUSIVO!!!!', 500, 55);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Pulseira Garmin Forerunner 230 235 630 735xt Preta Original', 500, 219);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Bicicleta Aro 29 KSW Xlt 21V Câmbios Shimano Freio a Disco Mecânico', 500, 1189);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Carretilha Marine Sports Brisa Bg Super Direita', 500, 398.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Box Blu-Ray Coleção Indiana Jones: A Aventura Completa (5 Discos)', 500, 249);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Blu-ray - Homem-Formiga e a Vespa', 500, 39.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'DVD - Aquaman', 500, 39.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Game God Of War - PS4', 500, 109.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Game FIFA 19 - XBOX ONE', 500, 99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (18, 'Fonte Carregador Nintendo Switch Feir Fr-802 Bivolt 110v 220v', 500, 32.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Gift Card Digital Sony Playstation R$ 60', 500, 60);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Montagem de Poltrona ou Sofá Estofado - Gift Card Digital', 500, 119);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (19, 'Gift Card Digital Google Play R$ 30 Recarga', 500, 30);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (20, 'Teclado Bluetooth Sem Fio Moko para Microsoft Surface Pro 4/Pro 3/Pro', 500, 949);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (20, 'Chromebook Samsung XE500C13-AD1BR Intel Celeron Dual Core 2GB 16GB Tela 11.6" LED HD Chrome OS - Preto', 500, 1199);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (20, 'PC Gamer EasyPC Extreme Intel Core i7 3.8Ghz 16GB (GeForce GTX 1060 6GB) SSD 480GB Gabinete Aero 800', 500, 3899);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (21, 'Contrabaixo 4 Cordas TBM-4 Branco TAGIMA', 500, 1049);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (21, 'Bateria Infantil Premium DX35J Preta', 500, 829);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (21, 'Guitarra Tagima Memphis Mg230 Azul Metálico', 500, 730.54);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (22, 'Livro - O Poder da Ação', 500, 21.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (22, 'Por Uma Vida Mais Doce - Acompanha Avental', 500, 69.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (22, 'Livro - Liderança: A Inteligência Emocional na Formação do Líder de Sucesso', 500, 30.32);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (23, 'Livro - Financiamento Da Política, O: Teoria Geral E Experiências No Direito Comparado', 500, 46.53);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (23, 'Eighty Four Rooms', 500, 131.94);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (23, 'Tratado de Direito Civil XI - Contratos em Especial', 500, 396);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (24, 'Mochila Masculina Preto Mochila Para Notebook 15.6'' Mochila Executiva Mochila Impermeável Swiss Mochila Estudante Universitário Moda Sacos de Escola Mochila com Zíper Vermelho', 500, 49.79);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (24, 'Pasta Denlex Preta', 500, 37.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (24, 'Gurda-chuva Fazzoletti Preto Mini Golf Abre E Fecha Automatico 906', 500, 99.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (25, 'Cinta Ajustavel Modeladora Abdominal Redutora Colete Com Zíper Alças para Ombro', 500, 28.88);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (25, 'Kit Com 12 Cuecas Boxer Sem Costura Lisa Super Confort Ocean + 6 Pares De Meia', 500, 49.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (25, 'Casaco De Malha Para Bebê Grow Up', 500, 98.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (26, 'Guarda Roupa Mayorca Madeira Macica 6 Portas E 9 Gavetas Marca Artes Industrias', 500, 5629);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (26, 'Colchão Magnético Solteiro Eco Master Linha Luxo Massageador Bio Quântico Cromoterapia', 500, 1955);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (26, 'Mesa para Escritório Self Appunto Branco Branco', 500, 172.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (27, 'Rosa De Saron - Acústico e Ao Vivo - CD', 500, 9.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (27, 'Cd Queen', 500, 25.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (27, 'Caneca The Beatles Anos 70', 500, 34.80);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (28, 'Desktop Gamer Aspire GX-783-BR11 Intel Core 7 I5 8GB (GeForce 1050TI com 4GB) 1TB W10 - Acer', 500, 3349.89);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (28, 'Notebook Gamer Aspire Nitro AN515-51-78D6 Intel Core I7 16GB (Geforce GTX 1050TI com 4GB) 1tb Tela IPS 15,6¿ W10 - Acer', 500, 4819.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (28, 'Game Batman: Arkham Origins BR - PC', 500, 14.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (29, 'Calculadora Casio científica Classwiz FX-991LAX BK para estudantes de engenharia - Preta', 500, 160.95);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (29, 'Maleta De Pintura Completa Spider-man C/72 Itens', 500, 59.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (29, 'Manequim 20 cm Madeira Natural Boneco Articulado Modelo Desenho Arte Decoração', 500, 39.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (30, 'Gerioox Labyes Condroprotetor Anti Idade 120 Comprimidos', 500, 500);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (30, 'Arranhador De Canto De Sofá Parede Para Gatos Em Carpete', 500, 49.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (30, 'Jeneca - GD-600 - Filtro externo de superfície 1100 l/h para aquários de 55 a 80cm 110V', 500, 137.29);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (31, 'Ovo de Páscoa Galak 185g - Nestlé', 500, 29.83);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (31, 'Ovo de Páscoa ao Leite com Casca Recheada 150g - Havanna', 500, 45.50);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (31, 'Forma Para Chocolate Perfeito Para Sua Pascoa - Modelos Sortidos', 500, 15.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (32, 'Relógio Xiaomi Mi Band 3 SmartWatch para Android iOS - Preto', 500, 183);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (32, 'Relógio Xiaomi Mi Band 3 SmartWatch para Android iOS - Preto', 500, 219.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (32, 'Rádio Relógio Digital Bluetooth Fm Despertador SD', 500, 34.49);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (33, 'Cadeira De Rodas Avd Alumínio Pés Fixos 48cm Prata Ortobras', 500, 1499);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (33, 'Termômetro Clínico G-Tech Digital de Testa Sem Contato - Medição da Temperatura Corpórea, Ambientes e Superfícies', 500, 125.73);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (33, 'Desumidificador De Ar Komeco Modelo Peltier 250ml Bivolt Branco', 500, 238);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (34, 'Fita Antiderrapante 50mmx5m Transparente - Peça - Vonder', 500, 25.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (34, 'Extintor co2', 500, 452.79);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (34, 'Caixa De Papelão Ondulado Pequena Pardo 22cm X 13,5cm X 15cm Nº1 - 50 Unidades', 500, 54.50);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (35, 'Whey 100% 2,04kg (4,5LB) Chocolate Optimum Nutrition', 500, 159.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (35, 'Dimpless 40mg 60 Cáps - Combate A Celulite', 500, 179);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (35, 'Squeeze Neonutri 700ml Azul', 500, 10.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (36, 'Cabo USB Programação Radio Comunicador Baofeng Novo', 500, 27.49);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (36, 'Controle Remoto P/ Portão E Alarme 433mhz Rcg Command Saw', 500, 19.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (36, 'Telefone Headset Elgin Com Teclado Hst 6000', 500, 106.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (37, 'Smart TV LED 39" AOC LE39S5970 HD com Conversor Digital 2 HDMI 1 USB Wi-Fi Função Closed Caption - Preta', 500, 1099.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (37, 'Dvd Player Ph136, Usb, Hdmi, Mp3 - Philco', 500, 159.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (37, 'Home Theater DVD 5.1 Canais, 330W, Karaokê, Hdmi - Dh4130S - LG', 500, 699.99);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (38, 'Churrasqueira a Bafo Gás ou Carvão Apolo 9', 500, 1003.92);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (38, 'Jogo de Panelas Tramontina Antiaderente Turim 7 peças Vermelho', 500, 213.81);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (38, 'Varal De Chão - Aço - Prata - 0,83 X 0,91 X 0,51 - A/l/p (m)', 500, 65.80);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (39, 'Bermuda Jammer Masc Arena Powerskin Carbon Ultra', 500, 1792.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (39, 'Tênis Feminino Climber - Everlast - ELW-138A', 500, 199);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (39, 'Tênis Olympikus Diffuse 2 Infantil Esporte - Indoor', 500, 149.90);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (40, 'Toca Disco Woodburn - Reproduz Mp3 , Cd E Vinil - Entrada Usb - Acabamento Clássico Em Madeira - Ri', 500, 999);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (40, 'Rádio Portátil Motobras, 7 Fxs., AM/FM/OC e Som da TV, Pilha e Luz', 500, 219);
INSERT INTO produtos (categoria_id, descricao, quantidade, preco) VALUES (40, 'Mini System Panasonic 250w Rms -sc-cmax4lb-k', 500, 599);



DELETE FROM clientes;
ALTER TABLE clientes AUTO_INCREMENT = 1;

INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Breno Vinicius Raimundo Assis', 'bbrenoviniciusraimundoassis@gabrielresende.com', 'Rua Sud Minucci, 928, Vila Boa Esperança, Ourinhos/SP', '1994-11-13');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Valentina Mariane Sabrina Sales', 'vvalentinamarianesabrinasales@gmx.com.br', 'Rua Pedro Padovan, 943, Jardim Imperial, Ourinhos/SP', '1985-05-06');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Vitor Danilo Nelson da Cruz', 'vitordanilonelsondacruz@humanfit.com.br', 'Rua Hugo Berlandi, 247, Jardim Ideal, Ourinhos/SP', '1999-05-03');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Heloise Milena Lima', 'heloisemilenalima@robiel.com.br', 'Praça Deputado Leônidas Camarinha 13, 561, Centro, Santa Cruz do Rio Pardo/SP', '1999-07-02');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Fernanda Giovanna Sabrina Monteiro', 'ffernandagiovannasabrinamonteiro@aerobravo.com.br', 'Rua Bárbara Abujamra, 671, Jardim Ouro Verde, Santa Cruz do Rio Pardo/SP', '1987-08-10');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Ian Márcio Fernando Gonçalves', 'ianmarciofernandogoncalves_@asproplastic.com.br', 'Rua Três, 786, Jardim Nazareth, Santa Cruz do Rio Pardo/SP', '1998-10-10');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Emanuel Mateus Leonardo Campos', 'emanuelmateusleonardocampos@terra.com.br', 'Rua Prudente de Morais 354-B, 796, Salto Grande/SP', '1986-01-01');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Ian Pietro Danilo Nogueira', 'ianpietrodanilonogueira@bwmtrade.com', 'Avenida Ourinhos, 511, Centro, Salto Grande/SP', '1993-10-15');
INSERT INTO clientes (nome, email, endereco, data_nascimento) 
    VALUES('Alessandra Sebastiana Lopes', 'alessandrasebastianalopes@alemponte.com.br', 'Rua Beira Rio, 312, Centro Salto Grande/SP', '1997-11-04');

DELETE FROM departamentos;
ALTER TABLE departamentos AUTO_INCREMENT = 1;

INSERT INTO departamentos (descricao) VALUES('Eletrônicos');
INSERT INTO departamentos (descricao) VALUES('Games');
INSERT INTO departamentos (descricao) VALUES('Moda');
INSERT INTO departamentos (descricao) VALUES('Moda Infantil');
INSERT INTO departamentos (descricao) VALUES('Casa & Decorações');

DELETE FROM vendedores;
ALTER TABLE vendedores AUTO_INCREMENT = 1;

INSERT INTO vendedores (nome, email, departamento_id, perc_comissao)
    VALUES('Enzo Sérgio Gonçalves', 'eenzosergiogoncalves@escolpi.com.br', 1, 0.15);
INSERT INTO vendedores (nome, email, departamento_id, perc_comissao)
    VALUES('Luan Luiz Filipe Jesus', 'luanljesus@escolpi.com.br', 2, 0.15);
INSERT INTO vendedores (nome, email, departamento_id, perc_comissao)
    VALUES('Pietra Elza Vieira', 'pietra.vieira@escolpi.com.br', 1, 0.15);
