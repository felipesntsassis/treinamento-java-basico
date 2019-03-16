package br.com.escolpi.livros.util;
import java.util.Random;

public class Util {

	public static int getNumeroAleatorio(int range) {
		Random random = new Random();

		return random.nextInt(range);
	}
}
