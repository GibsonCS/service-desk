# Requisitos Funcionais - Service Desk System

## 1. Objetivo

Este documento define os requisitos funcionais do Sistema de Chamados, especificando as funcionalidades disponibilizadas para autenticação, abertura e acompanhamento de chamados, atendimento técnico, administração da operação e consulta de indicadores.

Os requisitos servem como referência para o desenvolvimento, a validação das regras de negócio e a elaboração dos testes automatizados.

## 2. Linguagem Ubíqua

O sistema adota os seguintes termos de domínio:

| Termo | Definição |
|---|---|
| **User** | Conta de acesso e identidade utilizada para autenticação e autorização no sistema. |
| **Colaborador** | Entidade do contexto de Gestão de Pessoas que representa uma pessoa da organização que pode solicitar atendimento. Um Colaborador possui uma referência para a conta de acesso por meio de `userId`. |
| **Técnico** | Entidade do contexto de Gestão de Pessoas que representa um Colaborador habilitado para realizar atendimento e resolução de chamados. Um Técnico referencia um Colaborador por meio de `colaboradorId`. |
| **Administrador** | Papel de acesso representado por `Role.ADMIN`, responsável por operações administrativas do sistema. Não é necessário modelá-lo como uma entidade de Gestão de Pessoas. |
| **Solicitante** | Colaborador associado à abertura de um chamado. |
| **Responsável** | Técnico atualmente atribuído ao atendimento de um chamado. |
| **Chamado** | Solicitação de atendimento registrada no sistema. |
| **Atendimento** | Processo de tratamento de um chamado por um Técnico. |

**Importante:** User representa a identidade/conta de acesso. Colaborador e Técnico representam conceitos do domínio de Gestão de Pessoas e não são apenas valores de `Role`. O vínculo entre identidade e pessoa é explícito por meio de `userId` e `colaboradorId`. As roles permanecem em User para fins de autenticação e autorização.

## 3. Domínio do Sistema

### 3.1. Domínio Central — Gestão do Atendimento de Chamados

O domínio central é responsável pelo ciclo de vida dos chamados e pelo atendimento realizado pelos Técnicos.

### 3.2. Domínios de Suporte

- **Identidade e Acesso:** autenticação, credenciais, contas de acesso e papéis de autorização dos Users.
- **Gestão de Pessoas/Organização:** Colaboradores, Técnicos e setores.
- **Indicadores e relatórios:** consolidação e consulta de informações operacionais.

## 4. Atores do Sistema

| Ator | Descrição |
|---|---|
| **Colaborador** | Pessoa da organização vinculada a um User autenticado e autorizada a abrir e acompanhar suas próprias solicitações. |
| **Técnico** | Colaborador habilitado para atendimento e vinculado a um User autenticado com as permissões necessárias para executar operações técnicas. |
| **Administrador** | User autenticado com a role `ADMIN`, responsável por administrar Users, setores, permissões e funcionalidades administrativas. |

> **Nota:** User não é ator de negócio. User representa a identidade autenticada. Colaborador e Técnico são conceitos do domínio; as roles do User determinam as permissões de acesso às funcionalidades.

## 5. Requisitos Funcionais

### 5.1. Domínio Central — Gestão do Atendimento de Chamados

**RF01 — Abrir chamado**

O sistema deverá permitir que um **Colaborador** autenticado registre um chamado informando, no mínimo, título, descrição e setor responsável.

**RF02 — Identificar chamado**

O sistema deverá gerar um identificador único para cada chamado registrado.

**RF03 — Registrar solicitante**

O sistema deverá associar cada chamado ao **Colaborador** que realizou sua abertura, identificado no domínio como seu **Solicitante**.

**RF04 — Definir prioridade**

O sistema deverá permitir a classificação dos chamados conforme os níveis de prioridade:

- Baixa;
- Média;
- Alta;
- Crítica.

**RF05 — Consultar chamado**

O sistema deverá permitir a consulta dos dados de um chamado de acordo com as permissões do User autenticado.

**RF06 — Atualizar dados do chamado**

O sistema deverá permitir a atualização dos dados de um chamado por Users autorizados, respeitando as restrições aplicáveis ao seu estado atual.

**RF07 — Consultar chamados por critérios**

O sistema deverá permitir a filtragem de chamados pelos seguintes critérios:

- Status;
- Prioridade;
- Solicitante;
- Setor;
- Responsável;
- Período de abertura.

**RF08 — Atribuir responsável**

O sistema deverá permitir que Users autorizados atribuam um **Técnico** como responsável pelo atendimento de um chamado.

**RF09 — Alterar responsável**

O sistema deverá permitir a alteração do **Técnico responsável** pelo atendimento de um chamado, registrando a mudança no histórico.

**RF10 — Iniciar atendimento**

O sistema deverá permitir que um **Técnico** autorizado inicie o atendimento de um chamado pendente.

**RF11 — Concluir chamado**

O sistema deverá permitir que um **Técnico** autorizado registre a conclusão de um chamado em atendimento.

**RF12 — Reabrir chamado**

O sistema deverá permitir a reabertura de chamados concluídos, mediante autorização, para que retornem ao atendimento.

**RF13 — Cancelar chamado**

O sistema deverá permitir o cancelamento de chamados por Users autorizados, respeitando as regras de negócio aplicáveis.

### 5.2. Comentários e Histórico

**RF14 — Adicionar comentário**

O sistema deverá permitir que Users autorizados adicionem comentários a um chamado.

**RF15 — Consultar comentários**

O sistema deverá permitir a consulta dos comentários de um chamado, respeitando as permissões de acesso.

**RF16 — Consultar histórico**

O sistema deverá permitir a consulta do histórico de um chamado por Users autorizados.

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

**RF21 — Cadastrar conta de acesso**

O sistema deverá permitir o cadastro de uma conta de acesso (`User`) com login, endereço de e-mail, senha e roles de autorização, conforme as regras do sistema.

**RF22 — Consultar conta de acesso**

O sistema deverá permitir a consulta dos dados de uma conta de acesso, respeitando as permissões aplicáveis.

**RF23 — Atualizar conta de acesso**

O sistema deverá permitir a atualização dos dados cadastrais de uma conta de acesso autorizada, como login, e-mail e senha.

**RF24 — Desativar conta de acesso**

O sistema deverá permitir que um **Administrador** desative contas de acesso sem excluir seus registros históricos.

**RF25 — Autenticar User**

O sistema deverá autenticar a conta de acesso (`User`) por meio de suas credenciais e disponibilizar acesso às funcionalidades conforme as roles atribuídas.

**RF26 — Gerenciar roles de acesso**

O sistema deverá permitir que **Administradores** atribuam e alterem as roles de acesso de um User entre `COLLABORATOR`, `TECHNICIAN` e `ADMIN`, conforme as regras de autorização.

**RF27 — Consultar próprio perfil**

O sistema deverá permitir que o User autenticado consulte seus próprios dados cadastrais e suas roles de acesso.

### 5.5. Domínio de Suporte — Gestão de Pessoas e Organização

**RF28 — Cadastrar Colaborador**

O sistema deverá permitir o cadastro de um **Colaborador**, vinculando-o a uma conta de acesso (`User`) por meio de `userId`.

**RF29 — Consultar Colaborador**

O sistema deverá permitir a consulta dos dados de um **Colaborador** de acordo com as permissões aplicáveis.

**RF30 — Atualizar Colaborador**

O sistema deverá permitir a atualização dos dados cadastrais de um **Colaborador**, preservando sua referência à conta de acesso.

**RF31 — Cadastrar Técnico**

O sistema deverá permitir o cadastro de um **Técnico** a partir de um Colaborador habilitado para realizar atendimento, estabelecendo a referência por meio de `colaboradorId`.

**RF32 — Consultar e atualizar Técnico**

O sistema deverá permitir a consulta e atualização dos dados específicos de um **Técnico**, como seu nível de atendimento, de acordo com as permissões aplicáveis.

**RF33 — Cadastrar setor**

O sistema deverá permitir o cadastro de setores responsáveis pelo recebimento e atendimento de chamados.

**RF34 — Consultar setores**

O sistema deverá permitir a consulta dos setores cadastrados.

**RF35 — Atualizar e desativar setor**

O sistema deverá permitir que Administradores atualizem e desativem setores, preservando os registros históricos relacionados.

### 5.6. Domínio de Suporte — Indicadores e Relatórios

**RF36 — Consultar indicadores de chamados**

O sistema deverá disponibilizar indicadores quantitativos de chamados, permitindo sua análise por status, prioridade e setor.

**RF37 — Consultar indicadores por Técnico**

O sistema deverá permitir a consulta de indicadores relacionados aos **Técnicos** responsáveis pelo atendimento.

**RF38 — Consultar tempo médio de solução**

O sistema deverá disponibilizar o tempo médio de solução dos chamados concluídos, conforme os filtros e critérios definidos.

## 6. Regras Gerais

**RG01 — Autenticação e autorização**

As funcionalidades deverão ser acessíveis somente após autenticação, respeitando as roles e permissões do User autenticado.

**RG02 — Separação entre identidade, autorização e domínio**

User representa a identidade/conta de acesso e suas roles de autorização. Colaborador e Técnico representam entidades do domínio de Gestão de Pessoas e possuem referências explícitas à identidade por meio de `userId` e `colaboradorId`.

**RG03 — Integridade do ciclo de vida**

As mudanças de status do chamado deverão respeitar as transições permitidas pelo ciclo de vida definido pelo domínio.

**RG04 — Histórico**

Alterações relevantes do chamado deverão ser registradas para preservar a rastreabilidade do atendimento.

**RG05 — Preservação histórica**

A desativação de uma conta de acesso ou de um setor não deverá apagar os registros históricos de chamados e atendimentos relacionados.

**RG06 — Controle temporal**

As datas de abertura, início do atendimento, conclusão e demais eventos relevantes deverão ser registradas de forma consistente e permitir o cálculo do tempo de solução.

**RG07 — Consistência entre identidade e pessoa**

Um Colaborador deverá possuir uma referência válida para um User. Um Técnico deverá possuir uma referência válida para um Colaborador. A existência de um User não implica, por si só, a existência de um Colaborador ou Técnico.

## 7. Considerações sobre evolução

1. As regras de autenticação e autorização deverão ser aplicadas no backend, independentemente das restrições existentes na interface.
2. As regras de negócio do atendimento deverão permanecer independentes da infraestrutura de autenticação e persistência.
3. O domínio deverá expressar explicitamente as responsabilidades de **Colaborador** e **Técnico**, evitando o uso de `User` como substituto desses conceitos.
4. As roles de `User` deverão permanecer relacionadas à autorização de acesso, não sendo utilizadas como substitutas das entidades de domínio **Colaborador** e **Técnico**.
5. Os cálculos de tempo de solução deverão considerar os períodos efetivos de atendimento, regras de horário comercial, dias úteis e feriados.
6. Os requisitos poderão ser refinados durante o desenvolvimento, desde que as alterações sejam documentadas e mantenham a rastreabilidade das decisões.

___

#### *v2.3.0*