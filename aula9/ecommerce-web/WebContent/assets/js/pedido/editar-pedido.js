/**
 * editar-pedido.js 
 */
const formPedido = document.querySelector('#form-pedido');
const corpoTabela = document.querySelector('#tb-itens-produtos tbody');
const hdItemPedidoId = document.querySelector('#hd-item-pedido-id');
const comboProduto = document.querySelector('#combo-produto');
const txtQuantidade = document.querySelector('#txt-quantidade');
const txtValor = document.querySelector('#txt-valor');
const acoesItemPedido = document.querySelector('#acoes-item-pedido');
const pedidoId = document.querySelector('input[name="id"]').value;

let precoProdutoSelecionado = 0;
let produtoSelecionado = '';
let indexLinhaSelecionada = null;

comboProduto.addEventListener('change', (event) => {
	console.log('change');
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
	if (document.querySelectorAll('input[name="itemPedido.produtoId"]').length === 0) {
		alert('Inclua ao menos um Item de Pedido');
		event.preventDefault();
	}
});

const cancelarEdicao = () => {
	document.querySelectorAll('#subform-item-pedido input, #field-item-pedido select').forEach(
			input => input.value = '');
	acoesItemPedido.innerHTML = '';
	acoesItemPedido.insertAdjacentHTML('beforeend', '<br>');
	acoesItemPedido.insertAdjacentHTML('beforeend', 
			'<button type="button" onclick="incluirItem(event)">Salvar Item</button>');
	document.querySelectorAll('#tb-itens-produtos button').forEach(button => button.disabled = false);
	comboProduto.disabled = false;
	linhaSelecionada = null;
}

const calcularSubTotal = () => {
	const totaisItens = [];
	document.querySelectorAll('#tb-itens-produtos tbody tr').forEach(linha => 
		totaisItens.push(converterParaFloat(linha.childNodes[3].textContent)));
	const subTotal = totaisItens.reduce((total, atual) => total + atual, 0);
	document.getElementById('total-item-pedido').innerHTML = `R$ ${formatarDecimal(subTotal)}`;
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
		id: "",
		pedidoId: pedidoId,
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
		if (campo === 'id' || campo === 'pedidoId')
			continue;

		if (campo === 'item') {
			linha += '<td>';
			linha += `<input type="hidden" name="itemPedido.id" value="${itemPedido.id}">`;
			linha += `<input type="hidden" name="itemPedido.pedidoId" value="${pedidoId}">`;
			linha += `<input type="hidden" name="itemPedido.produtoId" value="${comboProduto.value}">`;
			linha += `<input type="hidden" name="itemPedido.preco" value="${converterParaFloat(itemPedido.preco)}">`;
			linha += `<input type="hidden" name="itemPedido.quantidade" value="${itemPedido.quantidade}">`;
			linha += `<input type="hidden" name="itemPedido.valor" value="${converterParaFloat(itemPedido.valor)}">`;
			linha += `${itemPedido.item}`;
			linha += '</td>';
			continue;
		}
		if (campo === 'opcoes') {
			linha += '<td class="text-center">';
			linha += '<button type="button" onclick="editarItem(event)">Editar</button><br>';
			linha += '<button type="button" onclick="removerItem(event)">Remover</button>';
			linha += '</td>';
			continue;
		}

		linha += `<td>${itemPedido[campo]}</td>`;
	}

	linha += '</tr>';
	corpoTabela.insertAdjacentHTML('beforeend', linha);
	calcularSubTotal();
}

const editarItem = (event) => {
	const linha = event.currentTarget.parentElement.parentElement;
	const coluna = linha.firstElementChild;
	linhaSelecionada = linha.rowIndex - 1;

	comboProduto.disabled = true;
	hdItemPedidoId.value = coluna.querySelector('input[name="itemPedido.id"]').value;
	comboProduto.value = coluna.querySelector('input[name="itemPedido.produtoId"]').value;
	txtQuantidade.value = coluna.querySelector('input[name="itemPedido.quantidade"]').value;
	precoProdutoSelecionado = coluna.querySelector('input[name="itemPedido.preco"]').value;
	txtValor.value = formatarDecimal(coluna.querySelector('input[name="itemPedido.valor"]').value);
	acoesItemPedido.innerHTML = '';
	acoesItemPedido.insertAdjacentHTML('beforeend', 
			'<button type="button" onclick="salvarItemPedido(event)">Salvar</button>');
	acoesItemPedido.insertAdjacentHTML('beforeend', '<br>');
	acoesItemPedido.insertAdjacentHTML('beforeend', 
			'<button type="button" onclick="cancelarEdicao(event)">Cancelar</button>');
	document.querySelectorAll('#tb-itens-produtos button').forEach(button => button.disabled = true);
};

const removerItem = (event) => {
	const linha = event.currentTarget.parentElement.parentElement;
	const id = parseInt(linha.firstElementChild.querySelector('input[name="itemPedido.id"]').value);

	if (id && id > 0)
		formPedido.insertAdjacentHTML('beforeend', `<input type="hidden" name="itensExclusao" value="${id}">`);

	linha.remove();
	calcularSubTotal();
	
	if (corpoTabela.innerHTML.trim() === '') {
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

const salvarItemPedido = () => {
	const linha = corpoTabela.querySelectorAll('tr')[linhaSelecionada];
	linha.querySelector('input[name="itemPedido.quantidade"]').value = txtQuantidade.value;
	linha.querySelector('input[name="itemPedido.valor"]').value = converterParaFloat(txtValor.value);
	linha.children[2].innerHTML = txtQuantidade.value;
	linha.children[3].innerHTML = `R$ ${txtValor.value}`;
	calcularSubTotal();
	cancelarEdicao();
}

const validarInclusaoDoItem = (produtoId) => document.querySelectorAll(
		`input[name="itemPedido.produtoId"][value="${produtoId}"]`).length === 0;
