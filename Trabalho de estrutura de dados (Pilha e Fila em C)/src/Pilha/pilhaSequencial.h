#define max_tam_stack 7

int SPilha[max_tam_stack];
int STopo;
/*
 * Empilha(insere um valor na pilha)
 * */
int Spush();
/*
 * Desempilha(remove um valor da pilha)
 */
int Spop();
/*
 * Exibe o valor no topo da pilha
 */
int Stop();
/*
 * Exibe todos os valores na pilha
 * Retorna -999 se a lista estiver vazia
 */
void SprintStack();
/*
 * Exibe o tamanho da pilha
 */
int SgetStackSize();
/*
 * Cria a pilha
 */
void ScreateStack();
/*
 * Retorna 1 se a pilha está cheia
 * Retorna 0 se a pilha não está cheia
 */
int isStackFull();
/*
 * Destroi a pilha
 */
int SdestroyStack();
/*
 * Retorna 1 se a pilha está vazia
 * Retorna 0 se a pilha não está vazia
 */
int SisStackEmpty();
