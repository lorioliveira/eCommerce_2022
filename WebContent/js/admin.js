// PARTE DE JAVASCRPT - PAGINA 90 DA APOSTILA

/* 
function validaBusca() {
	if (document.querySelector('#q').value =='') {
		alert('Não podia ter deixado em branco a busca!');
		return false;
	}
}
*/
// fazendo a associação da função acima com o evento
//document.querySelector('#form-busca').onsubmit = validaBusca;

// Mostrar uma janela de erro é considerado por muitos uma ação muito agressiva contra o usuário.
// Talvez um feedback mais sutil seja pintar o fundo de vermelho, indicando um erro.

/*function validaBusca() {
	if (document.querySelector('#q').value =='') {
		document.querySelector('#form-busca').style.background ='red';
		return false;
	}
}*/


/*
// funções anonimas - que permine definir a função diretamente na definição do onsubmit
// *acontece a mesma coisa que a função anterior, apenas define a função diretamente: .onsubmit = function(){*
document.querySelector('#form-busca').onsubmit = function(){
	if(document.querySelector('#q').value == '') {
		document.querySelector('#form-busca').style.background = 'red';
		return false;
	}
}
*/

// funções temporais - que permite executar umtrecho de código após certo tempo

//executa a minhaFuncao daqui a um segundo
 //setTimeout(minhaFuncao, 1000);

// executa a minhaFuncao de 1 em 1 segundo
 //setInterval(minhaFuncao, 1000);

 // DANDO PAUSE E PLAY NO BANNER 
var banners = ["../img/boss.png", "../img/Clothingindustry.png"];
var bannerAtual = 0;

function trocaBanner() {
	bannerAtual = (bannerAtual + 1) % 2;
	document.querySelector('.destaque img').src = banners[bannerAtual];
}

setInterval(trocaBanner, 2000);

var timer = setInterval(trocaBanner, 2000);

var controle = document.querySelector('.pause');

controle.onclick = function() {
	if (this.className == 'pause') {
		clearInterval(timer);
		controle.className = 'play';
	} else {
		timer = setInterval(trocaBanner, 2000);
		controle.className = 'pause';
	}
	return false;
};