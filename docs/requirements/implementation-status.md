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

## Como interpretar o status

O status acompanha a **implementação incremental** dos RFs. Um RF pode estar marcado como parcialmente implementado quando a funcionalidade principal já existe, mas ainda depende de regras ou infraestrutura que serão implementadas posteriormente.

A ordem de implementação não exige que todos os RFs sejam concluídos antes das RN/RG. A abordagem adotada é:

1. implementar o RF;
2. aplicar as regras de domínio já conhecidas e necessárias;
3. criar/ajustar os testes;
4. refatorar;
5. registrar regras dependentes de funcionalidades futuras como pendências;
6. quando uma funcionalidade de suporte, como autenticação, for implementada, retornar aos RFs afetados e fechar as regras pendentes.

### Pendências de regras por requisito

#### RF01 / RF03 — Solicitante

- [x] Criar e registrar o chamado com um solicitante.
- [x] Persistir `requesterId` como referência a `User.id`.
- [ ] Após a implementação da autenticação, derivar o solicitante da identidade autenticada.
- [ ] Remover o `requesterId` arbitrário do payload de abertura em operações autenticadas.

#### RF08 / RF10 / RF11 — Autorização do atendimento

- [ ] Implementar autenticação.
- [ ] Implementar autorização por `Role`.
- [ ] Aplicar autorização de `TECHNICIAN`/`ADMIN` nas operações de atendimento.
- [ ] Manter as invariantes do ciclo de vida no domínio, sem acoplar `Ticket` a `User` ou `Role`.

## Próxima implementação recomendada

**RF10 — Iniciar atendimento**

A implementação deve começar pela regra de domínio da transição:

`PENDENTE -> EM_ATENDIMENTO`

e pelos testes das transições válidas e inválidas. Neste momento, a regra de autorização por `Role.TECHNICIAN` ou `Role.ADMIN` pode ser registrada como pendência, pois depende da autenticação/autorização ainda não implementada.

Quando a autenticação estiver disponível, os RFs afetados deverão ser revisados para fechar essas regras sem alterar desnecessariamente a definição funcional original.

#### *v1.1.0*
