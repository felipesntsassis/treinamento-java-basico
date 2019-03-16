package br.com.escolpi.livros.modelo.rh.impl;

import br.com.escolpi.livros.modelo.produto.Livro;

public interface IVendedor {

	public void vender(Livro livroAVender, Livro... estante);

	public void relizarTroca(Livro livro);

}
