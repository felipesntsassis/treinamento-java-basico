package br.com.escolpi.aula8.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class TestaProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream(new File("resources" + 
				File.separator + "configuracoes.properties")));
		
		System.out.println(config.get("banco.dados.username"));
		System.out.println(config.get("banco.dados.senha"));
		System.out.println(config.get("banco.dados.base"));
		System.out.println(config.get("banco.dados.limite-conexao"));
		config.put("banco.dados.token", Base64.getEncoder().encodeToString("secret".getBytes()));
		System.out.println(Base64.getDecoder().decode(config.getProperty("banco.dados.token").toString()));
		System.out.println(config.get("banco.dados.token"));
	}
}
