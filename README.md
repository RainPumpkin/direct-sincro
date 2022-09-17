# Direct-Sincro
Direct-Sincro Project Repository

Para iniciar o projeto é necessário instalar:    
* [Postgres14](https://www.postgresql.org/download/)    
* [JDK 17](https://jdk.java.net/archive/)    
* [Node.js v16](https://nodejs.org/en/download/)     
* [Gradle](https://gradle.org/releases/)    

### Nota importante:

Após instalar o **Postgres14**, é necessário ir ao ficheiro ``` \Program Files\PostgreSQL\14\data\pg_hba.conf  ```, abrir o ficheiro e editar, no fim deste, o que está na primeira imagem (scram-sha-256) para a segunda (trust)

![image](https://user-images.githubusercontent.com/61508378/171878847-40b27ed5-6238-415c-941c-71ef04050d95.png)
![image](https://user-images.githubusercontent.com/61508378/171878890-e7e66fd0-d5fb-4ec8-8f0d-739ee5ba039c.png)

## Instruções para utilizar/inicializar Base de dados:    
* Criar base de dados com nome **directsincro** 

* Executar no **pgadmin**, sobre a base de dados **directsincro**, o script ```createschema.sql``` e o script ```insertdata.sql``` , presentes na pasta ```\database```    

Servidor:     
* Criar variavel de ambiente ```"DirectSincroDB"```, com o ```value:"jdbc:postgresql://localhost:5432/directsincro?user=postgres&password=123" ```

* Necessita de um e-mail da google ``` exemplo@gmail.com```

* Criar as variáveis de ambiente ```"MAIL_SENDER_USERNAME"``` e ```"MAIL_SENDER_PASSWORD"```, a primeira com um endereço de ```e-mail``` e a segunda a ```password```. Para obter a ```password ``` é necessário:

    * Ir às ```definições``` de conta do e-mail;
    
    * Ativar os ```dois passos de autenticação```, caso ainda não esteja ativo; 
    
    * Ir a ```palavras-passe de aplicações``` gerar uma password nova; 

    * Carregar em ```Selecionar aplicação``` e optar por ```"outro"```;

    * Indicar o nome da aplicação, por exemplo ```springbootemail``` e clicar em ```gerar```;
    
    * É esse o valor que deve ser utilizado como variável de ambiente ```"MAIL_SENDER_PASSWORD"```; 

* Reiniciar o computador, para garantir que as variáveis de ambiente estão acessíveis. 
* Na pasta ```server\direct-sincro\ ```executar o comando ```gradlew bootrun ```    
* O servidor encontra-se agora a receber e responder pedidos no porto ```8080```.     

## Instruções para iniciar o **Front-End**:   

* Por default, o servidor será executado sobre o porto ```8080```. É necessário isto para o **front-end**, de forma a que o proxy tenha sucesso     
* Na pasta ``` frontend\sincroui\ ``` executar o comando ``` npm install  ```   
* De seguida, para correr o frontend, executar o comando ``` npm start    ```  
* O frontend encontra-se presente no porto ```3001   ```  


## Instruções para iniciar o **Simulador SCOT**:

* executar o comando ``` npm install ``` na diretoria ``` \SCOT_Simulator\ ``` - este comando instala todos os pacotes de quais o **Simulador SCOT** depende 

* executar o comando ``` node index.js ``` na diretoria ``` \SCOT_Simulator\ ``` - este comando coloca o Simulador em execução no porto ``` 4000 ```

A página inicial do simulador está disponível no seguinte endereço ```http://localhost:4000/debugger/home/```. Daqui é possível navegar entre as diferentes páginas, basta aceder ao menu disponível no canto superior esquerdo.

## Instruções para iniciar o **Simulador SIGET**:
    
* executar o comando ``` npm install ``` na diretoria ``` \SIGET_Simulator\ ``` - este comando instala todos os pacotes de quais o **Simulador SIGET** depende 

* executar o comando ``` node index.js ``` na diretoria ``` \SIGET_Simulator\Simulator ``` - este comando coloca o Simulador em execução no porto ``` 3000 ```

Ao iniciar o servidor, é lido o ficheiro ``` \SIGET_Simulator\Data\eventos.json```, que contém as contraordenações que serão enviadas para o **Simulador SCOT** e o **SI DIRECT-SINCRO**. É importante ter previamente em execução o **Simulador SCOT** e o **SI DIRECT-SINCRO**, para que seja possível enviar as contraordenações.

Existe um ficheiro na diretoria ```\SIGET_Simulator\Data\IMT.json``` que contém as matrículas associadas aos respetivos **nif**, caso o utilizador pretenda adicionar uma nova matrícula ao **Simulador SIGET** através do **SI DIRECT-SINCRO** é necessário previamente associar essa matrícula ao **nif** pretendido. Caso contrário, o **Simulador SIGET** não irá aceitar a inserção de uma nova matrícula.




