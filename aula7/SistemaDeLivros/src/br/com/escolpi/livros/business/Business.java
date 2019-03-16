package br.com.escolpi.livros.business;

import java.io.File;
import java.io.IOException;

import br.com.escolpi.livros.business.impl.ICrud;

public abstract class Business<T> implements ICrud<T> {

	@Override
	public File getDataSource(String datasource) throws IOException {
		File arquivoDs = new File("datasource" + File.separator + "cadastros" + File.separator + 
				datasource + ".tb");
		
		if (!arquivoDs.exists()) {
			arquivoDs.createNewFile();
		}
	
		return arquivoDs;
	}

}
