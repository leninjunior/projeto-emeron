# projeto-emeron
Sistema de Gerenciamento de Salas

# Sistema de Gerenciamento de Salas
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/leninjunior/projeto-emeron/blob/main/LICENSE) 

# Sobre o projeto

O presente trabalho visa apresentar o desenvolvimento de um sistema de Gerenciamento de locais e reservas, utilizando as melhores práticas no desenvolvimento de sistemas e gerência de projetos. No decorrer da aplicação foram utilizadas metodologias ágeis para que todo o processo de construção do sistema fossem feitas quase de forma automática pelo próprio gerenciamento.
 buscando facilitar a gerência com a finalidade de gerar eficiência e eficácia nas reservas e planejamento e auxiliando os colaboradores até segurança e frear a incompatibilidade de horários. 



## Imagem do Sistema.


![Web 1](https://github.com/leninjunior/projeto-emeron/blob/main/github.PNG)


# Diagrama Relacional

O diagrama entidade relacionamento é o início de mapeamento das tabelas do banco de dados e seus relacionamentos, sendo que nesse momento começa-se a visualizar os atributos e as regras normais que serão aplicadas.


![Web 1](https://github.com/leninjunior/projeto-emeron/blob/main/diagramarelacional.png)

# Diagrama de Classe
O diagrama de classes é essencial pois une as estruturas de negócio e interface, demonstrando as tabelas com seus atributos e as funções que serão implementadas relacionando-as. Seu desenvolvimento é utilizado como base por vários outros diagramas.

![Web 1](https://github.com/leninjunior/projeto-emeron/blob/main/Main.png)

# Tecnologias utilizadas
## Back end
- JAVA 8
- SPRING BOOT
- SPRING DATA JPA
- MAVEN
- JASPERREPORTS
- SPRING SECURITY
- SPRING MVC
## Front end
- JS 
- HTML
- CSS
- JQUERY
- BOOTSTRAP v3
- THYMELEAF


## Banco de Dados
- Banco de dados: Postgresql

# Como executar o projeto

## Back end
Pré-requisitos: Java 8




```bash
# clonar repositório
git clone https://github.com/leninjunior/projeto-emeron.git

# entrar na pasta do projeto back end
cd projetoemeron

# executar o projeto
./mvnw spring-boot:run
```

```## Observações
# Você precisará gerar uma senha no projeto para conseguir fazer o login, segue o código.

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String result = encoder.encode("123");
 System.out.println(result);
 


```

# Autor

Lenin Junior Freire Bessa

https://www.linkedin.com/in/leninjunior/

