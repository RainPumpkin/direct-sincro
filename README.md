# direct-sincro
Direct-Sincro Project Repository

Instalar:    
-Postgres14    
-JDK 17    
-Node.js v16     
-Gradle    

IMPORTANTE:
Após instalar o Postgres14, é necessário ir ao ficheiro \Program Files\PostgreSQL\14\data\pg_hba.conf , abrir o ficheiro e editar, no fim deste, o que está na primeira imagem para a segunda
![image](https://user-images.githubusercontent.com/61508378/171878847-40b27ed5-6238-415c-941c-71ef04050d95.png)
![image](https://user-images.githubusercontent.com/61508378/171878890-e7e66fd0-d5fb-4ec8-8f0d-739ee5ba039c.png)

Instruções para utlizar/inicializar:    
Base de dados:    
-Criar base de dados com nome directsincro    
-Executar no pgadmin, sobre a base de dados directsincro, o script createschema.sql e o script insertdata.sql , presentes na pasta database    

Servidor:     
-Criar variavel de ambiente "DirectSincroDB", com o value:"jdbc:postgresql://localhost:5432/directsincro?user=postgres&password=123"     
-Reiniciar o computador após a mesma, para previnir problemas desta não estar acessivel pelo programa     
-na pasta server\direct-sincro\ executar o comando gradlew bootrun     
-O servidor encontra-se agora ouvir e responder pedidos do porto 8080.     

Front-end:     
-Por default, o servidor será executado sobre o porto 8080. É necessário isto para o front-end, de forma a que o proxy tenha sucesso     
-Na pasta frontend\sincroui\ executar o comando npm install     
-De seguida, para correr o frontend, executar o comando npm start     
-O frontend encontra-se presente no porto 3000     
Nota: de momento, o frontend está muito incompleto, não sendo possivel usar o mesmo para testar o trabalho.     

Simulador Siget:     
-Na pasta SIGET_Simulator\ executar o comando npm install     
-Na pasta SIGET_Simulator\Simulator executer o comando node index.js     
-Isto irá enviar eventos para o servidor, presentes no ficheiro \SIGET_Simulator\Data\eventos.json
