# Diagrama de Classes — Sistema de Chamados

## 1. Modelo de Domínio

Este diagrama representa as entidades de domínio do sistema, sem dependências de Spring Boot, JPA ou outras tecnologias de infraestrutura.

```mermaid
classDiagram
    direction TB

    class User {
        -UUID id
        -String name
        -String email
        -String passwordHash
        -UserRole role
        -Boolean active
        -UUID sectorId
        -LocalDate createdAt
        -LocalDate updatedAt
    }

    class Sector {
        -UUID id
        -String name
        -Boolean active
        -LocalDate createdAt
        -LocalDate updatedAt
    }

    class Ticket {
        -UUID id
        -String title
        -String description
        -TicketStatus status
        -TicketPriority priority
        -UUID requesterId
        -UUID sectorId
        -UUID assigneeId
        -LocalDate createdAt
        -LocalDate firstStartedAt
        -LocalDate resolvedAt
        -LocalDate cancelledAt
        -LocalDate archivedAt
    }

    class TicketComment {
        -UUID id
        -UUID ticketId
        -UUID authorId
        -String content
        -LocalDate createdAt
    }

    class UserRole {
        <<enumeration>>
        USER
        SUPPORT
        ADMIN
    }

    class TicketStatus {
        <<enumeration>>
        PENDENTE
        EM_ATENDIMENTO
        CONCLUIDO
        CANCELADO
    }

    class TicketPriority {
        <<enumeration>>
        BAIXA
        MEDIA
        ALTA
        CRITICA
    }



    User --> UserRole : role
    Ticket --> TicketStatus : status
    Ticket --> TicketPriority : prioridade

    Sector "1" <-- "0..*" User : pertence
    User "1" <-- "0..*" Ticket : requisitado
    Sector "1" <-- "0..*" Ticket : pertence
    User "0..1" <-- "0..*" Ticket : atendido
    Ticket "1" <-- "0..*" TicketComment : comentado
    User "1" <-- "0..*" TicketComment : autor
```

### Relacionamentos do domínio

- **User e Sector:** um setor pode possuir vários usuários; cada usuário pertence a um setor.
- **Ticket e User:** cada chamado possui um solicitante e pode ter um responsável pelo atendimento. O responsável pode ser nulo até a atribuição.
- **Ticket e Sector:** cada chamado pertence a um setor.
- **TicketComment:** registra os comentários associados a um chamado e o usuário que os escreveu.
- **Enums:** restringem os valores válidos de papéis, status, prioridades e tipos de evento.