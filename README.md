# Gerenciamento de Carrinho de Compras - Java + Spring Boot

![java10x Logo](https://java10x.dev/wp-content/uploads/2024/12/logo-java.png)

> Um serviço robusto de gerenciamento de carrinho de compras com tecnologias modernas, documentação completa e pronta para produção.

## 📖 Sobre o Projeto

Este projeto é um serviço de gerenciamento de carrinho de compras desenvolvido como parte do módulo de Spring Boot do curso java10x. Ele foi significativamente aprimorado com:

- Documentação API via Swagger UI
- Suíte abrangente de testes automatizados
- Configuração para deploy em EC2 AWS
- Melhorias de performance e segurança

**Principais Objetivos:**
- Fornecer uma API RESTful completa e bem documentada
- Garantir alta disponibilidade e escalabilidade
- Oferecer estatísticas de uso através do Actuator
- Facilitar o CI/CD com Docker e AWS

## 🛠 Stack Tecnológica

### Backend
- **Java 17** - LTS com melhor desempenho e features modernas
- **Spring Boot 3** - Framework principal
- **Spring Data MongoDB** - Integração com banco NoSQL
- **Spring Cache (Redis)** - Cache distribuído
- **OpenFeign** - Client HTTP declarativo
- **Lombok** - Redução de boilerplate
- **MapStruct** - Mapeamento entre DTOs e entidades

### Infraestrutura
- **Docker** - Containerização
- **Docker Compose** - Orquestração local

## ⚙️ Melhorias no Projeto
- - [x] Documentar a API utilizando Swagger
- - [x] Implementar testes unitários
- - [ ] Implementar testes de integração
- - [ ] Adicionar Mocks utilizando Mockito
- - [ ] Fazer o deploy do projeto em uma instância EC2 na AWS

## ✨ Funcionalidades Principais

### Carrinho de Compras
- ✅ Adicionar/remover produtos
- ✅ Atualizar quantidades
- ✅ Calcular total automaticamente
- ✅ Finalizar compra (checkout)

### Produtos
- 📦 Listagem paginada
- 🔍 Busca por categoria/nome
- 📊 Estatísticas de vendas

### Usuários
- 🔐 Autenticação básica (em desenvolvimento)
- 👤 Histórico de pedidos

### Admin
- 📈 Dashboard de métricas
- ⚙️ Gerenciamento de estoque
- 🔄 Sincronização com API externa

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Docker 20.10+
- MongoDB 5.0+ (opcional)
- Redis 6.2+ (opcional)

### Com Docker Compose (recomendado)
```bash
git clone https://github.com/seu-usuario/QuickShop.git
cd SquickShop
docker-compose up -d
```

A aplicação estará disponível em: http://localhost:8080

### Localmente
```bash
mvn spring-boot:run
```

## 📚 Documentação da API

Acesse a documentação interativa:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI: http://localhost:8080/v3/api-docs

## 🧪 Testes

```bash
# Todos os testes
mvn test

# Com cobertura
mvn jacoco:report
```

## 🌐 Deploy na AWS EC2

1. Criar instância EC2 (Amazon Linux 2)
2. Instalar Docker:
```bash
sudo yum update -y
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```

3. Clonar e executar:
```bash
git clone https://github.com/seu-usuario/QuickShop.git
cd QuickShop
docker-compose -f docker-compose.prod.yml up -d --build
```

## 🤝 Contribuição

Contribuições são bem-vindas! Siga estes passos:

1. Faça um fork do projeto
2. Crie sua branch (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

Desenvolvido com ❤️ por Caio Vasconcelos | [LinkedIn](https://linkedin.com/in/cabavas)
