<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<html>
<head>
	<title></title>
</head>
<body>
	<table border="1">
		<tr>
            <th>Email</th>
            <th>Senha</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Dt Nascimento</th>
            <th>Telefone</th>
            
            
        </tr>
		<%
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		List<EntidadeDominio> clientes = dao.consultar(cliente);
		
		for(EntidadeDominio e : clientes) {
		
		// Aplicado o CAST para poder popular o cliente
		Cliente c = (Cliente) e;
		// pega o usuario que esta dentro do cliente
		Usuario u = c.getUsuario();
		%>
			<tr>
				<td><%=u.getEmail() %></td>
				<td><%=u.getSenha() %></td>
				<td><%=u.getConfSenha() %></td>
				<td><%=c.getNome() %></td>
				<td><%=c.getCpf() %></td>
				<td><%=c.getDt_nasc() %></td>
				<td><%=c.getTelefone() %></td>
				<!--  <td>=c.getCdCliente() %></td> -->
			</tr>
		<%
		}
		%>
	</table>
	 <input type="button" value="Retornar" onclick="history.back()">
</body>
</html>