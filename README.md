
## Descrição
Execução de procedimento armazenado no DB2 de 3 formas; 

<p align="center">

 ![Badge](https://img.shields.io/static/v1?label=java&message=checked&color=blue&style=flat-square&logo=java)
 
 </p>

### Pré Requisito

- Imagem do docker do db2;
- Java 11+
- Lombok


#### Banco Db2
Baixar a imagem do docker do brunão em:
https://hub.docker.com/r/ibmcom/db2

```
docker pull ibmcom/db2
```

Subir o container com:

```
docker run -itd --name mydb2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=123 -e DBNAME=testdb -v C:\Temp\database ibmcom/db2
```

Obs: 
- Nome do usuário do db2 é *db2inst1*;
- Verifique o caminho do volume;

#### Stored Procedure 

Criar a procedure de exemplo no banco após subir a imagem do db2:

```
CREATE PROCEDURE PROC_DESBLOQUEIO (IN id INT , OUT CdRetorno int, OUT CdSqlRet int) LANGUAGE SQL
BEGIN
   set  CdRetorno = 1;
   set  CdSqlRet = 2;
 END
```

A proc simplesmente recebe um único número como parametro e tem como retorno uma tupla com 2 
valores inteiros;

Caso queira testar uma proc pelo DBeaver ou outra ferramenta segue a instrução:

```
CALL PROC_DESBLOQUEIO('1',?,?);
```

