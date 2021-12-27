#include <stdlib.h>
#include <stdio.h>
#include "pilhaEncadeada.h"

void EcreateStack(){
	topo = NULL;
	EStackSize = 0;
}

int Epush(int valor){
	EPilha *pilha, *temp;
	pilha = malloc(sizeof(pilha));
	pilha->valor = valor;
	pilha->prox = NULL;

	if(EStackSize != 0){
		temp = topo;
		topo = pilha;
		topo->prox = temp;
	}
	else
		topo = pilha;

	EStackSize++;
	return 1;
}

int Etop(){
	if(EisStackEmpty())
		return -999;
	return topo->valor;
}

int EgetStackSize(){
	return EStackSize;
}

void EprintStack(){
	if(EStackSize > 0){
		EPilha *temp;
		temp = topo;
		int i;
		while(temp != NULL){
			printf("\n%d", temp->valor);
			temp = temp->prox;
		}
	}
}
int Epop(){
	if(EStackSize > 0){
		EPilha *temp;
		temp = topo->prox;
		printf("\nElemento removido: %d", topo->valor);
		free(topo);
		topo = temp;
		EStackSize--;
		return 1;
	}
	return 0;
}

int EdestroyStack(){
	if(!EisStackEmpty()){
		EPilha *temp;
		temp = topo;
		while(temp->prox != NULL){
			free(topo);
			temp = temp->prox;
			topo = temp;
		}
		free(topo);
		topo = NULL;
		temp = NULL;
		EStackSize = 0;
		return 1;
	}
	return 0;

}

int EisStackEmpty(){
	if(EgetStackSize() == 0)
		return 1;
	return 0;
}
