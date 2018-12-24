# Implementação dos Escalonadores
	Neste trabalho, você deverá criar um programa de computador que implemente os 3
tipos de algoritmos de escalonamento que estudamos em aula, sendo eles:
* Não Preemptivo
* Preemptivo por Prioridade
* Preemptivo por Prioridade + Tempo

	Buscando facilitar sua implementação, foi disponibilizado um protótipo em java que
entrega tipos básicos relacionados ao tema, (Process e Scheduler), bem como uma
interface gráfica que permite a inserção de processos, onde é possível setar tempo de
processamento, prioridade e valor do quantum.
	Sua implementação precisa exibir, a cada tic, o PID do processo em processamento,
bem como o tempo restante para que este termine. Quando seu escalonador estiver
ocioso (idle), exiba o tempo médio de espera do todos os processos que já executaram
desde que você iniciou seu escalonador.

Critérios de Avaliação:
* Implementação dos 3 métodos de escalonamento;
* Exibição do PID e do tempo restante de processamento para o processo em
execução;
* Exibição do tempo médio de espera, quando idle.
* Domínio sobre o código