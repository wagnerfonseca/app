BuscaCep
-------------------

API para buscar endereços através do CEP.

## Maven
```
<dependency>
  <groupId>com.github.wagnerfonseca</groupId>
  <artifactId>buscacep</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Java

### <i class="icon-search"></i> Postmon
Você pode utilizar o consumo através do servidor Postmon
```
BuscaCep busca = new BuscaCepPostmon();
busca.buscaCep("012160001");

```

### <i class="icon-search"></i> ViaCep
```
BuscaCep busca = new BuscaCepViaCEP();
busca.buscaCep("20030-120");

```

