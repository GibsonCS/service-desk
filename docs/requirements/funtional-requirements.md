# Requisitos Funcionais - Service Desk System

## 1. Objetivo

Este documento define os requisitos funcionais do Sistema de Chamados, especificando as funcionalidades disponibilizadas para abertura e acompanhamento de chamados, atendimento técnico, administração da operação e consulta de indicadores.

Os requisitos funcionais descrevem **o que o sistema deve fazer**. As regras associadas a cada requisito definem restrições, condições e comportamentos que orientam sua implementação e validação.

## 2. Linguagem Ubíqua

| Termo | Definição |
|---|---|
| **User** | Pessoa/conta de acesso utilizada para autenticação, autorização e participação nas operações do sistema. O User também representa o colaborador no contexto do Service Desk. |
| **Role** | Enum de autorização associado ao User. Define as permissões de acesso às funcionalidades do sistema. |
| **Administrador** | Papel de autorização representado por `Role.ADMIN`, responsável por operações administrativas. Não é uma entidade separada. |
| **Solicitante (Requester)** | User associado à abertura de um chamado. |
| **Responsável (Assignee)** | User atualmente atribuído ao atendimento de um chamado. |
| **Chamado (Ticket)** | Solicitação de atendimento registrada no sistema. |
| **Atendimento** | Processo de tratamento de um chamado por um User autorizado a executar operações técnicas. |
| **Setor (Sector)** | Unidade organizacional à qual Users podem pertencer e que pode ser responsável pelo atendimento de chamados. |

**Decisões de modelagem:**
- User concentra a identidade/conta de acesso e os dados necessários para sua participação na organização. Não existem entidades separadas de Colaborador ou Técnico.
- A distinção entre colaborador, técnico e administrador, quando necessária para autorização, é expressa pelas roles do User.
- O e-mail do User é utilizado como login.
- Cada User possui exatamente uma Role: `COLLABORATOR`, `TECHNICIAN` ou `ADMIN`.
- `Ticket.requesterId` e `Ticket.assigneeId` referenciam `User.id`.

## 3. Domínio do Sistema

### 3.1. Domínio Central — Gestão do Atendimento de Chamados

Responsável pelo ciclo de vida dos chamados e pelo atendimento realizado por Users.

### 3.2. Domínios de Suporte

- **Identidade e Acesso:** autenticação, credenciais, contas e roles dos Users.
- **Gestão de Organização:** setores e vínculo organizacional dos Users.
- **Indicadores e relatórios:** consolidação e consulta de informações operacionais.

## 4. Atores do Sistema

| Ator | Descrição |
|---|---|
| **User** | Pessoa cadastrada no sistema que utiliza as funcionalidades disponíveis. |
| **User com role TECHNICIAN** | User cuja role permite executar operações de atendimento técnico, conforme as regras de autorização. |
| **Administrador** | User com role `ADMIN`, responsável por operações administrativas. |

As roles representam permissões, não entidades de domínio.

## 5. Requisitos Funcionais e Regras Associadas

As regras estão posicionadas junto aos RFs para facilitar leitura, rastreabilidade e derivação de testes. Regras compartilhadas por vários requisitos estão na seção 6.

As regras que dependem de capacidades ainda não implementadas são identificadas como **futuras**. Isso não impede a implementação das demais regras aplicáveis ao RF.

### 5.1. Domínio Central — Gestão do Atendimento de Chamados

#### RF01 — Abrir chamado

O sistema deverá permitir que um User registre um chamado informando, no mínimo, título, descrição e setor responsável.

**Regras associadas:**
- **RN01.01:** Todo chamado deve possuir título.
- **RN01.02:** Todo chamado deve possuir descrição.
- **RN01.03:** O chamado deve estar associado a um setor existente.
- **RN01.04:** O chamado deve ser criado com status `PENDENTE`.
- **RN01.05:** A prioridade inicial do chamado deve ser `BAIXA`.
- **RN01.06:** A data e hora de abertura devem ser definidas pelo sistema.
- **RN01.07:** O chamado deve possuir um solicitante válido, referenciado por `User.id`.

#### RF02 — Identificar chamado

O sistema deverá gerar um identificador único para cada chamado registrado.

**Regras associadas:**
- **RN02.01:** Cada chamado deve possuir um identificador único.
- **RN02.02:** O identificador deve ser gerado pelo sistema, não escolhido pelo cliente.
- **RN02.03:** O identificador de um chamado não pode ser alterado após sua criação.

#### RF03 — Registrar solicitante

O sistema deverá associar cada chamado ao User que realizou sua abertura. O atributo `Ticket.requesterId` deverá armazenar o identificador do User.

**Regras associadas:**
- **RN03.01:** O solicitante deve corresponder a um User existente.
- **RN03.02:** O vínculo deve ser persistido por meio de `User.id`.
- **RN03.03 (futura — autenticação):** Quando a abertura ocorrer em contexto autenticado, o solicitante deverá ser obtido da identidade autenticada no backend. O cliente não poderá escolher arbitrariamente outro solicitante.
- **RN03.04 (futura — autenticação):** Após a integração com a autenticação, o identificador do solicitante deverá deixar de ser recebido no payload de abertura e passar a ser obtido do contexto autenticado.

#### RF04 — Definir prioridade

O sistema deverá permitir a definição e alteração da prioridade de um chamado entre `BAIXA`, `MEDIA`, `ALTA` e `CRITICA`.

**Regras associadas:**
- **RN04.01:** A prioridade deve corresponder a um valor definido em `TicketPriority`.
- **RN04.02:** Não deve ser permitida a alteração para a prioridade que o chamado já possui.
- **RN04.03:** A alteração da prioridade não deve modificar os demais dados do chamado.
- **RN04.04:** A prioridade não pode ser nula.

#### RF05 — Consultar chamado

O sistema deverá permitir a consulta dos dados de um chamado.

**Regras associadas:**
- **RN05.01:** A consulta deve identificar o chamado por seu identificador único.
- **RN05.02:** Caso o identificador não corresponda a um chamado existente, o sistema deverá informar que o chamado não foi encontrado.
- **RN05.03:** A consulta não deve modificar o estado nem os dados do chamado.

#### RF06 — Atualizar dados do chamado

O sistema deverá permitir a atualização dos dados de um chamado, respeitando as restrições aplicáveis ao seu estado atual.

**Regras associadas:**
- **RN06.01:** Somente os atributos definidos como editáveis poderão ser alterados.
- **RN06.02:** A atualização não poderá modificar o identificador, o solicitante ou as datas dos eventos do ciclo de vida.
- **RN06.03:** A atualização deverá preservar os dados que não foram alterados.
- **RN06.04:** As alterações devem respeitar o estado atual do chamado e as regras gerais de ciclo de vida.
- **RN06.05:** As alterações relevantes deverão ser rastreáveis conforme as regras de histórico.

#### RF07 — Consultar chamados por critérios

O sistema deverá permitir a filtragem de chamados por status, prioridade, solicitante, setor, responsável e período de abertura.

**Regras associadas:**
- **RN07.01:** Os filtros informados devem ser combinados para restringir o resultado.
- **RN07.02:** Filtros não informados não devem restringir a consulta.
- **RN07.03:** O período de abertura, quando informado, deve possuir limites válidos.
- **RN07.04:** A consulta deve retornar somente chamados que atendam aos critérios informados.
- **RN07.05:** A consulta não deve alterar os chamados.

#### RF08 — Atribuir responsável

O sistema deverá permitir a atribuição de um User como responsável pelo atendimento de um chamado.

**Regras associadas:**
- **RN08.01:** O responsável deve corresponder a um User existente.
- **RN08.02:** O User atribuído deve possuir uma role autorizada a assumir atendimentos, conforme a política de autorização.
- **RN08.03:** A atribuição deve armazenar o identificador do User em `Ticket.assigneeId`.
- **RN08.04:** A atribuição não deve alterar o solicitante do chamado.
- **RN08.05:** A atribuição deve ser registrada no histórico.
- **RN08.06 (futura — autorização):** A operação deverá ser permitida somente a atores autorizados.

#### RF09 — Alterar responsável

O sistema deverá permitir a alteração do User responsável pelo atendimento de um chamado.

**Regras associadas:**
- **RN09.01:** O novo responsável deve corresponder a um User existente e autorizado a assumir atendimentos.
- **RN09.02:** A alteração deve substituir o responsável atual sem modificar o solicitante.
- **RN09.03:** A alteração deve registrar no histórico o responsável anterior, o novo responsável e o momento da mudança.
- **RN09.04:** A operação deve respeitar as restrições do estado atual do chamado.
- **RN09.05 (futura — autorização):** A operação deverá ser permitida somente a atores autorizados.

#### RF10 — Iniciar atendimento

O sistema deverá permitir o início do atendimento de um chamado pendente.

**Regras associadas:**
- **RN10.01:** Somente chamados com status `PENDENTE` podem ter o atendimento iniciado.
- **RN10.02:** Ao iniciar o atendimento, o status deve mudar para `EM_ATENDIMENTO`.
- **RN10.03:** A data e hora do primeiro início devem ser registradas em `firstStartedAt`.
- **RN10.04:** O primeiro início deve ser preservado e não sobrescrito por operações posteriores.
- **RN10.05:** O início do atendimento deve respeitar a atribuição de responsável definida pelo domínio.
- **RN10.06 (futura — autorização):** A operação deverá ser permitida somente a Users com role autorizada, inicialmente `TECHNICIAN` ou `ADMIN`.

#### RF11 — Concluir chamado

O sistema deverá permitir a conclusão de um chamado que esteja em atendimento.

**Regras associadas:**
- **RN11.01:** Somente chamados com status `EM_ATENDIMENTO` podem ser concluídos.
- **RN11.02:** Ao concluir, o status deve mudar para `CONCLUIDO`.
- **RN11.03:** A data e hora da conclusão devem ser registradas pelo sistema.
- **RN11.04:** A conclusão deve preservar os registros temporais de eventos anteriores.
- **RN11.05 (futura — autorização):** A operação deverá ser permitida somente a Users autorizados.

#### RF12 — Reabrir chamado

O sistema deverá permitir a reabertura de chamados concluídos para que retornem ao atendimento.

**Regras associadas:**
- **RN12.01:** Somente chamados com status `CONCLUIDO` podem ser reabertos.
- **RN12.02:** A reabertura deve alterar o status para o estado de retorno ao fluxo de atendimento definido pelo domínio.
- **RN12.03:** A reabertura não deve apagar nem sobrescrever o histórico de conclusões anteriores.
- **RN12.04:** A nova execução do atendimento deve ser rastreável como um novo ciclo.
- **RN12.05 (futura — autorização):** A operação deverá ser permitida somente a Users autorizados.

#### RF13 — Cancelar chamado

O sistema deverá permitir o cancelamento de chamados, respeitando as regras de negócio aplicáveis.

**Regras associadas:**
- **RN13.01:** Somente chamados em estados que permitam cancelamento poderão ser cancelados.
- **RN13.02:** Ao cancelar, o status deve mudar para `CANCELADO`.
- **RN13.03:** A data e hora do cancelamento devem ser registradas pelo sistema.
- **RN13.04:** O cancelamento não deve excluir o chamado nem seu histórico.
- **RN13.05 (futura — autorização):** A operação deverá ser permitida somente a Users autorizados.

### 5.2. Comentários e Histórico

#### RF14 — Adicionar comentário

O sistema deverá permitir a adição de comentários a um chamado.

**Regras associadas:**
- **RN14.01:** O comentário deve estar associado a um chamado existente.
- **RN14.02:** O comentário deve possuir conteúdo não vazio.
- **RN14.03:** O comentário deve registrar sua data e hora de criação.
- **RN14.04:** O comentário deve identificar o User autor.
- **RN14.05:** A inclusão de comentário não deve alterar automaticamente o status do chamado.
- **RN14.06 (futura — autenticação):** Em operações autenticadas, o autor deverá ser obtido da identidade autenticada.

#### RF15 — Consultar comentários

O sistema deverá permitir a consulta dos comentários de um chamado.

**Regras associadas:**
- **RN15.01:** A consulta deve referenciar um chamado existente.
- **RN15.02:** Devem ser retornados somente comentários associados ao chamado consultado.
- **RN15.03:** A consulta deve preservar a ordem cronológica dos comentários.
- **RN15.04:** A consulta não deve modificar comentários nem o chamado.

#### RF16 — Consultar histórico

O sistema deverá permitir a consulta do histórico de um chamado.

**Regras associadas:**
- **RN16.01:** O histórico deve estar associado a um chamado existente.
- **RN16.02:** O histórico deve apresentar os eventos relevantes em ordem cronológica.
- **RN16.03:** Cada evento deve identificar, quando aplicável, o User responsável pela ação e o momento em que ocorreu.
- **RN16.04:** Eventos históricos não podem ser alterados ou removidos por operações comuns de manutenção do chamado.

### 5.3. Controle de Tempo de Atendimento

#### RF17 — Registrar data de abertura

O sistema deverá registrar automaticamente a data e a hora de abertura de cada chamado.

**Regras associadas:**
- **RN17.01:** A data e hora devem ser geradas pelo sistema no momento da criação.
- **RN17.02:** O cliente não pode definir ou sobrescrever a data de abertura.
- **RN17.03:** O instante de abertura deve ser preservado durante todo o ciclo de vida do chamado.

#### RF18 — Registrar início do atendimento

O sistema deverá registrar a data e a hora em que o atendimento de um chamado for iniciado pela primeira vez.

**Regras associadas:**
- **RN18.01:** O registro deve ocorrer na primeira transição válida para `EM_ATENDIMENTO`.
- **RN18.02:** O instante do primeiro início deve ser preservado e não sobrescrito em reaberturas ou novos ciclos.
- **RN18.03:** O registro deve ser gerado pelo sistema.

#### RF19 — Registrar conclusão

O sistema deverá registrar a data e a hora de conclusão do atendimento, preservando as informações de conclusões anteriores quando houver reabertura.

**Regras associadas:**
- **RN19.01:** Cada conclusão válida deve gerar um registro temporal correspondente.
- **RN19.02:** Uma reabertura não deve apagar o registro da conclusão anterior.
- **RN19.03:** O instante de conclusão deve ser gerado pelo sistema.
- **RN19.04:** O modelo deverá permitir distinguir a conclusão mais recente das conclusões anteriores.

#### RF20 — Calcular tempo de solução

O sistema deverá calcular o tempo de solução dos chamados concluídos, considerando o horário de funcionamento configurado, os dias úteis e os feriados cadastrados.

**Regras associadas:**
- **RN20.01:** O cálculo deve considerar somente os intervalos definidos como expediente.
- **RN20.02:** Dias não úteis e feriados cadastrados devem ser excluídos do cálculo conforme o calendário operacional.
- **RN20.03:** O cálculo deve utilizar os eventos temporais registrados para o chamado.
- **RN20.04:** A regra de cálculo deve tratar chamados reabertos considerando os ciclos de atendimento, conforme critério operacional a ser definido.
- **RN20.05:** O resultado deve ser consistente para os mesmos eventos e configurações de calendário.

### 5.4. Domínio de Suporte — Identidade e Acesso

#### RF21 — Cadastrar User

O sistema deverá permitir o cadastro de um User com nome, endereço de e-mail, senha e uma role de autorização.

**Regras associadas:**
- **RN21.01:** O User deve possuir nome, e-mail, senha e uma única role.
- **RN21.02:** O e-mail deve ser único e será utilizado como login.
- **RN21.03:** A senha não deve ser armazenada em texto puro; deve ser persistida como hash seguro.
- **RN21.04:** A role deve corresponder a um dos valores permitidos: `COLLABORATOR`, `TECHNICIAN` ou `ADMIN`.
- **RN21.05:** O User deve possuir estado de ativação definido no cadastro.
- **RN21.06:** Quando informado, o setor associado deve existir.

#### RF22 — Consultar User

O sistema deverá permitir a consulta dos dados de um User.

**Regras associadas:**
- **RN22.01:** A consulta deve identificar o User por seu identificador.
- **RN22.02:** Caso o User não exista, o sistema deverá informar que não foi encontrado.
- **RN22.03:** Credenciais e hashes de senha não devem ser expostos nas respostas.
- **RN22.04:** A consulta não deve modificar os dados do User.

#### RF23 — Atualizar User

O sistema deverá permitir a atualização dos dados cadastrais de um User, como nome, e-mail e senha.

**Regras associadas:**
- **RN23.01:** O User a ser atualizado deve existir.
- **RN23.02:** O novo e-mail deve respeitar a unicidade e a utilização como login.
- **RN23.03:** Uma nova senha deve ser armazenada como hash seguro, nunca em texto puro.
- **RN23.04:** A atualização não deve modificar o identificador do User.
- **RN23.05:** Campos não informados para alteração devem preservar seus valores atuais.

#### RF24 — Desativar User

O sistema deverá permitir a desativação de Users sem excluir seus registros históricos.

**Regras associadas:**
- **RN24.01:** O User deve existir para ser desativado.
- **RN24.02:** A desativação deve alterar seu estado de ativação, sem exclusão física do registro.
- **RN24.03:** A desativação não deve remover vínculos históricos com chamados ou comentários.
- **RN24.04:** Users desativados não devem realizar operações que exijam uma conta ativa, após a implementação da autenticação.

#### RF25 — Autenticar User

O sistema deverá autenticar o User por meio do e-mail e senha e disponibilizar acesso às funcionalidades conforme a role atribuída.

**Regras associadas:**
- **RN25.01:** O e-mail será utilizado como identificador de login.
- **RN25.02:** A autenticação deve validar a senha informada contra o hash armazenado.
- **RN25.03:** Users desativados não podem autenticar.
- **RN25.04:** Falhas de autenticação não devem revelar se o e-mail ou a senha, isoladamente, estão incorretos.
- **RN25.05:** Após autenticação bem-sucedida, o contexto autenticado deve identificar o User e sua role.

#### RF26 — Gerenciar role de acesso

O sistema deverá permitir a atribuição e alteração da role de um User entre `COLLABORATOR`, `TECHNICIAN` e `ADMIN`.

**Regras associadas:**
- **RN26.01:** Cada User deve possuir exatamente uma role.
- **RN26.02:** A role atribuída deve pertencer ao conjunto permitido.
- **RN26.03:** A alteração da role deve substituir a role anterior, não acrescentar uma segunda role.
- **RN26.04:** A operação deve ser restrita a atores com permissão administrativa.
- **RN26.05:** A alteração deve produzir efeito nas autorizações aplicadas ao User.

#### RF27 — Consultar próprio perfil

O sistema deverá permitir que um User consulte seus próprios dados cadastrais e sua role de acesso.

**Regras associadas:**
- **RN27.01:** O perfil consultado deve corresponder ao User autenticado.
- **RN27.02:** O cliente não poderá escolher outro identificador de User para consultar como se fosse seu próprio perfil.
- **RN27.03:** A resposta não deve expor senha ou hash de senha.
- **RN27.04:** A operação depende da implementação do contexto de autenticação.

### 5.5. Domínio de Suporte — Gestão de Organização

#### RF28 — Cadastrar setor

O sistema deverá permitir o cadastro de setores responsáveis pelo recebimento e atendimento de chamados.

**Regras associadas:**
- **RN28.01:** O setor deve possuir nome.
- **RN28.02:** O nome do setor deve respeitar a regra de unicidade definida para o domínio.
- **RN28.03:** O setor deve ser criado com estado de ativação definido.
- **RN28.04:** O identificador e as datas de criação devem ser gerados pelo sistema.

#### RF29 — Consultar setores

O sistema deverá permitir a consulta dos setores cadastrados.

**Regras associadas:**
- **RN29.01:** A consulta deve permitir identificar os setores cadastrados.
- **RN29.02:** Quando houver filtro por estado, o resultado deve respeitar o estado solicitado.
- **RN29.03:** A consulta não deve modificar os setores.

#### RF30 — Atualizar e desativar setor

O sistema deverá permitir a atualização e desativação de setores, preservando os registros históricos relacionados.

**Regras associadas:**
- **RN30.01:** O setor deve existir para ser atualizado ou desativado.
- **RN30.02:** A atualização não deve modificar o identificador do setor.
- **RN30.03:** A desativação não deve excluir o setor nem remover vínculos históricos.
- **RN30.04:** Setores desativados não devem ser selecionáveis para novas operações que exijam um setor ativo.
- **RN30.05:** A desativação não deve alterar retroativamente os chamados associados ao setor.

### 5.6. Domínio de Suporte — Indicadores e Relatórios

#### RF31 — Consultar indicadores de chamados

O sistema deverá disponibilizar indicadores quantitativos de chamados, permitindo sua análise por status, prioridade e setor.

**Regras associadas:**
- **RN31.01:** Os indicadores devem ser calculados a partir dos chamados registrados.
- **RN31.02:** A classificação por status, prioridade e setor deve refletir os dados persistidos.
- **RN31.03:** Filtros informados devem ser aplicados de forma consistente.
- **RN31.04:** A consulta de indicadores não deve modificar os dados operacionais.

#### RF32 — Consultar indicadores por User responsável

O sistema deverá permitir a consulta de indicadores relacionados aos Users responsáveis pelo atendimento.

**Regras associadas:**
- **RN32.01:** A apuração deve considerar o User registrado como responsável nos dados de atribuição/histórico.
- **RN32.02:** Os critérios de contagem devem distinguir chamados atribuídos de chamados efetivamente atendidos, caso ambos sejam apresentados.
- **RN32.03:** Users desativados devem continuar identificáveis nos dados históricos.
- **RN32.04:** A consulta não deve modificar os dados operacionais.

#### RF33 — Consultar tempo médio de solução

O sistema deverá disponibilizar o tempo médio de solução dos chamados concluídos, conforme os filtros e critérios definidos.

**Regras associadas:**
- **RN33.01:** O cálculo deve considerar somente chamados que atendam ao critério de conclusão definido.
- **RN33.02:** O tempo individual deve seguir as regras estabelecidas no RF20.
- **RN33.03:** O cálculo deve explicitar o conjunto de chamados considerado e respeitar os filtros informados.
- **RN33.04:** Chamados sem dados temporais suficientes não devem produzir duração fictícia; devem ser tratados conforme critério de qualidade dos dados.

## 6. Regras Gerais e Transversais

As regras desta seção se aplicam a mais de um RF e, por isso, não são repetidas integralmente em cada requisito.

- **RG01 — Autenticação e autorização:** funcionalidades protegidas deverão ser acessíveis somente a Users autenticados e autorizados. A aplicação das permissões deve ocorrer no backend.
- **RG02 — Identidade e autorização:** User representa a identidade/conta; Role representa o conjunto de permissões. Role não substitui uma entidade de domínio.
- **RG03 — Identidade do solicitante:** `Ticket.requesterId` referencia exclusivamente `User.id`. Quando a autenticação estiver implementada, o solicitante será derivado da identidade autenticada no backend.
- **RG04 — Identidade do responsável:** `Ticket.assigneeId`, quando informado, referencia exclusivamente `User.id`. A aplicação deve validar se o User pode assumir atendimentos.
- **RG05 — Integridade do ciclo de vida:** mudanças de status devem respeitar as transições permitidas pelo domínio.
- **RG06 — Histórico:** alterações relevantes e eventos do ciclo de vida devem ser registrados para preservar a rastreabilidade.
- **RG07 — Preservação histórica:** desativar User ou setor não deve apagar registros históricos relacionados.
- **RG08 — Controle temporal:** datas de abertura, início, conclusão, cancelamento e demais eventos devem ser registradas de forma consistente.
- **RG09 — Setor do User e setor do Ticket:** `User.sectorId` representa o vínculo organizacional do User; `Ticket.sectorId` representa o setor responsável pelo chamado. São relações distintas.
- **RG10 — Role única:** cada User possui exatamente uma role de autorização.
- **RG11 — Identificadores de referência:** referências entre entidades devem apontar para registros existentes e compatíveis com o contexto da operação.
- **RG12 — Dados históricos:** alterações cadastrais posteriores não devem reescrever o significado dos eventos históricos já registrados.

## 7. Estratégia de Refinamento e Implementação

A documentação e a implementação seguirão uma abordagem incremental:

1. Cada RF será analisado junto das regras de negócio já conhecidas e necessárias para que seu comportamento seja correto.
2. As regras aplicáveis no estágio atual serão implementadas e testadas junto com o RF correspondente.
3. Regras que dependem de funcionalidades ainda não implementadas — por exemplo, identidade autenticada e autorização — podem permanecer explicitamente identificadas como futuras.
4. Quando uma funcionalidade de suporte for implementada, os RFs afetados deverão ser revisados para fechar as regras pendentes.
5. A implementação de um RF não significa necessariamente que todas as regras futuras associadas estejam concluídas. O acompanhamento fica em `docs/requirements/implementation-status.md`.
6. Regras de domínio, como invariantes e transições do ciclo de vida do chamado, devem ser protegidas pelo domínio e não adiadas quando forem necessárias ao comportamento atual.
7. Regras de autenticação e autorização devem ser aplicadas no backend, sem depender de restrições da interface.
8. Regras ainda não decididas ou que dependam de política operacional devem ser refinadas antes de sua implementação, evitando assumir comportamentos sem decisão de domínio.

## 8. Pontos que exigem decisão de domínio

Os itens abaixo foram mantidos explícitos para evitar transformar suposições em regras definitivas:

- Estados permitidos para cancelamento e reabertura, incluindo o estado para o qual um chamado reaberto retorna.
- Se iniciar atendimento exige que o chamado já possua responsável atribuído.
- Quais roles podem atribuir, alterar responsável, cancelar, reabrir e concluir chamados.
- Se um chamado pode ser atribuído a qualquer User técnico ou se deve existir compatibilidade entre o setor do responsável e o setor do chamado.
- Política de unicidade do nome do setor (global ou considerando apenas setores ativos).
- Critério de cálculo do tempo de solução quando há reabertura e múltiplos ciclos de atendimento.
- Quais atributos de um chamado podem ser editados depois de sua criação e em cada estado.

Essas decisões devem ser registradas quando forem definidas e refletidas nas regras correspondentes e nos testes.

---

#### *v2.7.0*
