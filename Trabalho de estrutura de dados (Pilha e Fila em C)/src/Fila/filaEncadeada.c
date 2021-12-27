#include "filaEncadeada.h"

#include <stdio.h>
#include <stdlib.h>

int EcreateQueue(){
	EQueueSize = 0;
	Estart = NULL;
	Eend = NULL;
	return 1;
}

int Eenqueue(int value){
	if(EisQueueEmpty()){
		EFila *fila;
		fila = malloc(sizeof(fila));
		fila->prox = NULL;
		fila->valor = value;
		Estart = fila;
		Eend = fila;
		EQueueSize++;
		return 1;
	}else{
		EFila *fila;
		//temp = Eend;
		fila = malloc(sizeof(fila));
		fila->valor = value;
		fila->prox = NULL;
		Eend->prox = fila;
		Eend = fila;
		EQueueSize++;
		return 1;

	}
}

int Edequeue(){
	if(EisQueueEmpty()){
		return 0;
	}
	EFila *temp;
	temp = Estart->prox;
	printf("\nValor removido: %d\n", Epeek());
	free(Estart);
	Estart = temp;
	EQueueSize--;
	return 1;
}

int Epeek(){
	if(EisQueueEmpty())
		return -999;
	return Estart->valor;
}

void EprintQueue(){
	EFila *temp;
	temp = Estart;
	while(temp != NULL){
		printf("%d-", temp->valor);
		temp = temp->prox;
	}
	printf("\n");
}
int EgetQueueSize(){
	return EQueueSize;
}

int EisQueueEmpty(){
	return (EQueueSize == 0) ? 1 : 0;
}

int EdestroyQueue(){
	if(EisQueueEmpty()){
		return 0;
	}
	EFila *temp;
	temp = Estart;
	while(temp->prox != NULL){
		free(Estart);
		temp = temp->prox;
		Estart = temp;
	}
	Estart = Eend = temp = NULL ;
	EQueueSize = 0;
	return 1;
}
