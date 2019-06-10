# Projeto de Estudos com OAuth2 + Angular + MySQL utilizando arquitetura de Microsserviços

O projeto está dividido da seguinte forma:

  - oauth-authorization-server: Servidor de autenticação OAuth;
  
  - oauth-resource-server-1: Servidor de recursos 1 (API);
    - A validação do token é feito no servidor OAuth;
    
  - oauth-resource-server-2: Servidor de recursos 2 (API);
    - A validação do token é feito no próprio servidor;
    
  - oauth-discovery: Responsável por gerenciar o status e a localização dos Microsserviços em nossa rede;
  
  - oauth-gateway: Funciona como uma porta de entrada aplicação, todo o tráfego passa por ele antes de ser encaminhado para o microserviço específico respeitando as rotas que são configuradas no mesmo.
  
  - client1: Projeto angular com autenticação utilizando o fluxo Authorization Code
  
  - client2: Projeto angular com autenticação utilizando o fluxo Password
  
  - oauth-sso-ui: Projeto com o objetivo de centralizar o login do usuário somente nessa entrada e o mesmo consiga entrar nos portais client1 e client2 sem a necessidade de realizar login novamente;
  
Tecnologias Utilizadas:

  - Spring Boot 2+
  
  - Spring Cloud 2+
  
  - Angular 7+
  
  - Eureka (Netflix)
