function graficoChart(){
	// pega os nomes dos produtos (3 Produtos mais vendidos)
	label1 = document.getElementById("nomeProduto1").value;
	label2 = document.getElementById("nomeProduto2").value;
	label3 = document.getElementById("nomeProduto3").value;
	
	// pega todas as colunas geradas, que esta no arquivo "grafico_chart_2.jsp"
	var selectColunasChart = document.getElementById('ColunasChart');
	// pega todas de valores do Produto1 gerados, que esta no arquivo "grafico_chart_2.jsp"
	var selectProduto1Chart = document.getElementById('Produto1Chart');
	// pega todas de valores do Produto2 gerados, que esta no arquivo "grafico_chart_2.jsp"
	var selectProduto2Chart = document.getElementById('Produto2Chart');
	// pega todas de valores do Produto3 gerados, que esta no arquivo "grafico_chart_2.jsp"
	var selectProduto3Chart = document.getElementById('Produto3Chart');
	
	var colunas = [];
	var ValoresProduto1 = [];
	var ValoresProduto2 = [];
	var ValoresProduto3 = [];
	
	// faz um laço no "selectColunasChart", 
	// e para cada item de "selectColunasChart", 
	// será preenchido as colunas, que será responsável de apresentar o eixo X no gráfico,
	// e também será preenchido os valores dos 3 produtos, que será responsável de apresentar as linhas do gráfico (eixo Y)
	for (i = 0; i < selectColunasChart.length; i ++) {
		// preenchimento das colunas
	    colunas.push(selectColunasChart.options[i].value);
	    
	    // preenchimento dos valores dos produtos
	    ValoresProduto1.push(selectProduto1Chart.options[i].value);
	    ValoresProduto2.push(selectProduto2Chart.options[i].value);
	    ValoresProduto3.push(selectProduto3Chart.options[i].value);
	}
	
	console.log(colunas);
	console.log(ValoresProduto1);
	console.log(ValoresProduto2);
	console.log(ValoresProduto3);
	
	// gera o Gráfico com os 3 Produtos mais vendidos
	var ctx = document.getElementById('myChart').getContext('2d');
	
	var chart = new Chart(ctx, {
	
	    type: 'line',
	    data: {
	        labels: colunas,
	        
	        
	        datasets: [
	        	{
		            label: label1,
		            backgroundColor: ['green'],
		            borderColor: 'rgb(0,128,0)',
		            data: ValoresProduto1
	        	},
	        	{
		            label: label2,
		            backgroundColor: ['blue'],
		            borderColor: 'rgb(0,0,255)',
		            data: ValoresProduto2
	        	},
	        	{
		            label: label3,
		            backgroundColor: ['yellow'],
		            borderColor: 'rgb(255,255,0)',
		            data: ValoresProduto3
	        	}
	        ]
	    },
	
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});

}