typedef struct{
	int valor;
	struct EFila *prox;
}EFila;

EFila *Estart;
EFila *Eend;
int EQueueSize;

/*
 * Enfilera (insere um elemento na fila)
 */
int Eenqueue(int value);

/*
 * Desenfilera (remove um elemento da fila)
 */
int Edequeue();

/*
 * Exibe o elemento no inicio da fila
 * Retorna -999 se a lista estiver vazia
 */
int Epeek();

/*
 * Exibe todos elementos da fila
 */
void EprintQueue();

/*
 * Exibe o tamanho da fila
 */
int EgetQueueSize();

/*
 * Cria a fila
 */
int EcreateQueue();

/*
 * Destrói a fila
 */
int EdestroyQueue();

/*
 * retorna 1 se fila vazia
 * retorna 0 se fila não estiver vazia
 */
int EisQueueEmpty();

