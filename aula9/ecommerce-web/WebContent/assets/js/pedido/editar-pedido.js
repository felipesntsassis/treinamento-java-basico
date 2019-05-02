/**
 * editar-pedido.js 
 */

const formPedido = document.getElementById('form-pedido');
const corpoTabela = document.querySelector('#tb-itens-produtos tbody');
const comboProduto = document.getElementById('combo-produto');
const txtQuantidade = document.getElementById('txt-quantidade');
const txtValor = document.getElementById('txt-valor');

let precoProdutoSelecionado = 0;
let produtoSelecionado = '';

comboProduto.addEventListener('change', (event) => {
	produtoSelecionado = comboProduto.options[comboProduto.selectedIndex].dataset.descricao;
	txtValor.value = comboProduto.options[comboProduto.selectedIndex].dataset.preco;
	precoProdutoSelecionado = (txtValor.value) ? parseFloat(txtValor.value.replace(',', '.')) : 0;
	txtQuantidade.value = precoProdutoSelecionado > 0 ? '1' : '';
}, true);

txtQuantidade.addEventListener('input', (event) => {
	if (precoProdutoSelecionado === 0) {
		this.value = '';
		txtValor.value = '';
		precoProdutoSelecionado = 0;
		return;
	}

	const totalProduto = (txtQuantidade.value >= 1) ? precoProdutoSelecionado * parseInt(txtQuantidade.value) 
			: 0;
	txtValor.value = formatarDecimal(totalProduto);
}, true);


document.getElementById('form-pedido').addEventListener('submit', (event) => {
	if (document.querySelectorAll('input[name="itemPedido.item"]').length === 0) {
		alert('Inclua ao menos um Item de Pedido');
		event.preventDefault();
	}
});

const calcularSubTotal = () => {
	const totaisItens = [];
	document.querySelectorAll('#tb-itens-produtos tbody tr').forEach(linha => 
		totaisItens.push(converterParaFloat(linha.childNodes[3].textContent)));
	const subTotal = totaisItens.reduce((total, atual) => total + atual, 0);
	document.getElementById('total-item-pedido').innerHTML = formatarDecimal(subTotal);
}

const incluirItem = () => {
	if (!comboProduto.value) {
		alert('Por favor, selecione um Produto.');
		return;
	}
	if (!txtQuantidade.value || txtQuantidade.value === '0') {
		alert('Informe no mínimo 1 unidade!');
		return;
	}

	const itemPedido = {
		item: produtoSelecionado,
		preco: `R$ ${formatarDecimal(precoProdutoSelecionado)}`,
		quantidade: txtQuantidade.value,
		valor: `R$ ${txtValor.value}`,
		opcoes: ''
	};

	if (!validarInclusaoDoItem(comboProduto.value)) {
		alert('Este Produto já incluso em sua lista de Itens! Por favor selecione outro produto.');
		return;
	}

	inserirRegistro(itemPedido);
	reiniciarFormItemPedido();
};

const inserirRegistro = (itemPedido) => {
	if (corpoTabela.firstElementChild.dataset.semRegistros) {
		corpoTabela.innerHTML = '';
	}
	
	let linha = '<tr>';

	for (let campo in itemPedido) {
		if (campo === 'opcoes') {
			linha += '<td class="text-center">';
			linha += '<button type="button" onclick="editarItem(event)">Editar</button><br>';
			linha += '<button type="button" onclick="removerItem(event)">Remover</button>';
			linha += '</td>';
		} else {
			let valorCampo = itemPedido[campo];

			if ((campo === 'preco' || campo === 'valor')) {
				valorCampo = converterParaFloat(itemPedido[campo]);
			}

			linha += `<td>`;
			linha += `<input type="hidden" name="itemPedido.${campo}" value="${campo === 'item' ? comboProduto.value : valorCampo}">`;
			linha += `${itemPedido[campo]}`;
			linha += `</td>`;
		}
	}

	linha += '</tr>';
	corpoTabela.insertAdjacentHTML('beforeend', linha);
	calcularSubTotal();
}

const editarItem = () => {
	alert('Incluir Item');
};

const removerItem = (event) => {
	event.currentTarget.parentElement.parentElement.remove();
	calcularSubTotal();
	
	if (corpoTabela.innerHTML === '') {
		let linha = '<tr data-sem-registros="true" >';
		linha += '<td class="text-center" colspan="5">';
		linha += '<em>Nenhum item incluso neste Pedido</em>';
		linha += '</td>';
		linha += '</tr>';
		corpoTabela.insertAdjacentHTML('beforeend', linha);
	}
};

const reiniciarFormItemPedido = () => {
	comboProduto.value = '';
	txtQuantidade.value = '';
	txtValor.value = '';
}

const validarInclusaoDoItem = (produtoId) => document.querySelectorAll(
		`input[name="itemPedido.item"][value="${produtoId}"]`).length === 0;
