
# Projeto-Full-Stack-Hexagonal
Projeto Full Stack Hexagonal - Spring 3.3.2 - Angular 18 - Docker - MySql e Testes Unitários com Mockito

Descrição:
<br>
Este projeto é uma aplicação Java baseada no framework Spring Boot, containerizada com Docker, utilizando MySQL como banco de dados. Além disso, o projeto inclui uma suíte de testes unitários utilizando o Mockito para simular componentes e testar o comportamento da aplicação de forma isolada.

Tecnologias Utilizadas:
<br>
Java 17
<br>
Spring Boot (versão 3.3.2)
<br>
Docker (versão 20.x ou superior)
<br>
Docker Compose (versão 1.27.x ou superior)
<br>
MySQL (versão 8.x)
<br>
JUnit 5 para testes unitários
<br>
Mockito para criação de mocks nos testes

Estrutura do Projeto:
<br>
/src: Contém o código-fonte da aplicação Spring Boot.
<br>
/src/main/resources: Contém arquivos de configuração, incluindo application.yml para configurações do Spring.
<br>
/src/test/java: Contém os testes unitários da aplicação.
<br>
docker-compose.yml: Arquivo para orquestração de containers, incluindo o container da aplicação e do banco de dados MySQL.

Pré-requisitos:
<br>
Antes de iniciar, você precisa ter os seguintes softwares instalados na sua máquina:
<br>
Java 17, Docker e Docker Compose.

Como Executar:
<br>
Estes comandos irão:
<br>
Criar uma rede Docker para a aplicação e o banco de dados.
<br>
Subir um container MySQL.
<br>
Subir um container para a aplicação Spring Boot.
<br>

1 - Construir a Imagem Docker:
<br>
No diretório raiz do projeto, execute o seguinte comando para construir a imagem Docker:
<br>
docker build -t d3n15hexagonal

2 - Subir os Containers com Docker Compose:
<br>
Use o Docker Compose para subir os containers da aplicação e do MySQL.
<br>
docker-compose up

3 - Executando o Projeto:
<br>
Usando o Maven
<br>
Instalar Maven:
<br>
sudo apt install maven

4 - Compile o projeto:
<br>
mvn clean install
<br>

5 - Execute a aplicação:
<br>
mvn spring-boot:run
<br>

6 - Usando o Executável Gerado
<br>
Outra forma de executar o projeto é usando o arquivo JAR gerado após a compilação:
<br>
Compile o projeto (caso não tenha feito ainda):
<br>
mvn clean package
<br>

7 - Execute o JAR:
<br>
java -jar back-hexagonal-0.0.1-SNAPSHOT.jar
<br>

8 - Acessar a Aplicação:
<br>
A aplicação estará disponível em http://localhost:8080
<br>

9 - Parar os Containers:
<br>
Para parar e remover os containers criados pelo Docker Compose, execute:
<br>
docker-compose down
<br>

Testes:
<br>
Testes Unitários com JUnit 5 e Mockito.
<br>
Os testes unitários estão localizados em /src/test/java. 
<br>
Eles foram desenvolvidos usando JUnit 5 e Mockito para simulação de componentes.
<br>
<br>
Executando os Testes
<br>
Para executar todos os testes, utilize o seguinte comando Maven:
<br>
mvn test
