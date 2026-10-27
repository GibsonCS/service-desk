# Requisitos Funcionais - Service Desk System

## 1. Objetivo

Este documento define os requisitos funcionais do Sistema de Chamados, especificando as funcionalidades que o sistema deverá disponibilizar para usuários solicitantes, profissionais de suporte e administradores.

Os requisitos servem como referência para o desenvolvimento, a validação das regras de negócio e a elaboração dos testes automatizados.

## 2. Atores do Sistema

| Ator | Descrição |
|---|---|
| **Usuário (USER)** | Registra chamados e acompanha suas próprias solicitações. |
| **Suporte (SUPPORT)** | Atua no atendimento, na atualização e na resolução dos chamados sob sua responsabilidade ou aos quais tenha acesso autorizado. |
| **Administrador (ADMIN)** | Gerencia usuários, setores, permissões e chamados, além de consultar indicadores administrativos. |

## 3. Requisitos Funcionais

### 3.1. Gerenciamento de Usuários e Autenticação

**RF01 — Cadastrar usuário**

O sistema deverá permitir o cadastro de usuários com nome, endereço de e-mail e senha.

**RF02 — Consultar usuário**

O sistema deverá permitir a consulta dos dados de um usuário, respeitando as permissões de acesso.

**RF03 — Atualizar usuário**

O sistema deverá permitir a atualização dos dados cadastrais de um usuário autorizado como nome, email e senha.

**RF04 — Desativar usuário**

O sistema deverá permitir que um administrador desative usuários sem excluir seus registros históricos.

**RF05 — Autenticar usuário**

O sistema deverá autenticar usuários por meio de suas credenciais e disponibilizar acesso às funcionalidades autorizadas para seu perfil.

**RF06 — Gerenciar perfis de acesso**

O sistema deverá permitir que administradores atribuam e alterem os perfis de acesso dos usuários.

**RF07 — Consultar próprio perfil**

O sistema deverá permitir que o usuário autenticado consulte seus próprios dados cadastrais.

### 3.2. Gerenciamento de Setores e Equipe de Suporte

**RF08 — Cadastrar setor**

O sistema deverá permitir o cadastro de setores responsáveis pelo recebimento e atendimento de chamados.

**RF09 — Consultar setores**

O sistema deverá permitir a consulta dos setores cadastrados.

**RF10 — Atualizar e desativar setor**

O sistema deverá permitir que administradores atualizem os dados de um setor ou o desativem, preservando os registros históricos relacionados.

**RF11 — Associar usuários a setores**

O sistema deverá permitir a associação de usuários a setores, conforme suas permissões e responsabilidades.

### 3.3. Gerenciamento de Chamados

**RF12 — Abrir chamado**

O sistema deverá permitir que usuários autenticados registrem chamados informando, no mínimo, título, descrição e setor responsável.

**RF13 — Identificar chamado**

O sistema deverá gerar um identificador único para cada chamado registrado.

**RF14 — Registrar solicitante**

O sistema deverá associar cada chamado ao usuário autenticado que realizou sua abertura.

**RF15 — Definir prioridade**

O sistema deverá permitir a classificação dos chamados conforme os níveis de prioridade:

- Baixa;
- Média;
- Alta;
- Crítica.

**RF16 — Consultar chamados**

O sistema deverá permitir a consulta dos chamados de acordo com as permissões do usuário autenticado.

**RF17 — Atualizar dados do chamado**

O sistema deverá permitir a atualização dos dados de um chamado por usuários autorizados, respeitando as restrições aplicáveis ao seu estado atual.

**RF18 — Consultar chamados por critérios**

O sistema deverá permitir a filtragem de chamados pelos seguintes critérios:

- Status;
- Prioridade;
- Solicitante;
- Setor;
- Responsável pelo atendimento;
- Período de abertura.

**RF19 — Atribuir responsável**

O sistema deverá permitir que usuários autorizados atribuam um profissional de suporte a um chamado.

**RF20 — Alterar responsável**

O sistema deverá permitir a alteração do responsável pelo atendimento de um chamado, registrando a mudança no histórico.

**RF21 — Iniciar atendimento**

O sistema deverá permitir que um profissional autorizado inicie o atendimento de um chamado pendente.

**RF22 — Concluir chamado**

O sistema deverá permitir que um profissional autorizado registre a conclusão de um chamado em atendimento.

**RF23 — Reabrir chamado**

O sistema deverá permitir a reabertura de chamados concluídos, mediante autorização, para que voltem ao atendimento.

**RF24 — Cancelar chamado**

O sistema deverá permitir o cancelamento de chamados por usuários autorizados, respeitando as regras de negócio aplicáveis.


### 3.4. Comentários e Histórico

**RF25 — Adicionar comentário**

O sistema deverá permitir que usuários autorizados adicionem comentários a um chamado.

**RF26 — Consultar comentários**

O sistema deverá permitir a consulta dos comentários de um chamado, respeitando as permissões de acesso.

**RF27 — Consultar histórico**

O sistema deverá permitir a consulta do histórico de um chamado por usuários autorizados.

### 3.5. Datas e Tempo de Solução

**RF28 — Registrar data de abertura**

O sistema deverá registrar automaticamente a data e a hora de abertura de cada chamado.

**RF29 — Registrar início do atendimento**

O sistema deverá registrar a data e a hora em que o atendimento de um chamado for iniciado pela primeira vez.

**RF30 — Registrar conclusão**

O sistema deverá registrar a data e a hora de conclusão do atendimento, preservando as informações de conclusões anteriores quando houver reabertura.

**RF31 — Calcular tempo de solução**

O sistema deverá calcular o tempo de solução dos chamados concluídos, considerando o horário de funcionamento configurado, os dias úteis e os feriados cadastrados.

### 3.6. Indicadores e Relatórios

**RF32 — Consultar indicadores de chamados**

O sistema deverá disponibilizar indicadores quantitativos de chamados, permitindo sua análise por status, prioridade e setor.

**RF33 — Consultar indicadores por responsável**

O sistema deverá permitir a consulta de indicadores relacionados aos profissionais responsáveis pelo atendimento.

**RF34 — Consultar tempo médio de solução**

O sistema deverá disponibilizar o tempo médio de solução dos chamados concluídos, conforme os filtros e critérios definidos.

## 4. Considerações

1. Os requisitos de autenticação e autorização deverão ser aplicados no backend, independentemente das restrições existentes na interface.
2. As operações de consulta e alteração deverão respeitar o perfil de acesso e as permissões específicas de cada usuário.
3. Os cálculos de tempo de solução deverão considerar os períodos efetivos de atendimento e as regras de horário comercial.
4. Os requisitos poderão ser refinados durante o desenvolvimento, desde que as alterações sejam documentadas e mantenham a rastreabilidade das decisões.

## 5. Escopo da Primeira Versão

A primeira versão do sistema contemplará:

- Cadastro e autenticação de usuários;
- Gerenciamento de setores;
- Abertura e acompanhamento de chamados;
- Classificação por prioridade;
- Atribuição de profissionais de suporte;
- Controle de status e ciclo de vida dos chamados;
- Comentários
- Cálculo de tempo de solução em horário comercial;
- Indicadores básicos de atendimento.

Funcionalidades como notificações por e-mail, integrações externas e exportação avançada de relatórios poderão ser avaliadas para versões futuras.
