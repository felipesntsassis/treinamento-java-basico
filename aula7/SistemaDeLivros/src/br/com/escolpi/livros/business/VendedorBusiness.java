package br.com.escolpi.livros.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import br.com.escolpi.livros.business.impl.ICrud;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Vendedor;
import br.com.escolpi.livros.util.Mensagem;

public class VendedorBusiness implements ICrud {

	private static final String DS_VENDEDORES = "Vendedores";

	@Override
	public Vendedor incluir(Vendedor funcionario) {
		File vendedores = new File(getDataSource(DS_VENDEDORES));
		try {
			funcionario.setId(proximoId());
			FileOutputStream output = new FileOutputStream(vendedores, true);
			PrintStream gravador = new PrintStream(output);

			StringBuilder registro = new StringBuilder();
			String template = "%s:%s|";

			for (Field field : funcionario.getClass().getSuperclass().getDeclaredFields()) {
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				registro.append(String.format(template, field.getName(), field.get(funcionario)));
				field.setAccessible(accessible);
			}

			gravador.println(registro.substring(0, registro.lastIndexOf("|")));
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

	@Override
	public Vendedor[] listar() {
		try {
			Scanner scanner = new Scanner(new File(getDataSource(DS_VENDEDORES)));
			int count = 0;

			while (scanner.hasNextLine()) {
				count++;
				scanner.nextLine();
			}

			scanner = new Scanner(new File(getDataSource(DS_VENDEDORES)));
			Vendedor[] registros = new Vendedor[count];
			count = 0;

			while (scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				String[] tupla = registro.split("\\|");
				Vendedor vendedor = new Vendedor();

				for (String coluna : tupla) {
					String[] campo = coluna.split(":");
					Field field = vendedor.getClass().getSuperclass().getDeclaredField(campo[0]);
					boolean accessible = field.isAccessible();
					field.setAccessible(true);

					if (field.getType().isAssignableFrom(Data.class)) {
						String[] data = campo[1].split("/");
						field.set(vendedor, new Data(
								Integer.valueOf(data[0]), 
								Integer.valueOf(data[1]), 
								Integer.valueOf(data[2])
							)
						);
					} else if (field.getType().getName() == "int") {
						field.set(vendedor, Integer.valueOf(campo[1]));
					} else if (field.getType().getName() == "double") {
						field.set(vendedor, Double.valueOf(campo[1]));
					} else {
						field.set(vendedor, campo[1]);
					}

					field.setAccessible(accessible);
				}

				registros[count] = vendedor;
				count ++;
			}

			scanner.close();
			System.out.println(Mensagem.getConsulta("Vendedor(es"));

			return registros;
		} catch (Exception e) {
			System.out.println(Mensagem.getErro("Ao carregar listar Vendedor(es): " + e.getMessage()));
		}

		return null;
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

				File datasource = new File(getDataSource(DS_VENDEDORES));
				PrintStream gravador = new PrintStream(datasource);

				
				String template = "%s:%s|";

				for (Vendedor vendedor : vendedores) {
					StringBuilder registro = new StringBuilder();

					for (Field field : vendedor.getClass().getSuperclass().getDeclaredFields()) {
						boolean accessible = field.isAccessible();
						field.setAccessible(true);
						registro.append(String.format(template, field.getName(), field.get(vendedor)));
						field.setAccessible(accessible);
					}

					gravador.println(registro.substring(0, registro.lastIndexOf("|")));
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
				throw new IllegalArgumentException("Registro com ID " + id + " não encontrado!");
			}

			Vendedor[] vendedores = listar();
			
			if (vendedores.length > 0) {
				for (int i = 0; i < vendedores.length; i++) {
					if (exclusao.getId() == vendedores[i].getId()) {
						vendedores[i] = null;
						break;
					}
				}

				File datasource = new File(getDataSource(DS_VENDEDORES));
				FileOutputStream output = new FileOutputStream(datasource);
				PrintStream gravador = new PrintStream(output);

				String template = "%s:%s|";

				for (Vendedor vendedor : vendedores) {
					if (vendedor != null) {
						StringBuilder registro = new StringBuilder();
						for (Field field : vendedor.getClass().getSuperclass().getDeclaredFields()) {
							boolean accessible = field.isAccessible();
							field.setAccessible(true);
							registro.append(String.format(template, field.getName(), field.get(vendedor)));
							field.setAccessible(accessible);
						}
						gravador.println(registro.substring(0, registro.lastIndexOf("|")));
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
	public String getDataSource(String datasource) {
		return "datasource" + File.separator + "cadastros" + File.separator + datasource + ".tb";
	}

	@Override
	public int proximoId() {
		Vendedor[] vendedores = listar();

		return (vendedores != null && vendedores.length > 0 ? 
				vendedores[vendedores.length - 1].getId() + 1 : ID_INICIAL);
	}

}
