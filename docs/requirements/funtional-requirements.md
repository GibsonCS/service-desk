# Requisitos Funcionais - Service Desk System

## 1. Objetivo

Este documento define os requisitos funcionais do Sistema de Chamados, especificando as funcionalidades disponibilizadas para autenticação, abertura e acompanhamento de chamados, atendimento técnico, administração da operação e consulta de indicadores.

Os requisitos servem como referência para o desenvolvimento, a validação das regras de negócio e a elaboração dos testes automatizados.

## 2. Linguagem Ubíqua

O sistema adota os seguintes termos de domínio:

| Termo | Definição |
|---|---|
| **User** | Pessoa/conta de acesso utilizada para autenticação, autorização e participação nas operações do sistema. O User também representa o colaborador no contexto do Service Desk. |
| **Role** | Enum de autorização associado ao User. Define as permissões de acesso às funcionalidades do sistema. |
| **Administrador** | Papel de autorização representado por `Role.ADMIN`, responsável por operações administrativas do sistema. Não é uma entidade separada. |
| **Solicitante (Requester)** | User associado à abertura de um chamado. |
| **Responsável (Assignee)** | User atualmente atribuído ao atendimento de um chamado. A atribuição é permitida conforme as roles de autorização definidas pelo sistema. |
| **Chamado (Ticket)** | Solicitação de atendimento registrada no sistema. |
| **Atendimento** | Processo de tratamento de um chamado por um User autorizado a executar operações técnicas. |
| **Setor (Sector)** | Unidade organizacional à qual Users podem pertencer e que pode ser responsável pelo atendimento de chamados. |

**Importante:** no contexto do Service Desk, User concentra a identidade/conta de acesso e os dados necessários para sua participação na organização. Não existe uma entidade separada de Colaborador ou Técnico. A distinção entre colaborador, técnico e administrador, quando necessária para autorização, é expressa pelas roles do User.

O e-mail do User é utilizado como seu login.

## 3. Domínio do Sistema

### 3.1. Domínio Central — Gestão do Atendimento de Chamados

O domínio central é responsável pelo ciclo de vida dos chamados e pelo atendimento realizado por Users autorizados.

### 3.2. Domínios de Suporte

- **Identidade e Acesso:** autenticação, credenciais, contas de acesso e roles de autorização dos Users.
- **Gestão de Organização:** setores e vínculo organizacional dos Users.
- **Indicadores e relatórios:** consolidação e consulta de informações operacionais.

## 4. Atores do Sistema

| Ator | Descrição |
|---|---|
| **User** | Pessoa cadastrada no sistema que pode utilizar as funcionalidades disponíveis de acordo com suas roles. |
| **User com role TECHNICIAN** | User autorizado a executar operações de atendimento técnico. |
| **Administrador** | User autenticado com a role `ADMIN`, responsável por administrar Users, setores, permissões e funcionalidades administrativas. |

> **Nota:** User é o conceito principal de identidade e participação no sistema. As roles determinam as permissões adicionais de acesso às funcionalidades.

## 5. Requisitos Funcionais

### 5.1. Domínio Central — Gestão do Atendimento de Chamados

**RF01 — Abrir chamado**

O sistema deverá permitir que um **User** registre um chamado informando, no mínimo, título, descrição e setor responsável.

**RF02 — Identificar chamado**

O sistema deverá gerar um identificador único para cada chamado registrado.

**RF03 — Registrar solicitante**

O sistema deverá associar cada chamado ao **User** que realizou sua abertura, identificado no domínio como seu **Solicitante**. O atributo `Ticket.requesterId` deverá armazenar exclusivamente o identificador do User (`User.id`).

Quando a abertura do chamado ocorrer por um User autenticado, a aplicação deverá utilizar o `User.id` da identidade autenticada como solicitante.

**RF04 — Definir prioridade**

O sistema deverá permitir a classificação dos chamados conforme os níveis de prioridade:

- Baixa;
- Média;
- Alta;
- Crítica.

**RF05 — Consultar chamado**

O sistema deverá permitir a consulta dos dados de um chamado.

**RF06 — Atualizar dados do chamado**

O sistema deverá permitir a atualização dos dados de um chamado, respeitando as restrições aplicáveis ao seu estado atual.

**RF07 — Consultar chamados por critérios**

O sistema deverá permitir a filtragem de chamados pelos seguintes critérios:

- Status;
- Prioridade;
- Solicitante;
- Setor;
- Responsável;
- Período de abertura.

**RF08 — Atribuir responsável**

O sistema deverá permitir a atribuição de um **User** como responsável pelo atendimento de um chamado.

**RF09 — Alterar responsável**

O sistema deverá permitir a alteração do User responsável pelo atendimento de um chamado, registrando a mudança no histórico.

**RF10 — Iniciar atendimento**

O sistema deverá permitir o início do atendimento de um chamado pendente.

**RF11 — Concluir chamado**

O sistema deverá permitir o registro da conclusão de um chamado em atendimento.

**RF12 — Reabrir chamado**

O sistema deverá permitir a reabertura de chamados concluídos para que retornem ao atendimento.

**RF13 — Cancelar chamado**

O sistema deverá permitir o cancelamento de chamados, respeitando as regras de negócio aplicáveis.

### 5.2. Comentários e Histórico

**RF14 — Adicionar comentário**

O sistema deverá permitir a adição de comentários a um chamado.

**RF15 — Consultar comentários**

O sistema deverá permitir a consulta dos comentários de um chamado.

**RF16 — Consultar histórico**

O sistema deverá permitir a consulta do histórico de um chamado.

### 5.3. Controle de Tempo de Atendimento

**RF17 — Registrar data de abertura**

O sistema deverá registrar automaticamente a data e a hora de abertura de cada chamado.

**RF18 — Registrar início do atendimento**

O sistema deverá registrar a data e a hora em que o atendimento de um chamado for iniciado pela primeira vez.

**RF19 — Registrar conclusão**

O sistema deverá registrar a data e a hora de conclusão do atendimento, preservando as informações de conclusões anteriores quando houver reabertura.

**RF20 — Calcular tempo de solução**

O sistema deverá calcular o tempo de solução dos chamados concluídos, considerando o horário de funcionamento configurado, os dias úteis e os feriados cadastrados.

### 5.4. Domínio de Suporte — Identidade e Acesso

**RF21 — Cadastrar User**

O sistema deverá permitir o cadastro de um User com nome, endereço de e-mail, senha e uma role de autorização, conforme as regras do sistema. O endereço de e-mail deverá ser utilizado como login e deverá ser único.

**RF22 — Consultar User**

O sistema deverá permitir a consulta dos dados de um User.

**RF23 — Atualizar User**

O sistema deverá permitir a atualização dos dados cadastrais de um User, como nome, e-mail e senha. Alterações no e-mail deverão respeitar sua utilização como login e sua unicidade.

**RF24 — Desativar User**

O sistema deverá permitir a desativação de Users sem excluir seus registros históricos.

**RF25 — Autenticar User**

O sistema deverá autenticar o User por meio do e-mail e senha e disponibilizar acesso às funcionalidades conforme a role atribuída.

**RF26 — Gerenciar role de acesso**

O sistema deverá permitir a atribuição e alteração da role de um User entre `COLLABORATOR`, `TECHNICIAN` e `ADMIN`.

**RF27 — Consultar próprio perfil**

O sistema deverá permitir que um User consulte seus próprios dados cadastrais e sua role de acesso.

### 5.5. Domínio de Suporte — Gestão de Organização

**RF28 — Cadastrar setor**

O sistema deverá permitir o cadastro de setores responsáveis pelo recebimento e atendimento de chamados.

**RF29 — Consultar setores**

O sistema deverá permitir a consulta dos setores cadastrados.

**RF30 — Atualizar e desativar setor**

O sistema deverá permitir a atualização e desativação de setores, preservando os registros históricos relacionados.

### 5.6. Domínio de Suporte — Indicadores e Relatórios

**RF31 — Consultar indicadores de chamados**

O sistema deverá disponibilizar indicadores quantitativos de chamados, permitindo sua análise por status, prioridade e setor.

**RF32 — Consultar indicadores por User responsável**

O sistema deverá permitir a consulta de indicadores relacionados aos Users responsáveis pelo atendimento.

**RF33 — Consultar tempo médio de solução**

O sistema deverá disponibilizar o tempo médio de solução dos chamados concluídos, conforme os filtros e critérios definidos.

## 6. Regras Gerais

**RG01 — Autenticação e autorização**

As funcionalidades protegidas deverão ser acessíveis somente após autenticação, respeitando a role do User autenticado.

**RG02 — Separação entre identidade e autorização**

User representa a pessoa/conta utilizada no sistema. Role representa sua autorização. Role não deve ser utilizada como substituta de uma entidade de domínio inexistente.

**RG03 — Identidade do solicitante**

O `Ticket.requesterId` deverá referenciar exclusivamente `User.id`. Em operações autenticadas, o solicitante deverá ser derivado da identidade autenticada no backend.

**RG04 — Identidade do responsável**

O `Ticket.assigneeId`, quando informado, deverá referenciar exclusivamente `User.id`. A aplicação deverá validar se o User possui a role necessária para assumir a responsabilidade pelo chamado.

**RG05 — Integridade do ciclo de vida**

As mudanças de status do chamado deverão respeitar as transições permitidas pelo ciclo de vida definido pelo domínio.

**RG06 — Histórico**

Alterações relevantes do chamado deverão ser registradas para preservar a rastreabilidade do atendimento.

**RG07 — Preservação histórica**

A desativação de um User ou de um setor não deverá apagar os registros históricos de chamados e atendimentos relacionados.

**RG08 — Controle temporal**

As datas de abertura, início do atendimento, conclusão e demais eventos relevantes deverão ser registradas de forma consistente e permitir o cálculo do tempo de solução.

**RG09 — Setor do User e setor do Ticket**

O setor ao qual um User pertence (`User.sectorId`) representa seu vínculo organizacional e não determina necessariamente o setor responsável pelo atendimento de um Ticket (`Ticket.sectorId`).

**RG10 — Role única**

Cada User possui uma única role de autorização. A role determina o conjunto de permissões aplicável ao User.

## 7. Regras, refinamento e evolução da implementação

Os Requisitos Funcionais descrevem **o que o sistema deve fazer**. As Regras de Negócio e Regras Gerais refinam essas funcionalidades com restrições, invariantes e critérios de segurança/autorização.

A implementação seguirá uma abordagem incremental:

1. Cada RF poderá ser implementado acompanhado das regras de domínio já conhecidas e necessárias para seu funcionamento correto.
2. Regras que dependem de funcionalidades ainda não implementadas, como autenticação, poderão permanecer pendentes e ser incorporadas posteriormente.
3. Quando uma funcionalidade de suporte for implementada, os RFs que dependem dela deverão ser revisados para incorporar as regras correspondentes.
4. A implementação de uma funcionalidade não implica que todas as suas regras futuras estejam concluídas; o status detalhado fica registrado em `docs/requirements/implementation-status.md`.
5. As regras de negócio do atendimento deverão permanecer independentes da infraestrutura de autenticação e persistência sempre que possível.
6. Regras de segurança e autorização deverão ser aplicadas no backend, independentemente das restrições existentes na interface.
7. User concentra, no contexto do Service Desk, a identidade de acesso e os dados necessários para sua participação na organização.
8. `Ticket.requesterId` e `Ticket.assigneeId` deverão referenciar `User.id`.
9. Em operações autenticadas, o identificador do solicitante deverá ser derivado da identidade autenticada no backend, evitando confiar em um `requesterId` arbitrário enviado pelo cliente.
10. A role `TECHNICIAN` representa autorização para executar operações técnicas; não é necessário modelar uma entidade Técnico enquanto não houver atributos ou comportamentos de domínio próprios que justifiquem essa entidade.
11. A role `ADMIN` representa autorização administrativa e não é uma entidade de domínio.
12. O e-mail do User é utilizado como login e deve permanecer único.
13. Os cálculos de tempo de solução deverão considerar os períodos efetivos de atendimento, regras de horário comercial, dias úteis e feriados.
14. Os requisitos e regras poderão ser refinados durante o desenvolvimento, desde que as alterações sejam documentadas e mantenham a rastreabilidade das decisões.

___

#### *v2.6.0*
