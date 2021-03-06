package br.com.escolpi.livros.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import br.com.escolpi.livros.business.impl.ICrud;
import br.com.escolpi.livros.modelo.rh.Vendedor;
import br.com.escolpi.livros.modelo.serializer.ModeloSerializer;
import br.com.escolpi.livros.util.Mensagem;

public class VendedorBusiness extends Business<Vendedor> implements ICrud<Vendedor> {

	private static final String DATASOURCE = "Vendedores";
	private ModeloSerializer<Vendedor> modeloSerializer = new ModeloSerializer<>();

	@Override
	public Vendedor incluir(Vendedor funcionario) {
		try {
			File vendedores = getDataSource(DATASOURCE);
			funcionario.setId(proximoId());
			FileOutputStream output = new FileOutputStream(vendedores, true);
			PrintStream gravador = new PrintStream(output);
			gravador.println(modeloSerializer.modeloParaTexto(funcionario));
			gravador.close();
			System.out.println(Mensagem.getInclusao("Vendedor"));
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Ao incluir um Vendedor: " + e.getMessage()));
		}

		return funcionario;
	}

	@Override
	public Vendedor obter(int id) {
		for (Vendedor vendedor : listar()) {
			if (id == vendedor.getId()) {
				return vendedor;
			}
		}

		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public Vendedor[] listar() {
		try {
			Scanner scanner = new Scanner(getDataSource(DATASOURCE));
			int count = 0;

			while (scanner.hasNextLine()) {
				count++;
				scanner.nextLine();
			}

			scanner = new Scanner(getDataSource(DATASOURCE));
			Vendedor[] registros = new Vendedor[count];
			count = 0;

			while (scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				registros[count] = modeloSerializer.textoParaModelo(registro, new Vendedor());
				count ++;
			}

			scanner.close();
			System.out.println(Mensagem.getConsulta("Vendedor(es)"));

			return registros;
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Ao carregar listar Vendedor(es): " + e.getMessage()));
		}

		return new Vendedor[] {};
	}

	@Override
	public Vendedor alterar(Vendedor funcionario) {
		try {
			Vendedor[] vendedores = listar();
			
			if (vendedores.length > 0) {
				for (int i = 0; i < vendedores.length; i++) {
					if (funcionario.getId() == vendedores[i].getId()) {
						vendedores[i] = funcionario;
						break;
					}
				}

				File datasource = getDataSource(DATASOURCE);
				PrintStream gravador = new PrintStream(datasource);

				for (Vendedor vendedor : vendedores) {
					gravador.println(modeloSerializer.modeloParaTexto(vendedor));
				}

				gravador.close();
			}

			return funcionario;
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Erro alterar Vendedor: " + e.getMessage()));
		}

		return null;
	}

	@Override
	public void excluir(int id) {
		try {
			Vendedor exclusao = obter(id);

			if (exclusao == null) {
				throw new IllegalArgumentException("Registro com ID " + id + " não foi encontrado!");
			}

			Vendedor[] vendedores = listar();
			
			if (vendedores.length > 0) {
				for (int i = 0; i < vendedores.length; i++) {
					if (exclusao.getId() == vendedores[i].getId()) {
						vendedores[i] = null;
						break;
					}
				}

				File datasource = getDataSource(DATASOURCE);
				PrintStream gravador = new PrintStream(datasource);

				for (Vendedor vendedor : vendedores) {
					if (vendedor != null) {
						gravador.println(modeloSerializer.modeloParaTexto(vendedor));
					}
				}

				gravador.close();
				System.out.println(Mensagem.getExclusao("Vendedor"));
			}
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Erro excluir Vendedor: " + e.getMessage()));
		}
	}

	@Override
	public int proximoId() {
		Vendedor[] vendedores = listar();

		return (vendedores != null && vendedores.length > 0 ? 
				vendedores[vendedores.length - 1].getId() + 1 : ID_INICIAL);
	}

}
