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
