/**
 * common.js - Implementa as funções JavaScript genéricas e utilizadas em todo projeto.
 */
/*
 * Forma antiga:
function irPara(url) {
	window.location = url;
}
*/

/*
 * Forma com Arrow Functions a partir do ECMA2015
 */
const irPara = (url) => window.location = url;

const formatarDecimal = (decimal) => {
	return Number(decimal).toFixed(2).replace('.', ',');
}

const converterParaFloat = (str) => {
	str = str.replace('R$ ', '').replace('.', '').replace(',', '.').trim();

	return parseFloat(str.replace(',', ''));
}