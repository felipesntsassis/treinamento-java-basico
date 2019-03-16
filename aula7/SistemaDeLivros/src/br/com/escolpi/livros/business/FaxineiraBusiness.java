package br.com.escolpi.livros.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import br.com.escolpi.livros.business.impl.ICrud;
import br.com.escolpi.livros.modelo.rh.Faxineira;
import br.com.escolpi.livros.modelo.serializer.ModeloSerializer;
import br.com.escolpi.livros.util.Mensagem;

public class FaxineiraBusiness extends Business<Faxineira> implements ICrud<Faxineira> {

	private static final String DATASOURCE = "Faxineiras";
	private ModeloSerializer<Faxineira> modeloSerializer = new ModeloSerializer<Faxineira>();

	@Override
	public Faxineira incluir(Faxineira funcionario) {
		try {
			File faxineiras = getDataSource(DATASOURCE);
			funcionario.setId(proximoId());
			FileOutputStream output = new FileOutputStream(faxineiras, true);
			PrintStream gravador = new PrintStream(output);
			gravador.println(modeloSerializer.modeloParaTexto(funcionario));
			gravador.close();
			System.out.println(Mensagem.getInclusao("Faxineira"));
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Ao incluir um Faxineira: " + e.getMessage()));
		}

		return funcionario;
	}

	@Override
	public Faxineira obter(int id) {
		for (Faxineira faxineira : listar()) {
			if (id == faxineira.getId()) {
				return faxineira;
			}
		}

		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public Faxineira[] listar() {
		try {
			Scanner scanner = new Scanner(getDataSource(DATASOURCE));
			int count = 0;
			
			if (!scanner.hasNextLine()) {
				return new Faxineira[] {};
			}

			while (scanner.hasNextLine()) {
				count++;
				scanner.nextLine();
			}

			scanner = new Scanner(getDataSource(DATASOURCE));
			Faxineira[] registros = new Faxineira[count];
			count = 0;

			while (scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				registros[count] = modeloSerializer.textoParaModelo(registro, new Faxineira());
				count ++;
			}

			scanner.close();
			System.out.println(Mensagem.getConsulta("Faxineira(s)"));

			return registros;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Mensagem.getErro("Ao carregar listar Faxineira(s): " + e.getMessage()));
		}

		return new Faxineira[] {};
	}

	@Override
	public Faxineira alterar(Faxineira funcionario) {
		try {
			Faxineira[] faxineiras = listar();

			if (faxineiras.length > 0) {
				for (int i = 0; i < faxineiras.length; i++) {
					if (funcionario.getId() == faxineiras[i].getId()) {
						faxineiras[i] = funcionario;
						break;
					}
				}

				File datasource = getDataSource(DATASOURCE);
				PrintStream gravador = new PrintStream(datasource);

				for (Faxineira faxineira : faxineiras) {
					gravador.println(modeloSerializer.modeloParaTexto(faxineira));
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
			Faxineira exclusao = obter(id);

			if (exclusao == null) {
				throw new IllegalArgumentException("Registro com ID " + id + " não foi encontrado!");
			}

			Faxineira[] faxineiras = listar();
			
			if (faxineiras.length > 0) {
				for (int i = 0; i < faxineiras.length; i++) {
					if (exclusao.getId() == faxineiras[i].getId()) {
						faxineiras[i] = null;
						break;
					}
				}

				File datasource = getDataSource(DATASOURCE);
				PrintStream gravador = new PrintStream(datasource);

				for (Faxineira faxineira : faxineiras) {
					if (faxineira != null) {
						gravador.println(modeloSerializer.modeloParaTexto(faxineira));
					}
				}

				gravador.close();
				System.out.println(Mensagem.getExclusao("Faxineira"));
			}
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Erro excluir Faxineira: " + e.getMessage()));
		}
	}

	@Override
	public int proximoId() {
		Faxineira[] faxineiras = listar();

		return (faxineiras != null && faxineiras.length > 0 ? 
				faxineiras[faxineiras.length - 1].getId() + 1 : ID_INICIAL);
	}

}
