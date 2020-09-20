`Disciplina: Programação para Dispositivos Móveis`<br/>
`Professora: Valéria Cavalcanti`<br/>
`Período: 2020.1`

**NOME**

Arroche o Número

**DESCRIÇÃO**

A partir do intervalo de valores entre 1 e 100, inclusive, o jogador deverá arrochar um número gerado
aleatoriamente.

**DINÂMICA DO APLICATIVO**

O jogo deverá gerar um número aleatório entre 1 (um) e 100 (cem). Esse número gerado NÃO pode ser
igual aos limites do intervalo (no caso, 1 ou 100). O jogador tentará arrochar esse valor, informando
seu chute no aplicativo.
O jogador perde nos seguintes casos:
* Seu chute seja exatamente igual ao valor que foi gerado aleatoriamente;
* Seu chute seja exatamente igual aos valores do limite do intervalo.
Caso contrário, poderão acontecer as seguintes situações:

| Situação  |  O que deve fazer  | Exemplo |
|---|---|---|
|O chute é menor do que o valor gerado | O novo intervalo inferior será o chute do jogador| Intervalo: 1 e 100 <br/>Valor gerado: 40<br/>Chute do jogador: 20<br/> Novo intervalo: 20 e 100  |
|O chute é maior do que o valor gerado | O novo intervalo superior será o chute do jogador| Intervalo: 1 e 100<br/> Valor gerado: 40<br/> Chute do jogador: 60<br/> Novo intervalo: 10 e 60  |

Após realizar alteração no intervalo, caso esse novo intervalo possua um único valor (ou seja, o número aleatório foi arrochado) o jogador ganha! Exemplo:
Intervalo: 20 e 22. Número gerado: 21.

O jogo não tem pontuação, nem níveis.

**REQUISITOS**
* Ao gerar um valor aleatório, o sistema deverá exibir esse número no Log com a TAG
"APP_ARROCHA";
* O jogo deverá ter duas telas. A tela do jogo e a tela do resultado (ganhou ou perdeu);
* Insira informações de autoria no aplicativo;
* Use sua criatividade para criar uma interface bem interessante.
