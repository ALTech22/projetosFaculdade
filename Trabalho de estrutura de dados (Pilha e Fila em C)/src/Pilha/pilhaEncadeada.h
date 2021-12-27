typedef struct{
	int valor;
	struct EPilha *prox;
}EPilha;

EPilha *topo;
int EStackSize;


/*
 * Empilha(insere um valor na pilha)
 * */
int Epush(int valor);

/*
 * Desempilha(remove um valor da pilha)
 */
int Epop();

/*
 * Exibe o valor no topo da pilha
 * Retorna -999 se a lista estiver vazia
 */
int Etop();

/*
 * Exibe todos os valores na pilha
 */
void EprintStack();

/*
 * Exibe o tamanho da pilha
 */
int EgetStackSize();

/*
 * Cria a pilha
 */
void EcreateStack();

/*
 * Destroi a pilha
 */
int EdestroyStack();

/*
 * Retorna 1 se a pilha está vazia
 * Retorna 0 se a pilha não está vazia
 */
int EisStackEmpty();
