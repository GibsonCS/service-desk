# Requisitos Funcionais - Service Desk System

## 1. Objetivo

Este documento define os requisitos funcionais do Sistema de Chamados, especificando as funcionalidades disponibilizadas para autenticação, abertura e acompanhamento de chamados, atendimento técnico, administração da operação e consulta de indicadores.

Os requisitos servem como referência para o desenvolvimento, a validação das regras de negócio e a elaboração dos testes automatizados.

## 2. Linguagem Ubíqua

O sistema adota os seguintes termos de domínio:

| Termo | Definição |
|---|---|
| **User** | Conta de acesso e identidade utilizada para autenticação no sistema. |
| **Colaborador** | Papel de negócio desempenhado por um User que solicita atendimento e abre chamados. |
| **Técnico** | Papel de negócio desempenhado por um User responsável pelo atendimento e resolução dos chamados. |
| **Administrador** | Papel de negócio desempenhado por um User responsável pela administração do sistema e de suas permissões. |
| **Solicitante** | Colaborador associado à abertura de um chamado. |
| **Responsável** | Técnico atualmente atribuído ao atendimento de um chamado. |
| **Chamado** | Solicitação de atendimento registrada no sistema. |
| **Atendimento** | Processo de tratamento de um chamado por um Técnico. |

**Importante:** User representa somente a identidade/conta de acesso. User não representa, por si só, o papel de quem abre ou resolve chamados. As responsabilidades de negócio são definidas pelo papel atribuído ao User: **Colaborador**, **Técnico** ou **Administrador**.

## 3. Domínio do Sistema

### 3.1. Domínio Central — Gestão do Atendimento de Chamados

O domínio central é responsável pelo ciclo de vida dos chamados e pelo atendimento realizado pelos Técnicos.

### 3.2. Domínios de Suporte

- **Identidade e acesso:** autenticação, dados cadastrais e papéis de acesso dos Users.
- **Organização da operação:** setores e associação de Users aos setores.
- **Indicadores e relatórios:** consolidação e consulta de informações operacionais.

## 4. Atores do Sistema

| Ator | Descrição |
|---|---|
| **Colaborador** | User autenticado com papel de Colaborador, responsável por abrir e acompanhar suas próprias solicitações. |
| **Técnico** | User autenticado com papel de Técnico, responsável por receber, atender e resolver chamados. |
| **Administrador** | User autenticado com papel de Administrador, responsável por administrar Users, setores, papéis e funcionalidades administrativas. |

> **Nota:** User não é tratado como ator de negócio. User representa a identidade autenticada; os comportamentos de negócio são exercidos pelos papéis atribuídos a essa identidade.

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

O sistema deverá permitir o cadastro de uma conta de acesso (`User`) com nome, endereço de e-mail e senha.

**RF22 — Consultar conta de acesso**

O sistema deverá permitir a consulta dos dados de uma conta de acesso, respeitando as permissões aplicáveis.

**RF23 — Atualizar conta de acesso**

O sistema deverá permitir a atualização dos dados cadastrais de uma conta de acesso autorizada, como nome, e-mail e senha.

**RF24 — Desativar conta de acesso**

O sistema deverá permitir que um **Administrador** desative contas de acesso sem excluir seus registros históricos.

**RF25 — Autenticar User**

O sistema deverá autenticar a conta de acesso (`User`) por meio de suas credenciais e disponibilizar acesso às funcionalidades autorizadas para o papel atribuído.

**RF26 — Gerenciar papéis de acesso**

O sistema deverá permitir que **Administradores** atribuam e alterem os papéis de acesso entre **Colaborador**, **Técnico** e **Administrador**, conforme as regras de autorização.

**RF27 — Consultar próprio perfil**

O sistema deverá permitir que o User autenticado consulte seus próprios dados cadastrais e seu papel de acesso.

### 5.5. Domínio de Suporte — Organização da Operação

**RF28 — Cadastrar setor**

O sistema deverá permitir o cadastro de setores responsáveis pelo recebimento e atendimento de chamados.

**RF29 — Consultar setores**

O sistema deverá permitir a consulta dos setores cadastrados.

**RF30 — Atualizar e desativar setor**

O sistema deverá permitir que Administradores atualizem e desativem setores, preservando os registros históricos relacionados.

**RF31 — Associar Users a setores**

O sistema deverá permitir a associação de contas de acesso (`User`) a setores, conforme suas permissões e responsabilidades.

### 5.6. Domínio de Suporte — Indicadores e Relatórios

**RF32 — Consultar indicadores de chamados**

O sistema deverá disponibilizar indicadores quantitativos de chamados, permitindo sua análise por status, prioridade e setor.

**RF33 — Consultar indicadores por Técnico**

O sistema deverá permitir a consulta de indicadores relacionados aos **Técnicos** responsáveis pelo atendimento.

**RF34 — Consultar tempo médio de solução**

O sistema deverá disponibilizar o tempo médio de solução dos chamados concluídos, conforme os filtros e critérios definidos.

## 6. Regras Gerais

**RG01 — Autenticação e autorização**

As funcionalidades deverão ser acessíveis somente após autenticação, respeitando o papel e as permissões do User autenticado.

**RG02 — Separação entre identidade e papel de negócio**

User representa exclusivamente a identidade/conta de acesso. As responsabilidades de negócio são exercidas pelos papéis **Colaborador**, **Técnico** e **Administrador** atribuídos ao User.

**RG03 — Integridade do ciclo de vida**

As mudanças de status do chamado deverão respeitar as transições permitidas pelo ciclo de vida definido pelo domínio.

**RG04 — Histórico**

Alterações relevantes do chamado deverão ser registradas para preservar a rastreabilidade do atendimento.

**RG05 — Preservação histórica**

A desativação de uma conta de acesso ou de um setor não deverá apagar os registros históricos de chamados e atendimentos relacionados.

**RG06 — Controle temporal**

As datas de abertura, início do atendimento, conclusão e demais eventos relevantes deverão ser registradas de forma consistente e permitir o cálculo do tempo de solução.

## 7. Considerações sobre evolução

1. As regras de autenticação e autorização deverão ser aplicadas no backend, independentemente das restrições existentes na interface.
2. As regras de negócio do atendimento deverão permanecer independentes da infraestrutura de autenticação e persistência.
3. O domínio deverá expressar explicitamente as responsabilidades de **Colaborador** e **Técnico**, evitando o uso genérico de "User" para representar comportamentos de negócio.
4. Os cálculos de tempo de solução deverão considerar os períodos efetivos de atendimento, regras de horário comercial, dias úteis e feriados.
5. Os requisitos poderão ser refinados durante o desenvolvimento, desde que as alterações sejam documentadas e mantenham a rastreabilidade das decisões.

___

#### *v2.2.0*