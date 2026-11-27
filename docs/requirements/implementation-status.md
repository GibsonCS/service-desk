# Status de Implementação - Service Desk

Este documento acompanha a evolução da implementação dos requisitos funcionais.

A especificação dos requisitos permanece em `docs/requirements/funtional-requirements.md`. Este arquivo serve como acompanhamento de implementação para evitar confundir **requisito definido** com **funcionalidade já implementada**.

## Legenda

- [x] Implementado e coberto pelo fluxo atual.
- [ ] Ainda não implementado.
- [~] Parcialmente implementado ou depende de uma etapa ainda não concluída.

## Requisitos funcionais

### Gestão de Chamados

- [~] RF01 — Abrir chamado
- [x] RF02 — Identificar chamado
- [~] RF03 — Registrar solicitante
- [x] RF04 — Definir prioridade
- [x] RF05 — Consultar chamado
- [ ] RF06 — Atualizar dados do chamado
- [ ] RF07 — Consultar chamados por critérios
- [ ] RF08 — Atribuir responsável
- [ ] RF09 — Alterar responsável
- [ ] RF10 — Iniciar atendimento
- [ ] RF11 — Concluir chamado
- [ ] RF12 — Reabrir chamado
- [ ] RF13 — Cancelar chamado

### Comentários e Histórico

- [ ] RF14 — Adicionar comentário
- [ ] RF15 — Consultar comentários
- [ ] RF16 — Consultar histórico

### Controle de Tempo de Atendimento

- [x] RF17 — Registrar data de abertura
- [ ] RF18 — Registrar início do atendimento
- [ ] RF19 — Registrar conclusão
- [ ] RF20 — Calcular tempo de solução

### Identidade e Acesso

- [~] RF21 — Cadastrar User
- [x] RF22 — Consultar User
- [x] RF23 — Atualizar User
- [x] RF24 — Desativar User
- [ ] RF25 — Autenticar User
- [ ] RF26 — Gerenciar role de acesso
- [ ] RF27 — Consultar próprio perfil

### Gestão de Organização

- [x] RF28 — Cadastrar setor
- [x] RF29 — Consultar setores
- [~] RF30 — Atualizar e desativar setor

### Indicadores e Relatórios

- [ ] RF31 — Consultar indicadores de chamados
- [ ] RF32 — Consultar indicadores por User responsável
- [ ] RF33 — Consultar tempo médio de solução

## Próxima implementação recomendada

**RF10 — Iniciar atendimento**

A implementação deve começar pela regra de domínio da transição:

`PENDENTE -> EM_ATENDIMENTO`

e pelos testes das transições válidas e inválidas. A autorização baseada em `Role.TECHNICIAN` ou `Role.ADMIN` deve permanecer na camada de aplicação/autorização, sem acoplar o agregado `Ticket` à entidade `User` ou à enum `Role`.

#### *v1.0.0*
