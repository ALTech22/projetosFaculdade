#define max_tam_queue 7

int Squeue[max_tam_queue];
int Sstart;
int Send;

/*
 * Enfilera (insere um elemento na fila)
 */
int Senqueue(int value);

/*
 * Desenfilera (remove um elemento da fila)
 */
int Sdequeue();

/*
 * Exibe o elemento no inicio da fila
 * Retorna -999 se a lista estiver vazia
 */
int Speek();

/*
 * Exibe todos elementos da fila
 */
void SprintQueue();

/*
 * Exibe o tamanho da fila
 */
int SgetQueueSize();

/*
 * Cria a fila
 */
int ScreateQueue();

/*
 * Destrói a fila
 */
int SdestroyQueue();

/*
 * retorna 1 se fila vazia
 * retorna 0 se fila não estiver vazia
 */
int SisQueueEmpty();

/*
 * retorna 1 se fila cheia
 * retorna 0 se fila não estiver cheia
 */
int SisQueueFull();
