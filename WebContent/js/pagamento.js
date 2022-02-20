function escondeCartao(){
    var opcao = document.getElementById("selecioneCartao").value;
    
    console.log(opcao);
    
    if (opcao == "cartao"){
        document.getElementById("Cartao1").style.visibility = "visible";
        document.getElementById("Cartao2").style.visibility = "visible";
    }
    
}