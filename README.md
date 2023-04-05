Nesse projeto, foi usada a seguinte linguagem :
- JAVA

Nesse projeto, foi usada a seguinte biblioteca :
- JasperReports, para fazer impressão de relatórios;
(Porém, estou com problemas na geração do relatório, talvez por conta da compatibilidade de versões do java).

Nesse projeto, foi usada a IDE:
- Netbeans

Nesse projeto, foi usado o banco de dados :
MySQL


O projeto tem como objetivo, contemplar negócios de consertos de periféricos, ou seja, celulares, computadores, impressoras, etc.
Mas também podendo ajudar em serviços de consertos de maquina de lavar, mecânicas, e por ai vai, tudo que necessite de uma organização e armazenamento de informações.


Nesse sistema, temos o CRUD funcionando muito bem, onde temos a inserção de dados no banco, alteração, atualização e deleções.

No sistema temos 2 tipos de usuários : 
- Administrador
- Usuário comum

O administrador ele tem acesso a todas as funcionalidades do sistema, já o usuário comum, tem acesso apenas em emissões de relatórios e cadastros de clientes.

No banco de dados temos os relacionamentos de tabelas, onde temos a chave estrangeira (idCliente) que é usada para vincular Ordens de serviços aos clientes, para que nenhuma ordem de serviço esteja sem um cliente vinculado.


